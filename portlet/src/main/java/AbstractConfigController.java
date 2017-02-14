package ${package}.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

public class AbstractConfigController implements ConfigurationAction {
	private static final String JSP_PATH = "/html/config.jsp";
	protected static final String PREF_JSON = "jsonConfig";
	private static Log LOG = LogFactoryUtil
			.getLog(AbstractConfigController.class);


	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String action = ParamUtil.getString(actionRequest, "action", "");

		if("save".equals(action)) {
			PortletPreferences preferences = actionRequest.getPreferences();
			String portletResource = ParamUtil.getString(actionRequest, "portletResource");
			if (Validator.isNotNull(portletResource)) {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
			}

			preferences.setValue(PREF_JSON, ParamUtil.getString(actionRequest, PREF_JSON));
			preferences.store();
			SessionMessages.add(actionRequest, "configuration-saved");
		}
	}



	@Override
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		String portletResource = ParamUtil.getString(renderRequest, "portletResource");
		try {
			PortletPreferences preferences = renderRequest.getPreferences();
			if (Validator.isNotNull(portletResource)) {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);
				String jsonConfig = preferences.getValue(PREF_JSON, "{}");
				if(jsonConfig.isEmpty()) {
					jsonConfig = "{}";
				}
				jsonConfig = jsonConfig.replaceAll("'", "\\\\'");
				renderRequest.setAttribute(PREF_JSON, jsonConfig);
			}
		} catch (PortalException | SystemException e) {
			LOG.error(e);
		}

		return JSP_PATH;
	}

	protected static String getJSONPreference(PortletRequest request, String preference) {
		PortletPreferences preferences = getPortletPreferences(request);
		String jsonConfig = preferences.getValue(preference, "{}");
		return jsonConfig;
	}

	public static PortletPreferences getPortletPreferences(PortletRequest request) {
		PortletPreferences preferences = request.getPreferences();
		HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
		String portletResource = ParamUtil.getString(servletRequest, "portletResource");
		if (Validator.isNotNull(portletResource)) {
			try {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(servletRequest, portletResource);
			} catch (PortalException | SystemException e) {
			}
		}
		return preferences;
	}
}

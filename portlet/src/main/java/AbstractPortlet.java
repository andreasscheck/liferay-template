package ${package}.portlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class AbstractPortlet extends MVCPortlet {
	private Logger LOG = Logger.getLogger(AbstractPortlet.class.getName());

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		super.doView(request, response);
	}

	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
		HttpServletResponse res = PortalUtil.getHttpServletResponse(response);
		String action = ParamUtil.getString(PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request)), "action");

    super.serveResource(request, response);
	}

	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws IOException, PortletException {
		HttpServletResponse res = PortalUtil.getHttpServletResponse(response);
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);

	  super.processAction(request, response);
	}
}

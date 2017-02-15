<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletContext"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>

<portlet:defineObjects />

<liferay-portlet:resourceURL var="ajaxURL" />

<portlet:actionURL var="actionURL"  windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"></portlet:actionURL>
<%
       LiferayPortletContext pContext = (LiferayPortletContext) portletConfig.getPortletContext();
       long timestamp = pContext.getPortlet().getTimestamp();
       pageContext.setAttribute("timestamp", timestamp);
%>
<div id="<portlet:namespace/>">
  <app-content></app-content>
</div>

<liferay-util:body-bottom>
	<script type="text/javascript">
		var <portlet:namespace/> = <portlet:namespace/> || {};

		<portlet:namespace/>.init = function () {
      var <portlet:namespace/> = new Vue({
          el: '#<portlet:namespace/>'
      });
			<portlet:namespace/>.$root.$children[0].namespace = '<portlet:namespace/>';
			<portlet:namespace/>.$root.$children[0].actionURL = '${actionURL}';
			<portlet:namespace/>.$root.$children[0].ajaxURL = '${ajaxURL}';
		}
		jQuery(<portlet:namespace/>.init);
	</script>
	<script src="<%= request.getContextPath() %>/js/vue-app/vendor.bundle.js?${timestamp}"></script>
	<script src="<%= request.getContextPath() %>/js/vue-app/main.bundle.js?${timestamp}"></script>
</liferay-util:body-bottom>

package com.liferay.commerce.demo.informational.product.renderer.list;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.render.list.CPContentListRenderer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jeff Handa
 */
@Component(
        immediate = true,
        property = {
                "commerce.product.content.list.renderer.key=" + InformationalCPContentListRenderer.KEY,
                "commerce.product.content.list.renderer.order=450",
                "commerce.product.content.list.renderer.portlet.name=" + CPPortletKeys.CP_PUBLISHER_WEB,
                "commerce.product.content.list.renderer.portlet.name=" + CPPortletKeys.CP_SEARCH_RESULTS
        },
        service = CPContentListRenderer.class
)
public class InformationalCPContentListRenderer implements CPContentListRenderer {

    public static final String KEY = "informational-list-renderer";

    @Override
    public String getKey() {
        _log.debug("Getting Informational List Renderer Key");
        return KEY;
    }

    @Override
    public String getLabel(Locale locale) {
        _log.debug("Getting Informational List Renderer Label");
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "informational");    }

    @Override
    public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        _log.debug("Rendering Informational List Renderer");
        httpServletRequest.setAttribute("cpInstanceHelper", _cpInstanceHelper);

        _jspRenderer.renderJSP(
                _servletContext, httpServletRequest, httpServletResponse,
                "/list_render/view.jsp");
    }

    private static final Log _log = LogFactoryUtil.getLog(
            InformationalCPContentListRenderer.class);

    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.commerce.demo.informational.product.renderer)"
    )
    private ServletContext _servletContext;

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference
    private CPInstanceHelper _cpInstanceHelper;

}


package br.uema.skeleton.config;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.lang.Nullable;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInitialize extends
        AbstractAnnotationConfigDispatcherServletInitializer  implements WebApplicationInitializer {

    @Nullable
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfiguration.class,AppWebConfiguration.class, JPAConfiguration.class};
    }

    @Nullable
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{};
    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppWebConfiguration.class);

        ctx.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }*/

    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement(""));
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
        servletContext.setInitParameter(
                AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");

    }
}

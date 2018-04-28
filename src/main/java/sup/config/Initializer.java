package sup.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/","/api/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                new DelegatingFilterProxy("springSecurityFilterChain")
        };
    }
}

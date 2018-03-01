package sup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private ResourceServerTokenServices tokenServices;

    @Autowired
    public void setTokenServices(ResourceServerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    private String resourceIds = "supjwtresourseid";

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer){
        configurer
                .resourceId(resourceIds)
                .tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity security) throws Exception{
        security
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated();
    }
}

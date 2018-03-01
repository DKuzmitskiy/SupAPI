package sup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collections;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private String clientId = "supjwtclientid";
    private String clientSecret = "DUZkjsd745LL";
    private String grantType = "password";
    private String scopeRead = "read";
    private String scopeWrite = "write";
    private String resourceId = "supjwtresourseid";


    private TokenStore tokenStore;
    private JwtAccessTokenConverter tokenConverter;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }
    @Autowired
    public void setTokenConverter(JwtAccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception{
        configurer.inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(grantType)
                .scopes(scopeRead, scopeWrite)
                .resourceIds(resourceId);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Collections.singletonList(tokenConverter));

        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(tokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
    }
}

package msa.gateway.msagateway.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    private final TokenStore tokenStore;
    private final DataSource dataSource;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userDetailsService;

    public AuthorizationServerConfig(TokenStore tokenStore, DataSource dataSource, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserServiceImpl userDetailsService) {
        this.tokenStore = tokenStore;
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .jdbc(dataSource)
                .withClient("msa-id")
                .secret(passwordEncoder.encode("msa-password"))
                .resourceIds("resource_id")
                .authorizedGrantTypes("password",
                        "authorization_code",
                        "refresh_token",
                        "implicit")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1*60*60)
                .refreshTokenValiditySeconds(6*60*60)
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .redirectUris("http://localhost:8000/home")
                .autoApprove(false);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

}

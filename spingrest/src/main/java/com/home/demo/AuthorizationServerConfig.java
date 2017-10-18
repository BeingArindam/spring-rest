package com.home.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String SERVER_RESOURCE_ID = "oauth2-server";

	@Autowired
	TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("clientId").secret("secret")
		.authorizedGrantTypes("client_credentials", "password", "authorization_code")
		.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").accessTokenValiditySeconds(5000)
		.scopes("read", "write", "trust").resourceIds(SERVER_RESOURCE_ID).accessTokenValiditySeconds(5000);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager).tokenStore(this.tokenStore);
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
		@Autowired
		TokenStore tokenStore;

		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.tokenStore(this.tokenStore).resourceId(SERVER_RESOURCE_ID);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/accounts/**").authenticated();
		}

	}
}

package yuzhou.gits.springCloudTest.config.oauth2RescServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@ConditionalOnExpression("${_CONFIG.OAUTH2RESCSERVER:false}")
@Configuration
//@EnableEurekaClient
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	//private static final String DEMO_RESOURCE_ID = "rescId";
	@Value("${_oauth2Auth.checkTokenEndPointUrl}")
	private String checkTokenEndPointUrl;
	@Value("${_oauth2Auth.rescId}")
	private String rescId;
	@Value("${_oauth2Auth.clientId}")
	private String clientId;
	@Value("${_oauth2Auth.clientSecret}")
	private String clientSecret;
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(rescId).stateless(true);
		//resources.tokenStore(tokenStore());
		resources.tokenServices(resourceServerTokenServices());
	}
	
	public TokenStore tokenStore() {
		InMemoryTokenStore tokenStore = new InMemoryTokenStore();
		return tokenStore;
	}
	
	public ResourceServerTokenServices resourceServerTokenServices() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl(checkTokenEndPointUrl);
		tokenService.setClientId(clientId);
		tokenService.setClientSecret(clientSecret);
		tokenService.setAccessTokenConverter(accessTokenConverter());
		return tokenService;
	}

	public DefaultAccessTokenConverter accessTokenConverter() {
		DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();
		return converter;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").authenticated();
	}
}

package yuzhou.gits.springCloudTest.config.oauth2Client;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Configuration
@EnableOAuth2Client
//@EnableDiscoveryClient
public class OAuth2ClientConfig {
	@Autowired
	private OAuth2ClientContext oauth2Context;

	@Bean
	public OAuth2RestTemplate sparklrRestTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(sparklr(), 
				oauth2Context);
        AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(
          Arrays.<AccessTokenProvider> asList(
            new ImplicitAccessTokenProvider(),
            new AuthorizationCodeAccessTokenProvider(),
            new ResourceOwnerPasswordAccessTokenProvider(),
            new ClientCredentialsAccessTokenProvider())
        );
        template.setAccessTokenProvider(accessTokenProvider);
        return template;
	}

	@Autowired
	MyRescDetailsConfig myRescDetailsConfig;
	
	private OAuth2ProtectedResourceDetails sparklr() {
		ClientCredentialsResourceDetails rescDetails = 
				new ClientCredentialsResourceDetails();
		rescDetails.setAccessTokenUri(myRescDetailsConfig.accessTokenUri);
		//rescDetails.setClientAuthenticationScheme(myRescDetailsConfig.clientAuthenticationScheme);
		rescDetails.setClientId(myRescDetailsConfig.clientId);
		rescDetails.setClientSecret(myRescDetailsConfig.clientSecret);
		rescDetails.setGrantType(myRescDetailsConfig.grantType);
		rescDetails.setId(myRescDetailsConfig.id);
		//rescDetails.setTokenName("token");
		rescDetails.setGrantType("client_credentials");
		rescDetails.setScope(myRescDetailsConfig.scope);
		
		return rescDetails;
	}
}
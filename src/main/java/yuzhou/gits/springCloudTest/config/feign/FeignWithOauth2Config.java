package yuzhou.gits.springCloudTest.config.feign;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import feign.RequestInterceptor;
import yuzhou.gits.springCloudTest.config.oauth2Client.OAuth2FeignRequestInterceptor;

@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Configuration
public class FeignWithOauth2Config {
	
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor(
			@Qualifier("sparklrRestTemplate") OAuth2RestTemplate oAuth2RestTemplate) {
		return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
	}
}

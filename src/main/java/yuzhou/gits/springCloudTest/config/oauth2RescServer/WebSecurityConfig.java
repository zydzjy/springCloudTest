package yuzhou.gits.springCloudTest.config.oauth2RescServer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ConditionalOnExpression("${_CONFIG.OAUTH2RESCSERVER:false}")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	public void configure(WebSecurity web) throws Exception {
//		SecurityBuilder secFilterChainBld = new SecurityBuilder();
//				
//		web.addSecurityFilterChainBuilder(securityFilterChainBuilder)
//	}
	
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/notOauth2Auth/**")
			.authenticated();
	}
}

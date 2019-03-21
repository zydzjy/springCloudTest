package yuzhou.gits.springCloudTest.config.webSecurity;

import java.util.Collections;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import yuzhou.gits.springCloudTest.service.springSecurity.CustomUserAuthenticationProvider;
import yuzhou.gits.springCloudTest.service.springSecurity.CustomUsernamePasswordAuthenticationFilter;


@ConditionalOnExpression("${_CONFIG.WEBSECURITY:false}")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserAuthenticationProvider customUserAuthenticationProvider;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		customUserAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		auth.authenticationProvider(customUserAuthenticationProvider);
	}

	@Value("${_security.loginPage}")
	private String loginPage;
	@Value("${_security.loginProcessingUrl}")
	private String loginProcessingUrl;
	@Value("${_security.failureUrl}")
	private String failureUrl;
	@Value("${_security.usernameParam}")
	private String usernameParam;
	@Value("${_security.passwordParam}")
	private String passwordParam;
	@Value("${_security.successForwardUrl}")
	private String successForwardUrl;
	@Value("${_security.defaultSuccessUrl}")
	private String defaultSuccessUrl;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/testNoSecurity/**");
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests() .anyRequest().permitAll();
		 */
		http.authorizeRequests().antMatchers("/login/form").permitAll().antMatchers("/securityPages/**")
				.access("hasRole('ROLE1')").and().formLogin().defaultSuccessUrl(this.defaultSuccessUrl)// 如果用户登陆之前，没有访问受保护的页面，默认跳转到页面
				// 登陆
				.loginPage(this.loginPage).loginProcessingUrl(this.loginProcessingUrl)
				.usernameParameter(this.usernameParam).passwordParameter(this.passwordParam)
				// .successForwardUrl(this.successForwardUrl)//登陆成功
				.failureForwardUrl(this.failureUrl)// 登陆失败
				.and()
				// for oauth2 client
				//.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
				// 登出
				.logout().and().csrf().disable();
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(super.authenticationManagerBean());
		http.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class);
	}
}

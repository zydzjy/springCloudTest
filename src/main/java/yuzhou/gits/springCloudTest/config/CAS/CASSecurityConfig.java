package yuzhou.gits.springCloudTest.config.CAS;

import java.util.Arrays;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import yuzhou.gits.springCloudTest.service.springSecurity.CustomUserDetailsService;


@ConditionalOnExpression("${_CONFIG.CASCLIENT:false}")
@EnableWebSecurity
@Configuration
public class CASSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${_security.cas.loginCallback}")
	private String casLoginCallback;
	@Value("${_security.cas.serviceURL}")
	private String casServiceURL;
	@Value("${_security.cas.ticketValidatorServer}")
	private String casTicketValidatorServer;
	@Value("${_security.cas.loginServerURL}")
	private String casLoginServerURL;
	@Value("${_security.cas.loginFilterURL}")
	private String casLoginFilterURL;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(this.casServiceURL);
		serviceProperties.setAuthenticateAllArtifacts(true);
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	// cas 认证 Provider
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService()); // 这里只是接口类型，实现的接口不一样，都可以的。
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas30ServiceTicketValidator());
		casAuthenticationProvider.setKey("casAuthenticationProviderKey");
		return casAuthenticationProvider;
	}

	public Cas30ServiceTicketValidator cas30ServiceTicketValidator() {
		return new Cas30ServiceTicketValidator(this.casTicketValidatorServer);
	}

	// 用户自定义的AuthenticationUserDetailsService
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Override
    protected AuthenticationManager authenticationManager() throws Exception {
      return new ProviderManager(Arrays.asList(casAuthenticationProvider()));
    }

	// CAS认证过滤器
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setFilterProcessesUrl(this.casLoginFilterURL);
		// casAuthenticationFilter.setServiceProperties(serviceProperties());
		return casAuthenticationFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().authorizeRequests()
		 // 配置安全策略 
		.antMatchers(this.casLoginFilterURL).permitAll()
		.antMatchers("/securityPages/**").authenticated()
		.and()
		.authorizeRequests().anyRequest().authenticated();
		  
		 http.exceptionHandling()
		 .authenticationEntryPoint(casAuthenticationEntryPoint())
		 .and() 
		 .addFilter(casAuthenticationFilter()); 
		 http.csrf().disable(); 
		 //关闭spring security默认的frame访问限制 
		 http.headers().frameOptions().sameOrigin();
	}

	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(this.casLoginServerURL);
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
}

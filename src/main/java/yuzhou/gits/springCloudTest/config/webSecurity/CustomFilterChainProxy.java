package yuzhou.gits.springCloudTest.config.webSecurity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
public class CustomFilterChainProxy{

//	@Bean("springSecurityFilterChain")
//	public FilterChainProxy filterChainProxy() {
//		List<SecurityFilterChain> filterChains = new ArrayList<>();
//		SecurityFilterChain chain1 = 
//				new DefaultSecurityFilterChain(
//						new AndRequestMatcher(new AntPathRequestMatcher("*.js"),
//								new AntPathRequestMatcher("*.css"),
//								new AntPathRequestMatcher("/testNoSecurity/**")));
//		filterChains.add(chain1);
//		FilterChainProxy filterChainProxy = new FilterChainProxy(filterChains);
//		return filterChainProxy;
//	}
}

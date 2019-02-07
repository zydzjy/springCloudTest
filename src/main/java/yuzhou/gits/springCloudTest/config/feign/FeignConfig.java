package yuzhou.gits.springCloudTest.config.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import feign.Contract;
import feign.Feign;


//@ConditionalOnClass({ Feign.class })
//@Configuration
public class FeignConfig {
	 //使用Feign自己的注解，使用springmvc的注解就会报错
	/*
	 * @Bean public Contract feignContract() { return new feign.Contract.Default();
	 * }
	 */
}

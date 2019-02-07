package yuzhou.gits.springCloudTest.controller.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import yuzhou.gits.springCloudTest.config.feign.FeignConfig;
import yuzhou.gits.springCloudTest.config.feign.FeignWithOauth2Config;
import yuzhou.gits.springCloudTest.controller.HystrixClientFallbackFactory;

@FeignClient(
configuration=FeignConfig.class,
name="OAUTH2RESCSERVER",
//primary = true, 
fallbackFactory = HystrixClientFallbackFactory.class)
public interface TestClient {
	@RequestMapping(value="/oauth2RescServer/v1.0/somePublic/{name}")
    public Map<String,String> publicEndPoint(@PathVariable("name") String name);
}

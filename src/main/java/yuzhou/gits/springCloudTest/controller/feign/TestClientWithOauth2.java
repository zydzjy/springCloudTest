package yuzhou.gits.springCloudTest.controller.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import yuzhou.gits.springCloudTest.config.feign.FeignWithOauth2Config;
import yuzhou.gits.springCloudTest.controller.HystrixClientFallbackFactory;

@FeignClient(
configuration = FeignWithOauth2Config.class,
name="OAUTH2RESCSERVER",
//primary = true, 
fallbackFactory = HystrixClientFallbackFactory.class)
public interface TestClientWithOauth2 {

	@RequestMapping(value="/oauth2RescServer/v1.0/somePrivacy/{name}")
    public Map<String,String> privacyEndPoint(@PathVariable("name") String name);
}

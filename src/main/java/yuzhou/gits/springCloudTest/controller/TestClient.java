package yuzhou.gits.springCloudTest.controller;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "springCloudService1", 
//primary = true, 
fallbackFactory = HystrixClientFallbackFactory.class)
public interface TestClient {
	@RequestMapping(method = RequestMethod.POST, 
			value = "${remoteServices.springCloudService1.TestRest.url}")
    public Map<String,String> remoteTestRest(
    		@PathVariable("name") String name);
}

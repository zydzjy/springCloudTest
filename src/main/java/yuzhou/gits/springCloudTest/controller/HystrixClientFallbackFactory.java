package yuzhou.gits.springCloudTest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;


@Component
public class HystrixClientFallbackFactory implements FallbackFactory<TestClient> {

	@Override
	public TestClient create(Throwable cause) {
		return new TestClient() {
			@Override
			public Map<String,String> remoteTestRest(String name){
				Map<String,String> response = new HashMap<String,String>();
				
				response.put("result", "TIMEOUT");
				response.put("MESSSG", "Hello "+name+"!");
				
				return response;
			}
		};
	}
}

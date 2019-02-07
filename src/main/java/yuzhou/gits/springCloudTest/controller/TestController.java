package yuzhou.gits.springCloudTest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yuzhou.gits.springCloudTest.controller.feign.TestClient;
import yuzhou.gits.springCloudTest.controller.feign.TestClientWithOauth2;
import yuzhou.gits.springCloudTest.service.UserService;

@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Controller
public class TestController {
	@Autowired
	private TestClient testClient;
	@Autowired
	private TestClientWithOauth2 testClientWithOauth2;
	
	@ResponseBody
	@RequestMapping(value = "testPublicRemoteWithFeign/{name}")
	public Map<String, String> testPublicRemoteWithFeign(@PathVariable String name) {
		Map<String, String> response = this.testClient.publicEndPoint(name);
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "testPrivacyRemoteWithFeign/{name}")
	public Map<String, String> testPrivacyRemoteWithFeign(@PathVariable String name) {
		Map<String, String> response = this.testClientWithOauth2.privacyEndPoint(name);
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "testRemoteWithJWT")
	public Map<String, String> testRemoteWithJWT() {
		return null;
	}
}

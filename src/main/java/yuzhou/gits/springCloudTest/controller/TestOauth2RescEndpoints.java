package yuzhou.gits.springCloudTest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ConditionalOnExpression("${_CONFIG.OAUTH2RESCSERVER:false}")
@Controller
public class TestOauth2RescEndpoints {

	@ResponseBody
	@RequestMapping(value="",produces="application/json")
	public Map<String,String> testRoot(){
		Map<String,String> response = new HashMap<String,String>();
		
		response.put("result", "OK");
		response.put("MESSSG", "Hello root,u invoked a public endpoint!");

		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="somePublic/{name}",produces="application/json")
	public Map<String,String> somePublic(
			@PathVariable String name){
		Map<String,String> response = new HashMap<String,String>();
		
		response.put("result", "OK");
		response.put("MESSSG", "Hello "+name+",u invoked a public endpoint!");

		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="somePrivacy/{name}",produces="application/json")
	public Map<String,String> somePrivacy(
			@PathVariable String name){
		Map<String,String> response = new HashMap<String,String>();
		
		response.put("result", "OK");
		response.put("MESSSG", "Hello "+name+",u invoked a privacy endpoint!");

		return response;
	}
}

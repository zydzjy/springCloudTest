package yuzhou.gits.springCloudTest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@ResponseBody
	@RequestMapping(value="testRest/{name}")
	public Map<String,String> testHello(
			@PathVariable String name){
		Map<String,String> response = new HashMap<String,String>();
		
		response.put("result", "OK");
		response.put("MESSSG", "Hello "+name+"!");
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="testRest2/{name}")
	public Map<String,String> testWelcom(
			@PathVariable String name){
		Map<String,String> response = new HashMap<String,String>();
		
		response.put("result", "OK");
		response.put("MESSSG", "Welcom "+name+"!");
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="testRemoteWithJWT")
	public Map<String,String> testRemoteWithJWT(){
		return null;
	}
}

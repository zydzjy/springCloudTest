package yuzhou.gits.springCloudTest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Controller
public class TestOAuth2ClientController {
	
	@Autowired
	OAuth2RestTemplate sparklrRestTemplate;
	@Value("${_oauth2Client.rescTestUrl}")
	private String oauth2RescTestUrl;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET,value="testOauth2Client")
	public Map<?,?> testRemoteWithOauth(){
		HashMap<?, ?> resp = this.sparklrRestTemplate.getForObject(oauth2RescTestUrl, 
				HashMap.class);
		return resp;
	}
}

package yuzhou.gits.springCloudTest.config.oauth2Client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@ConditionalOnExpression("${_CONFIG.OAUTH2CLIENT:false}")
@Configuration
public class MyRescDetailsConfig {
	@Value("${_oauth2Client.myResc.id}")
	public String id;
	@Value("${_oauth2Client.myResc.clientId}")
	public String clientId;
	@Value("${_oauth2Client.myResc.clientSecret}")
	public String clientSecret;
	@Value("${_oauth2Client.myResc.accessTokenUri}")
	public String accessTokenUri;
	@Value("#{'${_oauth2Client.myResc.scope}'.split(',')}")
	public List<String> scope;
	@Value("${_oauth2Client.myResc.clientAuthenticationScheme}")
	public AuthenticationScheme clientAuthenticationScheme;
	@Value("${_oauth2Client.myResc.userAuthorizationUri}")
	public String userAuthorizationUri;
	@Value("${_oauth2Client.myResc.grantType}")
	public String grantType;
}

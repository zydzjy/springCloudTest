package yuzhou.gits.springCloudTest.config;

import javax.validation.constraints.NotNull;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Configuration
public class AppConfig {
	
	@NotNull
	private String testCfgFromZK;

	public String getTestCfgFromZK() {
		return testCfgFromZK;
	}

	public void setTestCfgFromZK(String testCfgFromZK) {
		this.testCfgFromZK = testCfgFromZK;
	}
}
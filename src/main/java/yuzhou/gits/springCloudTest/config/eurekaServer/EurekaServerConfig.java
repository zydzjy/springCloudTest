package yuzhou.gits.springCloudTest.config.eurekaServer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@ConditionalOnExpression("${_CONFIG.EUREKASERVER:false}")
@Configuration
@EnableEurekaServer
public class EurekaServerConfig {

}

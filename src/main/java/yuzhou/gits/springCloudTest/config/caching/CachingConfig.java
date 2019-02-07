package yuzhou.gits.springCloudTest.config.caching;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
//@EnableRedisRepositories
public class CachingConfig {
	
	@Bean
	@Primary
	public ReactiveRedisConnectionFactory lettuceConnectionFactory() {

	  LettuceClientConfiguration clientConfig = 
			  LettuceClientConfiguration.builder()
	    //.useSsl().and()
	    .commandTimeout(Duration.ofSeconds(6))
	    .shutdownTimeout(Duration.ZERO)
	    .build();

	  return new LettuceConnectionFactory(
			  new RedisStandaloneConfiguration("localhost", 6379), 
			  clientConfig);
	}
	
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory lettuceConnectionFactory) {
		RedisCacheManager cm = RedisCacheManager.builder(lettuceConnectionFactory)
				.cacheDefaults(defaultCacheConfig())
				.withInitialCacheConfigurations(Collections.singletonMap("predefined", defaultCacheConfig().disableCachingNullValues()))
				.transactionAware()
				.build();

        return cm;
    }

	private RedisCacheConfiguration defaultCacheConfig() {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
			    .entryTtl(Duration.ofSeconds(100))
				.disableCachingNullValues();
		return config;
	}
	
	/*
	 * @Bean ReactiveRedisTemplate<String, String>
	 * reactiveRedisTemplate(ReactiveRedisConnectionFactory
	 * lettuceConnectionFactory) { return new
	 * ReactiveRedisTemplate<>(lettuceConnectionFactory,
	 * RedisSerializationContext.string()); }
	 */
}

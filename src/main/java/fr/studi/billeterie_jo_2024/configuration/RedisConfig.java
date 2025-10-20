package fr.studi.billeterie_jo_2024.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
@Profile("!test & !local") // Uniquement pour la config "prod"
public class RedisConfig {

	@Value("${spring.data.redis.host:localhost}")
	private String redisHost;

	@Value("${spring.data.redis.port:6379}")
	private int redisPort;

	@Value("${spring.data.redis.password:#{null}}")
	private String redisPassword;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(redisHost);
		configuration.setPort(redisPort);

		if (redisPassword != null && !redisPassword.isEmpty()) {
			configuration.setPassword(redisPassword);
		}
		return new LettuceConnectionFactory(configuration);
	}

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("JSESSIONID");
		serializer.setCookiePath("/");
		serializer.setSameSite("Lax");
		serializer.setUseSecureCookie(true);
		serializer.setUseHttpOnlyCookie(true);
		return serializer;
	}

}

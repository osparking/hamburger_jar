package space.bumtiger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "credential")
@Data
public class CredentialProperties {
	private String ccNumber;
	private String ccExpiration;
}

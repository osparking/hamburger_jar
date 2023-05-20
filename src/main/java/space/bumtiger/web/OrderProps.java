package space.bumtiger.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "burger.orders")
@Data
public class OrderProps {
	private int pageSize = 3;
}

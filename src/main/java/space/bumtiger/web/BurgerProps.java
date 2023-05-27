package space.bumtiger.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "burger.burgers")
@Data
@Validated
public class BurgerProps {
	
	@Min(value=5, message="5 이상 10 이하 값만 허용됨.")
	@Max(value=10, message="5 이상 10 이하 값만 허용됨.")
	private int pageSize = 5;
}

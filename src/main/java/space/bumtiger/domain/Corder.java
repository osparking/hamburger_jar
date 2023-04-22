package space.bumtiger.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Table
public class Corder {

	@Id
	private Integer id;

	private LocalDateTime placedAt;

	// @formatter:off
	@NotBlank(message = "(고객)성명은 반드시 입력하십시오:")
	private String custName;

	@NotBlank(message = "도로명 주소를 반드시 입력하십시오:")
	private String addrRoad;

	@NotBlank(message = "상세 주소를 반드시 입력하십시오:")
	private String addrDetail;

	@NotBlank(message = "우편번호를 반드시 입력하십시오:")
	private String addrZip;

	@CreditCardNumber(message = "신용카드 번호 형식 오류입니다!")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", 
			message = "카드 만기일은 반드시 입력하십시오.")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, 
			message = "카드 CVV 값을 반드시 입력하십시오:")
	@Pattern(regexp = "^[0-9]{3}$", 
	message = "카드 CVV 값을 3 자리 숫자로 입력하십시오.")
	private String ccCVV;
  // @formatter:on

	private List<CorderBurger> burgers = new ArrayList<>();

	public void addBurger(CorderBurger burger) {
		burgers.add(burger);
	}
}

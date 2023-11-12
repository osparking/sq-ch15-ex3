package sqch15ex3.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account {
	private Long id;
	private String name;
	private BigDecimal amount;
}

package sqch15ex3.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequest {
	private Long fromAccountId;
	private Long toAccountId;
	private BigDecimal transferAcount;
}

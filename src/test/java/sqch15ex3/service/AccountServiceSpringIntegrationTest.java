package sqch15ex3.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sqch15ex3.model.Account;
import sqch15ex3.repository.AccountRepository;

@SpringBootTest
class AccountServiceSpringIntegrationTest {
	
	@MockBean
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;

	@Test
	void accountServiceChangeAmountTest() {
		Account sender = new Account();
		sender.setId(1L);
		sender.setAmount(new BigDecimal(10000));
		
		Account receiver = new Account();
		receiver.setId(2L);
		receiver.setAmount(new BigDecimal(5000));
		
		when(accountRepository.findAccountById(sender.getId()))
			.thenReturn(Optional.of(sender));
		
		when(accountRepository.findAccountById(receiver.getId()))
		 .thenReturn(Optional.of(receiver));
		
		accountService.transferMoney(1L, 2L, new BigDecimal(1000));
		
		verify(accountRepository)
			.updateAccountAmount(new BigDecimal(9000), 1L);
		
		verify(accountRepository)
			.updateAccountAmount(new BigDecimal(6000), 2L);
	}

}

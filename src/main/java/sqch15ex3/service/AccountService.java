package sqch15ex3.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import sqch15ex3.exception.AccountNotFoundException;
import sqch15ex3.model.Account;
import sqch15ex3.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;

	// 계좌 정보 ID로 읽기
	public Optional<Account> findAccountById(Long id) {
		return accountRepository.findAccountById(id);
	}

	// 계좌 목록 읽기
	public List<Account> findAccounts() {
		return accountRepository.findAccounts();
	}

	// 두 계좌간 송금
	@Transactional
	public void transferMoney(Long fromId, Long toId, 
			BigDecimal transferAmount) {
		// 두 계좌 정보 읽기
		Account fromAccount = accountRepository.findAccountById(fromId)
				.orElseThrow(() -> new AccountNotFoundException());
		Account toAccount = accountRepository.findAccountById(toId)
				.orElseThrow(() -> new AccountNotFoundException());
		
		// 두 계좌 새 금액 계산
		var newFromAmount = fromAccount.getAmount().subtract(transferAmount);
		var newtoAmount = toAccount.getAmount().add(transferAmount);
		
		// 두 계좌 금액 갱신
		accountRepository.updateAccountAmount(newFromAmount, fromId);
		accountRepository.updateAccountAmount(newtoAmount, toId);
	}

}

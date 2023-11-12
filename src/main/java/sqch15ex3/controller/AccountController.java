package sqch15ex3.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sqch15ex3.dto.TransferRequest;
import sqch15ex3.model.Account;
import sqch15ex3.service.AccountService;

@RestController
@AllArgsConstructor
public class AccountController {
	private final AccountService accountService;

	// 계좌 목록 채취 종점
	@GetMapping("/accounts")
	public List<Account> findAccounts() {
		return accountService.findAccounts();
	}
	
	// 계좌 이체 종점
	@PostMapping("/transfer")
	public void transferMoney(@RequestBody TransferRequest transferRequest) {
		accountService.transferMoney(
				transferRequest.getFromAccountId(),
				transferRequest.getToAccountId(), 
				transferRequest.getTransferAcount());
	}
}

package sqch15ex3.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import sqch15ex3.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	// 계좌 정보 검색 메소드
	@Query("select * from account where id=:id")
	public Optional<Account> findAccountById(Long id);
	
	// 계좌 목록 읽는 메소드
	@Query("select * from account")
	public List<Account> findAccounts();
	
	// 계좌 금액 갱신 메소드
	@Query("update account set amount=:amount where id=:id")
	@Modifying
	public void updateAccountAmount(BigDecimal amount, Long id);

}

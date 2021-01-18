package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

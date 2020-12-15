package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

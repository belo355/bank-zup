package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, Long> {

}

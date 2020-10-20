package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}

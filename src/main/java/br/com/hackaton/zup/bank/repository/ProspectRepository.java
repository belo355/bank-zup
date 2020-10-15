package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.domain.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectRepository extends JpaRepository<Prospect, Long> {
}

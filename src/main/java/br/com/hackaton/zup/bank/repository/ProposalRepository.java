package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.domain.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}

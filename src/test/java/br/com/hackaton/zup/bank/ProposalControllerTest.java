package br.com.hackaton.zup.bank;

import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Optional;

@DisplayName("Test: ProposalController")
@WebMvcTest
class ProposalControllerTest {

	private Long idProposal;
	@Autowired
	ProposalRepository proposalRepository;
	@Test
	@DisplayName("test findById proposal")
	public void testGetProposal(Long idProposal) throws Exception {
		idProposal = 1L;
		Optional<Proposal> proposal = proposalRepository.findById(idProposal);
	}

}

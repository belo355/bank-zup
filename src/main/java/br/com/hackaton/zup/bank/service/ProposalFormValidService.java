package br.com.hackaton.zup.bank.service;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.domain.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalFormValidService {

    @Autowired
    private ProposalRepository proposalRepository;

    public boolean cpf(AccountProposalForm proposal){
        Proposal cpf = proposalRepository.findbyCpf(proposal.getCpf());
        if(cpf.getCpf().isEmpty()){
            return true;
        }
        return false;
    }
}

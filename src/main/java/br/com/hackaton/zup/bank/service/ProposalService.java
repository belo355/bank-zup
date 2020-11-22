package br.com.hackaton.zup.bank.service;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


/**
 *
 * @author: Edilson Belo
 * @apiNote:
 *
 */

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(ProposalService.class);

    @Transactional
    public boolean handleValidProposal(AccountProposalForm proposal){
        try {
            boolean cpf = validCpf(proposal.getCpf());
            boolean email = validEmail(proposal.getEmail());
            if (cpf == false || email == false){
                return false;
            }else{
                return true;
            }
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
        }
        return false;
    }

    public boolean validCpf(String cpf){
        try{
            String valid = proposalRepository.findByCpf(cpf);
            if(valid == null){
                return true;
            }else{
                return false;
            }
        }catch (EntityNotFoundException | ClassCastException  e){
            logger.info("invalid cpf or exists: " + cpf, e.getMessage());
        }
        return false;
    }

    public boolean validEmail(String email){
        try{
            String valid = proposalRepository.findByEmail(email);
            if(valid == null){
                return true;
            }else{
                return false;
            }
        }catch (EntityNotFoundException | ClassCastException  e){
            logger.info("invalid e-mail or exists: " + email, e.getMessage());
        }
        return false;
    }
}

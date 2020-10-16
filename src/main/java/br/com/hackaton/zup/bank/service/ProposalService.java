package br.com.hackaton.zup.bank.service;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Transactional
    public boolean handle(AccountProposalForm proposal){

        boolean cpf = validCpf(proposal.getCpf());
        boolean email = validEmail(proposal.getEmail());

        if (cpf == false || email == false){
            return false;
        }else{
            return true;
        }
    }

    public boolean validCpf(String cpf){
        try{
            String valid = proposalRepository.findByCpf(cpf);
            if(valid == null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}

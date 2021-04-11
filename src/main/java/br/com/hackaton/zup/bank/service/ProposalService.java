package br.com.hackaton.zup.bank.service;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class ProposalService {

    private static final int AGE_BIRTH = 18;

    @Autowired
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(ProposalService.class);

    public boolean handleValidationFormProposal(AccountProposalForm proposal){
        boolean isValid = false;
        try {
            boolean isEmailValid = validationExistisCPFandEmail(proposal);
            boolean isDateBirthValid = validationDateBirth(proposal.getDateBirth());

            if (isEmailValid && isDateBirthValid){
                return isValid;
            }
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
        }
        return isValid = false;
    }

    public boolean validationExistisCPFandEmail(AccountProposalForm proposal){
        try {
            boolean cpf = validCpf(proposal.getCpf());
            boolean email = validEmail(proposal.getEmail());
            return !cpf || !email;
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
        }
        return false;
    }

    public boolean validCpf(String cpf){
        try{
            String valid = proposalRepository.findByCpf(cpf);
            return valid != null;
        }catch (EntityNotFoundException | ClassCastException  e){
            logger.info("invalid cpf or exists: " + cpf, e.getMessage());
        }
        return false;
    }

    public boolean validEmail(String email) {
        try {
            String valid = proposalRepository.findByEmail(email);
            return valid != null;
        } catch (EntityNotFoundException | ClassCastException e) {
            logger.info("invalid e-mail or exists: " + email, e.getMessage());
            return false;
        }
    }

    public boolean validationDateBirth(LocalDate birth) {
        int ageYearBirth = birth.getYear();
        int yearActual = LocalDate.now().getYear();
        int ageFinal = yearActual - ageYearBirth;

        return ageFinal >= AGE_BIRTH;
    }
}

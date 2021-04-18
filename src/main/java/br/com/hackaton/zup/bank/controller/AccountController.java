package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.model.Account;
import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.AccountRepository;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import br.com.hackaton.zup.bank.service.utils.HandlelIdLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository accountRepository;
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountRepository accountRepository, ProposalRepository proposalRepository) {
        this.accountRepository = accountRepository;
        this.proposalRepository = proposalRepository;
    }

    @PostMapping()
    public ResponseEntity<Account> register(@RequestHeader(name = "x-com-location", required = true) String headerLocation) {

        try{
            Proposal newProposal = proposalRepository.getOne(HandlelIdLocation.handle(headerLocation));
            Account newAccount = new Account();

            if (newProposal.getCpf() != null) {
                newAccount.setAgencia("0001");
                newAccount.setNumber("01035123");
                newAccount.setSaldo(0);
            }
            accountRepository.save(newAccount);
            return new ResponseEntity<>(CREATED);

        }catch (EntityNotFoundException | IllegalArgumentException e){
            logger.error("erro register proposal" + e.getMessage());
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity getAll() {
        try {
            List<Account> accounts = accountRepository.findAll();
            return ResponseEntity.ok(accounts);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
}

package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.AccountDTO;
import br.com.hackaton.zup.bank.model.Account;
import br.com.hackaton.zup.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<AccountDTO> register(Account account){
        try {
            repository.save(account);
            return new ResponseEntity<>(CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    public ResponseEntity<AccountDTO> getOne(Long id){
        Optional<Account> account = repository.findById(id);
        return account.map(value -> ResponseEntity.ok(new AccountDTO(value))).orElse(null);
    }

    public ResponseEntity<List<Account>> getAll(){
        try{
            List<Account> accounts = repository.findAll();
            return ResponseEntity.ok(accounts);
        }catch (EntityNotFoundException e){
            return new ResponseEntity(BAD_REQUEST);
        }
    }
}

package br.com.hackaton.zup.bank.controller;


import br.com.hackaton.zup.bank.controller.form.AccountForm;
import br.com.hackaton.zup.bank.domain.Prospect;
import br.com.hackaton.zup.bank.repository.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/abertura-conta")
public class ProspectController {

    @Autowired
    private ProspectRepository prospectRepository;


    @GetMapping(value = "/")
    public String helloProspect(){
        return "nice zup";
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Prospect> proposal(@RequestBody AccountForm form){
    prospectRepository.save(new Prospect(form));
    return ResponseEntity.ok().build();
    }
}

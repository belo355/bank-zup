package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.ProposalAccountDto;
import br.com.hackaton.zup.bank.service.ProposalFormValidService;
import lombok.extern.slf4j.Slf4j;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.domain.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/abertura-conta")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalFormValidService proposalFormValidService;

    @GetMapping(value = "/")
    public String helloProspect(){
        return "nice zup";
    }

    //TODO: DOCUMENTAR
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProposalAccountDto> getProposal(@PathVariable(required = true) Long id){
        try {
            Optional<Proposal> proposal =  proposalRepository.findById(id);
            return ResponseEntity.ok(new ProposalAccountDto(proposal.get()));
        }catch (Error e){
            //TODO: LLOGGER ERROR
        }
        return ResponseEntity.notFound().build();
    }

    //TODO: DOCUMENTAR
    @PostMapping
    @Transactional
    public ResponseEntity<String>  registerProposal(@RequestBody @Valid AccountProposalForm form) {

        //proposalFormValidService TODO: validar cpf e posterior email existente
        try{
            proposalRepository.save(new Proposal(form));
            return new ResponseEntity("", HttpStatus.CREATED);
        }catch (Error e){
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);
        }
    }

//    //TODO: colocar regra em um service
//    public Boolean cpfValid(String cpf){
//        String valid = prospectRepository.find(cpf);
//
//        if (valid.isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    //TODO: colocar regra em um service
//    public Boolean emailValid(String email){
//        String valid = prospectRepository.findByEmail(email);
//
//        if (valid.isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}

package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.ProposalAccountDto;
import br.com.hackaton.zup.bank.service.ProposalService;
import lombok.extern.slf4j.Slf4j;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/abertura-conta")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalService proposalService;

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

    @PostMapping
    @Transactional
    public ResponseEntity<String>  registerProposal(@RequestBody @Valid AccountProposalForm form, HttpServletRequest req) {

        boolean formProposalValid = handleValidProposal(form);

        if(formProposalValid == true){
            try{
                Proposal proposal = new Proposal(form);
                proposalRepository.save(proposal);

                URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/{id}").build()
                        .expand(proposal.getId()).toUri();

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(location);
                return new ResponseEntity(headers, HttpStatus.CREATED);
            }catch (Error e){
                return new ResponseEntity("", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity("Proposal invalid CPF e/ou Email já Existente", HttpStatus.BAD_REQUEST);
        }
    }

    public boolean handleValidProposal(AccountProposalForm proposalForm){
        boolean status = proposalService.handle(proposalForm);
        return status;
    }



}

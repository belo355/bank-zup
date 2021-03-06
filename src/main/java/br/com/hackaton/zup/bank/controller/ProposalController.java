package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.ProposalAccountDto;
import lombok.extern.slf4j.Slf4j;

import br.com.hackaton.zup.bank.controller.form.AccountProposalForm;
import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(ProposalController.class);

    @Autowired
    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid AccountProposalForm form) {

        try {

            Proposal proposal = new Proposal(form);
            proposalRepository.save(proposal);

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/proposal/{id}").build().expand(proposal.getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            logger.info(("Proposal registed sucessfull:" + proposal.getId()));

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>("Erro register proposal", BAD_REQUEST);
        }

    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<Proposal>> getAll() {
        try{
            List<Proposal> proposals = proposalRepository.findAll();
            return ResponseEntity.ok(proposals);
        }catch (EntityNotFoundException e){
            logger.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProposalAccountDto> getOne(@PathVariable(required = true) Long id) {
        try {
            Optional<Proposal> proposal = proposalRepository.findById(id);
            return proposal.map(prop -> ResponseEntity.ok(new ProposalAccountDto(prop))).orElse(null);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }


}

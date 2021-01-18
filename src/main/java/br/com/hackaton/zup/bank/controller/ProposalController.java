package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.ProposalAccountDto;
import br.com.hackaton.zup.bank.controller.dto.ProposalAccountInformationDto;
import br.com.hackaton.zup.bank.service.ProposalService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 *
 * @author:
 * @apiNote:
 *
 */

@Slf4j
@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private static final int AGE = 18;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalService proposalService;

    Logger logger = LoggerFactory.getLogger(ProposalController.class);

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
            return ResponseEntity.ok(new ProposalAccountDto(proposal.get()));
        } catch (Exception e) {
            logger.info("Proposal not found: " + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/resume")
    @Transactional
    public ResponseEntity<ProposalAccountInformationDto> getDetails(@PathVariable(required = true) Long id) {
        try {
            logger.info("Find proposal information resume .. " + id);
            Optional<Proposal> proposal = proposalRepository.findById(id);
            return ResponseEntity.ok(new ProposalAccountInformationDto(proposal.get()));
        } catch (Exception e) {
            logger.info("Proposal not found" + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid AccountProposalForm form) {

        boolean formProposalValid = handleValidProposal(form);
        boolean ageRange = handleDateBirth(form.getDateBirth());

        if (formProposalValid == true) {
            if (ageRange == true) {
                try {
                    Proposal proposal = new Proposal(form);
                    proposalRepository.save(proposal);

                    URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/proposal/{id}").build() //TODO: ALTERAR URI MANUAL
                            .expand(proposal.getId()).toUri();

                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(location);
                    logger.info(("Proposal registed sucessfull:" + proposal.getId() + " generate location: " + headers));

                    return new ResponseEntity(headers, HttpStatus.CREATED);
                } catch (EntityNotFoundException e) {
                    logger.info(e.getMessage());
                    return new ResponseEntity("Erro register proposal", BAD_REQUEST);
                }
            } else {
                return new ResponseEntity("Proposal invalid, date birth < 18 years", BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("Proposal invalid,  CPF or EMAIL exists", BAD_REQUEST);
        }
    }

    public boolean handleValidProposal(AccountProposalForm proposalForm) {
        return proposalService.validationExistisCPFandEmail(proposalForm);
    }

    public boolean handleDateBirth(LocalDate birth) {
        int ageYearBirth = birth.getYear();
        int yearActual = LocalDate.now().getYear();

        int ageFinal = yearActual - ageYearBirth;
        if (ageFinal >= AGE) {
            return true;
        } else {
            return false;
        }
    }

}

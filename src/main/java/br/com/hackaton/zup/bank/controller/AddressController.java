package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.AdressAccountDto;
import br.com.hackaton.zup.bank.controller.form.AddressProposalForm;
import br.com.hackaton.zup.bank.service.utils.HandleIIdLocation;
import br.com.hackaton.zup.bank.model.Address;
import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.AddressRepository;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressRepository addressRepository;
    private ProposalRepository proposalRepository;

    private final String HEADER_LOCATION = "/endereco";

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    public AddressController(AddressRepository addressRepository, ProposalRepository proposalRepository) {
        this.addressRepository = addressRepository;
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid AddressProposalForm form,
                                           @RequestHeader(name = "x-com-location", required = true) String headerLocation) throws StringIndexOutOfBoundsException {
        try {
            Address address = new Address(form);
            addressRepository.save(address);
                try {
                    Optional<Proposal> proposal = proposalRepository.findById(HandleIIdLocation.handle(headerLocation));
                    proposal.get().setAddress(address);
                } catch (NoSuchElementException e) {
                    logger.info("headerLocation {}", headerLocation);
                    return new ResponseEntity("Proposal not found", BAD_REQUEST);
                }

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path(HEADER_LOCATION + "{id}").build().expand(address.getId()).toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.info("Not possible register adress information  " + e.getMessage());
            return new ResponseEntity("", BAD_REQUEST);
        }
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<Address>> getAll() {
        try {
            List<Address> adresses = addressRepository.findAll();
            return ResponseEntity.ok(adresses);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<AdressAccountDto> getOne(@PathVariable(required = true) Long id) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            return ResponseEntity.ok(new AdressAccountDto(address.get()));
        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


}

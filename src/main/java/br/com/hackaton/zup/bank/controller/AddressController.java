package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.AdressAccountDto;
import br.com.hackaton.zup.bank.controller.form.AddressProposalForm;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: Edilson Belo
 * @apiNote:
 */

@RestController
@RequestMapping("/abertura-conta/endereco")
public class AddressController {

    private static String ENV_LOCATION = "http://localhost:8080";
    private static String ENDPOINT = "/abertura-conta/";


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping
    @Transactional
    public ResponseEntity<List<Address>> getAdresses() {
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
    public ResponseEntity<AdressAccountDto> getAdressDetail(@PathVariable(required = true) Long id) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            return ResponseEntity.ok(new AdressAccountDto(address.get()));
        } catch (IllegalArgumentException e) {
            logger.info("Adress not found" + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerAdress(@RequestBody @Valid AddressProposalForm form,
                                                 @RequestHeader(name = "x-com-location", required = true) String headerLocation) throws StringIndexOutOfBoundsException {
        try {
            Address address = new Address(form);
            addressRepository.save(address);
            logger.info("Saved adress sucefull");

            if (address.getId() != null) {
                try {
                    Proposal proposal = proposalRepository.getOne(returnHeaderIdLocation(headerLocation));
                    proposal.setAddress(address);
                    logger.info("liked adress for proposal-id {}", proposal.getId());
                } catch (Exception e) {
                    return new ResponseEntity("Proposal not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/endereco/{id}").build().expand(address.getId()).toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity(headers, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            logger.info("Not possible register adress information  " + e.getMessage());
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);
        }
    }

    public Optional<Proposal> getHeaderLocation(String headerLocation) {
        try {
            Optional<Proposal> proposal = proposalRepository.findById(returnHeaderIdLocation(headerLocation));
            return proposal;
        } catch (NullPointerException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public Long returnHeaderIdLocation(String headerLocation) {
        return Long.parseLong(headerLocation);
    }

}

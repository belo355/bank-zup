package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.AdressAccountDto;
import br.com.hackaton.zup.bank.controller.form.AdressProposalForm;
import br.com.hackaton.zup.bank.model.Adress;
import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.AdressRepository;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/abertura-conta/endereco")
public class AdressController {

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(AdressController.class);

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<AdressAccountDto> getAdress(@PathVariable(required = true) Long id) {
        try {
            Optional<Adress> adress = adressRepository.findById(id);
            return ResponseEntity.ok(new AdressAccountDto(adress.get()));
        } catch (Exception e) {
            logger.info("Endereço nao encontrado " + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerAdress(@RequestBody @Valid AdressProposalForm form,
                                                 @RequestHeader(name = "x-com-location", required = true) String headerLocation) {
        try {
            Adress adress = new Adress(form);
            adressRepository.save(adress);

            logger.info("Find proposal to x-com-location .. " + headerLocation);
            Proposal proposal = getPorposalExist(returnLong(headerLocation));

//            adress.setProposal(proposal);
              proposal.setAdress(adress);
              logger.info("Adress : " + adress.getStreet() + "associado a proposal: "  + proposal.getId());

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/endereco/{id}")
                    .build().expand(adress.getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("Não foi possivel registrar o endereço " + e.getMessage());
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);
        }
    }

    public Proposal getPorposalExist(Long id){
        try{
            return proposalRepository.getOne(id);
        }catch (Exception e ){
            logger.info(e.getMessage());
        }
        return null;
    }

    public Long returnLong(String headerLocation){
        return Long.parseLong(headerLocation.substring(headerLocation.length() - 1));
    }

}

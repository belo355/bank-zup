package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.controller.dto.AdressAccountDto;
import br.com.hackaton.zup.bank.controller.form.AdressProposalForm;
import br.com.hackaton.zup.bank.model.Adress;
import br.com.hackaton.zup.bank.repository.AdressRepository;
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

@RestController
@RequestMapping("/abertura-conta/endereco")
public class AdressController {

    @Autowired
    private AdressRepository adressRepository;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<AdressAccountDto> getAdress(@PathVariable(required = true) Long id){
        try {
            Optional<Adress> adress =  adressRepository.findById(id);
            return ResponseEntity.ok(new AdressAccountDto(adress.get()));
        }catch (Error e){
            //TODO: LLOGGER ERROR
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String>  registerAdress(@RequestBody @Valid AdressProposalForm form, HttpServletRequest req) {
            try{
                Adress adress = new Adress(form);
                adressRepository.save(adress);

                URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/endereco/{id}").build()
                        .expand(adress.getId()).toUri();

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(location);
                return new ResponseEntity(headers, HttpStatus.CREATED);
            }catch (Error e){
                return new ResponseEntity("", HttpStatus.BAD_REQUEST);
            }
        }

}

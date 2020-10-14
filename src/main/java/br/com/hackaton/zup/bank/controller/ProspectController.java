package br.com.hackaton.zup.bank.controller;


import br.com.hackaton.zup.bank.domain.Prospect;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/abertura-conta")
public class ProspectController {

    @GetMapping(value = "/")
    public String helloProspect(){
        return "nice zup";
    }


    @PostMapping
    public ResponseEntity<Prospect> proposal(@RequestBody(required = false) String name,
                                            @RequestBody(required = false) String lastName,
                                            @RequestBody(required = false) String email,
                                            @RequestBody(required = false) LocalDateTime dateBirth,
                                            @RequestBody(required = false) String cpf){

        Prospect newProspect = new Prospect(name, lastName, email, dateBirth, cpf);
        return ResponseEntity.ok().build();
    }
}

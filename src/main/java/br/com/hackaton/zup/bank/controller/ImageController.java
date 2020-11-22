package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.config.files.ResponseImageHandler;
import br.com.hackaton.zup.bank.config.files.ResponseMessageHandler;
import br.com.hackaton.zup.bank.model.Image;
import br.com.hackaton.zup.bank.model.Proposal;
import br.com.hackaton.zup.bank.repository.ProposalRepository;
import br.com.hackaton.zup.bank.service.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//TODO: ADD JAVADOC

@RestController
@RequestMapping("/abertura-conta/upload")
public class ImageController {

    @Autowired
    private ImageStorageService storageService;

    @Autowired
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @PostMapping
    public ResponseEntity<ResponseMessageHandler> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
                                                             @RequestHeader(name = "x-com-location", required = true) String headerLocation) {
        String message = "";
        try {
            Image image = storageService.store(file);
            Proposal proposal = getPorposalExist(returnLong(headerLocation));

            proposal.setImage(image);
            logger.info("Image : " + image.getId() + " associeted for proposal: "  + proposal.getId());

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/upload/{id}").build()
                    .expand(image.getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (EntityNotFoundException | IOException e) {
            logger.info(e.getMessage());
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageHandler(message));
        }
    }


    @GetMapping
    public ResponseEntity<List<ResponseImageHandler>> getListFiles() {
        List<ResponseImageHandler> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/abertura-conta/upload/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseImageHandler(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    public Proposal getPorposalExist(Long id){
        try{
            return proposalRepository.getOne(id);
        }catch (EntityNotFoundException e ){
            logger.info(e.getMessage());
        }
        return null;
    }

    public Long returnLong(String headerLocation){
        return Long.parseLong(headerLocation.substring(headerLocation.length() - 1));
    }



}

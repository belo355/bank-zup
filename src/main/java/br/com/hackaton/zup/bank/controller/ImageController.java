package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.config.files.ResponseImageHandler;
import br.com.hackaton.zup.bank.config.files.ResponseMessageHandler;
import br.com.hackaton.zup.bank.service.utils.HandleIIdLocation;
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

import static sun.security.timestamp.TSResponse.BAD_REQUEST;

/**
 *
 * @author:
 * @apiNote:
 *
 */

@RestController
@RequestMapping("/abertura-conta/upload")
public class ImageController {

    @Autowired
    private ImageStorageService imageService;

    @Autowired
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @PostMapping
    public ResponseEntity<ResponseMessageHandler> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
                                                             @RequestHeader(name = "x-com-location", required = true) String headerLocation) {
        String message = "";
        try {
            Image image = imageService.handleImg(file);
            Proposal proposal = proposalRepository.getOne(HandleIIdLocation.handle(headerLocation));
            proposal.setImage(image);

            logger.info("Image : " + image.getId() + " associeted for proposal: " + proposal.getId());

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/upload/{id}").build()
                    .expand(image.getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            return new ResponseEntity(headers, HttpStatus.CREATED);
        } catch (EntityNotFoundException | IOException e) {
            logger.info(e.getMessage());
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessageHandler(message));
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseImageHandler>> getAll() {
        List<ResponseImageHandler> files = imageService.getAllFiles().map(dbFile -> {
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

}

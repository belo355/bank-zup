package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.config.files.ResponseImageHandler;
import br.com.hackaton.zup.bank.config.files.ResponseMessageHandler;
import br.com.hackaton.zup.bank.service.utils.HandlelIdLocation;
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
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proposal/upload/image")
public class ImageController {

    public static final String URL_IMAGE = "/proposal/upload/";

    private ImageStorageService imageService;
    private ProposalRepository proposalRepository;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    public ImageController(ImageStorageService imageService, ProposalRepository proposalRepository) {
        this.imageService = imageService;
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    public ResponseEntity<ResponseMessageHandler> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
                                                             @RequestHeader(name = "x-com-location", required = true) String headerLocation) {
        String message = "";
        try {
            Image image = imageService.insertImage(file);

            Optional<Proposal> proposal = proposalRepository.findById(HandlelIdLocation.handle(headerLocation));
            proposal.ifPresent(p -> p.setImage(image));

            logger.info("Image apply: " + image.getId());

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping()
                    .path(URL_IMAGE+ "{id}").build().expand(image.getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageHandler(message));
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseImageHandler>> getAll() {
        List<ResponseImageHandler> files = imageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(URL_IMAGE)
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

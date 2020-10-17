package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.config.files.ResponseImageHandler;
import br.com.hackaton.zup.bank.config.files.ResponseMessageHandler;
import br.com.hackaton.zup.bank.model.Image;
import br.com.hackaton.zup.bank.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/abertura-conta/upload")
public class ImageController {

    @Autowired
    private ImageStorageService storageService;

    @PostMapping
    public ResponseEntity<ResponseMessageHandler> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/abertura-conta/upload/{id}").build()
                    .expand(storageService.store(file).getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity(headers, HttpStatus.CREATED);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageHandler(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageHandler(message));
        }
    }


    @GetMapping
    public ResponseEntity<List<ResponseImageHandler>> getListFiles() {
        List<ResponseImageHandler> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/abertura-conta/upload/")
                    .path(dbFile.getId())
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

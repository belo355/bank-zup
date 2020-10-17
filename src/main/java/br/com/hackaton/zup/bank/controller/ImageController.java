package br.com.hackaton.zup.bank.controller;

import br.com.hackaton.zup.bank.config.files.ResponseImageHandler;
import br.com.hackaton.zup.bank.config.files.ResponseMessageHandler;
import br.com.hackaton.zup.bank.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
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

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageHandler(message));
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
                    .path("/files/")
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

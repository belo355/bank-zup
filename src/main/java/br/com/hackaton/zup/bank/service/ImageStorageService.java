package br.com.hackaton.zup.bank.service;


import br.com.hackaton.zup.bank.controller.ImageController;
import br.com.hackaton.zup.bank.model.Image;
import br.com.hackaton.zup.bank.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author: Edilson Belo
 * @apiNote:
 *
 */

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;

    Logger logger = LoggerFactory.getLogger(ImageStorageService.class);

    public Image store(MultipartFile file) throws IOException {
        try{
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image img = new Image(fileName, file.getContentType(), file.getBytes());
            imageRepository.save(img);
            return img;
        }catch (IOException e){
            logger.info(e.getMessage());
            return null;
        }
    }

    public Optional<Image> getFile(Long id) {
        return imageRepository.findById(id);
    }

    public Stream<Image> getAllFiles() {
        return imageRepository.findAll().stream();
    }



}

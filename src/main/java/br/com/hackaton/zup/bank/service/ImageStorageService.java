package br.com.hackaton.zup.bank.service;

import br.com.hackaton.zup.bank.model.Image;
import br.com.hackaton.zup.bank.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;

    Logger logger = LoggerFactory.getLogger(ImageStorageService.class);

    public Image insertImage(MultipartFile file) {
        String multipartFile = file.getOriginalFilename();
        if (multipartFile != null) {
            try {
                String fileName = StringUtils.cleanPath(multipartFile);
                Image img = new Image(fileName, file.getContentType(), file.getBytes());
                imageRepository.save(img);
                return img;
            } catch (IOException e) {
                logger.info(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public Stream<Image> getAllFiles() {
        return imageRepository.findAll().stream();
    }



}

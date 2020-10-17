package br.com.hackaton.zup.bank.service;


import br.com.hackaton.zup.bank.model.Image;
import br.com.hackaton.zup.bank.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image img = new Image(fileName, file.getContentType(), file.getBytes());

        return imageRepository.save(img);
    }

    public Optional<Image> getFile(Long id) {
        return imageRepository.findById(id);
    }

    public Stream<Image> getAllFiles() {
        return imageRepository.findAll().stream();
    }



}

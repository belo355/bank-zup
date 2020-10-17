package br.com.hackaton.zup.bank.repository;

import br.com.hackaton.zup.bank.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

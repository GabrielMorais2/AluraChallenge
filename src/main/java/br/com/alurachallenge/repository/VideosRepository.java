package br.com.alurachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alurachallenge.model.Videos;

import java.util.List;

public interface VideosRepository extends JpaRepository<Videos, Long> {
    List<Videos> findByTitulo(String titulo);
}

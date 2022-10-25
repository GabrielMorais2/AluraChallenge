package br.com.alurachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alurachallenge.model.Videos;

public interface VideosRepository extends JpaRepository<Videos, Long> {

}

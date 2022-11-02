package br.com.alurachallenge.repository;

import br.com.alurachallenge.model.Categorias;
import br.com.alurachallenge.model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {


}

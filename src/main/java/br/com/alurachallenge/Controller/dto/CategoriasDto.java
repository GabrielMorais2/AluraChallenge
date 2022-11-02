package br.com.alurachallenge.Controller.dto;

import br.com.alurachallenge.model.Categorias;
import br.com.alurachallenge.model.Videos;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriasDto {

    private long id;
    private String titulo;
    private String cor;

    public CategoriasDto(Categorias categorias) {
        this.id = categorias.getId();
        this.titulo = categorias.getTitulo();
        this.cor = categorias.getCor();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public static List<CategoriasDto> converter(List<Categorias> categorias){
        return categorias.stream().map(CategoriasDto::new).collect(Collectors.toList());
    }

}

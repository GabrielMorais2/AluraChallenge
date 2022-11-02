package br.com.alurachallenge.Controller.form;

import br.com.alurachallenge.model.Categorias;
import br.com.alurachallenge.model.Videos;
import br.com.alurachallenge.repository.CategoriasRepository;
import br.com.alurachallenge.repository.VideosRepository;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

public class CategoriasForm {

    @NotBlank
    private String titulo;

    @NotBlank
    private String cor;

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

    public Categorias converter(CategoriasRepository categoriasRepository) {
        return new Categorias(titulo, cor);
    }

}

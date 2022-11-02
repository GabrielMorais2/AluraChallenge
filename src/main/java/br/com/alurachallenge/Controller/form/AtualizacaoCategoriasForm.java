package br.com.alurachallenge.Controller.form;

import br.com.alurachallenge.model.Categorias;
import br.com.alurachallenge.repository.CategoriasRepository;

import javax.validation.constraints.NotBlank;

public class AtualizacaoCategoriasForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    public Categorias atualizar(long id, CategoriasRepository categoriasRepository){

        Categorias categorias = categoriasRepository.getReferenceById(id);
        categorias.setTitulo(this.titulo);
        categorias.setCor(this.cor);

        return categorias;
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
}

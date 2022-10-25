package br.com.alurachallenge.Controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import br.com.alurachallenge.model.Videos;
import br.com.alurachallenge.repository.VideosRepository;

public class AtualizacaoVideosForm {

	@NotBlank
	@Length(min = 3)
	private String titulo;
	@NotBlank
	@Length(min = 3)
	private String descricao;
	@NotBlank
	@Length(max = 200)
	@URL(protocol = "https")
	private String url;

	public Videos atualizar(Long id, VideosRepository videosRepository) {

		Videos videos = videosRepository.getReferenceById(id);
		videos.setTitulo(this.titulo);
		videos.setDescricao(descricao);
		videos.setUrl(url);

		return videos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

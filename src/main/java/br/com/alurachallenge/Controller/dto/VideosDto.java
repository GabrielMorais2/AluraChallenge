package br.com.alurachallenge.Controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alurachallenge.model.Videos;

public class VideosDto {
	
	private long id;
	private String titulo;
	private String descricao;
	private String url;
	
	public VideosDto(Videos video) {
		this.id = video.getId();
		this.descricao = video.getDescricao();
		this.titulo = video.getTitulo();
		this.url = video.getUrl();
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getUrl() {
		return url;
	}

	public Long getId() {
		return id;
	}
	
	public static List<VideosDto> converter(List<Videos> videos){
		return videos.stream().map(VideosDto::new).collect(Collectors.toList());
	}

	public static Optional<VideosDto> converterComID(Optional<Videos> videos) {
		return videos.map(VideosDto::new);
	}
	
}

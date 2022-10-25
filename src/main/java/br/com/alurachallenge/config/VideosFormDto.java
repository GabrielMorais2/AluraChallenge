package br.com.alurachallenge.config;

import java.util.Date;

public class VideosFormDto {

	private int statusCode;
	private Date data;
	private String campo;
	private String mensagem;

	public VideosFormDto(int statusCode, Date data, String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
		this.data = data;
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Date getData() {
		return data;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

}

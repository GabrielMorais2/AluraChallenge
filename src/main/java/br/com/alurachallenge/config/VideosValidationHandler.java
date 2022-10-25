package br.com.alurachallenge.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VideosValidationHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<VideosFormDto> hendler(MethodArgumentNotValidException exception) {
		List<VideosFormDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
				String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
				VideosFormDto erro = new VideosFormDto(HttpStatus.BAD_REQUEST.value(), new Date() ,e.getField(), mensagem);
				dto.add(erro);
		});
		
		return dto;
	}	
}

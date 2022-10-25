package br.com.alurachallenge.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alurachallenge.Controller.dto.VideosDto;
import br.com.alurachallenge.Controller.form.AtualizacaoVideosForm;
import br.com.alurachallenge.Controller.form.VideosForm;
import br.com.alurachallenge.model.Videos;
import br.com.alurachallenge.repository.VideosRepository;

@RestController
@RequestMapping(value = "/videos")
public class VideosController {

	@Autowired
	private VideosRepository videosRepository;

	@GetMapping
	public ResponseEntity<List<VideosDto>> getVideos() {

		try {
			List<Videos> videos = (List<Videos>) videosRepository.findAll();
			return new ResponseEntity<>(VideosDto.converter(videos), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideosDto> getVideosComID(@PathVariable long id) {
		Optional<Videos> videosDados = videosRepository.findById(id);

		if (videosDados.isPresent()) {
			return new ResponseEntity<>(new VideosDto(videosDados.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<VideosDto> postVideos(@RequestBody @Valid VideosForm form, UriComponentsBuilder uriBuilder) {
		Videos videos = form.converter(videosRepository);
		videosRepository.save(videos);

		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(videos.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideosDto(videos));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideosDto> updateVideos(@PathVariable long id,
			@RequestBody @Valid AtualizacaoVideosForm form) {
		Optional<Videos> videosDados = videosRepository.findById(id);

		if (videosDados.isPresent()) {
			Videos videos = form.atualizar(id, videosRepository);
			return new ResponseEntity<>(new VideosDto(videos), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<VideosDto> deleteVideos(@PathVariable long id) {
		Optional<Videos> videosDados = videosRepository.findById(id);

		if (videosDados.isPresent()) {
			videosRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}

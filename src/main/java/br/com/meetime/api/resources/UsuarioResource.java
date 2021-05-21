package br.com.meetime.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meetime.api.domain.Usuario;
import br.com.meetime.api.domain.to.UsuarioTO;
import br.com.meetime.api.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping()
	public ResponseEntity<List<UsuarioTO>> Usuarios() {
		List<UsuarioTO> usuarioDto = usuarioService.getAll().stream().map(UsuarioTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(usuarioDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioTO> find(@PathVariable Integer id) {
		return ResponseEntity.ok().body(UsuarioTO.toTO(usuarioService.findById(id)));
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UsuarioTO usuarioTO) {
		Usuario usuario = usuarioService.insert(UsuarioTO.toModel(usuarioTO));
		usuarioTO = UsuarioTO.toTO(usuario);
		URI uri = getUri(usuarioTO.getId());
		return ResponseEntity.created(uri).build();
	}

	private URI getUri(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}

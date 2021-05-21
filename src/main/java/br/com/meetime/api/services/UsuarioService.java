package br.com.meetime.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meetime.api.domain.Usuario;
import br.com.meetime.api.repositories.UsuarioRepository;
import br.com.meetime.api.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));
	}

	public Usuario findByEmail(String email) throws ObjectNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! E-mail: " + email));
	}
	@Transactional
	public Usuario insert(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}

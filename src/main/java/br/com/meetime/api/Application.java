package br.com.meetime.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.com.meetime.api.domain.Lead;
import br.com.meetime.api.domain.Usuario;
import br.com.meetime.api.repositories.LeadRepository;
import br.com.meetime.api.repositories.UsuarioRepository;
import br.com.meetime.api.domain.enums.Status;

@SpringBootApplication
@EnableFeignClients
public class Application implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuariorepository;
	@Autowired
	private LeadRepository leadrepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario u1 = new Usuario(1, "Beto", "beto@email.com");
		usuariorepository.save(u1);

		Lead l1 = new Lead(1, "Lead teste", "Lead1@email.com", "Empresa teste", "Site teste", "Bla bla bla",Status.LOST,
				"+55219879967", u1);
		leadrepository.save(l1);

	}

}

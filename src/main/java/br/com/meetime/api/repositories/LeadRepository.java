package br.com.meetime.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meetime.api.domain.Lead;


@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{
}

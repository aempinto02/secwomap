package br.com.secwomap.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.secwomap.domain.Incident;
import br.com.secwomap.domain.Location;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

	@Transactional(readOnly = true)
	List<Incident> findBySeverity(Integer severity);
	
	@Transactional(readOnly = true)
	List<Incident> findByAddressContainsIgnoreCase(String address);

	@Transactional(readOnly = true)
	List<Incident> findByLocation(@Valid Location location);
	
}

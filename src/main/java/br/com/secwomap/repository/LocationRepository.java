package br.com.secwomap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.secwomap.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {}

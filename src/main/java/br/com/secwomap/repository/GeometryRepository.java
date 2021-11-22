package br.com.secwomap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.secwomap.domain.Geometry;

public interface GeometryRepository extends JpaRepository<Geometry, Long> {}

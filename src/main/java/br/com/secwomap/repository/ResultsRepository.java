package br.com.secwomap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.secwomap.domain.Results;

public interface ResultsRepository extends JpaRepository<Results, Long> {}

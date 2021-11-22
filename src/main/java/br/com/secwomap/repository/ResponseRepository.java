package br.com.secwomap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.secwomap.domain.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {}

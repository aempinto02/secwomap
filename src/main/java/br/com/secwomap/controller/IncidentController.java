package br.com.secwomap.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.secwomap.domain.Address;
import br.com.secwomap.domain.Incident;
import br.com.secwomap.domain.Location;
import br.com.secwomap.service.IncidentService;

@RestController
@RequestMapping("/incident")
public class IncidentController {
	
	@Autowired
	private IncidentService service;
	
	@GetMapping
	public List<Incident> getAllIncidents() {
		return service.getAllIncidents();
	}
	
	@GetMapping(value = "/severity/{severity}")
	public List<Incident> getIncidentsBySeverity(@PathVariable @NotNull Integer severity) {
		return service.getIncidentsBySeverity(severity);
	}
	
	@GetMapping(value = "/address")
	public List<Incident> getIncidentsByAddress(@RequestParam @NotNull String address) {
		return service.getIncidentsByAddress(address);
	}
	
	@GetMapping(value = "/location")
	public List<Incident> getIncidentsByLocation(@RequestParam @Valid Location location) {
		return service.getIncidentsByLocation(location);
	}
	
	@PostMapping(value = "/address/{severity}")
	public Incident insertIncidentByAddress(@Valid @RequestBody Address address, @NotNull @PathVariable Integer severity, @NotBlank @RequestParam String description) {
		return service.insertIncidentByAddress(address, severity, description);
	}
	
	@PostMapping(value = "/location/{severity}")
	public Incident insertIncidentByLocation(@Valid @RequestBody Location location, @NotNull @PathVariable Integer severity, @NotBlank @RequestParam String description) {
		return service.insertIncidentByLocation(location, severity, description);
	}
	
}

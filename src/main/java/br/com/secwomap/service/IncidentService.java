package br.com.secwomap.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;

import br.com.secwomap.domain.Address;
import br.com.secwomap.domain.Incident;
import br.com.secwomap.domain.Location;
import br.com.secwomap.domain.Response;
import br.com.secwomap.domain.enums.Severity;
import br.com.secwomap.exception.AddressNotFoundException;
import br.com.secwomap.repository.IncidentRepository;
import br.com.secwomap.repository.LocationRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

@Service
public class IncidentService {

	@Autowired
	private IncidentRepository repository;	

	@Autowired
	private LocationRepository locationRepository;	

	public List<Incident> getAllIncidents() {
		return repository.findAll();
	}

	public List<Incident> getIncidentsBySeverity(Integer severity) {
		return repository.findBySeverity(severity);
	}

	public List<Incident> getIncidentsByAddress(String address) {
		return repository.findByAddressContainsIgnoreCase(address);
	}
	
	public List<Incident> getIncidentsByLocation(@Valid Location location) {
		return repository.findByLocation(location);
	}

	public Incident insertIncidentByAddress(@Validated Address address, @NotNull Integer severity, @NotBlank String description) {
		OkHttpClient client = new OkHttpClient();
		String toMaps = address.toMaps();
		Request request = new Request.Builder()
				.url(String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key={GOOGLE-MAPS-GEOCODING-API-KEY}", toMaps))
				.get()
				.build();
		ResponseBody responseBody = null;
		try {
			responseBody = client.newCall(request).execute().body();
		} catch (IOException e) {
			System.out.println("Problema ao enviar requisição ao Google Maps");
		}
		
		String json = null;
		Response response = null;
		try {
			json = responseBody.string();
			System.out.println(json);
			response = new ObjectMapper().readValue(json, Response.class);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		if(response.getResults().size() == 0) {
			throw new AddressNotFoundException();
		}
		
		String addressString = response.getResults().get(0).getAddress();
		
		Incident incident = new Incident(addressString, Severity.toEnum(severity), description);
		
		Location location = response.getResults().get(0).getGeometry().getLocation();
		incident.setLocation(location);
		
		locationRepository.save(location);
		repository.save(incident);
		
		return incident;
	}
	
	public Incident insertIncidentByLocation(@Valid Location location, @NotNull Integer severity, @NotBlank String description) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key={GOOGLE-MAPS-GEOCODING-API-KEY}", location.getLat().toString(), location.getLng().toString()))
				.get()
				.build();
		ResponseBody responseBody = null;
		try {
			responseBody = client.newCall(request).execute().body();
		} catch (IOException e) {
			System.out.println("Problema ao enviar requisição ao Google Maps");
		}
		
		String json = null;
		Response response = null;
		try {
			json = responseBody.string();
			System.out.println(json);
			response = new ObjectMapper().readValue(json, Response.class);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		if(response.getResults().size() == 0) {
			throw new AddressNotFoundException();
		}
		
		String addressString = response.getResults().get(0).getAddress();
		
		location = response.getResults().get(0).getGeometry().getLocation();
		
		Incident incident = new Incident(null, addressString, location, severity, description);
		
		locationRepository.save(location);
		repository.save(incident);
		
		return incident;
	}
}

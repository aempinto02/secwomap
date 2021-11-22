package br.com.secwomap.domain;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
	private Long id;
	
	@NotBlank(message = "Endereço não pode estar em branco")
	private String address;
	
	@NotBlank(message = "Endereço não pode estar em branco")
	@JsonProperty(value = "city-neighborhood")
	private String cityNeighborhood;
	
	@NotBlank(message = "Endereço não pode estar em branco")
	private String state;
	
	private String cep;
	
	@NotBlank(message = "Endereço não pode estar em branco")
	private String country;
	
	public String toMaps() {
		String middle = address + ", " + cityNeighborhood + ", " + ", " + state + ", " + cep + ", " + country;
		String last = middle.replaceAll(" ", "+");
		return last;
	}
}
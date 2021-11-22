package br.com.secwomap.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import br.com.secwomap.domain.enums.Severity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Incident {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String address;
	
	@OneToOne
	private Location location;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Integer severity;
	
	@NotBlank
	private String description;
	
	public Incident(String address, Severity severity, String description) {
		this.address = address;
		this.severity = severity.getCode();
		this.description = description;
	}
	
	public Incident(Location location, Severity severity, String description) {
		this.location = location;
		this.severity = severity.getCode();
		this.description = description;
	}
	
	public Severity getSeverity() {
		return Severity.toEnum(this.severity);
	}

	public void setSeverity(Severity severity) {
		this.severity = severity.getCode();
	}
}

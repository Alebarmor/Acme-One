package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity{
	
	private static final long	serialVersionUID	= 1L;
	
	
	protected String			words;
	
	@Range(min = 0, max = 1)
	private double				threshold;

	@NotBlank
	private String				language;
}

package acme.entities.tasks;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
	
	@Length(min = 1, max = 80)
	@NotBlank
	protected String title;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected LocalDateTime startTime;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected LocalDateTime endTime;
	
	@NotNull
	protected double workLoad;
	
	@NotBlank
	@Length(min = 1, max = 400)
	protected String description;
	
	@URL
	protected String link;
	
	@NotNull
	protected boolean isPublic;
	
	
	public Duration getExecutionPeriod() {
		final Duration duration = Duration.between(this.startTime, this.endTime);
		return duration;
	}
}

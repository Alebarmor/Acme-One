
package acme.entities.tasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	
	protected static final long	serialVersionUID	= 1L;
	
	@Length(min = 1, max = 80)
	@NotBlank
	protected String title;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startTime;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endTime;
	
	@NotBlank
	@Length(min = 1, max = 400)
	protected String description;
	
	@URL
	protected String info;
	
	protected boolean isPublic;
	
	public void setIsPublic(final boolean a) {
		this.isPublic = a;
	}
	
	
	// Derived variables
	
	@Transient
	public double getWorkload() {
	    return (double) (this.endTime.getTime() - this.startTime.getTime())/(1000*60*60);
	}
	
	
	// Auxiliar methods
	
	public static double round(final double value, final int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}

package acme.entities.workPlans;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {
	
	protected static final long	serialVersionUID	= 1L;
	
	@NotNull
	protected boolean isPublic;
	
	public void setIsPublic(final boolean a) {
		this.isPublic = a;
	}
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startTime;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endTime;
	
	
	// Derived variables
	
	@Transient
	public double getWorkload(){
		return this.tasks.stream().mapToDouble(x -> x.workload).sum();
	}
	
	
	// Associations
	
	@NotNull
	@Valid
	@ManyToMany(mappedBy="workPlans")
	Collection<Task> tasks;

}

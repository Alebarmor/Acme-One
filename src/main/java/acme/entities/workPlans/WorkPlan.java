package acme.entities.workPlans;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.entities.roles.Manager;
import acme.entities.taskWorkPlans.TaskWorkPlan;
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
	
	@ManyToOne(optional = false)
	protected Manager manager;
	
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
	
	// Associations
	
	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "workPlan")
	private Collection<TaskWorkPlan> taskWorkPlan;

}

package acme.entities.taskWorkPlans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TaskWorkPlan extends DomainEntity {
	
	protected static final long	serialVersionUID	= 1L;
	
	@NotNull
	@ManyToOne(optional=false)
	@JoinColumn(name = "task_id")
	protected Task task;
	
	@NotNull
	@ManyToOne(optional=false)
	@JoinColumn(name = "work_plan_id")
	protected WorkPlan workPlan;

}

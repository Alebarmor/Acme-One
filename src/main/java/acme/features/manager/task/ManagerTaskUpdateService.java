package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task>{
	
//	@Autowired
//	private Configuration configuration;
	
	@Autowired
	protected ManagerTaskRepository repository;
	
	@Override
	public boolean authorise (final Request<Task> request) {
		assert request != null;
		
		boolean result;
		int taskId;
		Task task;
		Manager manager;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();
		
		return result;
	}
	
	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
		if (!errors.hasErrors("endTime")) {
			errors.state(request, entity.getEndTime().after(entity.getStartTime()), "endTime", "manager.task.form.error.isBefore");
			
		}
		
		if (!errors.hasErrors("workload")) {
			final double executionPeriodInHours;
			long executionPeriod;
			
			executionPeriod = entity.getEndTime().getTime()-entity.getStartTime().getTime();
			
			executionPeriodInHours = executionPeriod / 3600000.0; //Un minuto son 60.000 milisegundos 
			
			errors.state(request, entity.getWorkload()<= executionPeriodInHours, "workload", "manager.task.form.error.workload");
			
		}
		
//		if (!errors.hasErrors("title")) {
//			errors.state(request, this.configuration.spamValidation(entity.getTitle()), "title", "manager.task.form.error.spam");
//		}
//		
//		if (!errors.hasErrors("description")) {
//			errors.state(request, this.configuration.spamValidation(entity.getDescription()), "description", "manager.task.form.error.spam");
//		}
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startTime", "endTime", "description", "info", "isPublic");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

		return result;
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	

}

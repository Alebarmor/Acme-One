package acme.features.manager.workPlans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlans.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanUpdateService implements AbstractUpdateService<Manager, WorkPlan>{
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Override
	public boolean authorise (final Request<WorkPlan> request) {
		assert request != null;
		
		boolean result;
		int workPlanId;
		WorkPlan workPlan;
		Manager manager;
		Principal principal;
		
		workPlanId = request.getModel().getInteger("id");
		workPlan = this.repository.findOneWorkPlanById(workPlanId);
		manager = workPlan.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();
		
		return result;
	}
	
	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
//		final List<Configuration> listConfigurations = new ArrayList<>(this.repository.getConfiguration());
//		
//		final Configuration confEng = listConfigurations.get(0);
//		final Configuration confEsp = listConfigurations.get(1);
//		
//		if (!errors.hasErrors("title")) {
//			errors.state(request, !(confEng.spamValidation(entity.getTitle()) || confEsp.spamValidation(entity.getTitle())), "title", "manager.task.form.error.spam");
//		}
//		
//		if (!errors.hasErrors("description")) {
//			errors.state(request, !(confEng.spamValidation(entity.getDescription()) || confEsp.spamValidation(entity.getDescription())), "description", "manager.task.form.error.spam");
//		}
	}

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "startTime", "endTime", "isPublic");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);

		return result;
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	

}

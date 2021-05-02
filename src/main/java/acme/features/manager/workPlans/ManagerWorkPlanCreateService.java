package acme.features.manager.workPlans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlans.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkPlanCreateService implements AbstractCreateService<Manager, WorkPlan> {
	
	@Autowired
	protected ManagerWorkPlanRepository repository;

	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "startTime", "endTime");
	}

	@Override
	public WorkPlan instantiate(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;

		Manager manager;
		manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		
		result = new WorkPlan();
		result.setStartTime(this.repository.findMinimumStartTime());
		result.setEndTime(this.repository.findMaximumEndTime());
		
		result.setManager(manager);
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
	public void create(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
//		entity.set

		this.repository.save(entity);
		
		
	}

	@Override
	public void onSuccess(final Request<WorkPlan> request, final Response<WorkPlan> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}

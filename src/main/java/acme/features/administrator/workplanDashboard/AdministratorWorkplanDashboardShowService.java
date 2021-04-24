
package acme.features.administrator.workplanDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.WorkPlanDashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;


@Service
public class AdministratorWorkplanDashboardShowService implements AbstractShowService<Administrator, WorkPlanDashboard> {

	@Autowired
	protected AdministratorWorkplanDashboardRepository repository;


	@Override
	public boolean authorise(final Request<WorkPlanDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkPlanDashboard> request, final WorkPlanDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfWorkplansPublic", "numberOfWorkplansPrivate", "numberOfWorkplansFinished", //
			"numberOfWorkplansUnfinished");
	}

	@Override
	public WorkPlanDashboard findOne(final Request<WorkPlanDashboard> request) {
		assert request != null;

		final WorkPlanDashboard result;

		// ------------------- WorkPlans -----------------------

		final Integer numberOfWorkPlansPublic;
		final Integer numberOfWorkPlansPrivate;
		final Integer numberOfWorkPlansFinished;
		final Integer numberOfWorkPlansUnfinished;

		numberOfWorkPlansPublic = this.repository.numberOfWorkPlansPublic();
		numberOfWorkPlansPrivate = this.repository.numberOfWorkPlansPrivate();
		numberOfWorkPlansFinished = this.repository.numberOfWorkPlansFinished();
		numberOfWorkPlansUnfinished = this.repository.numberOfWorkPlansUnfinished();
		
		result = new WorkPlanDashboard();
		result.setNumberOfWorkPlansPublic(numberOfWorkPlansPublic);
		result.setNumberOfWorkPlansPrivate(numberOfWorkPlansPrivate);
		result.setNumberOfWorkPlansFinished(numberOfWorkPlansFinished);
		result.setNumberOfWorkPlansUnfinished(numberOfWorkPlansUnfinished);

		
		return result;
	}
}

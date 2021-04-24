package acme.features.administrator.workplanDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.forms.WorkPlanDashboard;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/workplandashboard/")
public class AdministratorWorkplanDashboardController extends AbstractController<Administrator, WorkPlanDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorWorkplanDashboardShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}

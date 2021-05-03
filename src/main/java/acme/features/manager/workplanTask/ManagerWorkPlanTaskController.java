package acme.features.manager.workplanTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/task-work-plan/")
public class ManagerWorkPlanTaskController extends AbstractController<Manager, Task> {

	// Internal state ---------------------------------------------------------

	
	@Autowired
	protected ManagerWorkPlanTaskListService	listService;

	

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		
	}


}

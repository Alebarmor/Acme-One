package acme.features.manager.workPlans;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository{

	@Query("select wp from WorkPlan wp")
	Collection<WorkPlan> findMany();
	
	@Query("select wp from WorkPlan wp where wp.manager.id = ?1")
	Collection<WorkPlan> findManyWorkPlanByManagerId(int id); 
	
	@Query("select wp from WorkPlan wp where wp.id = ?1")
	WorkPlan findOneWorkPlanById(int id);
	
	@Query("select min(t.startTime) from Task t")
	Date findMinimumStartTime();
	
	@Query("select max(t.endTime) from Task t")
	Date findMaximumEndTime();
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
	@Query("select t from Task t where t.manager.id = ?1")
	Collection<Task> findManyTaskByManager(int id);
	
//	@Query("select wp from WorkPlan wp where wp.isPublic = TRUE")
//	Collection<Task> findManyWorkPlansByIsPublicTrue();
//	@Query("select c from Configuration c")
//	Collection<Configuration> getConfiguration();
	
}

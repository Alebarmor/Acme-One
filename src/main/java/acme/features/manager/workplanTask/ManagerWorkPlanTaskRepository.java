package acme.features.manager.workplanTask;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.manager.id = ?1")
	Collection<Task> findManyTaskByManager(int id);
	
//	@Query("select wp from WorkPlan wp where wp.isPublic = TRUE")
//	Collection<Task> findManyWorkPlansByIsPublicTrue();
//	@Query("select c from Configuration c")
//	Collection<Configuration> getConfiguration();
	
}

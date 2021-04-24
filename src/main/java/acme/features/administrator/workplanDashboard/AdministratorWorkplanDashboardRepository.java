package acme.features.administrator.workplanDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWorkplanDashboardRepository extends AbstractRepository {
	
	@Query("select count(w) from WorkPlan w where w.isPublic = true")
	Integer numberOfWorkPlansPublic();
	
	@Query("select count(w) from WorkPlan w where w.isPublic = false")
	Integer numberOfWorkPlansPrivate();
	
	@Query("select count(w) from WorkPlan w where w.endTime < CURRENT_TIMESTAMP")
	Integer numberOfWorkPlansFinished();
		
	@Query("select count(w) from WorkPlan w where w.endTime > CURRENT_TIMESTAMP")
	Integer numberOfWorkPlansUnfinished();
}

/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerWorker", // 
			"avegageNumberOfApplicationsPerEmployer", "ratioOfPendingApplications", //
			"ratioOfRejectedApplications", "ratioOfAcceptedApplications", //
			"numberOfTasksPublic", "numberOfTasksPrivate", "numberOfTasksFinished", //
			"numberOfTasksUnfinished", "averageWorkload", "deviationWorkload", //
			"maximumWorkload", "minimumWorkload", "averageExecutionPeriod", //
			"deviationExecutionPeriod", "maximumExecutionPeriod", "minimumExecutionPeriod", //
			"numberOfWorkPlans",
			"numberOfWorkPlansPublic", "numberOfWorkPlansPrivate", "numberOfWorkPlansFinished", 
			"numberOfWorkPlansUnfinished", //
			"averageWorkPlansExecutionPeriod", "deviationWorkPlansExecutionPeriod",
			"maximumWorkPlansExecutionPeriod","minimumWorkPlansExecutionPeriod", //
			"averageWorkPlansWorkload",  "deviationWorkPlansWorkload",
			"maximumWorkPlansWorkload", "minimumWorkPlansWorkload");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Double averageNumberOfApplicationsPerEmployer;
		Double averageNumberOfApplicationsPerWorker;
		Double averageNumberOfJobsPerEmployer;
		Double ratioOfPendingApplications;
		Double ratioOfAcceptedApplications;
		Double ratioOfRejectedApplications;

		averageNumberOfApplicationsPerEmployer = this.repository.averageNumberOfApplicationsPerEmployer();
		averageNumberOfApplicationsPerWorker = this.repository.averageNumberOfApplicationsPerWorker();
		averageNumberOfJobsPerEmployer = this.repository.averageNumberOfJobsPerEmployer();
		ratioOfPendingApplications = this.repository.ratioOfPendingApplications();
		ratioOfAcceptedApplications = this.repository.ratioOfAcceptedApplications();
		ratioOfRejectedApplications = this.repository.ratioOfRejectedApplications();

		result = new Dashboard();
		result.setAvegageNumberOfApplicationsPerEmployer(averageNumberOfApplicationsPerEmployer);
		result.setAverageNumberOfApplicationsPerWorker(averageNumberOfApplicationsPerWorker);
		result.setAverageNumberOfJobsPerEmployer(averageNumberOfJobsPerEmployer);
		result.setRatioOfPendingApplications(ratioOfPendingApplications);
		result.setRatioOfAcceptedApplications(ratioOfAcceptedApplications);
		result.setRatioOfRejectedApplications(ratioOfRejectedApplications);

		// ------------------- Task -----------------------

		final Integer numberOfTasksPublic;
		final Integer numberOfTasksPrivate;
		final Integer numberOfTasksFinished;
		final Integer numberOfTasksUnfinished;

		numberOfTasksPublic = this.repository.numberOfTasksPublic();
		numberOfTasksPrivate = this.repository.numberOfTasksPrivate();
		numberOfTasksFinished = this.repository.numberOfTasksFinished();
		numberOfTasksUnfinished = this.repository.numberOfTasksUnfinished();

		result.setNumberOfTasksPublic(numberOfTasksPublic);
		result.setNumberOfTasksPrivate(numberOfTasksPrivate);
		result.setNumberOfTasksFinished(numberOfTasksFinished);
		result.setNumberOfTasksUnfinished(numberOfTasksUnfinished);

		// ------------------- Task Stats -----------------------

		final Double averageWorkload;
		final Double deviationWorkload;
		final Double maximumWorkload;
		final Double minimumWorkload;

		final List<Double> wl = new ArrayList<Double>();

		for (final Task t : this.repository.findMany()) {
			wl.add(t.getWorkload());
		}

		Double n = 0.0;
		Double stddev = 0.0;

		for (final double d : wl) {
			n += d;
		}

		averageWorkload = n / wl.size();

		for (final double d : wl) {
			stddev += Math.pow(d - averageWorkload, 2);
		}

		deviationWorkload = Math.sqrt(stddev / wl.size());
		minimumWorkload = wl.stream().min(Comparator.naturalOrder()).get();
		maximumWorkload = wl.stream().max(Comparator.naturalOrder()).get();

		result.setAverageWorkload(averageWorkload);
		result.setDeviationWorkload(deviationWorkload);
		result.setMaximumWorkload(maximumWorkload);
		result.setMinimumWorkload(minimumWorkload);

		// ------------------- Execution Period Stats -----------------------

		final Double averageExecutionPeriod;
		final Double deviationExecutionPeriod;
		final Double maximumExecutionPeriod;
		final Double minimumExecutionPeriod;

		final List<Double> days = new ArrayList<Double>();

		for (final Task t : this.repository.findMany()) {
			days.add(t.getDays());
		}

		Double i = 0.0;

		for (final double d : days) {
			i += d;
		}

		averageExecutionPeriod = i / days.size();

		for (final double d : days) {
			stddev += Math.pow(d - averageExecutionPeriod, 2);
		}

		deviationExecutionPeriod = Math.sqrt(stddev / days.size());
		minimumExecutionPeriod = days.stream().min(Comparator.naturalOrder()).get();
		maximumExecutionPeriod = days.stream().max(Comparator.naturalOrder()).get();

		result.setAverageExecutionPeriod(averageExecutionPeriod);
		result.setDeviationExecutionPeriod(deviationExecutionPeriod);
		result.setMinimumExecutionPeriod(minimumExecutionPeriod);
		result.setMaximumExecutionPeriod(maximumExecutionPeriod);

		// ------------------- WorkPlans -----------------------

		final Integer numberOfWorkPlans;
		final Integer numberOfWorkPlansPublic;
		final Integer numberOfWorkPlansPrivate;
		final Integer numberOfWorkPlansFinished;
		final Integer numberOfWorkPlansUnfinished;

		numberOfWorkPlans = this.repository.numberOfWorkPlans();
		numberOfWorkPlansPublic = this.repository.numberOfWorkPlansPublic();
		numberOfWorkPlansPrivate = this.repository.numberOfWorkPlansPrivate();
		numberOfWorkPlansFinished = this.repository.numberOfWorkPlansFinished();
		numberOfWorkPlansUnfinished = this.repository.numberOfWorkPlansUnfinished();

		result.setNumberOfWorkPlans(numberOfWorkPlans);
		result.setNumberOfWorkPlansPublic(numberOfWorkPlansPublic);
		result.setNumberOfWorkPlansPrivate(numberOfWorkPlansPrivate);
		result.setNumberOfWorkPlansFinished(numberOfWorkPlansFinished);
		result.setNumberOfWorkPlansUnfinished(numberOfWorkPlansUnfinished);

		// ------------------- WorkPlans Stats -----------------------
		
		final Double averageWorkPlansWorkload;
		final Double deviationWorkPlansWorkload;
		final Double maximumWorkPlansWorkload;
		final Double minimumWorkPlansWorkload;

		final List<Double> wlwp = new ArrayList<Double>();

		for (final WorkPlan wp : this.repository.findManyWorkPlans()) {
			wlwp.add(wp.getWorkload());
		}

		Double nwp = 0.0;
		Double stddevwp = 0.0;

		for (final double d : wlwp) {
			nwp += d;
		}

		averageWorkPlansWorkload = nwp / wlwp.size();

		for (final double d : wlwp) {
			stddevwp += Math.pow(d - averageWorkload, 2);
		}

		deviationWorkPlansWorkload = Math.sqrt(stddevwp / wlwp.size());
		minimumWorkPlansWorkload = wlwp.stream().min(Comparator.naturalOrder()).get();
		maximumWorkPlansWorkload = wlwp.stream().max(Comparator.naturalOrder()).get();
		
		result.setAverageWorkPlansWorkload(averageWorkPlansWorkload);
		result.setDeviationWorkPlansWorkload(deviationWorkPlansWorkload);
		result.setMaximumWorkPlansWorkload(maximumWorkPlansWorkload);
		result.setMinimumWorkPlansWorkload(minimumWorkPlansWorkload);
		
		
		
		// ------------------- Execution Period Workplan Stats -----------------------
		final Double averageWorkPlansExecutionPeriod;
		final Double deviationWorkPlansExecutionPeriod;
		final Double maximumWorkPlansExecutionPeriod;
		final Double minimumWorkPlansExecutionPeriod;
		
		final List<Double> wpdays = new ArrayList<Double>();

		for (final WorkPlan wp : this.repository.findManyWorkPlans()) {
			wpdays.add(wp.getDays());
		}
		
		Double j = 0.0;

		for (final double d : wpdays) {
			j += d;
		}

		averageWorkPlansExecutionPeriod = j / wpdays.size();
		
		for (final double d : wpdays) {
			stddevwp += Math.pow(d - averageWorkPlansExecutionPeriod, 2);
		}
		
		deviationWorkPlansExecutionPeriod = Math.sqrt(stddevwp / wpdays.size());
		minimumWorkPlansExecutionPeriod = wpdays.stream().min(Comparator.naturalOrder()).get();
		maximumWorkPlansExecutionPeriod = wpdays.stream().max(Comparator.naturalOrder()).get();
		
		result.setAverageWorkPlansExecutionPeriod(averageWorkPlansExecutionPeriod);
		result.setDeviationWorkPlansExecutionPeriod(deviationWorkPlansExecutionPeriod);
		result.setMinimumWorkPlansExecutionPeriod(minimumWorkPlansExecutionPeriod);
		result.setMaximumWorkPlansExecutionPeriod(maximumWorkPlansExecutionPeriod);
	
		return result;
	}
	
	

}

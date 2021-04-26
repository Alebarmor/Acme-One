/*
 * AnonymousShoutRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.shout;

import java.lang.module.Configuration;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.entities.Anonymous;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {

	
	@Query("select t from Shout t")
	Collection<Shout> findMany();
	
	@Query("select t from Shout t where t.manager.id = ?1")
	Collection<Shout> findManyShoutByAnonymous(int id);
	
	@Query("select m from Anonymous m where m.id = ?1")
	Anonymous findOneAnnonymousById(int id);

	@Query("select t from Task t where t.id = ?1")
	Shout findOneShoutById(int id);
	
	@Query("select c from Configuration c")
	Collection<Configuration> getConfiguration();
	

}

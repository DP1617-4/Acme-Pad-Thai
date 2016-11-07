package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contest;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer> {

	@Query ("select min(c.qualified.size), avg(c.qualified.size)," +
			" max(c.qualified.size) from Contest c")
	Collection<Integer> selectMinAvgMaxRecipesInContests();
	
	@Query ("select c from Contest c where c.qualified.size = (select max(c.qualified.size) from Contest c)")
	Contest selectContestMostRecipes();
}

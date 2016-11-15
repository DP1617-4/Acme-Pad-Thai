package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contest;
import domain.Recipe;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer>{
	
	@Query("select min(c.qualified.size), avg(c.qualified.size), max(c.qualified.size) from Contest c")
	Double[] getMinAvgMaxRecipesQualifiedForContest();
	
	@Query("select c from Contest c where c.qualified.size = (select max(c.qualified.size) from Contest c)")
	Contest getContestWithMoreRecipesQualified();
	
	//This method returns the recipes qualified for a certain contest that have a score ordered in descending order
	//This needs an auxiliary method in the service, maybe another query
	@Query("select c.qualified from Contest c left join c.qualified q left join q.scores s where c.id = ?1 order by sum(case when s.likes = 1 then 1 else 0 end) DESC ")
	Collection<Recipe> findBestRecipes(int contestId); 

}

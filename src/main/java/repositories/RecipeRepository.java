package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recipe;
import domain.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	
	@Query("select avg(r.steps.size), stddev(r.steps.size) from Recipe r")
	Collection<Double> getAvgStdStepsPerRecipe();
	
	@Query("select avg(r.quantities.size), stddev(r.quantities.size) from Recipe r")
	Collection<Double> getAvgStdIngredientsPerRecipe();
	
	@Query("select r.user from Recipe r group by r.user Order By avg(r.scores.size) DESC")
	Collection<User> getUsersByAvgOfLikesAndDislikesOfRecipe();
	
	

}

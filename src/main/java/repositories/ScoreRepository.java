package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recipe;
import domain.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer>{

	@Query("select s from Score s where s.recipe.id = ?1")
	Collection<Recipe> findAllByRecipe(int recipeId);

	
	@Query("Select s from Score s where s.socialUser.id = ?1")
	Collection<Recipe> findAllBySocialUser(int socialUserId);

}

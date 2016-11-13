package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query ("select min(u.recipes.size), avg(u.recipes.size), max(u.recipes.size) from User u")
	Collection<Integer> selectMinAvgMaxRecipesInUsers();
	
	@Query ("select u from User u where u.recipes.size = (select max(u.recipes.size) from User u)")
	User selectUserWithMostRecipes();
	
	@Query ("select u from User u Order BY u.followers.size DESC")
	Collection<User> selectAllUsersDescendingNumberOfFollowers();
	
	@Query("select u from User u where u.userAccount.id = ?1")
	User findOneByUserAccountId(int userAccountId);
	
	
}

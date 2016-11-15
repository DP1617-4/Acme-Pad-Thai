package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Nutritionist;
import domain.SocialUser;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {

	@Query("select u from Nutritionist u where u.userAccount.id = ?1")
	Nutritionist findOneByUserAccountId(int userAccountId);
}

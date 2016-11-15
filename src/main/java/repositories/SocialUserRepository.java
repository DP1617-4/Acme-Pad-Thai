package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialUser;
import domain.User;

@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser, Integer> {
	

	@Query("select u from SocialUser u where u.userAccount.id = ?1")
	SocialUser findOneByUserAccountId(int userAccountId);
}

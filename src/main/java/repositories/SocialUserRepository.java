package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SocialUser;

@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser, Integer> {

}

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer> {

	@Query("select si from SocialIdentity si where si.actor.id = ?1")
	Collection<SocialIdentity> findAllByActor(int id);
}

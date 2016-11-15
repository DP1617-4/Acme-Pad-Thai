package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cook;

@Repository
public interface CookRepository extends JpaRepository<Cook, Integer> {

	@Query("select min(c.masterClasses.size), max(c.masterClasses.size), avg(c.masterClasses.size), stddev(c.masterClasses.size) from Cook c")
	Collection<Double> calculateMinMaxAvgDevFromMasterClassesOfCooks();
	
	@Query("select c from Cook c where c.userAccount.id = ?1")
	Cook findByUserAccountId(int id);

}

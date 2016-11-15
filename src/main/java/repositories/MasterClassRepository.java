package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Cook;
import domain.MasterClass;

@Repository
public interface MasterClassRepository extends JpaRepository<MasterClass, Integer> {

	@Query("select count(m) from MasterClass m where m.promoted = true")
	Long countNumberPromotedMasterClasses();
	
	@Query("select r.cook from MasterClass r group by r.cook order by count(r.promoted) DESC")
	Collection<Cook> findCooksOrderByPromotedMasterClasses();
	
	@Query("select count(mcp)*1.0/(select count(c) from Cook c), (select count(mcd) from MasterClass mcd where mcd.promoted=false)*1.0/(select count(c) from Cook c) from MasterClass mcp where mcp.promoted=true")
	Double[][] calculateAvgPromotedAndDemotedMasterClassesPerCook();
	
	@Query("select count(l)*1.0/(select count(m) from MasterClass m) from LearningMaterial l group by l.class")
	Double calculateAvgLearningMaterialsPerMasterClass();

	@Query("select mc from MasterClass mc where mc.cook.id = ?1 and mc.deleted = FALSE")
	Collection<MasterClass> findAllByCookId(int cookId);

	@Query("select a.enroled from Actor a where a.id = ?1")
	Collection<MasterClass> findMasterClassesByActor (int id);
	
}

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.TextMaterial;

@Repository
public interface TextMaterialRepository extends JpaRepository<TextMaterial, Integer> {
	
	@Query("select tm from TextMaterial tm where tm.masterClass.id = ?1")
	Collection<TextMaterial> findAllByMasterClassId(int masterClassId);
}

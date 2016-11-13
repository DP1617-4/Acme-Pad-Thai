package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PresentationMaterial;

@Repository
public interface PresentationMaterialRepository extends JpaRepository<PresentationMaterial, Integer> {

	@Query("select pm from PresentationMaterial pm where pm.masterClass.id = ?1")
	Collection<PresentationMaterial> findAllByMasterClassId(int masterClassId);
	
}

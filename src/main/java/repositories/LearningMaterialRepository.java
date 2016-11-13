package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LearningMaterial;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {
	
	@Query("select lm from LearningMaterial lm where lm.masterClass.id = ?1")
	Collection<LearningMaterial> findAllByMasterClass(int id);

}

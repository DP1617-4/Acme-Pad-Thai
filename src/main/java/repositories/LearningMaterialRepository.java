package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LearningMaterial;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {


	
}

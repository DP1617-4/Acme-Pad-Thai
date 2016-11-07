package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PresentationMaterial;

@Repository
public interface PresentationMaterialRepository extends JpaRepository<PresentationMaterial, Integer> {

}

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.VideoMaterial;

@Repository
public interface VideoMaterialRepository extends JpaRepository<VideoMaterial, Integer> {

	@Query("select vm from VideoMaterial vm where vm.masterClass.id = ?1")
	Collection<VideoMaterial> findAllByMasterClassId(int masterClassId);
	
}

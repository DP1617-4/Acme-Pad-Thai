package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.VideoMaterial;

@Repository
public interface VideoMaterialRepository extends JpaRepository<VideoMaterial, Integer> {

}

package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PresentationMaterialRepository;
import repositories.TextMaterialRepository;
import repositories.VideoMaterialRepository;
import domain.LearningMaterial;

@Service
@Transactional
public class LearningMaterialService {
	
	@Autowired
	private TextMaterialRepository textMaterialRepository;
	

	@Autowired
	private PresentationMaterialRepository presentationMaterialReposiotry;
	

	@Autowired
	private VideoMaterialRepository videoMaterialRepository;

	//Other Methods --------------------------------------------------------
	
	public Collection<LearningMaterial> findAllByMasterClass(int masterClassId){
		//TODO check loged user is an actor
		Collection<LearningMaterial> learningMaterial = new ArrayList<LearningMaterial>();;
		learningMaterial.addAll(textMaterialRepository.findAllByMasterClassId(masterClassId));
		learningMaterial.addAll(presentationMaterialReposiotry.findAllByMasterClassId(masterClassId));
		learningMaterial.addAll(videoMaterialRepository.findAllByMasterClassId(masterClassId));

		return learningMaterial;
	}

}

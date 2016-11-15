package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.LearningMaterialRepository;
import domain.LearningMaterial;

@Service
@Transactional
public class LearningMaterialService {
	
	//Constructor
	
	public LearningMaterialService(){
		super();
	}
	//ManagedRepositories
	@Autowired
	private LearningMaterialRepository learningMaterialRepository;
	
	//AuxiliaryServices
	
	@Autowired
	private MasterClassService masterClassService;
	
	

	//Other Methods --------------------------------------------------------
	
	public Collection<LearningMaterial> findAllByMasterClass(int masterClassId){

		Collection<LearningMaterial> learningMaterial = new ArrayList<LearningMaterial>();;
		learningMaterial = learningMaterialRepository.findAllByMasterClass(masterClassId); 
		
		return learningMaterial;
	}
	

}

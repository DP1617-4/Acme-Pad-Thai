package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Cook;
import domain.MasterClass;
import domain.PresentationMaterial;

import repositories.PresentationMaterialRepository;

@Service
@Transactional
public class PresentationMaterialService {
	
	//Constructor
	public PresentationMaterialService(){
		super();
	}
	
	//Managed Repository
	@Autowired
	private PresentationMaterialRepository presentationMaterialRepository;
	
	//Auxiliary Services
	@Autowired
	private CookService cookService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	//CRUD
	public PresentationMaterial create(MasterClass masterClass){
		PresentationMaterial result = new PresentationMaterial();
		result.setMasterClass(masterClass);
		return result;
	}
	
	public PresentationMaterial findOne(int id){
		PresentationMaterial result;
		result = presentationMaterialRepository.findOne(id);
		Assert.notNull(result);
		checkPrincipal(result);
		return result;
	}
	
	public Collection<PresentationMaterial> findByMasterClass(int id){
		Collection<PresentationMaterial> result;
		masterClassService.checkEnrolled(id);
		result = presentationMaterialRepository.findAllByMasterClassId(id);
		Assert.notNull(result);
		return result;
	}
	
	public PresentationMaterial save(PresentationMaterial presentationMaterial){
		PresentationMaterial result;
		Assert.notNull(presentationMaterial);
		checkPrincipal(presentationMaterial);
		result = presentationMaterialRepository.save(presentationMaterial);
		return result;
	}
	
	public void delete(PresentationMaterial presentationMaterial){
		Assert.notNull(presentationMaterial);
		checkPrincipal(presentationMaterial);
		presentationMaterialRepository.delete(presentationMaterial);
	}
	//Business methods
	
	public void checkPrincipal(PresentationMaterial presentationMaterial){
		Cook cook;
		cook = cookService.findByPrincipal();
//		Assert.isTrue(presentationMaterial.getMasterClass().getCook().equals(cook), "Dear user, you must be the teacher of the master class to edit its materials.");
	}
}

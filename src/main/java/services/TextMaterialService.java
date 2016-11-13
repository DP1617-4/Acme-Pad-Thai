package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Cook;
import domain.MasterClass;
import domain.TextMaterial;

import repositories.TextMaterialRepository;

@Service
@Transactional
public class TextMaterialService {

	
	//Constructor
	
	public TextMaterialService(){
		super();
	}
	

	//Managed Repository
	@Autowired
	private TextMaterialRepository textMaterialRepository;
	
	//Auxiliary Services
	@Autowired
	private CookService cookService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	
	//CRUD
	
	public TextMaterial create(MasterClass masterClass){
		TextMaterial result = new TextMaterial();
		result.setMasterClass(masterClass);
		return result;
	}
	
	public TextMaterial findOne(int id){
		TextMaterial result;
		result = textMaterialRepository.findOne(id);
		Assert.notNull(result);
		checkPrincipal(result);
		return result;
	}
	
	public Collection<TextMaterial> findByMasterClass(int id){
		Collection<TextMaterial> result;
		masterClassService.checkEnrolled(id);
		result = textMaterialRepository.findAllByMasterClassId(id);
		Assert.notNull(result);
		return result;
	}
	
	public TextMaterial save(TextMaterial textMaterial){
		TextMaterial result;
		Assert.notNull(textMaterial);
		checkPrincipal(textMaterial);
		result = textMaterialRepository.save(textMaterial);
		return result;
	}
	
	public void delete(TextMaterial textMaterial){
		Assert.notNull(textMaterial);
		checkPrincipal(textMaterial);
		textMaterialRepository.delete(textMaterial);
	}
	//Business methods
	
	public void checkPrincipal(TextMaterial textMaterial){
		Cook cook;
		cook = cookService.findByPrincipal();
		Assert.isTrue(textMaterial.getMasterClass().getCook().equals(cook), "Dear user, you must be the teacher of the master class to edit its materials.");
	}
	
}

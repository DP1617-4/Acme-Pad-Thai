package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Cook;
import domain.LearningMaterial;
import domain.MasterClass;
import repositories.MasterClassRepository;

@Service
@Transactional
public class MasterClassService {
	
	@Autowired
	private MasterClassRepository masterClassRepository;
	
	@Autowired
	private CookService cookService;
	
	//TODO Enviar mensajes a los actores
	/*
	@Autowired
	private MessageService messageService;
	*/
	
	//TODO Comprobar administrador creando cook
	/*
	@Autowired
	private AdministratorService adminsitratorService;
	*/
	
	// Simple CRUD Methods -------------------------------------------------
	
	public MasterClass create(){
		Cook cook = cookService.findOneByPrincipal();
		Assert.notNull(cook,"Dear user, you are not a cook.");
		MasterClass masterClass = new MasterClass();
		masterClass.setActors(new ArrayList<Actor>());
		masterClass.setCook(cook);
		masterClass.setDeleted(false);
		masterClass.setLearningMaterials(new ArrayList<LearningMaterial>());
		masterClass.setPromoted(false);
		
		return masterClass;
	}
	
	public MasterClass findOneToEdit(int masterClassId){
		Assert.isTrue(masterClassId>0,"Dear user, you cannot edit that Master Class");
		Cook cook = cookService.findOneByPrincipal();
		Assert.notNull(cook,"Dear user, you are not a cook.");
		MasterClass masterClass = masterClassRepository.findOne(masterClassId);
		Assert.notNull(masterClass,"Dear user, the Master Class you requested does not exist");
		
		return masterClass;
	}
	
	public void save(MasterClass masterClass){
		Cook cook = cookService.findOneByPrincipal();
		Assert.notNull(cook,"Dear user, you are not a cook.");
		Assert.notNull(masterClass,"Dear user, the Master Class you requested to save does not exist");
		
		masterClassRepository.save(masterClass);
		
	}
	
	public void delete(MasterClass masterClass){
		Cook cook = cookService.findOneByPrincipal();
		Assert.notNull(cook,"Dear user, you are not a cook.");
		Assert.notNull(masterClass,"Dear user, the Master Class you requested to delete does not exist");
		Assert.isTrue(masterClass.getId()>0,"Dear user, you cannot delete that Master Class");
		masterClass.setDeleted(true);
		//TODO Enviar mensajes a los actores
		/*
		for(Actor a:masterClass.getActors()){
			messageService.create(a);
		}
		*/
		masterClassRepository.save(masterClass);
	}

	//Other Methods --------------------------------------------------------
	
	public Collection<MasterClass> listAll(){
		Collection<MasterClass> masterClasses;
		masterClasses = masterClassRepository.findAll();
		return masterClasses;	
	}
	
	public Collection<MasterClass> listAllByCookId(){
		Cook cook = cookService.findOneByPrincipal();
		Assert.notNull(cook,"You must be a cook to list these Master Classes");
		Collection<MasterClass> masterClasses;
		masterClasses = masterClassRepository.findAllByCookId(cook.getId());
		return masterClasses;	
	}
	
	public void promoteOrDemoteMasterClass(MasterClass masterClass){
		//TODO Comprobar administrador cambiando masterClass
		/*
		Administrator administrator = administratorService.findOneByPrincipal();
		Assert.notNull(administrator); 
		*/
		if(masterClass.getPromoted()){
			masterClass.setPromoted(false);
		}else{
			masterClass.setPromoted(true);
		}
		masterClassRepository.save(masterClass);
	}
	
	public Long countNumberPromotedMasterClasses(){
		//TODO Comprobar administrador cambiando masterClass
		/*
		Administrator administrator = administratorService.findOneByPrincipal();
		Assert.notNull(administrator); 
		*/
		return masterClassRepository.countNumberPromotedMasterClasses();
	}
	
	public Collection<Cook> findCooksOrderByPromotedMasterClasses(){
		//TODO Comprobar administrador cambiando masterClass
		/*
		Administrator administrator = administratorService.findOneByPrincipal();
		Assert.notNull(administrator); 
		*/
		return masterClassRepository.findCooksOrderByPromotedMasterClasses();
	}
	
	public Collection<Double> calculateAvgPromotedAndDemotedMasterClassesPerCook(){
		//TODO Comprobar administrador cambiando masterClass
		/*
		Administrator administrator = administratorService.findOneByPrincipal();
		Assert.notNull(administrator); 
		*/
		return masterClassRepository.calculateAvgPromotedAndDemotedMasterClassesPerCook();
	}

	public Double calculateAvgLearningMaterialsPerMasterClass(){
		//TODO Comprobar administrador cambiando masterClass
		/*
		Administrator administrator = administratorService.findOneByPrincipal();
		Assert.notNull(administrator); 
		*/
		return masterClassRepository.calculateAvgLearningMaterialsPerMasterClass();
	}
	
}

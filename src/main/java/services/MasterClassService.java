package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Cook;
import domain.MasterClass;

import repositories.MasterClassRepository;

@Service
@Transactional
public class MasterClassService {

	//Constructor
	public MasterClassService(){
		super();
	}
	
	//Managed Repository
	@Autowired
	private MasterClassRepository masterClassRepository;
	
	//Auxiliary Services
	
	@Autowired
	private CookService cookService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService adminService;
	
	//CRUD
	
	public MasterClass create(Cook cook){
		MasterClass result = new MasterClass();
		result.setCook(cook);
		result.setDeleted(false);
		result.setPromoted(false);
		return result;
	}
	
	public MasterClass findOneToEdit(int id){
		MasterClass result;
		result = masterClassRepository.findOne(id);
		Assert.notNull(result);
		checkPrincipalCook(result);
		return result;
	}
	
	public MasterClass save(MasterClass masterClass){
		MasterClass result;
		checkPrincipalCook(masterClass);
		result = masterClassRepository.save(masterClass);
		return result;
	}
	
	public void delete(MasterClass masterClass){
		checkPrincipalCook(masterClass);
		masterClass.setDeleted(true);
		masterClassRepository.save(masterClass);
	}
	
	public Collection<MasterClass> findAll(){
		Collection<MasterClass> result;
		result = masterClassRepository.findAll();
		return result;
	}
	
	public Collection<MasterClass> findEnrolledByPrincipal(){
		Actor actor;
		Collection<MasterClass> result;
		actor = actorService.findByPrincipal();
		result = masterClassRepository.findMasterClassesByActor(actor.getId());
		
		return result;
	}
	
	public Collection<MasterClass> findImpartedByPrincipal(){
		Cook cook;
		Collection<MasterClass> result;
		cook = cookService.findByPrincipal();
		result = masterClassRepository.findImpartedClasses(cook.getId());
	}
	//Business Methods
	

	public void promoteDemote(MasterClass masterClass){
		adminService.checkPrincipalAdmin();
		masterClass.setPromoted(!masterClass.getPromoted());
		masterClassRepository.save(masterClass);
	}
	
	
	public void checkPrincipalCook(MasterClass masterClass){
		Cook cook;
		cook = cookService.findByPrincipal();
		Assert.isTrue(masterClass.getCook().equals(cook));
	}
	
	
	public Long countNumberPromotedMasterClasses(){
		Long result;
		result = masterClassRepository.countNumberPromotedMasterClasses();
	}
	
	public	Collection<Cook> findCooksOrderByPromotedMasterClasses(){
		Collection<Cook> result;
		result = masterClassRepository.findCooksOrderByPromotedMasterClasses();
	}
	
	public Collection<Double> calculateAvgPromotedAndDemotedMasterClassesPerCook(){
		Collection<Double> result;
		result = masterClassRepository.calculateAvgPromotedAndDemotedMasterClassesPerCook();
	}
	
	public Double calculateAvgLearningMaterialsPerMasterClass(){
		Double result;
		result = masterClassRepository.calculateAvgLearningMaterialsPerMasterClass();
	}

}

package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.MasterClass;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {

	
	//Constructor
	public ActorService(){
		super();
	}
	
	//Managed Repository
	
	@Autowired
	private ActorRepository actorRepository;
	
	//Auxiliary Services
	
	@Autowired
	private LoginService loginService;
	
	//CRUD
	
	public Actor findByUserAccount(UserAccount userAccount){
		Actor result;
		result = actorRepository.findByUserAccount(userAccount.getId());
		
		return result;
	}
	
	public Actor findByPrincipal(){
		Actor result;
		UserAccount userAccount;
		
		userAccount = loginService.getPrincipal();
		result = findByUserAccount(userAccount);
		return result;
	}
	//Business Methods
	
	//Maybe in MasterClassService makes more sense, but it is mapped by "Actor" so...
	public void register (MasterClass masterClass){
		Actor actor;
		actor = findByPrincipal();
		actor.getEnroled().add(masterClass);
		actorRepository.save(actor);
	}
}

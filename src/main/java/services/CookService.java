package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Cook;
import domain.Folder;

import repositories.CookRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CookService {

	
	//Constructor
	public CookService(){
		super();
	}
	
	//Managed Repository
	@Autowired
	private CookRepository cookRepository;
	
	//Auxiliary Services
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private FolderService folderService;
	
	//CRUD
	
	public Cook create(){
		Cook result = new Cook();
		result.setFolders(new ArrayList<Folder>());
		return result;
	}
	
	public Cook findOneToEdit(int id){
		Cook result;
		result = cookRepository.findOne(id);
		return result;
	}
	
	public Cook save(Cook cook){
		Cook result;
		
		result = cookRepository.save(cook);
		return result;
	}
	
	public void delete(Cook cook){
		cookRepository.delete(cook);
	}
	
	public Cook findByUserAccount(UserAccount userAccount){
		Cook result;
		result = cookRepository.findByUserAccountId(userAccount.getId());
		return result;
	}
	
	public Cook findByPrincipal(){
		Cook result;
		UserAccount userAccount;
		userAccount = loginService.getPrincipal();
		result = findByUserAccount(userAccount);
		return result;
	}
	
	//Business Methods
	public Collection<Double> calculateMinMaxAvgDevFromMasterClassesOfCooks() {
		Collection<Double> result;
		result = cookRepository.calculateMinMaxAvgDevFromMasterClassesOfCooks();
		return result;
	}

}

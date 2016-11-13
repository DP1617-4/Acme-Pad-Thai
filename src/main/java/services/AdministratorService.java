package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	
	//Constructor
	
	public AdministratorService(){
		super();
	}
	
	//Managed Repository
	@Autowired
	private AdministratorRepository adminRepository;
	
	//Auxiliary Services
	@Autowired
	private LoginService loginService;
	
	//CRUD
	
	public Administrator findSystem(){
		Administrator result;
		result = adminRepository.findSystem();
		return result;
	}
	
	
	//Business Methods
	
	public void checkAdministrator(){
		UserAccount userAccount;
		userAccount = loginService.getPrincipal();
		Boolean checker = false;
		userAccount = LoginService.getPrincipal();
		for(Authority a: userAccount.getAuthorities()){
			if(a.getAuthority().equals(Authority.ADMIN)){
				checker = true;
				break;
			}
		}
		Assert.isTrue(checker); 
	}
}
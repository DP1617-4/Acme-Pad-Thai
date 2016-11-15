package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Endorser;
import domain.Nutritionist;

@Service
@Transactional
public class EndorserService {
	
	//managed repository-------------------
		@Autowired
		private EndorserRepository endorserRepository;
		
		//supporting services-------------------
		@Autowired
		private NutritionistService nutritionistService;
		
		
		//Basic CRUD methods-------------------
		
		public Endorser create(){
			
			Endorser created;
			created = new Endorser();
			created.setCurricula(nutritionistService.findByPrincipal().getCurricula());
			return created;
		}
		
		public Endorser findOne(int endorserId){
			
			Endorser retrieved;
			retrieved = endorserRepository.findOne(endorserId);
			checkPrincipal(retrieved);
			return retrieved;
		}

		public Endorser save(Endorser endorser){
			
			checkPrincipal(endorser);
			Endorser saved = endorserRepository.save(endorser);
			
			
			return saved;
			
		}
		
		public void delete(Endorser endorser){
			
			checkPrincipal(endorser);
			endorserRepository.delete(endorser);
			
		}
		
		
		//Auxiliary methods

		public Boolean checkPrincipal(Endorser e){
			
			Boolean result = false;
			UserAccount nutritionistUser = e.getCurricula().getNutritionist().getUserAccount();
			UserAccount principal = LoginService.getPrincipal();
			if(nutritionistUser.equals(principal)){
				result = true;
			}
			return result;
			
		}
		//Our other bussiness methods
		

}

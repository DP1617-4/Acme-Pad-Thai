package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
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

		public void checkPrincipal(Endorser e){
			
			Nutritionist n = nutritionistService.findByPrincipal();
			Assert.isTrue(n.getCurricula().getEndorsers().contains(e));
			
		}
		//Our other bussiness methods
		

}

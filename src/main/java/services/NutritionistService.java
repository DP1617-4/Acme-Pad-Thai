package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.NutritionistRepository;
import security.LoginService;
import security.UserAccount;
import domain.Nutritionist;

@Service
@Transactional
public class NutritionistService {

	
	//managed repository-------------------
			@Autowired
			private NutritionistRepository nutritionistRepository;
			
			//supporting services-------------------
			@Autowired
			private LoginService loginService;
			
			//Basic CRUD methods-------------------
			
			public Nutritionist create(){
				
				Nutritionist created;
				created = new Nutritionist();
				return created;
			}
			
			public Nutritionist findOne(int nutritionistId){
				
				Nutritionist retrieved;
				retrieved = nutritionistRepository.findOne(nutritionistId);
				return retrieved;
			}
			
			public Nutritionist findByPrincipal(){
				
				Nutritionist nutritionist;
				UserAccount userAccount;
				
				userAccount = loginService.getPrincipal();
				nutritionist = findOne(userAccount.getId());
				
				return nutritionist;
			}

			public Nutritionist save(Nutritionist nutritionist){
				
				Nutritionist saved = nutritionistRepository.save(nutritionist);
				
				return saved;
				
			}
			
			public void delete(Nutritionist nutritionist){
				
				nutritionistRepository.delete(nutritionist);
				
			}
			
			//Auxiliary methods

			//Our other bussiness methods
			
			
			
}

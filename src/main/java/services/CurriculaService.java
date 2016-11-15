package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import security.LoginService;
import security.UserAccount;
import domain.Curricula;
import domain.Endorser;
import domain.Nutritionist;

@Service
@Transactional
public class CurriculaService {

	
	//managed repository-------------------
	@Autowired
	private CurriculaRepository curriculaRepository;
	
	//supporting services-------------------
	@Autowired
	private NutritionistService nutritionistService;
	
	//Basic CRUD methods-------------------
	
	public Curricula create(){
		
		Curricula created;
		created = new Curricula();
		created.setDeleted(false);
		created.setNutritionist(nutritionistService.findByPrincipal());	
		created.setEndorsers(new ArrayList<Endorser>());
		return created;
	}
	
	public Curricula findOne(int curriculaId){
		
		Curricula retrieved;
		retrieved = curriculaRepository.findOne(curriculaId);
		Assert.isTrue(checkPrincipal(retrieved));
		return retrieved;
	}

	public Curricula save(Curricula curricula){
		
		Assert.notNull(curricula);
		Curricula saved = curriculaRepository.save(curricula);
		nutritionistService.findByPrincipal().setCurricula(saved);
		Assert.isTrue(checkPrincipal(curricula));
		
		
		return saved;
		
	}
	
	public void delete(Curricula curricula){
		
		Assert.isTrue(checkPrincipal(curricula));
		curriculaRepository.delete(curricula);
		
	}
	
	//Auxiliary methods
	
	public Boolean checkPrincipal(Curricula c){
		
//		Nutritionist nutritionist = nutritionistService.findByPrincipal();
//		Assert.isTrue(c.equals(nutritionist.getCurricula()));
		
//		Boolean result = false;
//		UserAccount recipeUser = recipe.getUser().getUserAccount();
//		UserAccount principal = LoginService.getPrincipal();
//		if(recipeUser.equals(principal)){
//			
//			result = true;
//		}
//		
//		return result;
		
		Boolean result = false;
		UserAccount nutritionistUser = c.getNutritionist().getUserAccount();
		UserAccount principal = LoginService.getPrincipal();
		if(nutritionistUser.equals(principal)){
			result = true;
		}
		return result;
	}

	//Our other bussiness methods
	
	
}

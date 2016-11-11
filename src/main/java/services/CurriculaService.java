package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Curricula;
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
		return created;
	}
	
	public Curricula findOne(int curriculaId){
		
		Curricula retrieved;
		retrieved = curriculaRepository.findOne(curriculaId);
		checkPrincipal(retrieved);
		return retrieved;
	}

	public Curricula save(Curricula curricula){
		
		checkPrincipal(curricula);
		Curricula saved = curriculaRepository.save(curricula);
		
		
		return saved;
		
	}
	
	public void delete(Curricula curricula){
		
		checkPrincipal(curricula);
		curriculaRepository.delete(curricula);
		
	}
	
	//Auxiliary methods
	
	public void checkPrincipal(Curricula c){
		
		Nutritionist nutritionist = nutritionistService.findByPrincipal();
		Assert.isTrue(c.equals(nutritionist.getCurricula()));
	}

	//Our other bussiness methods
	
	
}

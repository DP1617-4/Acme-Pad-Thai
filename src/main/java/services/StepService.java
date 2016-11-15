package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.StepRepository;
import domain.Recipe;
import domain.Step;


@Service
@Transactional
public class StepService {

	//managed repository-------------------
		@Autowired
		private StepRepository stepRepository;
		
		//supporting services-------------------
//		@Autowired
//		private RecipeService recipeService;
		
		//Basic CRUD methods-------------------
		
		public Step create(Recipe recipe){
			
			Step created;
			created = new Step();
			created.setRecipe(recipe);
			return created;
		}
		
		public Step findOne(int stepId){
			
			Step retrieved;
			retrieved = stepRepository.findOne(stepId);
			return retrieved;
		}
		
		public Collection<Step> findAll(){
			
			return stepRepository.findAll();
		}

		public Step save(Step step){
			
			Step saved;
			saved = stepRepository.save(step);
			return saved;
			
		}
		
		public void delete(Step step){
			
			stepRepository.delete(step);
		}
		
		//Auxiliary methods

		//Our other bussiness methods
		
		public Step createCopy(Step step){
			
			Step copied = new Step();
			copied.setDescription(step.getDescription());
			copied.setPictures(step.getPictures());
			copied.setHints(step.getHints());
			copied.setStepNumber(step.getStepNumber());
			copied.setRecipe(step.getRecipe());
			Step copiedSaved = this.save(copied);
			return copiedSaved;
		}
	
}

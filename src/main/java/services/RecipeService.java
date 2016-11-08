package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Comment;
import domain.Quantity;
import domain.Recipe;
import domain.Step;
import domain.User;

import repositories.RecipeRepository;



@Service
@Transactional
public class RecipeService {
	
	//managed repository-------------------
	@Autowired
	private RecipeRepository recipeRepository;
	
	//supporting services-------------------
	@Autowired
//	private StepService stepService;
	
	//Basic CRUD methods-------------------
	
	public Recipe create(){
		
		Recipe created;
		Date moment = new Date(System.currentTimeMillis()-100);
		created = new Recipe();
		created.setAuthored(moment);
		created.setDeleted(false);
		return created;
	}
	
	public Recipe findOne(int recipeId){
		
		Recipe retrieved;
		retrieved = recipeRepository.findOne(recipeId);
		return retrieved;
	}

	public Recipe save(Recipe recipe){
		
		Recipe saved, toSave;
		toSave = recipe;
		Date moment = new Date(System.currentTimeMillis()-100);
		String ticker = this.createTicker();
		toSave.setUpdated(moment);
		toSave.setTicker(ticker);
		saved = recipeRepository.save(recipe);
		return saved;
		
	}
	
	public void delete(Recipe recipe){
		
		recipeRepository.delete(recipe);
		
	}
	
	//Auxiliary methods
	public char randomLetter(){
		char result;
		String alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
        result = alphabet.charAt(random.nextInt(52));
		return result;
	}
	
//	public Collection<Step> copyStepts(Recipe recipe){
//		Collection <Step> stepsToCopy = recipe.getSteps();
//		Collection <Step> result;
//		for(Step s : stepsToCopy){
//			
//			Step stepCreated = stepService.create();
//			stepCreated.setDescription(s.getDescription());
//			stepCreated.setHints(s.getHints());
//			stepCreated.setPictures(s.getPictures());
//			stepCreated.setStepNumber(s.getStepNumber());
//			stepCreated.setRecipe(s.getRecipe());
//		}
//	}
	
	//Our other bussiness methods
	
	public String createTicker(){
		
		String result;
		String datePattern = "YYMMDD";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		String moment = simpleDateFormat.format(new Date());
		String code = "";
		code += this.randomLetter() + this.randomLetter() + this.randomLetter() + this.randomLetter();
		result= moment + code;
		return result;
	}
	
	public Collection<Double> getAvgStdStepsPerRecipe(){
		
		return recipeRepository.getAvgStdIngredientsPerRecipe();
	}
	
	public Collection<Double> getAvgStdIngredientsPerRecipe(){
		
		return recipeRepository.getAvgStdIngredientsPerRecipe();
	}
	
	public Collection<User> getUsersByAvgOfLikesAndDislikesOfRecipe(){
		
		return recipeRepository.getUsersByAvgOfLikesAndDislikesOfRecipe();
	}
	
	public Recipe createCopyForContest(Recipe recipe){
		
		String ticker = this.createTicker();
		Recipe copy;
		copy = new Recipe();
		copy.setAuthored(recipe.getAuthored());
		copy.setCategories(recipe.getCategories());
		copy.setHints(recipe.getHints());
		copy.setPictures(recipe.getPictures());
		copy.setQuantities(new ArrayList<Quantity>());
		copy.setSummary(recipe.getSummary());
		copy.setSteps(new ArrayList<Step>());//No estoy seguro del todo de esta, puesto que no se si steps son exclusivos a una receta.
		copy.setTicker(ticker);
		copy.setTitle(recipe.getTitle());
		copy.setComments(new ArrayList<Comment>());
		copy.setDeleted(recipe.getDeleted());
		return copy;
	}
	
	
}

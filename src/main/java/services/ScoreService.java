package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ScoreRepository;

import domain.Recipe;
import domain.Score;
import domain.SocialUser;

@Service
@Transactional
public class ScoreService {
	
	//managed repository-------------------
	@Autowired
	private ScoreRepository scoreRepository;
	
	//supporting services-------------------
	
	//Basic CRUD methods-------------------
	
	public Score create(SocialUser socialUser, Recipe recipe){
		
		Score created = new Score();
		created.setRecipe(recipe);
		created.setSocialUser(socialUser);
		return created;
	}
	
	public Score findOne(int scoreId){
		
		Score retrieved;
		retrieved = scoreRepository.findOne(scoreId);
		return retrieved;
	}

	public Score save(Score score){
		
		Score saved = scoreRepository.save(score);
		
		return saved;
		
	}
	
	public void delete(Score score){
		
		scoreRepository.delete(score);
		
	}
	
	//Auxiliary methods

	//Our other bussiness methods
	
	public Collection<Recipe> findAllByRecipe(int recipeId){
		
		Collection<Recipe> result;
		result = scoreRepository.findAllByRecipe(recipeId);
		
		return result;
	}
	
public Collection<Recipe> findAllBySocialUser(int socialUserId){
		
		Collection<Recipe> result;
		result = scoreRepository.findAllBySocialUser(socialUserId);
		
		return result;
	}
	
	

}

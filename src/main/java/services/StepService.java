package services;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.StepRepository;
import domain.Step;


@Service
@Transactional
public class StepService {

	//managed repository-------------------
		@Autowired
		private StepRepository stepRepository;
		
		//supporting services-------------------
		//@Autowired
		//private StepService stepService;
		
		//Basic CRUD methods-------------------
		
		public Step create(){
			
			Step created;
			created = new Step();
			return created;
		}
		
		public Step findOne(int stepId){
			
			Step retrieved;
			retrieved = stepRepository.findOne(stepId);
			return retrieved;
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
		public char randomLetter(){
			char result;
			String alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random random = new Random();
	        result = alphabet.charAt(random.nextInt(52));
			return result;
		}
		
		//Our other bussiness methods
		
		public Step createCopy(Step step){
			
			Step copied = new Step();
			copied.setDescription(step.getDescription());
			copied.setPictures(step.getPictures());
			copied.setHints(step.getHints());
			copied.setStepNumber(step.getStepNumber());
			
			return copied;
		}
	
}

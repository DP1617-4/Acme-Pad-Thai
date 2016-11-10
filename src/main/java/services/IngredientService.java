package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.IngredientRepository;
import domain.Ingredient;


@Service
@Transactional
public class IngredientService {

	//managed repository-------------------
		@Autowired
		private IngredientRepository ingredientRepository;
		
		//supporting services-------------------
		
		//Basic CRUD methods-------------------
		
		public Ingredient create(){
			
			Ingredient created;
			created = new Ingredient();
			return created;
		}
		
		public Ingredient findOne(int ingredientId){
			
			Ingredient retrieved;
			retrieved = ingredientRepository.findOne(ingredientId);
			return retrieved;
		}

		public Ingredient save(Ingredient ingredient){
			
			Ingredient saved;
			saved = ingredientRepository.save(ingredient);
			return saved;
			
		}
		
		public void delete(Ingredient ingredient){
			
			ingredientRepository.delete(ingredient);
			
		}
		
		//Other Bussiness Methods
		
		public Ingredient delete2(Ingredient ingredient){
			
			ingredient.setDeleted(true);
			Ingredient saved = this.save(ingredient);
			return saved;
		}
		
		public Ingredient restore(Ingredient ingredient){
			
			ingredient.setDeleted(false);
			Ingredient saved = this.save(ingredient);
			return saved;
		}
}

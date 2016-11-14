package services;


import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.Collection;

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
			created.setDeleted(false);
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
		
		public Collection<Ingredient> findAll(){
			
			return ingredientRepository.findAll();
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
		
		public Collection<Ingredient> findAllNotDeleted(){
			
			Collection<Ingredient> notDeleted = new ArrayList<Ingredient>();
			for(Ingredient i: ingredientRepository.findAll()){
				
				if(i.getDeleted()==false){
					
					notDeleted.add(i);
				}
			}
			
			return notDeleted;
		}
}

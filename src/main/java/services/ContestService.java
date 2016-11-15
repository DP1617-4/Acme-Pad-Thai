package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ContestRepository;
import domain.Contest;
import domain.Recipe;

@Service
@Transactional
public class ContestService {

	//managed repository-------------------
			@Autowired
			private ContestRepository contestRepository;
			
			//supporting services-------------------
			@Autowired
			private RecipeService recipeService;
			//Basic CRUD methods-------------------
			
			public Contest create(){
				
				Contest created;
				created = new Contest();
				return created;
			}
			
			public Contest findOne(int contestId){
				
				Contest retrieved;
				retrieved = contestRepository.findOne(contestId);
				return retrieved;
			}

			public Contest save(Contest contest){
				
				Contest saved;
				saved = contestRepository.save(contest);
				return saved;
				
			}
			
			public void delete(Contest contest){
				
				contestRepository.delete(contest);
				
			}
			
			//Other Bussiness Methods
			
			public Collection<Recipe> getContestWinners(Contest contest){
				Collection<Recipe> winners = new ArrayList<Recipe>();
				Collection<Recipe> todas = contest.getQualified();
				List<Recipe> aux = (List<Recipe>) contestRepository.findBestRecipes(contest.getId());
				for(int i=0;i<3;i++){
					if(aux.iterator().hasNext()&&!(winners.contains(aux.iterator().next()))){
						winners.add(aux.iterator().next());
					} else {
						break;	
					}
				}
				for(Recipe r: todas){
					if(winners.size()<3 && !(winners.contains(r))){
						winners.add(r);
					}
				}
//				contest.setWinners(winners);
//				save(contest);
				return winners;
			}
			public void setWon(Contest contest){
				Collection<Recipe> winners = new ArrayList<Recipe>();
				winners = this.getContestWinners(contest);
				for(Recipe r : winners){
					recipeService.winContest(contest,r);
				}
				contest.setWinners(winners);
				save(contest);
			}
			
			public Collection<Double> getMinAvgMaxRecipesQualifiedForContest(){
				Collection<Double> result = new ArrayList<Double>();
				Double[] aux = contestRepository.getMinAvgMaxRecipesQualifiedForContest();
				for(int i=0;i<aux.length;i++){
					
					result.add(aux[i]);
				}
				return result;
			}
			
			public Contest getContestWithMoreRecipesQualified(){
				Contest result = contestRepository.getContestWithMoreRecipesQualified();
				return result;
			}
			
			public Contest delete2(Contest contest){
				
				contest.setDeleted(true);
				Contest saved = this.save(contest);
				return saved;
			}
			
			public Contest restore(Contest contest){
				
				contest.setDeleted(false);
				Contest saved = this.save(contest);
				return saved;
			}
	
			public Collection<Contest> findAllNotDeleted(){
				
				Collection<Contest> notDeleted = new ArrayList<Contest>();
				for(Contest c: contestRepository.findAll()){
					
					if(c.getDeleted()==false){
						
						notDeleted.add(c);
					}
				}
				
				return notDeleted;
			}
			
}

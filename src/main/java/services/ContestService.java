package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ContestRepository;
import domain.Contest;
import domain.Recipe;
import domain.Score;

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
				Collection<Recipe> possibles = new ArrayList<Recipe>();
				Collection<Recipe> winners = new ArrayList<Recipe>();
				possibles = contest.getQualified();
				for (Recipe r : possibles){
					Recipe first = null;
					Recipe second = null;
					Recipe third = null;
				if(first == null){
					first = r;
				} if(r.getScore() >= first.getScore()){
					first = r;
				} else {
					if(second == null){
						second = r;
						}
					if(r.getScore() >= second.getScore()){
						second = r;
						}
					else {
						if(third == null){
							third = r;
							}
						if(r.getScore() >= third.getScore()){
							third = r;
							}
					}
					}
				winners.add(first);
				winners.add(second);
				winners.add(third);
				}
				return winners;
				
			}
			
			public void setWon(Contest contest){
				Collection<Recipe> winners = new ArrayList<Recipe>(this.getContestWinners(contest));
				for(Recipe r : winners){
					recipeService.winContest(contest,r);
				}
			}
			
			public Collection<Double> getMinAvgMaxRecipesQualifiedForContest(){
				
				return contestRepository.getMinAvgMaxRecipesQualifiedForContest();
			}
			
			public Contest getContestWithMoreRecipesQualified(){
				
				return getContestWithMoreRecipesQualified();
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

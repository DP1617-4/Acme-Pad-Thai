package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialUserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Recipe;
import domain.Score;
import domain.SocialUser;

@Service
@Transactional
public class SocialUserService {

	//managed repository-------------------
			@Autowired
			private SocialUserRepository socialUserRepository;
			
			//supporting services-------------------
			@Autowired
			private LoginService loginService;
			@Autowired
			private ScoreService scoreService;
			
			//Basic CRUD methods-------------------
			
			public SocialUser findOne(int socialUserId){
				
				SocialUser retrieved;
				retrieved = socialUserRepository.findOne(socialUserId);
				return retrieved;
			}

			public SocialUser save(SocialUser user){
				
				SocialUser saved = socialUserRepository.save(user);
				
				return saved;
				
			}
			
			public void delete(SocialUser user){
				
				socialUserRepository.delete(user);
				
			}
			
			public SocialUser findByPrincipal(){
				SocialUser result;
				UserAccount userAccount;
				
				userAccount = loginService.getPrincipal();
				result = findOne(userAccount.getId());
				return result;
			}
			
			//Auxiliary methods

			//Our other bussiness methods
			
			public void follow(SocialUser followed){
				
				findByPrincipal().getFollowed().add(followed);
				followed.getFollowers().add(findByPrincipal());
			}
			
			public void comment(SocialUser socialUser, Comment comment){
				
				Assert.isTrue(socialUser.equals(findByPrincipal()));
				socialUser.getComments().add(comment);
			}
			
			public Score like(Recipe recipe){
				
				Score score = scoreService.create(findByPrincipal(), recipe);
				score.setLikes(true);
				scoreService.save(score);
				
				
				return score;
			}
			
			public Score dislike(Recipe recipe){
				
				Score score = scoreService.create(findByPrincipal(), recipe);
				score.setLikes(false);
				scoreService.save(score);
				
				
				return score;
			}
}

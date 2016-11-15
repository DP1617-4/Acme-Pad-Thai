package services;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Cook;
import domain.Curricula;
import domain.Folder;
import domain.MasterClass;
import domain.Nutritionist;
import domain.Recipe;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;

@Service
@Transactional
public class NutritionistService {

	
	//managed repository-------------------
			@Autowired
			private NutritionistRepository nutritionistRepository;
			
			//supporting services-------------------
			@Autowired
			private LoginService loginService;
			
			@Autowired
			private AdministratorService adminService;
			
			//Basic CRUD methods-------------------
			
			public Nutritionist create(){
				
				Nutritionist created = new Nutritionist();
				created.setComments(new ArrayList<Comment>());
				created.setEnroled(new ArrayList<MasterClass>());
				created.setFolders(new ArrayList<Folder>());
				created.setFollowed(new ArrayList<SocialUser>());
				created.setFollowers(new ArrayList<SocialUser>());
			    created.setScores(new ArrayList<Score>());
			    created.setSocialIdentities(new ArrayList<SocialIdentity>());
				
				UserAccount userAccount = new UserAccount();
				Authority authority = new Authority();
				authority.setAuthority(Authority.NUTRITIONIST);
				Collection<Authority> authorities = new ArrayList<Authority>();
				authorities.add(authority);
				userAccount.setAuthorities(authorities);
				
				created.setUserAccount(userAccount);
				return created;
			}
			
			public Nutritionist findOne(int nutritionistId){
				
				Nutritionist retrieved;
				retrieved = nutritionistRepository.findOne(nutritionistId);
				return retrieved;
			}
			
			public Nutritionist findByPrincipal(){
				
			    UserAccount userAccount = loginService.getPrincipal();
			    Nutritionist nutritionist;
				nutritionist = nutritionistRepository.findOneByUserAccountId(userAccount.getId());
				return nutritionist;
			}

			public Nutritionist save(Nutritionist nutritionist){
				
				Nutritionist saved = nutritionistRepository.save(nutritionist);
				
				return saved;
				
			}
			
			public void delete(Nutritionist nutritionist){
				
				nutritionistRepository.delete(nutritionist);
				
			}
			
			public Collection<Nutritionist> findAll(){
				
				return nutritionistRepository.findAll();
			}
			
			//Auxiliary methods

			//Our other bussiness methods
			
			
			
}

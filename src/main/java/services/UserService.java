package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Folder;
import domain.MasterClass;
import domain.Recipe;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;
import domain.User;

@Service
@Transactional
public class UserService {

	
	//managed repository-------------------
		@Autowired
		private UserRepository userRepository;
		
		//supporting services-------------------
		@Autowired
		private LoginService loginService;
		
		//Basic CRUD methods-------------------
		
		public User create(){
			User created;
			created = new User();
			created.setRecipes( new ArrayList<Recipe>());
			created.setComments(new ArrayList<Comment>());
			created.setEnroled(new ArrayList<MasterClass>());
			created.setFolders(new ArrayList<Folder>());
			created.setFollowed(new ArrayList<SocialUser>());
			created.setFollowers(new ArrayList<SocialUser>());
		    created.setScores(new ArrayList<Score>());
		    created.setSocialIdentities(new ArrayList<SocialIdentity>());
			
			UserAccount userAccount = new UserAccount();
			Authority authority = new Authority();
			authority.setAuthority(Authority.USER);
			Collection<Authority> authorities = new ArrayList<Authority>();
			authorities.add(authority);
			userAccount.setAuthorities(authorities);
			
			created.setUserAccount(userAccount);
			
			return created;
		}
		
		public User findOne(int userId){
			
			User retrieved;
			retrieved = userRepository.findOne(userId);
			return retrieved;
		}

		public User save(User user){
			
			User saved;
			saved = userRepository.save(user);
			
			return saved;
			
		}
		
		public void delete(User user){
			
			userRepository.delete(user);
			
		}
		
		public User findByPrincipal(){
			
			User user = userRepository.findOneByUserAccountId(loginService.getPrincipal().getId());
			return user;

		}
		
		public Collection<User> findAll(){
			
			return userRepository.findAll();
		}
		
		//Auxiliary methods

		//Our other bussiness methods
		
		public Collection<Integer> selectMinAvgMaxRecipesInUsers(){
			
			return userRepository.selectMinAvgMaxRecipesInUsers();
		}
		
		public User selectUserWithMostRecipes(){
			
			return userRepository.selectUserWithMostRecipes();
		}
		
		public Collection<User> selectAllUsersDescendingNumberOfFollowers(){
			
			return userRepository.selectAllUsersDescendingNumberOfFollowers();
		}
		
}

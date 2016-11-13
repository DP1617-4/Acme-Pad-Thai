package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Cook;
import domain.Recipe;
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
			return created;
		}
		
		public User findOne(int userId){
			
			User retrieved;
			retrieved = userRepository.findOne(userId);
			return retrieved;
		}

		public User save(User user){
			
			User saved = userRepository.save(user);
			
			return saved;
			
		}
		
		public void delete(User user){
			
			userRepository.delete(user);
			
		}
		
		public User findByPrincipal(){
			
			User user = userRepository.findOneByUserAccountId(LoginService.getPrincipal().getId());
			return user;

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

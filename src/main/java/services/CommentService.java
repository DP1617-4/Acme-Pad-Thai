package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CommentRepository;
import domain.Comment;
import domain.Recipe;
import domain.SocialUser;

@Service
@Transactional
public class CommentService {

	//managed repository-------------------
		@Autowired
		private CommentRepository commentRepository;
		
		//supporting services-------------------
		@Autowired
		private SocialUserService socialUserService;
		//Basic CRUD methods-------------------
		
		public Comment create(Recipe recipe){
			
			Comment created;
			created = new Comment();
			Date moment = new Date(System.currentTimeMillis()-100);
			SocialUser socialUser = socialUserService.findByPrincipal();
			created.setSocialUser(socialUser);
			created.setDate(moment);
			created.setRecipe(recipe);
			return created;
		}
		
		public Comment findOne(int commentId){
			
			Comment retrieved;
			retrieved = commentRepository.findOne(commentId);
			return retrieved;
		}

		public Collection<Comment> findAll(){
			
			return commentRepository.findAll();
		}
		
		public Comment save(Comment comment){
			
			Comment saved;
			saved = commentRepository.save(comment);
			return saved;
			
		}
		
		public void delete(Comment comment){
			
			commentRepository.delete(comment);
			
		}
		
		//Auxiliary methods

		//Our other bussiness methods
	
}

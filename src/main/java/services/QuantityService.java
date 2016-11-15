package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Quantity;
import repositories.QuantityRepository;



@Service
@Transactional
public class QuantityService {
	
	//managed repository-------------------
	@Autowired
	private QuantityRepository quantityRepository;
	
	//supporting services-------------------
	
	//Basic CRUD methods-------------------
	
	public Quantity create(){
		
		Quantity created;
		created = new Quantity();
		return created;
	}
	
	public Quantity findOne(int quantityId){
		
		Quantity retrieved;
		retrieved = quantityRepository.findOne(quantityId);
		return retrieved;
	}

	public Quantity save(Quantity quantity){
		
		Quantity saved = quantityRepository.save(quantity);
		
		return saved;
		
	}
	
	public void delete(Quantity quantity){
		
		quantityRepository.delete(quantity);
		
	}
	
	public Collection<Quantity> findAll(){
		
		return quantityRepository.findAll();
	}
	
	//Our other Bussiness methods
	
	public Quantity createCopy(Quantity quantity){
		
		Quantity copied = new Quantity();
		copied.setIngredient(quantity.getIngredient());
		copied.setQuantity(quantity.getQuantity());
		copied.setUnit(quantity.getUnit());
		
		return copied;
	}
	
}
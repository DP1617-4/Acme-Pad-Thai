package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Value;
import repositories.ValueRepository;



@Service
@Transactional
public class ValueService {
	
	//managed repository-------------------
	@Autowired
	private ValueRepository valueRepository;
	
	//supporting services-------------------
	
	//Basic CRUD methods-------------------
	
	public Value create(){
		
		Value created;
		created = new Value();
		return created;
	}
	
	public Value findOne(int valueId){
		
		Value retrieved;
		retrieved = valueRepository.findOne(valueId);
		return retrieved;
	}

	public Value save(Value value){
		
		Value saved = valueRepository.save(value);
		
		return saved;
		
	}
	
	public void delete(Value value){
		
		valueRepository.delete(value);
		
	}
	
	//Our other Bussiness methods
	
}
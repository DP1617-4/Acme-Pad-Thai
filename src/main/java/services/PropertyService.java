package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PropertyRepository;
import domain.Property;


@Service
@Transactional
public class PropertyService {

	//managed repository-------------------
		@Autowired
		private PropertyRepository propertyRepository;
		
		//supporting services-------------------
		
		//Basic CRUD methods-------------------
		
		public Property create(){
			
			Property created;
			created = new Property();
			created.setDeleted(false);
			return created;
		}
		
		public Property findOne(int propertyId){
			
			Property retrieved;
			retrieved = propertyRepository.findOne(propertyId);
			return retrieved;
		}

		public Property save(Property property){
			
			Property saved;
			saved = propertyRepository.save(property);
			return saved;
			
		}
		
		public void delete(Property property){
			
			propertyRepository.delete(property);
			
		}
		
		public Collection<Property> findAll(){
			
			return propertyRepository.findAll();
		}
		
		//Other Bussiness Methods
		
		public Property delete2(Property property){
			
			property.setDeleted(true);
			Property saved = this.save(property);
			return saved;
		}
		
		public Property restore(Property property){
			
			property.setDeleted(false);
			Property saved = this.save(property);
			return saved;
		}
		
		public Collection<Property> findAllNotDeleted(){
			
			Collection<Property> notDeleted = new ArrayList<Property>();
			for(Property p: propertyRepository.findAll()){
				
				if(p.getDeleted()==false){
					
					notDeleted.add(p);
				}
			}
			
			return notDeleted;
		}
}
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity {
	
	//Constructor
	
	public Quantity(){
		super();
	}
	
	//Attributes
	
	private double quantity;
	private String unit;
	
	@NotNull
	@Min(0)
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@NotBlank
	
	@Pattern (regexp = "^grams|kilograms|ounces|pounds|millilitres|litres|spoons|cups|pieces$")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	// Relationships
	
	private Recipe recipe;
	private Ingredient ingredient;

	@Valid
	@ManyToOne(optional = false)
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@Valid
	@ManyToOne(optional = false)
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	
	

}

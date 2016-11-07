package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

}

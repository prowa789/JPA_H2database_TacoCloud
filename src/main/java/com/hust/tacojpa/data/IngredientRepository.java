package com.hust.tacojpa.data;

import com.hust.tacojpa.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient , String> {
}

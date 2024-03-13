package tacos.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{
	//Converter의 string은 변환할 값의 타입이고, Ingredient는 변환된 값의 타입을 나타낸다.
	
	
	private IngredientRepository ingredientRepo;

	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@Override
	public Ingredient convert(String id) {
		// findById에서 변환할 String값을 id로 갖는 식자재 데이터를 데이터베이스에서 찾는다.
		// JdbcIngredientRepository에서 mapRowToIngredient() 메서드를 호출해 결과 세트의 행 데이터를 속성값으로 갖는 Ingredient객체를 생성하고 반환한다.
//		return ingredientRepo.findById(id);
		
		Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
		return optionalIngredient.isPresent() ? optionalIngredient.get() : null;	
	}
	
	
	
}

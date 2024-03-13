package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	
	//Ingredient 리퍼지터리가 해야할 일을 IngredientRepository인터페이스에 정의했으므로 이제는 JdbcTemplate을 이용해
	//데이터베이스 쿼리에 사용할 수 있도록 IngredientRepository 인터페이스를 구현해야한다. ==> JdbcIngredientRepository
//	Iterable<Ingredient> findAll();
//	Ingredient findById(String id);
//	Ingredient save(Ingredient ingredient);

}

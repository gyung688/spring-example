//package tacos.data;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import tacos.Ingredient;
//
////스테리오타입 애너테이션임. (스테리오annotation : 스프링에서 주로 사용하는 역할 그룹을 나타내는 애노테이션)
//@Repository // @Component에서 특화된 데이터 액세스 관련 애노테이션
//public class JdbcIngredientRepository implements IngredientRepository {
//	
//	private JdbcTemplate jdbc;
//	
//	
//	// JdbcIngredientRepository빈이 생성되면 @Autowired애노테이션을 통해 스프링이 해당 빈을 JdbcTemplate에 주입한다.
//	// JdbcIngredientRepository의 생성자에서는 JdbcTemplate 참조를 인스턴스 변수jdbc에 저장한다. 
//	// jdbc는 데이터베이스의 데이터를 쿼리하고 추가하기 위해 다른 메서드에서 사용된다.
//	@Autowired 
//	public JdbcIngredientRepository(JdbcTemplate jdbc) {
//		this.jdbc = jdbc;
//	}
//	
//	@Override
//	public Iterable<Ingredient> findAll(){
//		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
//	}
//
//	@Override
//	public Ingredient findById(String id) {
//		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
//	}
////	@Override
////	public Ingredient findById(String id) {
////		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", 
////				new RowMapper<Ingredient>() {
////					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException{
////							return new Ingredient(
////									rs.getString("id"),
////									rs.getString("name"),
////									Ingredient.Type.valueOf(rs.getString("type"))
////									);
////					};
////				}, id);
////	}
//	
//	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException{
//		return new Ingredient(
//				rs.getString("id"),
//				rs.getString("name"),
//				Ingredient.Type.valueOf(rs.getString("type"))
//				);
//				
//	}
//
//	@Override
//	public Ingredient save(Ingredient ingredient) {
//		jdbc.update(
//				"insert into Ingredient (id, name, type) values (?,?,?)",
//				ingredient.getId(),
//				ingredient.getName(),
//				ingredient.getType().toString());
//		return ingredient;
//	}
//}

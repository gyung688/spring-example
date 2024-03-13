//package tacos.data;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import tacos.Order;
//import tacos.Taco;
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository{
//
//	private SimpleJdbcInsert orderInserter;
//	private SimpleJdbcInsert orderTacoInserter;
//	private ObjectMapper objectMapper;
//	
//	@Autowired
//	public JdbcOrderRepository(JdbcTemplate jdbc) {
//		this.orderInserter = new SimpleJdbcInsert(jdbc)
//				.withTableName("Taco_Order")
//				.usingGeneratedKeyColumns("id");
//		
//		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
//				.withTableName("Taco_Order_Tacos");
//		
//		this.objectMapper = new ObjectMapper();
//	}
//	
//	@Override
//	public Order save(Order order) {
//		order.setPlacedAt(new Date());
//		long orderId = saveOrderDetails(order);
//		order.setId(orderId);
//		List<Taco> tacos = order.getTacos();
//		
//		for(Taco taco : tacos) {
//			saveTacoToOrder(taco, orderId);
//		}
//		
//		return order;
//	}
//	
//	private long saveOrderDetails(Order order) {
//		@SuppressWarnings("unchecked")
//		Map<String, Object> values = objectMapper.convertValue(order, Map.class); //order객체를 map형태로 변환해준다.
//		// ObjectMapper는 Date타입의 값을 long타입의 값으로 변환하므로 Date형의 속성값은 다시 세팅해줘야한다. 
//		values.put("plcaedAt", order.getPlacedAt());
//		
//		long orderId = orderInserter.executeAndReturnKey(values).longValue(); // long타입으로 변환하여 반환
//		
//		return orderId;
//	}
//	
//	private void saveTacoToOrder(Taco taco, long orderId) {
//		Map<String, Object> values = new HashMap<>();
//		values.put("tacoOrder", orderId);
//		values.put("taco", taco.getId());
//		orderTacoInserter.execute(values);
//	}
//
//	
//}

package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Data // 인자가 있는 생성자를 자동으로 추가. 그러나 @NoArgsConstructor가 지정되면 그런 생성자는 제거된다. 
	  // 하지만 @RequiredArgsConstructor가 있으면 private의 인자 없는 생성자와 더불어 인자가 있는 생성자를 여전히 가질 수 있다.
@RequiredArgsConstructor  
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true) //jPA에서는 개체가 인자없는 noarguments생성자를 가져야한다.  하지만여기서는 이 생성자를 원치않으므로 private으로 외부에서 사용불가로만듦.
@Entity //JPA 개체로 선언시 필요
public class Ingredient {

	//lombok에 의해 getter, setter, toString 등 메소드들을 런타임 시에 자동으로 생성.
	//초기화가 필요한 final 속성들이 있으므로 force속성을 true로 설정. 이에 따라 lmbok이 자동 생성한 생성자들에서 그 속성들을 null로 설정.
	
	@Id //JPA에서 데이터베이스의 개체를 고유하게 식별한다는 것을 나타냄
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type{
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}

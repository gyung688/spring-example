package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)// db가 자동으로 생성해주는 값이 사용됨.
	private Long id;
	
	private Date createdAt;

	@NotNull
	@Size(min=5, message="Name must be at least 5 charactoers long")
	private String name;
	
	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	
	@PrePersist // Taco객체가 저장되기 전에 createdAt속성을 현재 일자와 시간으로 설정하는 데 사용됨.
	void createdAt() {
		this.createdAt = new Date();
	}
}

package tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
@Validated
public class OrderProps {
	// @Component가 지정됐으므로, 스프링 컴포넌트 검색에서 OrderProps를 자동으로 찾은 후 스프링 애플리케이션 컨텍스트의 빈으로 생성해준다. > 이 빈을 controller에 주입할것임.
	// Controller에서 직접 속성을 처리할 필요 없이, 여기서 처리. controller코드는 더 깔끔해지고 다른 빈에서 OrderProps의 속성들을 재사용할 수 있게 된다.
	
	@Min(value=5, message="must be between 5 and 25")
	@Max(value=25, message="must be between 5 and 25")
	private int pageSize = 20;
	
}

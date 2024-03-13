package tacos;



import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest // 스프링 MVC애플리케이션의 형태로 테스트가 실행되도록 한다.
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception{ //홈페이지에 대해 수행하고자 하는 테스트 정의.
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect((ResultMatcher) content().string(
					containsString("Welcome to...")));
	}
}

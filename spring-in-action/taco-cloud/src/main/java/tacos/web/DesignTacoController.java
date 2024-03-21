package tacos.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order") // 이 annotation을 주문과 같은 모델 객체에 지정하면 세션에서 계속 보존되면서 다수의 요청에 걸쳐 사용될 수 있다.
// 세션에 데이터를 지정하는 역할을 하며, 세션에 저장된 데이터는 여러 요청 간에 공유되어 사용될 수 있다.
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	
	private TacoRepository tacoRepo;

	private UserRepository userRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo
			,UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
		this.userRepo = userRepo;
	}
	
	@ModelAttribute(name = "order") // Order 객체가 모델에 생성되도록 해준다.
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model, Principal principal) { 
//		List<Ingredient> ingredients = Arrays.asList( //Ingredient객체를 저장하는 list생성
//				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE),
//				new Ingredient("JACK", "Monterry Jack", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE),
//				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//				);
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type)); //필터링.
		}
		//model : 컨트롤러와 데이터를 보여주는 뷰 사이에서 데이터를 운반하는 객체.
		//궁극적으로 model 객체의 속성에 있는 데이터는 뷰가 알 수 있는 서블릿 요청 속성들로 복사됨.
//		model.addAttribute("taco", new Taco());
		
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		
		return "design"; // 모델 데이터를 브라우저에 나타내는 데 사용될 뷰의 논리적인 이름이다
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
	
	
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		// 타고 디자인을 저장한다.
		// order의 @ModelAttribute은 order 값이 모델로부터 전달되어야 한다는 것과 
		// 스프링 MVC가 이 order에 요청 매개변수를 바인딩하지 않아야 한다는 것을 나타낸다.
		if(errors.hasErrors()) {
			return "design";
		}

//		log.info("Processing design: " + design);
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved); // 세션에 보존된 Order에 Taco객체를 추가한다.
		
		return "redirect:/orders/current";
	}
	
}

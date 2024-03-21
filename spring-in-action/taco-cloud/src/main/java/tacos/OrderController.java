package tacos;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;
	
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user,
			@ModelAttribute Order order) {
//		model.addAttribute("order",new Order());
		
		// GET요청이 제출될 때 해당 사용자 정보가 미리 채워진 상태로 주문 폼이 전송됨.
		if(order.getDeliveryName() == null) {
			order.setDeliveryName(user.getFullname());
		}
		if(order.getDeliveryStreet() == null) {
			order.setDeliveryStreet(user.getStreet());
		}
		if(order.getDeliveryCity() == null) {
			order.setDeliveryCity(user.getCity());
		}
		if(order.getDeliveryState() == null) {
			order.setDeliveryState(user.getState());
		}
		if(order.getDeliveryZip() == null) {
			order.setDeliveryZip(user.getZip());
		}
		return "orderForm";
	}
	
	/*
	 * 사용자가 누구인지 결정하는 방법(보안 특정 코드가 많으면 좋지 않다.)
	 * 1. Principal 메서드에 주입.
				User user = userRepository.findByUsername(principal.getName()); //보안 관련 없는 코드 혼재
	 * 2. Authentication 메서드에 주입.
				User user = (User) authentication.getPrincipal(); // Object를 반환해서 User타입으로 변환 필요.
	 * 3. SecurityContextHolder로 보안 컨텍스트를 얻는다.
	 * 			Authnticaion authentication = SecurityContxtHolder.getContext().getAuthentication();
				User user = (User) authentication.getPrincipal(); // Object를 반환해서 User타입으로 변환 필요.
	 * 4. @AuthenticationPrincipal  메서드에 지정.
	 */
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {
		if(errors.hasErrors()) {
			return "orderForm";
		}

		order.setUser(user);
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
}

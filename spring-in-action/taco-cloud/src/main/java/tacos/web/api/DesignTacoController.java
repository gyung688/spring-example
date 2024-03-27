package tacos.web.api;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/design", produces="applicatin/json")
@CrossOrigin(origins="*")
public class DesignTacoController {

	private TacoRepository tacoRepo;
	
//	@Autowired
//	EntityLinks entityLinks;
	
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping("/recent")
	public Iterable<Taco> recentTacos(){
		//최근 생성 일자 순으로 정렬된 처음 12개의 결과를 갖는 첫 번째 페이지만 원한다는 것을 page에 저장
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//		return tacoRepo.findAll(page).getContent();
		return tacoRepo.findAll();
	}
	
	/*
	 * {id} : 플레이스홀더. @PathVariable에 의해 {id}플레이스홀더와 대응되는 id매개변수에 해당 요청의 실제 값이 지정됨.
	 * ResponseEntity에 Taco객체가 포함되거나 null이 포함되어 클라이언트에 반환된다.
	 * */
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if(optTaco.isPresent()) {
			//optTaco가 null이 나온다고 null을 반환하면 HTTP200으로 정상이 반환되는데, 404가 나도록 유도하는게 좋다.
//			return optTaco.get();
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
	}
	
	/*
	 * path속성을 지정하지 않았으므로 해당 메서드는 /design 경로에 대한 요청을 처리한다.
	 * consumes속성을 통해 Content-type이 application/json과 일치하는 요청만 처리한다.
	 * */
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}
	
}

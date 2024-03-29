package space.bumtiger.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import space.bumtiger.domain.Burger;
import space.bumtiger.repository.BurgerPageSortRepo;

@RestController
@RequestMapping("/data-api")
public class BurgerAPI {
	@Autowired
	private BurgerPageSortRepo burRepository;
	
	// @formatter:off
	@GetMapping(value = "/burgers", params = { "size", "page" })
	public List<Burger> findAllBySortAndPage( 
			@RequestParam("size") final int size, 
			@RequestParam("page") final int page,
			@RequestParam("sort") final String sort) {
		var sortKeys = sort.split(",");
		var direc = Direction.fromString(sortKeys[1]);
    PageRequest pageable = PageRequest.of(
    		page, size, direc, sortKeys[0]);
    Page<Burger> result = burRepository.findAll(pageable);
    if (!result.isEmpty())
      return result.getContent();
    else
      return null;		
	}
	// @formatter:on
}

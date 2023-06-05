package space.bumtiger.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import space.bumtiger.domain.Corder;
import space.bumtiger.repository.CorderRepository;

@RestController
@RequestMapping(path="/api/orders",
								produces="application/json")
@CrossOrigin(origins = "http://hamburger:8080")
@AllArgsConstructor
public class CorderApiCon {
	private CorderRepository orderRepo;
	
	@PatchMapping(path="/{orderId}", consumes="application/json") 
	public Corder patchOrder(@PathVariable("orderId") Integer orderId,
			@RequestBody Corder patch) {
		
		  Corder order = orderRepo.findById(orderId).get();
		  if (patch.getCustName() != null) {
		    order.setCustName(patch.getCustName());
		  }
		  if (patch.getAddrRoad() != null) {
		    order.setAddrRoad(patch.getAddrRoad());
		  }
		  if (patch.getAddrDetail() != null) {
		    order.setAddrDetail(patch.getAddrDetail());
		  }
		  if (patch.getAddrZip() != null) {
		    order.setAddrZip(patch.getAddrZip());
		  }
		  if (patch.getCcNumber() != null) {
		    order.setCcNumber(patch.getCcNumber());
		  }
		  if (patch.getCcExpiration() != null) {
		    order.setCcExpiration(patch.getCcExpiration());
		  }
		  if (patch.getCcCVV() != null) {
		    order.setCcCVV(patch.getCcCVV());
		  }
		  return orderRepo.save(order);
	}
	
	@PutMapping(path="/{orderId}", consumes="application/json") 
	public Corder putOrder(
			@PathVariable("orderId") Integer orderId,
			@RequestBody Corder corder) {
		corder.setId(orderId);
		return orderRepo.save(corder);
	}
		
}

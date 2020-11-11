package com.ayudaencasa.request;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ayudaencasa.request.model.Request;
import com.ayudaencasa.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping(value = "/request")
public class DemoApplication {
	@Autowired
	private RequestRepository repository;

	@GetMapping("/{wId}")
	public Request GetRequest(@PathVariable String wId){
		return repository.findRequestById(wId);
	}
	@PostMapping
	public Request PostRequest(@RequestBody Request request){
		return repository.addRequest(request);
	}
	@PutMapping
	public String UpdateRequest(@RequestBody Request request){
		return repository.editRequest(request);
	}
	@DeleteMapping
	public String DeleteRequest(@RequestBody Request request){
		return repository.deleteRequest(request);
	}
	@GetMapping
	public PaginatedScanList<Request> GetAllRequests(){
		return repository.findAllRequests();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

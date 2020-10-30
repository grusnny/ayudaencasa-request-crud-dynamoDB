package com.ayudaencasa.request;
import com.ayudaencasa.request.model.Request;
import com.ayudaencasa.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {
	@Autowired
	private RequestRepository repository;

	@GetMapping("/getrequest/{wId}")
	public Request GetRequest(@PathVariable String wId){
		return repository.findRequestById(wId);
	}
	@PostMapping("/saverequest")
	public Request PostRequest(@RequestBody Request request){
		return repository.addRequest(request);
	}
	@PutMapping("/editrequest")
	public String UpdateRequest(@RequestBody Request request){
		return repository.editRequest(request);
	}
	@DeleteMapping("/deleterequest")
	public String DeleteRequest(@RequestBody Request request){
		return repository.deleteRequest(request);
	}
	@GetMapping
	public Request[] GetAllRequest(String uId){
		Request Requests[] = new Request[0];
		return Requests;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

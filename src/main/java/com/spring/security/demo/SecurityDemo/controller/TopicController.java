package com.spring.security.demo.SecurityDemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.security.demo.SecurityDemo.model.Topic;

@RestController
public class TopicController {

	@GetMapping("/topics")
	public List<Topic> getTopics() {
		Topic topic1= new Topic(1, "Spring Security", "Spring");
		Topic topic2= new Topic(2, "Spring Data", "Spring");
		
		List newArrList= new ArrayList<Topic>();
		newArrList.add(topic1);
		newArrList.add(topic2);

		return newArrList;
	}
	
	@GetMapping("/topics/{id}")
	public Topic getTopicById(@PathVariable("id") int id) {
		Topic topic= new Topic(1, "Spring Security", "Spring");
		
		if(id==2)
			throw new RuntimeException("Custom exception thrown...");
				
		return topic;
	}
	
	@PostMapping("/topics")
	public void saveTopic(@RequestBody Topic topic) {
		System.out.println("Post called with admin credentials...");
	}
	/*
	@GetMapping("/error")
	public String getError(){
		return "Error occured!";
	}*/
}

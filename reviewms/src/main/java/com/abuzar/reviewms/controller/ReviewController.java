package com.abuzar.reviewms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abuzar.reviewms.model.Review;
import com.abuzar.reviewms.service.ReviewService;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addReview(@RequestBody Review review, @RequestParam Long companyId) {
		Boolean isAdded = reviewService.addReviewById(review, companyId);
		if (isAdded) {
			return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
		}
	}
		
	
	@GetMapping("/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
		Review review = reviewService.getReviewById(id);
		if(review != null)
		{
			return new ResponseEntity<>(review, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateReviewById(@RequestBody Review review, @PathVariable Long id) { 
		Boolean isUpdated = reviewService.updateReviewById(review, id);
		if(isUpdated)
		{
			return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to Update", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<String> deleteReviewById(@PathVariable Long id) {
		Boolean isDeleted = reviewService.deleteReviewById(id);
		if(isDeleted)
		{
			return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to Delete", HttpStatus.NOT_FOUND);
		}
	}

}


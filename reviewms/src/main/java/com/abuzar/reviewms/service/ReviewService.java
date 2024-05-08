package com.abuzar.reviewms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abuzar.reviewms.model.Review;
import com.abuzar.reviewms.repository.ReviewRepository;


@Service
public class ReviewService {
	ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

	public Boolean addReviewById(Review review, Long companyId) {
		//Company company = companyService.findCompanyById(companyId);
		if(companyId != null)
		{
			review.setCompanyId(companyId);
			reviewRepository.save(review);
			return true;
		}
		return false;
		
	}

	public Review getReviewById(Long id) {
		
		/*List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews.stream().filter(review -> review.getId().equals(id)).
				findFirst().orElse(null);*/
		return reviewRepository.findById(id).orElse(null);
	}
	
	public Boolean updateReviewById(Review updatedReview, Long id) {
		Review review = reviewRepository.findById(id).orElse(null);
		
		if(review != null)
		{
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			reviewRepository.save(review);
			return true;
		}
		return false;
	}
	
	public Boolean deleteReviewById(Long id) {
		if(reviewRepository.existsById(id)) {
			/*Review review = reviewRepository.findById(id).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			companyService.updateCompany(companyId, company);
			*/
			reviewRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}

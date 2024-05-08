package com.abuzar.jobms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abuzar.jobms.external.Review;

@FeignClient(name="REVIEWMS", url="http://localhost:8083")
public interface ReviewClient {
	@GetMapping("/reviews")
	public List<Review> getReviews(@RequestParam("companyId") Long companyId);

}

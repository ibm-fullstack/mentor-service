package com.ibm.mentor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.mentor.message.request.BlockUserRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@FeignClient("user-service")
public interface UserClient {

	@PostMapping("/user/block")
	ResponseEntity<?> blockUser(@RequestBody BlockUserRequest blockUserRequest);
	
	@PostMapping("/user/unblock")
	ResponseEntity<?> unblockUser(@RequestBody BlockUserRequest blockUserRequest);
}

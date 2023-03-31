package com.example.elsaticsearch.user.presentation;

import com.example.elsaticsearch.user.application.UserRequestDto;
import com.example.elsaticsearch.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Void> save(@RequestBody UserRequest userRequest) {
        UserRequestDto userRequestDto = new UserRequestDto(
                userRequest.getName(),
                userRequest.getDescription()
        );
        Long id = userService.save(userRequestDto);

        URI uri = URI.create(String.valueOf(id));
        return ResponseEntity.created(uri)
                .build();
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<List<UserResponse>> search(@PathVariable String name, Pageable pageable) {
        List<UserResponse> userResponses = userService.searchByName(name, pageable)
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<List<UserResponse>> search2(@PathVariable String name, Pageable pageable) {
        List<UserResponse> userResponses = userService.searchByNameInQuery(name,pageable)
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }
}


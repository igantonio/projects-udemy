package com.spring.course.restfulspringbootaws.resource;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.dto.*;
import com.spring.course.restfulspringbootaws.model.PageModel;
import com.spring.course.restfulspringbootaws.model.PageRequestModel;
import com.spring.course.restfulspringbootaws.security.JwtManager;
import com.spring.course.restfulspringbootaws.service.RequestService;
import com.spring.course.restfulspringbootaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtManager jwtManager;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userSaveDto) {
        User userToSave = userSaveDto.tranformToUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userToSave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        User userToUpdate = userUpdateDto.tranformToUser();
        userToUpdate.setId(id);
        return ResponseEntity.ok(userService.save(userToUpdate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<PageModel<User>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<User> pageModel = userService.listAllOnLazyMode(pageRequestModel);
        return ResponseEntity.ok(pageModel);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());

        Authentication auth = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(auth);//Colocando a autenticação no contexto de segurança

        org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User)auth.getPrincipal();

        String email = userSpring.getUsername();
        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        UserLoginResponseDto jwt = jwtManager.createToken(email, roles);

        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsByOwnerId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> pageModel = requestService.listAllByOwnerIdOnLazyMode(id, pageRequestModel);
        return ResponseEntity.ok(pageModel);
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(
            @RequestBody @Valid UserUpdateRoleDto userUpdateRoleDto,
            @PathVariable("id") Long id) {

        User user = new User();
        user.setId(id);
        user.setRole(userUpdateRoleDto.getRole());

        userService.updateRole(user);

        return ResponseEntity.ok().build();
    }

}

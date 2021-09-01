package com.imunizacao.vacina.controllers;

import com.imunizacao.vacina.model.entities.User;
import com.imunizacao.vacina.security.AccountCredentialsDTO;
import com.imunizacao.vacina.security.jwt.JWTTokenProvider;
import com.imunizacao.vacina.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "Authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @ApiOperation(value = "Used to make an authentication and get a token from server, that will used to get requests based on your permission")
    @PostMapping("/signing")
    public ResponseEntity<?> signing(@RequestBody AccountCredentialsDTO data){
        try{
            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = (User) userService.loadUserByUsername(username);

            String token = "";

            if (user != null){
                token = tokenProvider.createToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (AuthenticationException ex){
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }
}

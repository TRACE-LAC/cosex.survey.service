package com.monkeypox.survey.service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monkeypox.survey.service.business.facade.IAuthenticationService;
import com.monkeypox.survey.service.model.authentication.Token;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping("authentication")
public class AuthenticationController {

  @Autowired
  private IAuthenticationService authenticationService;

  /**
   * This method generates the authentication
   * 
   * @param auth: authentication data
   * @return token
   */
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Token> generateAuthentication() throws Exception {
    Token token = new Token();
    log.info("Authentication Controller - generateAuthentication");
    token = authenticationService.generateAuthentication();
    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
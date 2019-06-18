package com.example;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Value("classpath:provider.json")
  private Resource providerJson;


  @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody byte[] getUsers() throws IOException {
    return Files.readAllBytes(providerJson.getFile().toPath());
  }
}

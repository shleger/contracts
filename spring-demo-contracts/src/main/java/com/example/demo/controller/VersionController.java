package com.example.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.demo.dto.VersionDto;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impl")
public class VersionController {

  @GetMapping(value = "/version", produces = APPLICATION_JSON_VALUE)
  @ResponseBody
  public VersionDto version() {
    return VersionDto.builder()
        .branch("newBranch1")
        .commit("commitHASH")
        .commitTime(LocalDateTime.now())
        .version("1.0.0-SNAPSHOT")
        .build();
  }
}

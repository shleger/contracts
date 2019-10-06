package com.example.demo.dto;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionDto {

  private String version;
  private LocalDateTime commitTime;
  private String commit;
  private String branch;

}

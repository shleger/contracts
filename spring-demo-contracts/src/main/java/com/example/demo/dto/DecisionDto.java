package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DecisionDto {

  private Long id;
  private String assignee;
  private String privateComment;
  private String publicComment;
  private String entityType;
  private EnumDto decisionType;

}

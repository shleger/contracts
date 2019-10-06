package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnumDto {
  private String code;
  private String name;
}

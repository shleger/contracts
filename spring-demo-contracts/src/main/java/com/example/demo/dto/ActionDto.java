package com.example.demo.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionDto {

  private Long id;
  private String assigneeLogin;
  private Boolean controllerApprovalNecessity;
  private BigDecimal calculationResult;
  private EnumDto status;
  private EnumDto personalInsurance;
  private Integer creditTerm;
  private BigDecimal creditSum;
  private Boolean calcPerformed;
  private String uidForCalcParams;
  private String uid;
  private EnumDto requesterSystem;
  private DecisionDto supervisorDecision;
  private DecisionDto fraudDecision;
  private DecisionDto operatorDecision;

}

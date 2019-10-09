package com.example.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.demo.dto.ActionDto;
import com.example.demo.dto.DecisionDto;
import com.example.demo.dto.EnumDto;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class ActionController {

  @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseBody
  public ActionDto actionGet(@PathVariable("id") Long id) {

    return ActionDto.builder()
        .uidForCalcParams(UUID.randomUUID().toString())
        .uid(UUID.randomUUID().toString())

        .fraudDecision(DecisionDto.builder()
            .decisionType(EnumDto.builder().code("FRAUD_NOT_CONFIRMED_ORDER").name("").build())
            .entityType("order")
            .assignee("loginFraud")
            .id(567L)
            .publicComment("publicCommentFraud")
            .privateComment("privateCommentFraud")
            .build())

        .supervisorDecision(DecisionDto.builder().decisionType(
            EnumDto.builder().code("SUPERVISOR_CONFIRMED_ORDER").name("").build())
            .entityType("order")
            .publicComment("publicCommentSupervisor")
            .privateComment("privateCommentSupervisor")
            .assignee("loginSuper").id(123L).build())

        .personalInsurance(EnumDto.builder().code("ENABLED").name("Включено").build())
        .creditSum(BigDecimal.ONE)
        .creditTerm(2)
        .calcPerformed(true)
        .calculationResult(BigDecimal.TEN)
        .controllerApprovalNecessity(true)
        .requesterSystem(EnumDto.builder().code("NEW").name("Новая").build())
        .assigneeLogin("loginAssignee")
        .status(EnumDto.builder().code("START_ACTION").build())
        .id(id)
        .build();
  }


  @PostMapping(value = "/check",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseBody
  public ActionDto check(@RequestBody ActionDto actionDto) {
    if (actionDto.getCreditSum().compareTo(BigDecimal.valueOf(1_000_000)) > 0) {
      actionDto.setFraudDecision(DecisionDto.builder()
          .decisionType(
              EnumDto.builder().code("FRAUD_CONFIRMED_ORDER").name("Fraud check").build())
          .build());
    }
    return actionDto;
  }
}

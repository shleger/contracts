package com.example.demo;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringConsumerContractTest {


  @Rule
  public StubRunnerRule stubRunnerRule = new StubRunnerRule()
      .downloadStub("com.example.demo", "spring-demo-contracts", "0.0.1-SNAPSHOT", "stubs")
      .withPort(8100)
      .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

  @Test
  public void shouldAskForActionFive() {

    when()
        .get("http://localhost:8100/action/5")
        .then()
        .statusCode(200)
        .body("assigneeLogin", equalTo("test"))
        .body("controllerApprovalNecessity", equalTo(true))
        .body("status.code", equalTo("START_ACTION"))
        .body("uid", is(uuid()))
    ;


  }



  private TypeSafeMatcher<String> uuid() {

    return new TypeSafeMatcher<String>(){

      @Override
      public void describeTo(Description description) {
        description.appendText("It is not UUID format");

      }

      @Override
      protected boolean matchesSafely(String item) {
        return Pattern.compile("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}").matcher(item).matches();
      }
    };
  }

}

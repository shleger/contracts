package com.example.demo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
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
        .body("uid", is(regexp("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")))
    ;


  }


  @Test
  public void shouldRedirectedToFraudAction() {

    given()
        .contentType(ContentType.JSON)
        .body(
            "{"
                + "\"assigneeLogin\":\"loginAssigned\","
                + "\"uid\":\"8888181d-b58f-440f-bdf4-21f65c738888\","
                + "\"creditSum\":2000000000"
                + "}")
        .post("http://localhost:8100/action/check")
        .then()
        .statusCode(200)
        .body("fraudDecision.decisionType.name", equalTo("Fraud check"))
        .body("fraudDecision.decisionType.code", equalTo("FRAUD_CONFIRMED_ORDER"))
    ;


  }

  @Test
  public void shouldReturnVersion() {
    String alphabet = "[a-zA-Z]+";
    when()
        .get("http://localhost:8100/impl/version")
        .then()
        .statusCode(200)
        .body("version", regexp("\\d{1,2}\\.\\d{1,2}\\.\\d{1,2}"))
        .body("branch", regexp(alphabet))
        .body("commit", regexp(alphabet))
        .body("commitTime", notNullValue())
    ;

  }


  private TypeSafeMatcher<String> regexp(String regexp) {

    return new TypeSafeMatcher<String>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Regexp does not match - " + regexp);
      }

      @Override
      protected boolean matchesSafely(String item) {
        return Pattern.compile(regexp).matcher(item).matches();
      }
    };
  }

}

package com.example;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
    "server.port=8080"
})
@Provider("Users Provider")
@PactFolder("C:/dev/my/contracts/pact/hello-pact-consumer/pacts")
public class LibraryTest {

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State({"provider accepts a new person", "person 42 exists"})
  public void toCreatePersonState() {
//    User user = new User();
//    user.setId(42L);
//    user.setFirstName("Arthur");
//    user.setLastName("Dent");
//    when(userRepository.findById(eq(42L))).thenReturn(Optional.of(user));
//    when(userRepository.save(any(User.class))).thenReturn(user);
  }
}

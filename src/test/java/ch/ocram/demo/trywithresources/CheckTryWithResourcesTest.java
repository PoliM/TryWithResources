package ch.ocram.demo.trywithresources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckTryWithResourcesTest {

  @Test
  public void thisIsATest() {
    // workaround so that this class will be recognized as test
  }

  // tag::testFactory[]
  @TestFactory
  @DisplayName("all configurations")
  public Stream<DynamicTest> createDynamicTests() {
    return IntStream.range(0, Config.NUMBER_OF_CASES)
      .mapToObj(value -> Config.from(value))
      .map(config -> DynamicTest.dynamicTest(config.toDisplayName(), () -> assertEquals(whatDoYouThink(config), test(config))));
  }
  // end::testFactory[]

  // tag::test[]
  private String test(Config config) throws IOException {
    try {
      return new CheckTryWithResources().doSomething(config);
    } catch (RuntimeException ex) {
      return ex.getMessage();
    }
  }
  // end::test[]

  // tag::whatDoYouThink[]
  private String whatDoYouThink(Config config) {
    // Implement your logic here to determine what you'll expect to happen

    if (isHappyPath(config)) {
      return "normallyExecuted";
    }

    if (config.failOnFinally) {
      // Crashes in finally have the highest priority
      return "failedOnFinally";
    }

    if (config.returnInFinally) {
      // Second highest priority
      return "returnedInFinally";
    }

    if (config.failOnExceptionHandling) {
      // This "overrides" the exception that was being handled
      return "failedOnExceptionHandling";
    }

    if (config.failOnGetResource) {
      // It will be handled by the exception handler of the try-with-resources statement
      return "exceptionHandled";
    }

    if (config.failOnClose) {
      // It will be handled by the exception handler of the try-with-resources statement
      return "exceptionHandled";
    }

    if (config.failOnBusinessLogic) {
      // That's the normal case
      return "exceptionHandled";
    }

    return null;
  }

  private boolean isHappyPath(Config config) {
    // the failOnExceptionHandling is ignored, because this wont happen in the happy case
    return !config.failOnFinally && !config.failOnBusinessLogic && !config.failOnGetResource && !config.failOnClose && !config.returnInFinally;
  }
  // end::whatDoYouThink[]
}

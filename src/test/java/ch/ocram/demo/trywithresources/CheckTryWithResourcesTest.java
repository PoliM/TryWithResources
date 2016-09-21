package ch.ocram.demo.trywithresources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTryWithResourcesTest {

  @Test
  public void thisIsATest() {
    // workaround so that this class will be recognized as test
  }

  @TestFactory
  @DisplayName("all configurations")
  public Stream<DynamicTest> ads() {
    return IntStream.range(0, Config.NUMBER_OF_CASES)
      .mapToObj(value -> Config.from(value))
      .map(config -> DynamicTest.dynamicTest(config.toDisplayName(), () -> assertEquals(whatDoYouThink(config), test(config))));
  }

  private String test(Config config) throws IOException {
    try {
      return new CheckTryWithResources().doSomething(config);
    } catch (RuntimeException ex) {
      return ex.getMessage();
    }
  }

  private String whatDoYouThink(Config config) {
    // Implement your logic here to determine what you'll expect to happen
    return null;
  }
}

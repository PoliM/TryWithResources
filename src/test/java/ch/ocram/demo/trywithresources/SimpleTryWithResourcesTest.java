package ch.ocram.demo.trywithresources;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleTryWithResourcesTest {
  @Test
  public void simple00() throws IOException {
    new SimpleTryWithResources().doSomething(false, false);
  }

  @Test
  public void simple01() throws IOException {
    assertThrows(RuntimeException.class, () -> new SimpleTryWithResources().doSomething(false, true));
  }

  @Test
  public void simple10() throws IOException {
    new SimpleTryWithResources().doSomething(true, false);
  }

  @Test
  public void simple11() throws IOException {
    new SimpleTryWithResources().doSomething(true, true);
  }
}

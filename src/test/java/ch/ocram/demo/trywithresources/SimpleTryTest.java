package ch.ocram.demo.trywithresources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTryTest {

  @Test
  public void simpleTryNormal() {
    assertEquals("normallyExecuted", new SimpleTry().doSomething(false));
  }

  @Test
  public void simpleTryFailed() {
    assertEquals("exceptionHandled", new SimpleTry().doSomething(true));
  }
}

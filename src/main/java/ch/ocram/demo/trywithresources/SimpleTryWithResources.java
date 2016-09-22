package ch.ocram.demo.trywithresources;

import java.io.Closeable;
import java.io.IOException;

public class SimpleTryWithResources {
  public void doSomething(boolean resourceWillBeNull, boolean failOnClose) throws IOException {
    try (Closeable c = getResource(resourceWillBeNull, failOnClose)) {

    }
  }

  private Closeable getResource(boolean resourceWillBeNull, boolean failOnClose) {
    if (resourceWillBeNull) {
      return null;
    }

    return () -> {
      if (failOnClose) {
        throw new RuntimeException("failedOnClose");
      }
    };
  }
}

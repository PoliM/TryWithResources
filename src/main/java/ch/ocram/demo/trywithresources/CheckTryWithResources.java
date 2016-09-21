package ch.ocram.demo.trywithresources;

import java.io.Closeable;
import java.io.IOException;

public class CheckTryWithResources {

  public String doSomething(Config cfg) throws IOException {
    try (Closeable c = getResource(cfg.failOnGetResource, cfg.failOnClose)) {
      callSomeBusinessLogic(cfg.failOnBusinessLogic);
      return "normal";
    } catch (Exception ex) {
      handleException(cfg.failOnExceptionHandling);
      return "exceptionHandeled";
    } finally {
      doFinally(cfg.failOnFinally);
      if (cfg.returnInFinally) {
        return "returnInFinally";
      }
    }
  }

  private void doFinally(boolean failOnFinally) {
    if (failOnFinally) {
      throw new RuntimeException("failOnFinally");
    }
  }

  private void handleException(boolean failOnExceptionHandling) {
    if (failOnExceptionHandling) {
      throw new RuntimeException("failOnExceptionHandling");
    }
  }

  private void callSomeBusinessLogic(boolean failOnBusinessLogic) {
    if (failOnBusinessLogic) {
      throw new RuntimeException("failOnBusinessLogic");
    }
  }

  private Closeable getResource(boolean failOnGetResource, boolean failOnClose) {
    if (failOnGetResource) {
      throw new RuntimeException("failOnGetResource");
    }

    return () -> {
      if (failOnClose) {
        throw new RuntimeException("failedOnClose");
      }
    };
  }
}

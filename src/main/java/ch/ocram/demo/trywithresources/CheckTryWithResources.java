package ch.ocram.demo.trywithresources;

import java.io.Closeable;
import java.io.IOException;

public class CheckTryWithResources {

  public String doSomething(Config cfg) throws IOException {
    try (Closeable c = getResource(cfg.failOnGetResource, cfg.failOnClose)) {
      callSomeBusinessLogic(cfg.failOnBusinessLogic);
      return "normallyExecuted";
    } catch (Exception ex) {
      handleException(cfg.failOnExceptionHandling);
      return "exceptionHandeled";
    } finally {
      doFinally(cfg.failOnFinally);
      if (cfg.returnInFinally) {
        return "returnedInFinally";
      }
    }
  }

  private void doFinally(boolean failOnFinally) {
    if (failOnFinally) {
      throw new RuntimeException("failedOnFinally");
    }
  }

  private void handleException(boolean failOnExceptionHandling) {
    if (failOnExceptionHandling) {
      throw new RuntimeException("failedOnExceptionHandling");
    }
  }

  private void callSomeBusinessLogic(boolean failOnBusinessLogic) {
    if (failOnBusinessLogic) {
      throw new RuntimeException("failedOnBusinessLogic");
    }
  }

  private Closeable getResource(boolean failOnGetResource, boolean failOnClose) {
    if (failOnGetResource) {
      throw new RuntimeException("failedOnGetResource");
    }

    return () -> {
      if (failOnClose) {
        throw new RuntimeException("failedOnClose");
      }
    };
  }
}

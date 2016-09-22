package ch.ocram.demo.trywithresources;

public class SimpleTry {
  public String doSomething(boolean failOnBusinessLogic) {
    try {
      callSomeBusinessLogic(failOnBusinessLogic);
      return "normallyExecuted";
    } catch (Exception ex) {
      return "exceptionHandled";
    }
  }

  private void callSomeBusinessLogic(boolean failOnBusinessLogic) {
    if (failOnBusinessLogic) {
      throw new RuntimeException("failedOnBusinessLogic");
    }
  }
}

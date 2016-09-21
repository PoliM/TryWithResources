package ch.ocram.demo.trywithresources;

import java.util.BitSet;

/**
 * Represents one test scenario.
 */
public class Config {

  public static final int NUMBER_OF_CASES = 64;

  public final boolean failOnGetResource;
  public final boolean failOnClose;
  public final boolean failOnBusinessLogic;
  public final boolean failOnExceptionHandling;
  public final boolean failOnFinally;
  public final boolean returnInFinally;

  private Config(boolean failOnGetResource, boolean failOnClose, boolean failOnBusinessLogic, boolean failOnExceptionHandling,
                 boolean failOnFinally, boolean returnInFinally) {
    this.failOnGetResource = failOnGetResource;
    this.failOnClose = failOnClose;
    this.failOnBusinessLogic = failOnBusinessLogic;
    this.failOnExceptionHandling = failOnExceptionHandling;
    this.failOnFinally = failOnFinally;
    this.returnInFinally = returnInFinally;
  }

  public static Config from(int value) {
    BitSet bits = BitSet.valueOf(new long[] {value});
    return new Config(bits.get(0), bits.get(1), bits.get(2), bits.get(3), bits.get(4), bits.get(5));
  }

  public static String flag(boolean flag) {
    return flag ? "[x]" : "[_]";
  }

  public String toDisplayName() {
    return
      "gr=" + flag(failOnGetResource) +
        " cl=" + flag(failOnClose) +
        " bl=" + flag(failOnBusinessLogic) +
        " eh=" + flag(failOnExceptionHandling) +
        " fn=" + flag(failOnFinally) +
        " rf=" + flag(returnInFinally);
  }
}

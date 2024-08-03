package test.helper.assertion;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class 実行結果<T> {
  private T actual;

  private 実行結果() {
  }

  public 実行結果(T actual) {
    this.actual = actual;
  }

  public static <T> 実行結果<T> の(T actual) {
    return new 実行結果(actual);
  }

  public void はnullです() {
    assertNull(this.actual, () -> "実行結果はnullではなく、" + actual.toString() + "でした。");
  }

  public void はnullです(String message) {
    assertNull(this.actual, message);
  }

  public void はnullです(Supplier<String> messageSupplier) {
    assertNull(this.actual, messageSupplier);
  }

  public void はnullではありません() {
    assertNotNull(this.actual, "実行結果はnullでした。");
  }

  public void はnullではありません(String message) {
    assertNotNull(this.actual, message);
  }

  public void はnullではありません(Supplier<String> messageSupplier) {
    assertNotNull(this.actual, messageSupplier);
  }

  public <V> ExpectedValue<V> は期待値の(V expected) {
    return new ExpectedValue(expected);
  }

  public class ExpectedValue<V> {
    private V expected;

    private ExpectedValue(V expected) {
      this.expected = expected;
    }

    public void と同じです() {
      assertEquals(expected, actual, () -> "実行結果: " + actual.toString() + "は期待値: " + expected.toString() + "と同じになりませんでした。" );
    }

    public void と同じです(String message) {
      assertEquals(expected, actual, message);
    }

    public void と同じです(Supplier<String> messageSupplier) {
      assertEquals(expected, actual, messageSupplier);
    }

    public void と異なります() {
      assertNotEquals(expected, actual, () -> "実行結果: " + actual.toString() + "は期待値: " + expected.toString() + "と異なる値になりませんでした。" );
    }

    public void と異なります(String message) {
      assertNotEquals(expected, actual, message);
    }

    public void と異なります(Supplier<String> messageSupplier) {
      assertNotEquals(expected, actual, messageSupplier);
    }

    public void と同じインスタンスです() {
      assertSame(expected, actual, () -> "実行結果: " + actual.toString() + "は期待値: " + expected.toString() + "と同じインスタンスではありませんでした。" );
    }

    public void と同じインスタンスです(String message) {
      assertSame(expected, actual, message);
    }

    public void と同じインスタンスです(Supplier<String> messageSupplier) {
      assertSame(expected, actual, messageSupplier);
    }

    public void と異なるインスタンスです() {
      assertNotSame(expected, actual, () -> "実行結果: " + actual.toString() + "は期待値: " + expected.toString() + "と異なるインスタンスではありませんでした。");
    }

    public void と異なるインスタンスです(String message) {
      assertNotSame(expected, actual, message);
    }

    public void と異なるインスタンスです(Supplier<String> messageSupplier) {
      assertNotSame(expected, actual, messageSupplier);
    }
  }
}

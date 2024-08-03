package test;

import chrono.FormatBuilder;

import test.helper.assertion.実行結果;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class JapaneseCalendarTest {
  @Test
  public void デフォルトの日付フォーマット() {
    String dateText = new FormatBuilder().format(LocalDate.of(2022, 5, 6));
    実行結果.の(dateText).は期待値の("2022/05/06").と同じです();
  }

  @Test
  public void 元号の日付フォーマット() {
    String dateText = new FormatBuilder().useEra(true).format(LocalDate.of(2022, 5, 6));
    実行結果.の(dateText).は期待値の("令和04/05/06").と同じです();
  }

  @Test
  public void 元号の年月日フォーマット() {
    String dateText = new FormatBuilder().useEra(true).separator(FormatBuilder.DateSeparator.KANJI).format(LocalDate.of(2022, 5, 6));
    実行結果.の(dateText).は期待値の("令和04年05月06日").と同じです();
  }

  @Test
  public void 年なしの日付フォーマット() {
    String dateText = new FormatBuilder().showYear(false).format(LocalDate.of(2022, 5, 6));
    実行結果.の(dateText).は期待値の("05/06").と同じです();
  }

  @Test
  public void 年月日区切りの日付フォーマット() {
    String dateText = new FormatBuilder().separator(FormatBuilder.DateSeparator.KANJI).format(LocalDate.of(2022, 5, 6));
    実行結果.の(dateText).は期待値の("2022年05月06日").と同じです();
  }
}
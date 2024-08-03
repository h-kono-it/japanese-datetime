package chrono;

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Locale;

public class FormatBuilder {
  public enum DateSeparator {
    KANJI,
    SLASH,
    DASH,
    NON,
  }

  private DateSeparator dateSeparator = DateSeparator.SLASH;
  private boolean showYear = true;
  private boolean useZeroPadding = true;
  private boolean useEra = false;
  private boolean addWeekOfDay = false;

  public FormatBuilder(){}
  public FormatBuilder(DateSeparator dateSeparator, boolean showYear, boolean useZeroPadding, boolean useEra, boolean addWeekOfDay) {
    this.dateSeparator = dateSeparator;
    this.showYear = showYear;
    this.useZeroPadding = useZeroPadding;
    this.useEra = useEra;
    this.addWeekOfDay = addWeekOfDay;
  }

  public FormatBuilder separator(DateSeparator dateSeparator) {
    this.dateSeparator = dateSeparator;
    return this;
  }

  public FormatBuilder showYear(boolean showYear) {
    this.showYear = showYear;
    return this;
  }

  public FormatBuilder useZeroPadding(boolean useZeroPadding) {
    this.useZeroPadding = useZeroPadding;
    return this;
  }

  public FormatBuilder useEra(boolean useEra) {
    this.useEra = useEra;
    return this;
  }

  public FormatBuilder addWeekOfDay(boolean addWeekOfDay) {
    this.addWeekOfDay = addWeekOfDay;
    return this;
  }

  public String format(final LocalDate date){
    DateTimeFormatter dateTimeFormatter = this.build();
    TemporalAccessor targetDate = this.useEra ? JapaneseDate.from(date) : date;
    return dateTimeFormatter.format(targetDate);
  }

  public String format(final JapaneseCalendar date){
    DateTimeFormatter dateTimeFormatter = this.build();
    LocalDate localDate = date.toLocalDate();
    TemporalAccessor targetDate = this.useEra ? JapaneseDate.from(localDate) : localDate;
    return dateTimeFormatter.format(targetDate);
  }

  DateTimeFormatter build() {
    StringBuilder patternBuilder = new StringBuilder();
    String[] separators = switch (this.dateSeparator) {
      case NON -> new String[]{"", "", ""};
      case DASH -> new String[]{"-", "-", ""};
      case KANJI -> new String[]{"年", "月", "日"};
      case SLASH -> new String[]{"/", "/", ""};
    };

    if (this.showYear) {
      if (this.useEra) {
        patternBuilder.append("Gy");
        if (this.useZeroPadding) {
          patternBuilder.append("y");
        }
      } else {
        patternBuilder.append("yyyy");
      }
      patternBuilder.append("%s");
    }else{
      separators = Arrays.copyOfRange(separators, 1 , 3);
    }
    final int repeatCount = this.useZeroPadding ? 2 : 1;
    patternBuilder.append("M".repeat(repeatCount));
    patternBuilder.append("%s");
    patternBuilder.append("d".repeat(repeatCount));
    patternBuilder.append("%s");
    if (this.addWeekOfDay) {
      patternBuilder.append("ccc");
    }
    return DateTimeFormatter.ofPattern(patternBuilder.toString().formatted(separators), Locale.JAPANESE);
  }

}

package chrono;

import java.time.LocalDate;

public class JapaneseCalendar {
  private LocalDate localDate;

  public LocalDate toLocalDate(){
    return LocalDate.from(localDate);
  }

  public LocalDate nextDay() {
    return this.nextDay(1);
  }

  public LocalDate nextDay(final long daysToAdd) {
    return this.localDate.plusDays(daysToAdd);
  }
}

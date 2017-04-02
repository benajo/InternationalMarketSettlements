package com.benajo.helper;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.*;
import static org.junit.Assert.assertEquals;

public class ReportHelperTest {

  private ReportHelper helper = new ReportHelper();

  /**
   * Should return FRIDAY for FRIDAY
   * Should return MONDAY for SATURDAY and SUNDAY
   *
   * @throws Exception
   */
  @Test
  public void determineSettlementDateGBP() throws Exception {

    // FRIDAY
    DayOfWeek date1 = helper.determineSettlementDate(LocalDate.of(2017, 1, 6), "GBP").getDayOfWeek();
    // SATURDAY
    DayOfWeek date2 = helper.determineSettlementDate(LocalDate.of(2017, 1, 7), "GBP").getDayOfWeek();
    // SUNDAY
    DayOfWeek date3 = helper.determineSettlementDate(LocalDate.of(2017, 1, 8), "GBP").getDayOfWeek();

    assertEquals(date1, FRIDAY);
    assertEquals(date2, MONDAY);
    assertEquals(date3, MONDAY);
  }

  /**
   * Should return THURSDAY for THURSDAY
   * Should return SUNDAY for FRIDAY and SATURDAY
   *
   * @throws Exception
   */
  @Test
  public void determineSettlementDateAED() throws Exception {

    // THURSDAY
    DayOfWeek date1 = helper.determineSettlementDate(LocalDate.of(2017, 1, 5), "AED").getDayOfWeek();
    // FRIDAY
    DayOfWeek date2 = helper.determineSettlementDate(LocalDate.of(2017, 1, 6), "AED").getDayOfWeek();
    // SATURDAY
    DayOfWeek date3 = helper.determineSettlementDate(LocalDate.of(2017, 1, 7), "AED").getDayOfWeek();

    assertEquals(date1, THURSDAY);
    assertEquals(date2, SUNDAY);
    assertEquals(date3, SUNDAY);
  }

  @Test
  public void calcAmountOfTrade() throws Exception {

    assertEquals(helper.calcAmountOfTrade(1, 2, 3), 6.0, 0);
    assertEquals(helper.calcAmountOfTrade(300, 200, 0.33), 19800.0, 0);
  }
}
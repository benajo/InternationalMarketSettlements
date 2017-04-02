package com.benajo.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Helper class to assist building the report
 */
public class ReportHelper {

  /**
   * Settlement dates that fall on weekends must be changed to the next woring day.
   * For currencies AED and SAR the work week is Sun-Thu, else it is Mon-Fri.
   *
   * @param settlementDate
   * @param currency
   * @return The settlementDate changed to next working day, only if required.
   */
  public LocalDate determineSettlementDate(LocalDate settlementDate, String currency) {

    DayOfWeek dayOfWeek = settlementDate.getDayOfWeek();

    if (currency.equals("AED") || currency.equals("SAR")) {
      switch (dayOfWeek) {
        case FRIDAY:
          return settlementDate.plusDays(2);
        case SATURDAY:
          return settlementDate.plusDays(1);
        default:
          return settlementDate;
      }
    }

    switch (dayOfWeek) {
      case SATURDAY:
        return settlementDate.plusDays(2);
      case SUNDAY:
        return settlementDate.plusDays(1);
      default:
        return settlementDate;
    }
  }

  /**
   * Simply multiplies the price per unit by the amount of units by the agreed foreign exchange rate.
   *
   * @param pricePerUnit
   * @param units
   * @param agreedFx
   * @return Value of parameters multiplied together.
   */
  public double calcAmountOfTrade(double pricePerUnit, int units, double agreedFx) {
    return pricePerUnit * units * agreedFx;
  }
}

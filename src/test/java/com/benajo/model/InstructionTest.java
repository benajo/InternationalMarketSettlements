package com.benajo.model;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.DayOfWeek.*;
import static org.junit.Assert.assertEquals;

public class InstructionTest {

  private Instruction instruction = new Instruction();

  /**
   * Should return FRIDAY for FRIDAY
   * Should return MONDAY for SATURDAY and SUNDAY
   *
   * @throws Exception
   */
  @Test
  public void determineSettlementDateGBP() throws Exception {

    instruction.setCurrency("GBP");

    instruction.setSettlementDate(LocalDate.of(2017, 1, 6)); // Friday
    assertEquals(FRIDAY, instruction.determineSettlementDate().getDayOfWeek());

    instruction.setSettlementDate(LocalDate.of(2017, 1, 7)); // Saturday
    assertEquals(MONDAY, instruction.determineSettlementDate().getDayOfWeek());

    instruction.setSettlementDate(LocalDate.of(2017, 1, 8)); // Sunday
    assertEquals(MONDAY, instruction.determineSettlementDate().getDayOfWeek());
  }

  /**
   * Should return THURSDAY for THURSDAY
   * Should return SUNDAY for FRIDAY and SATURDAY
   *
   * @throws Exception
   */
  @Test
  public void determineSettlementDateAED() throws Exception {

    instruction.setCurrency("AED");

    instruction.setSettlementDate(LocalDate.of(2017, 1, 5)); // Thursday
    assertEquals(THURSDAY, instruction.determineSettlementDate().getDayOfWeek());

    instruction.setSettlementDate(LocalDate.of(2017, 1, 6)); // Friday
    assertEquals(SUNDAY, instruction.determineSettlementDate().getDayOfWeek());

    instruction.setSettlementDate(LocalDate.of(2017, 1, 7)); // Saturday
    assertEquals(SUNDAY, instruction.determineSettlementDate().getDayOfWeek());
  }

  @Test
  public void calcAmountOfTrade() throws Exception {

    instruction.setUnits(2);
    instruction.setPricePerUnit(4);
    instruction.setAgreedFx(2);

    assertEquals(instruction.calcAmountOfTrade(), 16, 0);
  }
}
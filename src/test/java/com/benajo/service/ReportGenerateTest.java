package com.benajo.service;

import com.benajo.model.Instruction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static com.benajo.model.Instruction.typeOfInstructions.BUY;
import static com.benajo.model.Instruction.typeOfInstructions.SELL;
import static org.junit.Assert.assertEquals;

public class ReportGenerateTest {

  private List<Instruction> instructionsBuy = new ArrayList<>();
  private List<Instruction> instructionsSell = new ArrayList<>();
  private List<Instruction> instructions = new ArrayList<>();
  private ReportGenerate report;

  /**
   * Creates some sample data
   *
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {

    instructionsBuy.add(new Instruction("foo", BUY, 0.5, "SGP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 2), 200, 100.25));
    instructionsBuy.add(new Instruction("mat", BUY, 0.25, "EUR", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 14), 132, 170));
    instructionsBuy.add(new Instruction("baz", BUY, 0.4, "GBP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 7), 133, 150));
    instructionsBuy.add(new Instruction("pat", BUY, 0.4, "GBP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 17), 100, 180));
    instructionsBuy.add(new Instruction("bat", BUY, 0.3, "USD", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 9), 155, 160));

    instructionsSell.add(new Instruction("dog", SELL, 0.14, "SAR", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 9), 323, 89));
    instructionsSell.add(new Instruction("pug", SELL, 0.44, "SAR", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 10), 119, 199));
    instructionsSell.add(new Instruction("bar", SELL, 0.22, "AED", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 7), 450, 150.5));
    instructionsSell.add(new Instruction("cat", SELL, 0.21, "AED", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 5), 222, 66));

    instructions.addAll(instructionsBuy);
    instructions.addAll(instructionsSell);

    report = new ReportGenerate(instructions);
    report.run();
  }

  /**
   * Checks the outgoing each day in USD is correct for sample data.
   *
   * @throws Exception
   */
  @Test
  public void checkOutgoingIsCorrect() throws Exception {

    Map<LocalDate, Double> expected = new HashMap<>();

    expected.put(LocalDate.of(2016, 1, 14), 5610.0);
    expected.put(LocalDate.of(2016, 1, 11), 7440.0);
    expected.put(LocalDate.of(2016, 1, 7), 7980.0);
    expected.put(LocalDate.of(2016, 1, 4), 10025.0);
    expected.put(LocalDate.of(2016, 1, 18), 7200.0);

    assertEquals(expected, report.getOutgoingEachDayInUSD());
  }

  /**
   * Checks the outgoing each day in USD is correct for sample data.
   *
   * @throws Exception
   */
  @Test
  public void checkIncomingIsCorrect() throws Exception {

    Map<LocalDate, Double> expected = new HashMap<>();

    expected.put(LocalDate.of(2016, 1, 10), 14444.22);
    expected.put(LocalDate.of(2016, 1, 7), 14899.5);
    expected.put(LocalDate.of(2016, 1, 5), 3076.92);

    assertEquals(expected, report.getIncomingEachDayInUSD());
  }

  /**
   * Checks if the outgoing entity rankings are correct for sample data.
   *
   * @throws Exception
   */
  @Test
  public void checkOutgoingEntityRankingsAreCorrect() throws Exception {

    TreeSet<Instruction> expected = new TreeSet<>(instructionsBuy);
    assertEquals(expected, report.getRankingOutgoingEntities());
  }

  /**
   * Checks if the incoming entity rankings are correct for sample data.
   *
   * @throws Exception
   */
  @Test
  public void checkIncomingEntityRankingsAreCorrect() throws Exception {

    TreeSet<Instruction> expected = new TreeSet<>(instructionsSell);
    assertEquals(expected, report.getRankingIncomingEntities());
  }
}
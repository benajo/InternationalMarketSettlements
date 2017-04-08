package com.benajo.service;

import com.benajo.model.Entity;
import com.benajo.model.Instruction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ReportGenerateTest {

  private List<Instruction> instructions = new ArrayList<>();
  private ReportGenerate report;

  /**
   * Creates some sample data
   *
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {

    instructions.add(new Instruction("foo", "B", 0.5, "SGP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 2), 200, 100.25));
    instructions.add(new Instruction("baz", "B", 0.4, "GBP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 7), 133, 150));
    instructions.add(new Instruction("bat", "B", 0.3, "USD", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 9), 155, 160));
    instructions.add(new Instruction("mat", "B", 0.25, "EUR", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 14), 132, 170));
    instructions.add(new Instruction("pat", "B", 0.4, "GBP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 17), 100, 180));

    instructions.add(new Instruction("bar", "S", 0.22, "AED", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 7), 450, 150.5));
    instructions.add(new Instruction("cat", "S", 0.21, "AED", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 5), 222, 66));
    instructions.add(new Instruction("dog", "S", 0.14, "SAR", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 9), 323, 89));
    instructions.add(new Instruction("pug", "S", 0.44, "SAR", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 10), 119, 199));

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

    LinkedList<Entity> expected = new LinkedList<>();

    expected.add(new Entity("foo", 10025.0));
    expected.add(new Entity("baz", 7980.0));
    expected.add(new Entity("bat", 7440.0));
    expected.add(new Entity("pat", 7200.0));
    expected.add(new Entity("mat", 5610.0));

    assertEquals(expected, report.getRankingOutgoingEntities());
  }

  /**
   * Checks if the incoming entity rankings are correct for sample data.
   *
   * @throws Exception
   */
  @Test
  public void checkIncomingEntityRankingsAreCorrect() throws Exception {

    LinkedList<Entity> expected = new LinkedList<>();

    expected.add(new Entity("bar", 14899.5));
    expected.add(new Entity("pug", 10419.64));
    expected.add(new Entity("dog", 4024.5800000000004));
    expected.add(new Entity("cat", 3076.92));

    assertEquals(expected, report.getRankingIncomingEntities());
  }
}
package com.benajo.service;

import com.benajo.model.Instruction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * This class takes in a list of instructions and iterates over them to generate the report.
 */
public class ReportGenerate {

  private final List<Instruction> instructions;

  private Map<LocalDate, Double> incomingEachDayInUSD = new HashMap<>();
  private Map<LocalDate, Double> outgoingEachDayInUSD = new HashMap<>();
  private TreeSet<Instruction> rankingIncomingEntities = new TreeSet<>();
  private TreeSet<Instruction> rankingOutgoingEntities = new TreeSet<>();

  /**
   * Iterates over the instructions, calculates the trade amount, modifies settlement date (if required)
   * and ranks the entities by the highest buy price.
   *
   * @param instructions
   */
  public ReportGenerate(List<Instruction> instructions) {

    this.instructions = instructions;
  }

  public void run() {

    instructions.forEach(instruction -> {

      double amountOfTrade = instruction.calcAmountOfTrade();
      LocalDate settlementDate = instruction.determineSettlementDate();

      if (instruction.isBuyInstruction()) {

        recalculateDailySettlementTotals(outgoingEachDayInUSD, amountOfTrade, settlementDate);
        rankingOutgoingEntities.add(instruction);

      } else if (instruction.isSellInstruction()) {

        recalculateDailySettlementTotals(incomingEachDayInUSD, amountOfTrade, settlementDate);
        rankingIncomingEntities.add(instruction);
      }
    });
  }

  /**
   * Prints the report to System.out.
   */
  public void print() {

    // outgoing each day
    System.out.println();
    System.out.println("Outgoing USD each day:");
    outgoingEachDayInUSD.forEach((k, v) -> System.out.println(k + ": " + v));

    // incoming each day
    System.out.println();
    System.out.println("Incoming USD each day:");
    incomingEachDayInUSD.forEach((k, v) -> System.out.println(k + ": " + v));

    // ranking of outgoing entities
    System.out.println();
    System.out.println("Ranking of buy entities:");
    rankingOutgoingEntities.forEach(i -> System.out.println(i.getEntity() + " - " + i.calcAmountOfTrade()));

    // ranking of incoming entities
    System.out.println();
    System.out.println("Ranking of sell entities:");
    rankingIncomingEntities.forEach(i -> System.out.println(i.getEntity() + " - " + i.calcAmountOfTrade()));
  }

  /**
   * This method keeps the daily trade totals updated for incoming and outgoing trades.
   *
   * @param eachDayInUSD
   * @param amountOfTrade
   * @param settlementDate
   */
  private void recalculateDailySettlementTotals(Map<LocalDate, Double> eachDayInUSD, double amountOfTrade,
                                                LocalDate settlementDate) {

    if (!eachDayInUSD.containsKey(settlementDate)) {
      eachDayInUSD.put(settlementDate, amountOfTrade);
    } else {
      eachDayInUSD.replace(settlementDate, eachDayInUSD.get(settlementDate) + amountOfTrade);
    }
  }

  public Map<LocalDate, Double> getIncomingEachDayInUSD() {
    return incomingEachDayInUSD;
  }

  public Map<LocalDate, Double> getOutgoingEachDayInUSD() {
    return outgoingEachDayInUSD;
  }

  public TreeSet<Instruction> getRankingIncomingEntities() {
    return rankingIncomingEntities;
  }

  public TreeSet<Instruction> getRankingOutgoingEntities() {
    return rankingOutgoingEntities;
  }
}

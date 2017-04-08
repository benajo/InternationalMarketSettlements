package com.benajo.service;

import com.benajo.helper.ReportHelper;
import com.benajo.model.Entity;
import com.benajo.model.Instruction;

import java.time.LocalDate;
import java.util.*;

/**
 * This class takes in a list of instructions and iterates over them to generate the report.
 */
public class ReportGenerate {

  private final List<Instruction> instructions;

  private ReportHelper helper = new ReportHelper();
  private Map<LocalDate, Double> incomingEachDayInUSD = new HashMap<>();
  private Map<LocalDate, Double> outgoingEachDayInUSD = new HashMap<>();
  private TreeSet<Entity> rankingIncomingEntities = new TreeSet<>();
  private TreeSet<Entity> rankingOutgoingEntities = new TreeSet<>();

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

    instructions.forEach(i -> {

      double amountOfTrade = helper.calcAmountOfTrade(i.getPricePerUnit(), i.getUnits(), i.getAgreedFx());
      LocalDate settlementDate = helper.determineSettlementDate(i.getSettlementDate(), i.getCurrency());


      if (i.isBuyInstruction()) {
        recalculateDailySettlementTotals(outgoingEachDayInUSD, amountOfTrade, settlementDate);
        rankingOutgoingEntities.add(new Entity(i.getEntity(), amountOfTrade));
      } else if (i.isSellInstruction()) {
        recalculateDailySettlementTotals(incomingEachDayInUSD, amountOfTrade, settlementDate);
        rankingIncomingEntities.add(new Entity(i.getEntity(), amountOfTrade));
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
    rankingOutgoingEntities.forEach(e -> System.out.println(e.getEntity() + " - " + e.getPrice()));

    // ranking of incoming entities
    System.out.println();
    System.out.println("Ranking of sell entities:");
    rankingIncomingEntities.forEach(e -> System.out.println(e.getEntity() + " - " + e.getPrice()));
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

  public TreeSet<Entity> getRankingIncomingEntities() {
    return rankingIncomingEntities;
  }

  public TreeSet<Entity> getRankingOutgoingEntities() {
    return rankingOutgoingEntities;
  }
}

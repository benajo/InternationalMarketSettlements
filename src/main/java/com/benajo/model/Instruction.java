package com.benajo.model;

import java.time.LocalDate;

/**
 * Model class for the instructions used in the international market.
 */
public class Instruction {

  private String entity;
  private String buyOrSell;
  private double agreedFx;
  private String currency;
  private LocalDate instructionDate;
  private LocalDate settlementDate;
  private int units;
  private double pricePerUnit;

  public Instruction(String entity, String buyOrSell, double agreedFx, String currency, LocalDate instructionDate,
                     LocalDate settlementDate, int units, double pricePerUnit) {
    this.entity = entity;
    this.buyOrSell = buyOrSell;
    this.agreedFx = agreedFx;
    this.currency = currency;
    this.instructionDate = instructionDate;
    this.settlementDate = settlementDate;
    this.units = units;
    this.pricePerUnit = pricePerUnit;
  }

  public String getEntity() {
    return entity;
  }

  public String getBuyOrSell() {
    return buyOrSell;
  }

  public double getAgreedFx() {
    return agreedFx;
  }

  public String getCurrency() {
    return currency;
  }

  public LocalDate getInstructionDate() {
    return instructionDate;
  }

  public LocalDate getSettlementDate() {
    return settlementDate;
  }

  public int getUnits() {
    return units;
  }

  public double getPricePerUnit() {
    return pricePerUnit;
  }

  @Override
  public String toString() {
    return "Instruction{" +
            "entity='" + entity + '\'' +
            ", buyOrSell='" + buyOrSell + '\'' +
            ", agreedFx=" + agreedFx +
            ", currency='" + currency + '\'' +
            ", instructionDate=" + instructionDate +
            ", settlementDate=" + settlementDate +
            ", units=" + units +
            ", pricePerUnit=" + pricePerUnit +
            '}';
  }
}

package com.benajo.model;

import java.time.LocalDate;

/**
 * Model class for the instructions used in the international market.
 */
public class Instruction {

  public enum typeOfInstructions {
    BUY, SELL
  }

  private String entity;
  private typeOfInstructions typeOfInstruction;
  private double agreedFx;
  private String currency;
  private LocalDate instructionDate;
  private LocalDate settlementDate;
  private int units;
  private double pricePerUnit;

  public Instruction(String entity, typeOfInstructions typeOfInstruction, double agreedFx, String currency, LocalDate instructionDate,
                     LocalDate settlementDate, int units, double pricePerUnit) {
    this.entity = entity;
    this.typeOfInstruction = typeOfInstruction;
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

  public typeOfInstructions getTypeOfInstruction() {
    return typeOfInstruction;
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

  public boolean isBuyInstruction() {
    return typeOfInstruction.equals(typeOfInstructions.BUY);
  }

  public boolean isSellInstruction() {
    return typeOfInstruction.equals(typeOfInstructions.SELL);
  }

  @Override
  public String toString() {
    return "Instruction{" +
            "entity='" + entity + '\'' +
            ", typeOfInstruction='" + typeOfInstruction + '\'' +
            ", agreedFx=" + agreedFx +
            ", currency='" + currency + '\'' +
            ", instructionDate=" + instructionDate +
            ", settlementDate=" + settlementDate +
            ", units=" + units +
            ", pricePerUnit=" + pricePerUnit +
            '}';
  }
}

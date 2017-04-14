package com.benajo.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Model class for the instructions used in the international market.
 */
public class Instruction implements Comparable<Instruction> {

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

  public Instruction() {
  }

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

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public void setTypeOfInstruction(typeOfInstructions typeOfInstruction) {
    this.typeOfInstruction = typeOfInstruction;
  }

  public void setAgreedFx(double agreedFx) {
    this.agreedFx = agreedFx;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setInstructionDate(LocalDate instructionDate) {
    this.instructionDate = instructionDate;
  }

  public void setSettlementDate(LocalDate settlementDate) {
    this.settlementDate = settlementDate;
  }

  public void setUnits(int units) {
    this.units = units;
  }

  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  public boolean isBuyInstruction() {
    return typeOfInstruction.equals(typeOfInstructions.BUY);
  }

  public boolean isSellInstruction() {
    return typeOfInstruction.equals(typeOfInstructions.SELL);
  }

  /**
   * Settlement dates that fall on weekends must be changed to the next working day.
   * For currencies AED and SAR the work week is Sun-Thu, else it is Mon-Fri.
   *
   * @return The settlementDate changed to next working day, only if required.
   */
  public LocalDate determineSettlementDate() {

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
   * @return Value of parameters multiplied together.
   */
  public double calcAmountOfTrade() {
    return pricePerUnit * units * agreedFx;
  }

  @Override
  public int compareTo(Instruction o) {

    if (calcAmountOfTrade() > o.calcAmountOfTrade())
      return -1;
    else if (calcAmountOfTrade() < o.calcAmountOfTrade())
      return 1;
    return 0;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Instruction that = (Instruction) o;

    if (Double.compare(that.agreedFx, agreedFx) != 0) return false;
    if (units != that.units) return false;
    if (Double.compare(that.pricePerUnit, pricePerUnit) != 0) return false;
    if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
    if (typeOfInstruction != that.typeOfInstruction) return false;
    if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
    if (instructionDate != null ? !instructionDate.equals(that.instructionDate) : that.instructionDate != null)
      return false;
    return settlementDate != null ? settlementDate.equals(that.settlementDate) : that.settlementDate == null;

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = entity != null ? entity.hashCode() : 0;
    result = 31 * result + (typeOfInstruction != null ? typeOfInstruction.hashCode() : 0);
    temp = Double.doubleToLongBits(agreedFx);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (currency != null ? currency.hashCode() : 0);
    result = 31 * result + (instructionDate != null ? instructionDate.hashCode() : 0);
    result = 31 * result + (settlementDate != null ? settlementDate.hashCode() : 0);
    result = 31 * result + units;
    temp = Double.doubleToLongBits(pricePerUnit);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}

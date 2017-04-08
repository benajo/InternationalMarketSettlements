package com.benajo;

import com.benajo.model.Instruction;
import com.benajo.service.ReportGenerate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.benajo.model.Instruction.typeOfInstructions.BUY;
import static com.benajo.model.Instruction.typeOfInstructions.SELL;

/**
 * Main class to run the application
 */
public class App {

  public static void main(String[] args) {

    List<Instruction> instructions = new ArrayList<>();

    instructions.add(new Instruction("foo", BUY, 0.5, "SGP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 2), 200, 100.25));
    instructions.add(new Instruction("baz", BUY, 0.4, "GBP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 7), 133, 150));
    instructions.add(new Instruction("bat", BUY, 0.3, "USD", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 9), 155, 160));
    instructions.add(new Instruction("mat", BUY, 0.25, "EUR", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 14), 132, 170));
    instructions.add(new Instruction("pat", BUY, 0.4, "GBP", LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 17), 100, 180));

    instructions.add(new Instruction("bar", SELL, 0.22, "AED", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 7), 450, 150.5));
    instructions.add(new Instruction("cat", SELL, 0.21, "AED", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 5), 222, 66));
    instructions.add(new Instruction("dog", SELL, 0.14, "SAR", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 9), 323, 89));
    instructions.add(new Instruction("pug", SELL, 0.44, "SAR", LocalDate.of(2016, 1, 5), LocalDate.of(2016, 1, 10), 119, 199));

    System.out.println("List of instructions:");
    instructions.forEach(i -> System.out.println(i.toString()));

    ReportGenerate report = new ReportGenerate(instructions);
    report.run();
    report.print();
  }
}

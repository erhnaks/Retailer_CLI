package com.generic.retailer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public final class Cli implements AutoCloseable {

    private static final String SINGLE_ITEM_LINE_FORMAT = "%-4s%16s";
    private static final String MULTIPLE_ITEM_LINE_FORMAT = "%-11s%9s";
    private static final String SUB_TOTAL_LINE_FORMAT = "TOTAL%15s";
    private static final String THURSDAY_DISCOUNT_LINE_FORMAT = "THURS%15s";
    private static final String TWO_FOR_ONE_DISCOUNT_LINE_FORMAT = "2 FOR 1%13s";
    private static final String POUND_SYMBOL = "£";
    private static final String DISCOUNT_LINE_POUND_SYMBOL = "-£";

    public static Cli create(String prompt, BufferedReader reader, BufferedWriter writer, LocalDate date) {
        requireNonNull(prompt);
        requireNonNull(reader);
        requireNonNull(writer);
        return new Cli(prompt, reader, writer, date);
    }

    public static Cli create(BufferedReader reader, BufferedWriter writer) {
        return new Cli(">", reader, writer, LocalDate.now());
    }

    private static final Predicate<String> WHITESPACE = Pattern.compile("^\\s{0,}$").asPredicate();

    private final String prompt;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final LocalDate date;

    private Cli(String prompt, BufferedReader reader, BufferedWriter writer, LocalDate date) {
        this.prompt = prompt;
        this.reader = reader;
        this.writer = writer;
        this.date = date;
    }

    private void prompt() throws IOException {
        writeLine(prompt);
    }

    private Optional<String> readLine() throws IOException {
        String line = reader.readLine();
        return line == null || WHITESPACE.test(line) ? Optional.empty() : Optional.of(line);
    }

    private void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
        writer.flush();
    }

    public void run(Trolley trolley) throws IOException {
        writeLine("What would you like to buy?");
        prompt();
        Optional<String> line = readLine();
        while (line.isPresent()) {
            ProductType productType = buildProductType(line.get().toUpperCase());
            if (productType != null) {
                trolley.add(buildProduct(productType));
            }
            writeLine("Would you like anything else?");
            prompt();
            line = readLine();
        }
        writeLine(String.format("Thank you for visiting Generic Retailer, your total is %s", 0));
        printReceipt(trolley);
    }

    private void printReceipt(Trolley trolley) throws IOException {
        writeLine("===== RECEIPT ======");
        for (TrolleyItem trolleyItem : trolley.getItems().values()) {
            if (trolleyItem.getQuantity() == 1) {
                writeLine(String.format(SINGLE_ITEM_LINE_FORMAT,
                        trolleyItem.getProduct().getType(), POUND_SYMBOL + trolleyItem.getProduct().getPrice().setScale(2)));
            } else {
                writeLine(String.format(MULTIPLE_ITEM_LINE_FORMAT,
                        trolleyItem.getProduct().getType() + " (x" + trolleyItem.getQuantity() + ")", POUND_SYMBOL + trolleyItem.getTotal().setScale(2)));
            }
        }
        BigDecimal thursdayDiscount = trolley.applyDiscount(new ThursdayDiscount(this.date));
        if (thursdayDiscount.compareTo(BigDecimal.ZERO) > 0) {
            writeLine(String.format(THURSDAY_DISCOUNT_LINE_FORMAT, DISCOUNT_LINE_POUND_SYMBOL + thursdayDiscount.setScale(2)));
        }
        BigDecimal twoForOneDiscount = trolley.applyDiscount(new TwoForOneDiscount());
        if (twoForOneDiscount.compareTo(BigDecimal.ZERO) > 0) {
            writeLine(String.format(TWO_FOR_ONE_DISCOUNT_LINE_FORMAT, DISCOUNT_LINE_POUND_SYMBOL + twoForOneDiscount.setScale(2)));
        }
        writeLine("====================");
        writeLine(String.format(SUB_TOTAL_LINE_FORMAT, POUND_SYMBOL +
                trolley.calculateTotal().subtract(thursdayDiscount).subtract(twoForOneDiscount).setScale(2)));
    }

    private ProductType buildProductType(String productType) {
        for (ProductType type : ProductType.values()) {
            if (type.name().equals(productType)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }

    private Product buildProduct(ProductType productType) {
        switch (productType) {
            case BOOK:
                return new Book();
            case CD:
                return new CD();
            case DVD:
                return new DVD();
            default:
                return null;
        }
    }
}

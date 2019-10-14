package org.katas.refactoring;

public class OrderReceipt {
    private static final double SALES_TAX_RATE = .10;
    private Order order;
    private double totalSalesTax = 0d;
    private double lineItemTotalAmount = 0d;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = generateReceipt();
        return output.toString();
    }

    private StringBuilder generateReceipt() {
        StringBuilder output = new StringBuilder();

        output.append("======Printing Orders======\n")
                .append(order.getCustomerName())
                .append(order.getCustomerAddress());

        for(LineItem lineItem : order.getLineItems()){
            output.append(generateItemReceipt(lineItem));
            updateSalesTaxAndTotalAmount(lineItem);
        }

        output.append("Sales Tax\t").append(totalSalesTax)
                .append("Total Amount\t").append(lineItemTotalAmount);

        return output;
    }

    private String generateItemReceipt(LineItem lineItem) {
        return  lineItem.getDescription() + "\t" +
                lineItem.getPrice() + "\t" +
                lineItem.getQuantity() + "\t" +
                lineItem.calculateTotalAmount() + "\n";
    }

    private void updateSalesTaxAndTotalAmount(LineItem lineItem) {
        double totalAmount = lineItem.calculateTotalAmount();
        double salesTax = totalAmount * SALES_TAX_RATE;

        totalSalesTax += salesTax;
        lineItemTotalAmount += totalAmount+salesTax;
    }
}
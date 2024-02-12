package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Transactions {

    public static List<String> findRejectedTransactions(List<String> transactions, int creditLimit) {

        System.out.println(creditLimit);
        System.out.println(transactions.stream().map(transaction -> transaction.concat("\n")).collect(Collectors.toList()));

        Map<Customer, Integer> usersCredit = new HashMap();

        List<Transaction> transactionList =
                transactions.stream().map(transaction -> {
                    String[] columns = transaction.split(",");
                    return new Transaction(
                            new Customer(
                                    columns[0],
                                    columns[1],
                                    columns[2]),
                            Integer.parseInt(columns[3]),
                            columns[4]
                    );
                }).collect(Collectors.toList());

        return transactionList.stream()
                .filter(transaction -> {
                    if (!usersCredit.containsKey(transaction.getCustomer())) {
                        usersCredit.put(transaction.getCustomer(), creditLimit);
                    }

                    if (usersCredit.get(transaction.getCustomer()) - transaction.getAmount() < 0) {
                        return true;
                    } else {
                        usersCredit.put(transaction.getCustomer(), usersCredit.get(transaction.getCustomer()) - transaction.getAmount());
                        return false;
                    }
                })
                .map(transaction -> transaction.getId())
                .collect(Collectors.toList());

    }
}

class Transaction {

    public Transaction(Customer customer, int amount, String id) {
        this.customer = customer;
        this.amount = amount;
        this.id = id;
    }

    private Customer customer;

    private int amount;
    private String id;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Customer {

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
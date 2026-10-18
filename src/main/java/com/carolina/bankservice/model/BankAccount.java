package com.carolina.bankservice.model;

public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount destination, double amount) {
        if (destination == null) {
            return false;
        }

        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            destination.balance += amount;
            return true;
        }
        return false;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
}


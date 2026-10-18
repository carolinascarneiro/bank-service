package com.carolina.bankservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    void shouldReturnTrueIfTransferSuccessful() {
        BankAccount anna = new BankAccount("Anna", 100.0);
        BankAccount max = new BankAccount("Max", 200.0);

        boolean result = anna.transfer(max, 30);
        assertTrue(result);
        assertEquals(70.0, anna.getBalance());
        assertEquals(230.0, max.getBalance());
    }

    @Test
    void shouldReturnFalseIfTransferReceivesNegativeValue() {
        BankAccount anna = new BankAccount("Anna", 100.0);
        BankAccount max = new BankAccount("Max", 200.0);

        boolean result = anna.transfer(max, -30);
        assertFalse(result);
        assertEquals(100.0, anna.getBalance());
        assertEquals(200.0, max.getBalance());
    }

    @Test
    void shouldRejectTransferIfBalanceIsInsufficient() {
        BankAccount anna = new BankAccount("Anna", 100.0);
        BankAccount max = new BankAccount("Max", 200.0);

        boolean result = anna.transfer(max, 120);
        assertFalse(result);
        assertEquals(100.0, anna.getBalance());
        assertEquals(200.0, max.getBalance());
    }

    @Test
    void shouldReturnFalseIfTransferReceivesNullAsDestinationAccount() {
        BankAccount anna = new BankAccount("Anna", 100.0);
        boolean result = anna.transfer(null, 2);
        assertFalse(result);
        assertEquals(100.0, anna.getBalance());
    }
}

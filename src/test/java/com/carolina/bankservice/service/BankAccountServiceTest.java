package com.carolina.bankservice.service;

import com.carolina.bankservice.model.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {

    private BankAccountService bankAccountService;

    @BeforeEach
    void init() {
        bankAccountService = new BankAccountService();
    }

    @Test
    void shouldAddAccountSuccessfully() {
        BankAccount anna = new BankAccount("Anna", 100.0);
        boolean result = bankAccountService.addAccount(anna);

        assertTrue(result);
    }

    @Test
    void shouldRejectSameAccountInstance() {
        BankAccount anna = new BankAccount("Anna", 100.0);

        bankAccountService.addAccount(anna);
        boolean result = bankAccountService.addAccount(anna);
        assertFalse(result);
    }

    @Test
    void shouldAllowTwoDifferentAccountsWithSameOwner() {
        BankAccount anna1 = new BankAccount("Anna", 100.0);
        BankAccount anna2 = new BankAccount("Anna", 200.0);

        boolean result = bankAccountService.addAccount(anna1);
        boolean result2 = bankAccountService.addAccount(anna2);
        boolean result3 = bankAccountService.addAccount(anna1);

        assertTrue(result);
        assertTrue(result2);
        assertFalse(result3);
    }

    @Test
    void getsAccountWithRightOwnerWhenOwnerGiven() {
        BankAccount anna = new BankAccount("Anna", 50.0);
        BankAccount max = new BankAccount("Max", 100.0);

        bankAccountService.addAccount(anna);
        bankAccountService.addAccount(max);

        BankAccount result = bankAccountService.findAccountByOwner("Anna");
        BankAccount result2 = bankAccountService.findAccountByOwner("Max");
        assertEquals("Anna", result.getOwner());
        assertEquals("Max", result2.getOwner());
    }

    @Test
    void getsNullWhenOwnerFalseCaseGiven() {
        BankAccount anna = new BankAccount("Anna", 50.0);
        BankAccount max = new BankAccount("Max", 100.0);
        bankAccountService.addAccount(anna);
        bankAccountService.addAccount(max);

        BankAccount result = bankAccountService.findAccountByOwner("anna");
        assertNull(result);
    }

    @Test
    void verifyIfInstanceIsTheSameWhenFindAccountByOwner() {
        BankAccount max = new BankAccount("Max", 100.0);
        bankAccountService.addAccount(max);
        BankAccount result = bankAccountService.findAccountByOwner("Max");
        //assertEquals(result, max);
        Assertions.assertSame(max, result);
    }

    @Test
    void getsNullWhenLooksForAccountOfUnregisteredUser() {
        BankAccount anna = new BankAccount("Anna", 50.0);
        bankAccountService.addAccount(anna);
        BankAccount result = bankAccountService.findAccountByOwner("Julia");
        assertNull(result);
    }

    @Test
    void returnsFirstInstanceWhenTwoAccountsWithSameOwner() {
        BankAccount anna1 = new BankAccount("Anna", 100.0);
        BankAccount anna2 = new BankAccount("Anna", 200.0);
        bankAccountService.addAccount(anna1);
        bankAccountService.addAccount(anna2);
        BankAccount result = bankAccountService.findAccountByOwner("Anna");
        Assertions.assertSame(anna1, result);
    }

    @Test
    void shouldReturnNullWhenOwnerIsNull() {
        BankAccount anna = new BankAccount("Anna", 100.0);
        bankAccountService.addAccount(anna);
        BankAccount result = bankAccountService.findAccountByOwner(null);
        Assertions.assertNull(result);
    }

    @Test
    void shouldReturnCorrectInstanceIfFindsIncompleteOwnerData() {
        BankAccount mysterious = new BankAccount(null, 100.0);
        BankAccount anna = new BankAccount("Anna", 200.0);

        bankAccountService.addAccount(mysterious);
        bankAccountService.addAccount(anna);

        BankAccount result = bankAccountService.findAccountByOwner("Anna");
        assertSame(anna, result);
    }
}


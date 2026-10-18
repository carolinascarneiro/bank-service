package com.carolina.bankservice.service;

import com.carolina.bankservice.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {

    @Test
    void shouldAddAccountSuccessfully() {
        BankAccountService service = new BankAccountService();
        BankAccount anna = new BankAccount("Anna", 100.0);

        boolean result = service.addAccount(anna);

        assertTrue(result);
    }

    @Test
    void shouldRejectSameAccountInstance() {
        BankAccountService service = new BankAccountService();
        BankAccount anna = new BankAccount("Anna", 100.0);

        service.addAccount(anna);
        boolean result = service.addAccount(anna);
        assertFalse(result);
    }

    @Test
    void shouldAllowTwoDifferentAccountsWithSameOwner() {
        BankAccountService service = new BankAccountService();
        BankAccount anna1 = new BankAccount("Anna", 100.0);
        BankAccount anna2 = new BankAccount("Anna", 200.0);

        boolean result = service.addAccount(anna1);
        boolean result2 = service.addAccount(anna2);
        boolean result3 = service.addAccount(anna1);

        assertTrue(result);
        assertTrue(result2);
        assertFalse(result3);
    }

    @Test
    void getsAccountWithRightOwnerWhenOwnerGiven() {
        BankAccount anna = new BankAccount("Anna", 50.0);
        BankAccount max = new BankAccount("Max", 100.0);
        BankAccountService bankAccountService = new BankAccountService();

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
        BankAccountService bankAccountService = new BankAccountService();

        bankAccountService.addAccount(anna);
        bankAccountService.addAccount(max);

        BankAccount result = bankAccountService.findAccountByOwner("anna");
        assertNull(result);
    }


}


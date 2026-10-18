package com.carolina.bankservice.service;

import com.carolina.bankservice.model.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankAccountService {

    private List<BankAccount> bankAccountList = new ArrayList<>();

    public boolean addAccount(BankAccount account) {
        if (account == null) {
            return false;
        }

        for (BankAccount existing : bankAccountList) {
            if (existing == account) {
                return false;
            }
        }

        bankAccountList.add(account);
        return true;
    }

    public BankAccount findAccountByOwner(String owner) {
        for (BankAccount account : this.bankAccountList) {
            if (account.getOwner().equals(owner)) {
                return account;
            }
        }
        return null;
    }
}

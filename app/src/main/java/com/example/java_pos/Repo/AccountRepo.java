package com.example.java_pos.Repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.java_pos.Models.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AccountRepo {
    private static final String PREFS_NAME = "user_accounts";
    private static final String ACCOUNTS_KEY = "accounts";
    private static final String LOGGED_IN_USER_KEY = "logged_in_user";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Map<String, Account> accounts;

    public AccountRepo(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        loadAccounts();
    }

    private void loadAccounts() {
        String json = sharedPreferences.getString(ACCOUNTS_KEY, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<Map<String, Account>>() {}.getType();
            accounts = gson.fromJson(json, type);
        } else {
            accounts = new HashMap<>();
        }
    }

    private void saveAccounts() {
        String json = gson.toJson(accounts);
        sharedPreferences.edit().putString(ACCOUNTS_KEY, json).apply();
    }

    private void saveLoggedInUser(String username) {
        sharedPreferences.edit().putString(LOGGED_IN_USER_KEY, username).apply();
    }

    private String getLoggedInUser() {
        return sharedPreferences.getString(LOGGED_IN_USER_KEY, null);
    }

    public boolean register(String username, String password) {
        if (accounts.containsKey(username)) {
            return false; // Username already exists
        }
        Account account = new Account(username, password);
        accounts.put(username, account);
        saveAccounts();
        return true;
    }

    public Account login(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            account.setLoggedIn(true);
            saveAccounts();
            saveLoggedInUser(username);
            return account;
        }
        return null; // Invalid username or password
    }

    public void logout(String username) {
        Account account = accounts.get(username);
        if (account != null) {
            account.setLoggedIn(false);
            saveAccounts();
            saveLoggedInUser(null); // Clear the logged-in user
        }
    }

    public boolean isRegistered(String username) {
        return accounts.containsKey(username);
    }

    public Account getLoggedInAccount() {
        String username = getLoggedInUser();
        if (username != null) {
            return accounts.get(username);
        }
        return null;
    }
}

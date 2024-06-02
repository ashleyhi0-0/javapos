package com.example.java_pos.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.java_pos.Models.Order;
import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.java_pos.Models.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefHelper {

    private static final String ORDER_PREF_NAME = "order_pref";

    // Store order list in shared preferences
    public static void storeOrderList(Context context, List<Order> orderList) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ORDER_PREF_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(orderList);
        editor.putString("orderList", json);
        editor.apply();
    }

    // Retrieve order list from shared preferences
    public static List<Order> retrieveOrderList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ORDER_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("orderList", null);
        Type type = new TypeToken<List<Order>>() {}.getType();
        return gson.fromJson(json, type);
    }


    // Remove order list from shared preferences
    public static void removeOrderList(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ORDER_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.remove("orderList");
        editor.apply();
    }

}

package com.example.foodorderapp.activity;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapter.OrderAdapter;
import com.example.foodorderapp.model.Order;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    GridView orderHistoryList;
    ArrayList<Order> orderList;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         orderHistoryList = findViewById(R.id.lvOrderHistory);
         orderList = new ArrayList<>();
         orderList.add(new Order("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 200, 10));
         orderAdapter = new OrderAdapter(this, orderList);
         orderHistoryList.setAdapter(orderAdapter);

    }
}
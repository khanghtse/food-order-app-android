package com.example.foodorderapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapter.FoodAdapter;
import com.example.foodorderapp.model.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {

    GridView gridView;
    FoodAdapter foodAdapter;
    ArrayList<Food> foodList;
    SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(FoodListActivity.this, v);
            popupMenu.getMenuInflater().inflate(R.menu.option_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.logout) {
                        Toast.makeText(FoodListActivity.this, "Click logout", Toast.LENGTH_SHORT).show();
                    } else if (item.getItemId() == R.id.orderHistory) {
                        Intent intent = new Intent(FoodListActivity.this, OrderHistoryActivity.class);
                        startActivity(intent);
                    }
                    return false;
                }
            });
            popupMenu.show();
        });

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        gridView = findViewById(R.id.foodListView);
        foodList = new ArrayList<>();
        loadFoodData();

        foodAdapter = new FoodAdapter(this, foodList);
        gridView.setAdapter(foodAdapter);

        // Handle pull-to-refresh
        swipeRefreshLayout.setOnRefreshListener(() -> {
            refreshFoodData();  // Your method to refresh data
            swipeRefreshLayout.setRefreshing(false);  // Stop the refresh animation
        });
//        foodAdapter = new FoodAdapter(this, foodList);
//        gridView.setAdapter(foodAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(FoodListActivity.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_cart) {
                    Toast.makeText(FoodListActivity.this, "cart", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_profile) {
                    Toast.makeText(FoodListActivity.this, "profile", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_location) {
                    Toast.makeText(FoodListActivity.this, "location", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    // Method to load the initial data
    private void loadFoodData() {
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        foodList.add(new Food("Salad", R.drawable.salah, "Ingredients: Lettuce, Tomato, Cucumber", 10.00, 100));
        // Add more food items...
    }

    // Method to refresh data
    private void refreshFoodData() {
        foodList.clear();  // Clear the existing list
        loadFoodData();    // Reload the data
        foodAdapter.notifyDataSetChanged();  // Notify adapter of the data change
    }
}
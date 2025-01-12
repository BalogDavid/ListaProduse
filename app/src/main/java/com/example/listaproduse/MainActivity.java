package com.example.listaproduse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.listaproduse.adapter.ProductAdapter;
import com.example.listaproduse.api.ApiClient;
import com.example.listaproduse.api.ProductApi;
import com.example.listaproduse.model.Product;
import com.example.listaproduse.response.ProductResponse;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView productsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        productsRecyclerView = findViewById(R.id.productsRecyclerView);

        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(v -> searchProducts(searchEditText.getText().toString()));
    }

    private void searchProducts(String query) {
        // Înlocuiește cu biblioteca ta preferată pentru HTTP requests (ex. Retrofit sau Volley).
        new Thread(() -> {
            try {
                String url = "https://dummyjson.com/products/search?q=" + query;
                String response = Utils.httpGet(url); // Utils este o clasă pentru HTTP requests
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray productsArray = jsonResponse.getJSONArray("products");

                List<Product> products = new ArrayList<>();
                for (int i = 0; i < productsArray.length(); i++) {
                    JSONObject productObj = productsArray.getJSONObject(i);
                    products.add(new Product(
                            productObj.getInt("id"),
                            productObj.getString("title"),
                            productObj.getString("description"),
                            productObj.getString("thumbnail")
                    ));
                }

                runOnUiThread(() -> productsRecyclerView.setAdapter(new ProductAdapter(MainActivity.this, products)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

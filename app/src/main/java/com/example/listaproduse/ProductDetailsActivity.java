package com.example.listaproduse;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.listaproduse.R;
import com.example.listaproduse.model.Product;



public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ImageView productImageView = findViewById(R.id.productImageView);
        TextView productTitleTextView = findViewById(R.id.productTitleTextView);
        TextView productDescriptionTextView = findViewById(R.id.productDescriptionTextView);

        Product product;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            product = (Product) getIntent().getSerializableExtra("product", Product.class);
        } else {
            product = (Product) getIntent().getSerializableExtra("product");
        }

        if (product != null) {
            productTitleTextView.setText(product.getTitle());
            productDescriptionTextView.setText(product.getDescription());
            Glide.with(this)
                    .load(product.getImage())

                    .into(productImageView);
        } else {
            Log.e("ProductDetails", "Product object is null!");
        }
    }


}

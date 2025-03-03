package android.ejemplo.es.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Obtener el email desde el Intent
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        String userName = getIntent().getStringExtra("USERNAME");
        // Obtener el ImageView por su ID
        ImageView userIcon = findViewById(R.id.user_icon);

        // Establecer el OnClickListener para el icono
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad de perfil
                Intent intent = new Intent(ShopActivity.this, ProfileActivity.class);

                // Pasar el email al perfil
                intent.putExtra("USER_EMAIL", userEmail);
                intent.putExtra("USERNAME", userName);
                startActivity(intent);
            }
        });


        // Configurar el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.products_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Crear una lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("Paquete de A4", "10.99", R.drawable.papel));
        products.add(new Product("Lapiz 2H", "0.50", R.drawable.lapiz));
        products.add(new Product("3 Gomas Milan", "1.00", R.drawable.gomas));
        products.add(new Product("Tijeras Mapped", "8.99", R.drawable.tijeras));
        products.add(new Product("Boligrafo BIC", "0.25", R.drawable.boli));

        // Crear y asignar un Adapter
        MyAdapter adapter = new MyAdapter(products);
        recyclerView.setAdapter(adapter);
    }

    // Adapter interno
    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<Product> itemList;

        public MyAdapter(List<Product> itemList) {
            this.itemList = itemList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Product product = itemList.get(position);
            holder.productName.setText(product.getName());
            holder.productPrice.setText(product.getPrice()+"€");
            holder.productImage.setImageResource(product.getImageResId());
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView productImage;
            TextView productName;
            TextView productPrice;

            public MyViewHolder(View itemView) {
                super(itemView);
                productImage = itemView.findViewById(R.id.product_image);
                productName = itemView.findViewById(R.id.product_name);
                productPrice = itemView.findViewById(R.id.product_price);
            }
        }
    }

    // Clase para representar el producto
    public static class Product {
        private String name;
        private String price;
        private int imageResId;  // Recurso drawable

        public Product(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public int getImageResId() {
            return imageResId;
        }
    }
}


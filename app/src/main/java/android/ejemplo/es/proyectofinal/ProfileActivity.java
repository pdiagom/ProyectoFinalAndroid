package android.ejemplo.es.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // Obtener el email desde el Intent
        String userName=getIntent().getStringExtra("USERNAME");
        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Buscar el TextView donde se mostrará el email
        TextView emailTextView = findViewById(R.id.profile_email);
        TextView userNameTextView = findViewById(R.id.profile_name);

        // Mostrar el email en el TextView
        if (userEmail != null) {
            emailTextView.setText(userEmail);
            userNameTextView.setText(userName);
        } else {
            emailTextView.setText("No hay email disponible");
        }

        // Referencia al botón de Logout
        Button btnLogout = findViewById(R.id.btnLogout);

        // Configurar el listener para cerrar sesión
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Borra el historial de actividades
                startActivity(intent);
                finish();
            }
        });

        LinearLayout navHome = findViewById(R.id.nav_home);
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ShopActivity.class);
                intent.putExtra("USERNAME", userName);
                intent.putExtra("USER_EMAIL", userEmail);
                startActivity(intent);
                finish(); // Opcional: cerrar ProfileActivity
            }
        });
    }
}

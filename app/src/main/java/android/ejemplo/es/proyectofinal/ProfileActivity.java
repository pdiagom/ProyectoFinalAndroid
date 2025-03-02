package android.ejemplo.es.proyectofinal;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Obtener el email desde el Intent
        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Buscar el TextView donde se mostrará el email
        TextView emailTextView = findViewById(R.id.profile_email);

        // Mostrar el email en el TextView
        if (userEmail != null) {
            emailTextView.setText(userEmail);
        } else {
            emailTextView.setText("No hay email disponible");
        }
    }
}

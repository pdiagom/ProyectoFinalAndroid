package android.ejemplo.es.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private String username;  // Variable para almacenar el username

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        // Referencias a los inputs
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnRegister = findViewById(R.id.btnRegister);

        // Recuperar los datos enviados desde RegisterActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("EMAIL", "");
            String password = extras.getString("PASSWORD", "");
            username = extras.getString("USERNAME", "");  // Guardamos el username
            etEmail.setText(email);
            etPassword.setText(password);
        }

        // Listener para iniciar sesión
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String emailIngresado = etEmail.getText().toString().trim();
                btnSignIn.startAnimation(scaleUp);
                // Pasar el email y username a ShopActivity
                Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
                intent.putExtra("USER_EMAIL", emailIngresado);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        // Listener para ir a la pantalla de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegister.startAnimation(scaleUp);
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

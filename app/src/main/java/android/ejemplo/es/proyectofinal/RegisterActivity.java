package android.ejemplo.es.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
        private TextView registro;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registro= findViewById(R.id.registro);

        Animation entrada= AnimationUtils.loadAnimation(this,R.anim.entrada);

        registro.startAnimation(entrada);
        // Referencias a los inputs
        EditText etUsername= findViewById(R.id.etUsername);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String username=etUsername.getText().toString();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PASSWORD", password);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "La actividad está a punto de ser visible.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "La actividad está siendo visible.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "La actividad está en segundo plano.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "La actividad no es visible.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "La actividad está siendo destruida.");
    }
}

package android.ejemplo.es.proyectofinal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int REQUEST_CALL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Obtener el email desde el Intent
        String userName = getIntent().getStringExtra("USERNAME");
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

        // Llamar al botón para atención al cliente
        LinearLayout optionCall = findViewById(R.id.option_call);
        optionCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
    }

    private void makeCall() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, se solicita
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Si ya se tiene el permiso, hacer la llamada
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:656123432")); // Reemplaza con el número de atención
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El permiso fue concedido, hacer la llamada
                makeCall();
            } else {
                // El permiso fue denegado, mostrar mensaje o manejar el caso
                Log.d(TAG, "Permiso de llamada no concedido.");
            }
        }
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

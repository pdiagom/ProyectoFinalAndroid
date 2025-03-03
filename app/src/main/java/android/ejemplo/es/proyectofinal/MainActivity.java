package android.ejemplo.es.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView bienvenida;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bienvenida = findViewById(R.id.bienvenida);
        Button btnLogin = findViewById(R.id.btnLogin);
        Animation pulsar = AnimationUtils.loadAnimation(this, R.anim.pulso);

        // Aplicar la animación al TextView
        bienvenida.startAnimation(pulsar);

        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
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

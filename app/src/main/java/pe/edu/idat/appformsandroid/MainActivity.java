package pe.edu.idat.appformsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.idat.appformsandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnListado.setOnClickListener(this);
        binding.btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnListado ) {
            startActivity(new Intent(MainActivity.this,
                    ListaActivity.class));

        } else if (view.getId() == R.id.btnRegistro) {

            startActivity(new Intent( MainActivity.this,
                    RegistroActivity.class));
        }
    }
}
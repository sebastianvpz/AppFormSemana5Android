package pe.edu.idat.appformsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import pe.edu.idat.appformsandroid.databinding.ActivityListaBinding;

public class ListaActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityListaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<String> ListaPersonas = (ArrayList<String>)
                getIntent().getSerializableExtra("ListaPersonas");
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                ListaPersonas
        );
        binding.lvPersonas.setAdapter(arrayAdapter);
        binding.btnRegresarRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),RegistroActivity.class));
    }
}
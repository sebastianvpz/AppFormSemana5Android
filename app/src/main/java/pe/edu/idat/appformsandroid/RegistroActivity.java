package pe.edu.idat.appformsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.idat.appformsandroid.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ActivityRegistroBinding binding;
    private String estadocivil = "";

    private List<String> preferencias = new ArrayList<>();
    private List<String> personas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<CharSequence> adapterSpinner =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.estado_civil,
                        android.R.layout.simple_spinner_item
                );
        binding.spEstadoCivil.setAdapter(adapterSpinner);
        binding.btnRegistrarPer.setOnClickListener(this);
        binding.cbDeporte.setOnClickListener(this);
        binding.cbArte.setOnClickListener(this);
        binding.cbOtros.setOnClickListener(this);
        binding.spEstadoCivil.setOnItemSelectedListener(this);
    }



    private String obtenerGenero() {
        String genero = "";

        if (binding.rgGenero.getCheckedRadioButtonId() == R.id.rbMasculino) {
            genero = binding.rbMasculino.getText().toString();
        }else {
            genero = binding.rbFemenino.getText().toString();
        }
        return  genero;
    }

    private void agregarQuitarPreferencias(View view, String preferencia) {
        boolean checked = ((CheckBox)view).isChecked();

        if (checked) {
            preferencias.add(preferencia);
        }else {
            preferencias.remove(preferencia);
        }
    }

    private boolean validarNombreApellido() {
        boolean answer = true;

        if (binding.txtNombre.getText().toString().trim().isEmpty()) {
            binding.txtNombre.setFocusableInTouchMode(true);
            binding.txtNombre.requestFocus();
            answer = false;
        } else if (binding.txtApellidos.getText().toString().trim().isEmpty()) {
            binding.txtApellidos.setFocusableInTouchMode(true);
            binding.txtApellidos.requestFocus();
            answer = false;
        }
        return  answer;
    }

    private boolean validarGenero() {
        boolean answer = true;

        if (binding.rgGenero.getCheckedRadioButtonId() == -1) {
            answer = false;
        }
        return  answer;
    }

    private boolean validarPreferencias() {
        boolean answer = false;

        if(binding.cbDeporte.isChecked() || binding.cbArte.isChecked() || binding.cbOtros.isChecked()) {
            answer = true;
        }

        return  answer;
    }

    private  boolean validarEstadoCivil() {

        boolean answer = true  ;

        if (estadocivil.equals("")) {
            answer = false;
        }
        return answer;
    }

    private boolean validarFormulario() {
        boolean answer = false;

        String mensaje = "";
        if (!validarNombreApellido()) {
            mensaje = "Ingrese su nombre y apellido";
        } else if (!validarGenero()) {
            mensaje = "Seleccione su genero";
        } else if (!validarPreferencias()) {
            mensaje = "Seleccione al menos una preferencia";
        } else if (!validarEstadoCivil()) {
            mensaje = "Selecciones su estado civil";
        }else {
            answer = true;
        }

        if (!answer)
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();

        return answer;
    }

    private  void registrarPersona() {

        if (validarFormulario()) {
            StringBuilder infoPersona = new StringBuilder();
            infoPersona.append(binding.txtNombre.getText().toString() + "-");
            infoPersona.append(binding.txtApellidos.getText().toString() + "-");
            infoPersona.append(obtenerGenero()+ "-");
            infoPersona.append(preferencias.toString() + "-");
            infoPersona.append(estadocivil + "-");
            infoPersona.append(binding.swNotificacion.isChecked());
            personas.add(infoPersona.toString());
            Toast.makeText(this, "Persona Registrada", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (i == 0) {
            estadocivil = "";
        }else {
            estadocivil = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.cbDeporte) {
            agregarQuitarPreferencias(view, "Deporte");
        } else if (view.getId() == R.id.cbArte) {
            agregarQuitarPreferencias(view, "Arte");
        } else if (view.getId() == R.id.cbOtros ) {
            agregarQuitarPreferencias(view, "Otras Preferencias");
        } else if (view.getId() == R.id.btnRegistrarPer) {
            registrarPersona();
            setearControles();

        } else if (view.getId() == R.id.btnRegiListado)  {
            Intent intentLista = new Intent(getApplicationContext(),
                    ListaActivity.class);
            intentLista.putExtra("ListaPersonas",
                    (ArrayList<String>)personas);
            startActivity(intentLista);

        }
    }

    private void  setearControles() {
        binding.txtNombre.setText("");
        binding.txtApellidos.setText("");
        binding.rgGenero.clearCheck();
        binding.cbDeporte.setChecked(false);
        binding.cbArte.setChecked(false);
        binding.cbOtros.setChecked(false);
        binding.spEstadoCivil.setSelection(0);
        binding.swNotificacion.setChecked(false);
        preferencias.clear();
        binding.txtNombre.setFocusableInTouchMode(true);
        binding.txtNombre.requestFocus();

    }
}
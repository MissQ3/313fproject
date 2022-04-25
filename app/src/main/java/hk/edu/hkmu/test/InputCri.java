package hk.edu.hkmu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class InputCri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_cri);

        Spinner spinner1 = findViewById(R.id.categoryspinner);
        Spinner spinner2 = findViewById(R.id.genderspinner);
        Spinner spinner3 = findViewById(R.id.sessionspinner);
        Spinner spinner4 = findViewById(R.id.districtspinner);
        Spinner spinner5 = findViewById(R.id.fintypespinner);
        Spinner spinner6 = findViewById(R.id.levelspinner);
        Spinner spinner7 = findViewById(R.id.religionspinner);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.categoryspinner, android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(0, false);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.genderspinner, android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0, false);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.sessionspinner, android.R.layout.simple_spinner_item);
        spinner3.setAdapter(adapter3);
        spinner3.setSelection(0, false);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this, R.array.districtspinner, android.R.layout.simple_spinner_item);
        spinner4.setAdapter(adapter4);
        spinner4.setSelection(0, false);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this, R.array.fintypespineer, android.R.layout.simple_spinner_item);
        spinner5.setAdapter(adapter5);
        spinner5.setSelection(0, false);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter6 = ArrayAdapter.createFromResource(this, R.array.levelspinner, android.R.layout.simple_spinner_item);
        spinner6.setAdapter(adapter6);
        spinner6.setSelection(0, false);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter7 = ArrayAdapter.createFromResource(this, R.array.religionspinner, android.R.layout.simple_spinner_item);
        spinner7.setAdapter(adapter7);
        spinner7.setSelection(0, false);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

}
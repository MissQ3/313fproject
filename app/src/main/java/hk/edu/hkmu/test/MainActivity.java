package hk.edu.hkmu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button name;
    private Button criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.button1);
        criteria = findViewById(R.id.button2);
        name.setOnClickListener(this);
        criteria.setOnClickListener(this);
    }

    public void onClick(View button) {
        if (button.getId() == R.id.button1) {
            Intent intent = new Intent(MainActivity.this, InputName.class);
            startActivity(intent);
            finish();
        } else if (button.getId() == R.id.button2) {
            Intent intent = new Intent(MainActivity.this, InputCri.class);
            startActivity(intent);
            finish();
        }
    }
}
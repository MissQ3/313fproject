package hk.edu.hkmu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class InputName extends AppCompatActivity {
    static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private EditText EditText;
    private Button SubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        EditText = findViewById(R.id.name);
        SubmitButton = findViewById(R.id.submitbutton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Str = EditText.getText().toString();
                Intent intent = new Intent(InputName.this, Result.class);
                intent.putExtra(EXTRA_MESSAGE, Str);
                startActivity(intent);
            }
        });
    }
}

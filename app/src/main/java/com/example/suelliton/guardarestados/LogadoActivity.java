package com.example.suelliton.guardarestados;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogadoActivity extends AppCompatActivity {
    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        Intent intent = getIntent();
        Bundle bundle =  intent.getExtras();
        String login = bundle.getString("login");
        TextView tlogin = (TextView) findViewById(R.id.Tlogin);
        tlogin.setText(login);

        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c,MainActivity.class);
                setResult(RESULT_CANCELED,intent);
               finish();
            }
        });
    }
}

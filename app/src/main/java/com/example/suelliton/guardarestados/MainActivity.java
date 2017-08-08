package com.example.suelliton.guardarestados;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public  static final int REQUEST_LOGIN = 1;
	Context c = this;
	EditText edtLogin ;
	EditText edtSenha ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtLogin.setText(prefs.getString("login",""));

        if(prefs.getString("login","") == "" || prefs.getString("senha","") == "" ) {
            Button btnLogar = (Button) findViewById(R.id.btnLogar);
            btnLogar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                edtLogin = (EditText) findViewById(R.id.edtLogin);
                                                edtSenha = (EditText) findViewById(R.id.edtSenha);
                                                String login = edtLogin.getText().toString();
                                                String senha = edtSenha.getText().toString();
                                                if (login.equals("suelliton") && senha.equals("123")) {
                                                    Intent intent = new Intent(c, LogadoActivity.class);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("login", login);
                                                    bundle.putString("senha", senha);
                                                    intent.putExtras(bundle);
                                                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = prefs.edit();
                                                    editor.putString("login", login);
                                                    editor.putString("senha", senha);
                                                    editor.commit();
                                                    startActivityForResult(intent,REQUEST_LOGIN);
                                                }

                                            }
                                        }
            );
        }else{
            Intent intent = new Intent(c,LogadoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("login",prefs.getString("login",""));
            intent.putExtras(bundle);
            startActivity(intent);
        }

                
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == REQUEST_LOGIN ){
           if(resultCode == RESULT_CANCELED){
               SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
               SharedPreferences.Editor editor = prefs.edit();
               editor.remove("senha");
               editor.commit();
               edtSenha.setText("");
               edtLogin.setText(prefs.getString("login",""));

           }
       }
    }
}

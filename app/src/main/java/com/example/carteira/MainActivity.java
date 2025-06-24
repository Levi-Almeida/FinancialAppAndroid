package com.example.carteira;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carteira.util.Constantes;

public class MainActivity extends AppCompatActivity {


    // Alunos:  Levi Almeida | Mariana Novaes
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvCadastro = findViewById(R.id.tvCadastro);
        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });

        Button btLogar = findViewById(R.id.btLogar);
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin();
            }
        });
    }

    private void validarLogin() {
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etLoginSenha = findViewById(R.id.etLoginSenha);

        SharedPreferences prefs = getSharedPreferences(Constantes.PREFS_NAME, MODE_PRIVATE);
        String usuarioPref = prefs.getString(Constantes.USUARIO_NOME, null);
        String senhaPref = prefs.getString(Constantes.USUARIO_SENHA, null);

        if (usuarioPref.equals(etUsuario.getText().toString())) {
            if (senhaPref.equals(etLoginSenha.getText().toString())) {
                Intent telaHome = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(telaHome);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Senha inválida",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Usuário inválido",
                    Toast.LENGTH_LONG).show();
        }
    }






}

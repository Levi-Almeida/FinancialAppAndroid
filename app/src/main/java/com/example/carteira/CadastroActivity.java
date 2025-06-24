package com.example.carteira;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carteira.util.Constantes;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btCadastrar = findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {
        EditText etNome = findViewById(R.id.etNome);
        EditText etSobrenome = findViewById(R.id.etSobrenome);
        EditText etSenha = findViewById(R.id.etSenha);
        EditText etConfirmaSenha = findViewById(R.id.etConfirmaSenha);
        EditText etTelefone = findViewById(R.id.etTelefone);

        if (etSenha.getText().toString()
                .equals(etConfirmaSenha.getText().toString())) {
            // Realizar o cadastro
            SharedPreferences prefs = getSharedPreferences(Constantes.PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(Constantes.USUARIO_NOME, etNome.getText().toString());
            editor.putString(Constantes.USUARIO_SOBRENOME, etSobrenome.getText().toString());
            editor.putString(Constantes.USUARIO_TELEFONE, etTelefone.getText().toString());
            editor.putString(Constantes.USUARIO_SENHA, etSenha.getText().toString());

            editor.apply();

            Toast.makeText(CadastroActivity.this,
                    "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(CadastroActivity.this,
                    "Senhas não conferem", Toast.LENGTH_LONG).show();
        }
    }
}
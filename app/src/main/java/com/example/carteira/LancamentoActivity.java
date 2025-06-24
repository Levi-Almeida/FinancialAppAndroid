package com.example.carteira;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carteira.dao.CategoriaDAO;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Categoria;
import com.example.carteira.model.Lancamento;

import java.util.List;

public class LancamentoActivity extends AppCompatActivity {

    private Spinner spinnerCategoria;
    private String tipoLancamento;
    private String categoriaSelecionada;
    private CategoriaDAO categoriaDAO;
    private ArrayAdapter<Categoria> categoriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lancamento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        categoriaDAO = new CategoriaDAO(this);

        ImageButton ibAddCategoria = findViewById(R.id.imageButtonAddCategoria);
        ibAddCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarCategoria();
            }
        });
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        loadCategorias();
        handleRadioButton();

        Button btCadastrar = findViewById(R.id.buttonCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        ImageView ivRelatorio = findViewById(R.id.ivRelatorio);
        ivRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaRelatorio = new Intent(LancamentoActivity.this, RelatorioActivity.class);
                startActivity(telaRelatorio);
            }
        });

        ImageView ivListagem = findViewById(R.id.ivListagem);
        ivListagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaHome = new Intent(LancamentoActivity.this, HomeActivity.class);
                startActivity(telaHome);
            }
        });
    }

    private void loadCategorias() {
        categoriaDAO = new CategoriaDAO(this);
        List<Categoria> categorias = categoriaDAO.listarCategorias();

        categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(categoriaAdapter);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSelecionada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void reloadSpinner() {
        categoriaAdapter.clear();
        List<Categoria> categorias = categoriaDAO.listarCategorias();
        for (Categoria c: categorias){
            categoriaAdapter.add(c);
        }
        categoriaAdapter.notifyDataSetChanged();
    }

    private void cadastrarCategoria() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LancamentoActivity.this);
        builder.setTitle("Cadastrar Categoria");

        LinearLayout linearLayout = new LinearLayout(LancamentoActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(32, 32, 32, 32);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        EditText etCategoria = new EditText(LancamentoActivity.this);
        etCategoria.setLayoutParams(params);
        etCategoria.setHint("Nome da Categoria");

        linearLayout.addView(etCategoria);
        builder.setView(linearLayout);

        builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Categoria categoria = new Categoria(0, etCategoria.getText().toString());
                categoriaDAO.inserirCategoria(categoria);
                Toast.makeText(LancamentoActivity.this, "Categoria cadastrada", Toast.LENGTH_LONG).show();
                reloadSpinner();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void handleRadioButton() {
        RadioButton rdEntrada = findViewById(R.id.radioButtonEntrada);
        rdEntrada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tipoLancamento = "Entrada";
            }
        });

        RadioButton rdSaida = findViewById(R.id.radioButtonSaida);
        rdSaida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tipoLancamento = "Saída";
            }
        });
    }

    private void cadastrar() {
        EditText etDescricao = findViewById(R.id.editTextDescricao);
        EditText etValor = findViewById(R.id.editTextValor);

        String descricao = etDescricao.getText().toString();
        double valor = Double.parseDouble(etValor.getText().toString());

        Lancamento lancamento = new Lancamento(
                0, categoriaSelecionada, descricao, tipoLancamento,
                valor
        );
        LancamentoDAO lDAO = new LancamentoDAO(LancamentoActivity.this);
        lDAO.inserirLancamento(lancamento);
        Toast.makeText(LancamentoActivity.this, "Lançamento Cadastrado", Toast.LENGTH_LONG).show();
        finish();
    }
}















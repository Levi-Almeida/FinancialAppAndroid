package com.example.carteira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carteira.adapter.LancamentoAdapter;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Lancamento;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ivLancamento = findViewById(R.id.ivLancamento);
        ivLancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLancamento = new Intent(HomeActivity.this, LancamentoActivity.class);
                startActivity(telaLancamento);
            }
        });

        ImageView ivRelatorio = findViewById(R.id.ivRelatorio);
        ivRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaRelatorio = new Intent(HomeActivity.this, RelatorioActivity.class);
                startActivity(telaRelatorio);
            }
        });

        loadLancamentos();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadLancamentos();
    }

    private void loadLancamentos() {
        LancamentoDAO lancamentoDAO = new LancamentoDAO(this);
        List<Lancamento> lancamentos = lancamentoDAO.listarLancamentos();
        LancamentoAdapter lancamentosAdapter = new LancamentoAdapter(lancamentos, HomeActivity.this);

        ListView listViewLancamentos = findViewById(R.id.listViewLancamentos);
        listViewLancamentos.setAdapter(lancamentosAdapter);
    }
}
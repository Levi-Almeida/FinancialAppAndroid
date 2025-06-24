package com.example.carteira;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carteira.adapter.LancamentoAdapter;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Lancamento;

import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    double entradas = 00.00;
    double saidas = 00.00;

    double saldo = 00.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        loadValues();

        Button btnGerarRelatorio = findViewById(R.id.btnGerarRelatorio);
        btnGerarRelatorio.setOnClickListener(view -> showReportDialog());

        ImageView ivLancamento = findViewById(R.id.ivLancamento);
        ivLancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLancamento = new Intent(RelatorioActivity.this, LancamentoActivity.class);
                startActivity(telaLancamento);
            }
        });

        ImageView ivListagem = findViewById(R.id.ivListagem);
        ivListagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaHome = new Intent(RelatorioActivity.this, HomeActivity.class);
                startActivity(telaHome);
            }
        });
    }

    private void showReportDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_report, null);

        TextView txtEntradas = dialogView.findViewById(R.id.txtEntradas);
        TextView txtSaidas = dialogView.findViewById(R.id.txtSaidas);
        TextView txtSaldo = dialogView.findViewById(R.id.txtSaldo);

        double saldo = entradas - saidas;

        txtEntradas.setText("Entradas: R$ " + String.format("%.2f", entradas));
        txtSaidas.setText("Saídas: R$ " + String.format("%.2f", saidas));
        txtSaldo.setText("Saldo: R$ " + String.format("%.2f", saldo));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Relatório Financeiro")
                .setPositiveButton("Fechar", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void loadValues() {
        LancamentoDAO lancamentoDAO = new LancamentoDAO(this);
        List<Lancamento> lancamentos = lancamentoDAO.listarLancamentos();


        double totalEntradas = 0;
        double totalSaidas = 0;

        for (Lancamento l : lancamentos) {
            if (l.getTipo().equalsIgnoreCase("Entrada")) {
                totalEntradas += l.getValor();
            } else if (l.getTipo().equalsIgnoreCase("Saída")) {
                totalSaidas += l.getValor();
            }
        }

        double saldo = totalEntradas - totalSaidas;

        this.entradas = totalEntradas;
        this.saidas = totalSaidas;
        this.saldo = saldo;

    }
}

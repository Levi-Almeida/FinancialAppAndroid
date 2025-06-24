package com.example.carteira.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carteira.R;
import com.example.carteira.model.Lancamento;

import java.util.List;

public class LancamentoAdapter extends BaseAdapter {

    private final List<Lancamento> lancamentos;
    private final Activity activity;

    public LancamentoAdapter(List<Lancamento> lancamentos, Activity activity) {
        this.lancamentos = lancamentos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lancamentos.size();
    }

    @Override
    public Object getItem(int i) {
        return lancamentos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = activity.getLayoutInflater().inflate(R.layout.lista_lancamentos, viewGroup, false);
        Lancamento lancamento = lancamentos.get(position);

        TextView textViewLancamentoDescricaoLista = view.findViewById(R.id.textViewLancamentoDescricaoLista);
        textViewLancamentoDescricaoLista.setText(lancamento.getDescricao());

        TextView textViewLancamentoCategoriaLista = view.findViewById(R.id.textViewLancamentoCategoriaLista);
        textViewLancamentoCategoriaLista.setText(lancamento.getCategoria());

        TextView textViewLancamentoValorLista = view.findViewById(R.id.textViewLancamentoValorLista);
        textViewLancamentoValorLista.setText(String.valueOf(lancamento.showValor()));

        if (lancamento.getTipo().equals("Entrada")) {
            textViewLancamentoValorLista.setTextColor(activity.getResources().getColor(R.color.blue));
        } else if (lancamento.getTipo().equals("Saída")) {
            textViewLancamentoValorLista.setTextColor(activity.getResources().getColor(R.color.red));
        }

        TextView textViewLancamentoDataLista = view.findViewById(R.id.textViewLancamentoDataLista);
        textViewLancamentoDataLista.setText(lancamento.showDataFormatada());

        return view;
    }
}

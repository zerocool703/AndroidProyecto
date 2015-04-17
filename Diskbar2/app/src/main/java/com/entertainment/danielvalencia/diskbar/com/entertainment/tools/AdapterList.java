package com.entertainment.danielvalencia.diskbar.com.entertainment.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by daniel.valencia on 15/04/2015.
 */
public abstract class AdapterList extends BaseAdapter {

    private List<?> input;
    private int R_layout_IdView;
    private Context contexto;

    public AdapterList(Context contexto, int R_layout_IdView, List<?> input) {
        super();
        this.contexto = contexto;
        this.input = input;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int position, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onAttach(input.get(position), view);
        return view;
    }

    @Override
    public int getCount() {
        return input.size();
    }

    @Override
    public Object getItem(int position) {
        return input.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract void onAttach(Object entrada, View view);

}


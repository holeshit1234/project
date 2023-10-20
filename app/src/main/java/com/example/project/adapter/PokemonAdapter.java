package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Pokemon> pokemonList;

    public PokemonAdapter(Context context, int layout, List<Pokemon> pokemonList) {
        this.context = context;
        this.layout = layout;
        this.pokemonList = pokemonList;
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView description;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.title= (TextView) view.findViewById(R.id.name);
            holder.description= (TextView) view.findViewById(R.id.element);
            holder.imageView = (ImageView) view.findViewById(R.id.imageView) ;
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Pokemon pokemon = pokemonList.get(i);
        holder.title.setText(pokemon.getName());
        holder.description.setText(pokemon.getElement());
        holder.imageView.setImageResource(pokemon.getImagePokemon());

        return view;
    }
}

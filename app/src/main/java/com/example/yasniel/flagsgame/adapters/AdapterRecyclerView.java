package com.example.yasniel.flagsgame.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.yasniel.flagsgame.DetallePais;
import com.example.yasniel.flagsgame.R;
import com.example.yasniel.flagsgame.TransitionActivity;

import java.util.ArrayList;

/**
 * Created by Lisi y Tito on 10/12/2018.
 */
public class AdapterRecyclerView extends RecyclerView.Adapter {
    Context context;
    ArrayList<Item> items;
    boolean expanded = false;

    public AdapterRecyclerView(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contenView = LayoutInflater.from(context).inflate(R.layout.imagen_grid, null);
        return new Holder(contenView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Item item = items.get(position);
        Holder holder1 = (Holder) holder;

        Glide.with(context)
                .load(item.getFoto())
                .placeholder(R.drawable.ic_noimage)
                .skipMemoryCache(true)
                .into(holder1.foto);

        ((Holder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent(context, DetallePais.class);
                    intent.putExtra("pos", position);//Posición del elemento
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, v, "transition1");
                    ActivityCompat.startActivity(context, intent, options.toBundle());
                }else{
                    Intent i = new Intent(context, DetallePais.class);
                    i.putExtra("pos", position);//Posición del elemento
                    context.startActivity(i);
                }







            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView foto;
        RelativeLayout linearLayout;

        public Holder(View itemView) {
            super(itemView);

            foto = (ImageView) itemView.findViewById(R.id.imagenn);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.imagenlayout);
        }
    }
}




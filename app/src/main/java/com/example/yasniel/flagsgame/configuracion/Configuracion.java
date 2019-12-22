package com.example.yasniel.flagsgame.configuracion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yasniel.flagsgame.R;
import com.example.yasniel.flagsgame.adapters.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Created by LisiyTito on 31/10/2018.
 */

public class Configuracion {


    public static ArrayList<Item> getList(Context context) {
        ArrayList<Item> listaItems = new ArrayList<>();
        Field[] drawables = R.drawable.class.getFields();
        for (Field f : drawables) {
            if (f.getName().contains("flag_"))
                listaItems.add(new Item(context.getResources().getIdentifier(f.getName(), "drawable", context.getPackageName())));
        }
        return listaItems;
    }

}
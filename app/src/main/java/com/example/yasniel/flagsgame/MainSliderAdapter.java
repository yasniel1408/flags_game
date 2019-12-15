package com.example.yasniel.flagsgame;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

/**
 * @author S.Shahini
 * @since 2/12/18
 */

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(R.drawable.a);
                break;
            case 1:
                viewHolder.bindImageSlide(R.drawable.b);
                break;
            case 2:
                viewHolder.bindImageSlide(R.drawable.c);
                break;
        }
    }

}

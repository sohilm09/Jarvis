package net.knightsys.jarvis;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by root on 11/23/17.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {

    private List<Card> pCardsVal;
    private Context context;

    public CardsAdapter(Context context, List<Card> pCardsVal) {
        this.pCardsVal = pCardsVal;
        this.context = context;
    }

    @Override
    public CardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardsAdapter.MyViewHolder holder, int position) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Card card = pCardsVal.get(position);
        holder.txtDetails.setText(card.Details);
        holder.txtTrailer.setText(card.Trailer);
        if (card.PicUri != null)
            switch (card.PicUri) {

                case "goodmorning":
                    Glide.with(context).load(R.drawable.goodmorning).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.na).into(holder.picImg);
                    break;

                case "goodafternoon":
                    Glide.with(context).load(R.drawable.goodafternoon).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.na).into(holder.picImg);
                    break;

                case "goodevening":
                    Glide.with(context).load(R.drawable.goodevening).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.na).into(holder.picImg);
                    break;
                default:
                    Glide.with(context).load(card.PicUri).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.na).into(holder.picImg);
                    break;
            }
    }

    @Override
    public int getItemCount() {
        return pCardsVal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDetails;
        public TextView txtTrailer;
        public ImageView picImg;

        public MyViewHolder(View view) {
            super(view);
            txtDetails = view.findViewById(R.id.details);
            txtTrailer = view.findViewById(R.id.trailer);
            picImg = view.findViewById(R.id.pic);
        }
    }
}

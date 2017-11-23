package net.knightsys.jarvis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 11/23/17.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {

    private List<Card> pCardsVal;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView popText;

        public MyViewHolder(View view) {
            super(view);
            popText = (TextView) view.findViewById(R.id.pop);
        }
    }

    public CardsAdapter(List<Card> pCardsVal)
    {
        this.pCardsVal = pCardsVal;
    }

    @Override
    public CardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardsAdapter.MyViewHolder holder, int position) {
        Card sal = pCardsVal.get(position);
        holder.popText.setText(sal.Details);
    }

    @Override
    public int getItemCount() {
        return pCardsVal.size();
    }
}

package net.knightsys.jarvis;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Card> cardList = new ArrayList<>();
    public CardsAdapter ca;
    public SwipeRefreshLayout sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        sr = findViewById(R.id.swiprefresh);
        sr.setEnabled(true);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshData();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        createCards();
        ca = new CardsAdapter(this, cardList);
        rv.setAdapter(ca);
    }

    private void createCards() {
        cardList.add(new Salutation(this, Card.CardType.Salutation));
        cardList.add(new Weather(this, Card.CardType.Weather));
    }

    public void DataChanged() {
        ca.notifyDataSetChanged();
    }

    public void RefreshData() {
        cardList.clear();
        createCards();
        sr.setRefreshing(false);
    }
}

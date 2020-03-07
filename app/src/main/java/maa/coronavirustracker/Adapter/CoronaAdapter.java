package maa.coronavirustracker.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import maa.coronavirustracker.Interfaces.onRecyclerViewItemClick;
import maa.coronavirustracker.Models.Complete;
import maa.coronavirustracker.R;

public class CoronaAdapter extends RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder> {
    private Context mContext;
    private ArrayList<Complete> data = new ArrayList<>();
    private onRecyclerViewItemClick monRecyclerViewItemClick;

    public CoronaAdapter(Context mContext, ArrayList<Complete> data, onRecyclerViewItemClick monRecyclerViewItemClick) {
        this.mContext = mContext;
        this.data = data;
        this.monRecyclerViewItemClick = monRecyclerViewItemClick;
    }

    @NonNull
    @Override
    public CoronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new CoronaViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaViewHolder holder, int position) {
        holder.onBindView(position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class CoronaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mCountry, mNum;

        public CoronaViewHolder(@NonNull View itemView) {
            super(itemView);
            mCountry = itemView.findViewById(R.id.Country);
            mNum = itemView.findViewById(R.id.Num);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            monRecyclerViewItemClick.onRecyclerViewItemClick(getAdapterPosition());
        }

        @SuppressLint("DefaultLocale")
        public void onBindView(int position) {
            mNum.setText(String.format("%,d", Integer.parseInt(data.get(position).getCases())));
            mCountry.setText(data.get(position).getCountry());

        }
    }
}

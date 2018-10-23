package com.developer.abhinav.automationprizecalculator.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Model.Switch;
import com.developer.abhinav.automationprizecalculator.R;

import java.util.List;

public class SwitchAdapter extends RecyclerView.Adapter<SwitchAdapter.Holder> {

    private Context mContext;
    private List<Switch> SwitchList;
    private final OnItemClickListener listener;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * View holder for application list item
     */
    public class Holder extends RecyclerView.ViewHolder {

        TextView SwitchName;

        public Holder(View itemView) {
            super(itemView);
            this.SwitchName = itemView.findViewById(R.id.switch_name);
        }

        public void setup(final Switch Switch, final OnItemClickListener listener) {
            SwitchName.setText(Switch.getSwitchBoard());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(Switch);
                }
            });
        }
    }

    public SwitchAdapter(Context mContext, List<Switch> SwitchList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.SwitchList = SwitchList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.switch_list, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setup(SwitchList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return SwitchList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Switch item);
    }

}

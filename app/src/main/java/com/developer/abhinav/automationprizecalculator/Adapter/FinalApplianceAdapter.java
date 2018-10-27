package com.developer.abhinav.automationprizecalculator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Model.FinalAppliance;
import com.developer.abhinav.automationprizecalculator.R;

import java.util.List;

public class FinalApplianceAdapter extends ArrayAdapter<FinalAppliance> {

    private Context mContext;
    private List<FinalAppliance> applianceLists;

    public FinalApplianceAdapter(Context mContext, List<FinalAppliance> appliancesLists){
        super(mContext, R.layout.custom_list, appliancesLists);
        this.mContext = mContext;
        this.applianceLists = appliancesLists;
    }

    private static class ViewHolder {
        TextView applianceName;
        TextView applianceId;
        TextView prize;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FinalAppliance appliance = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.final_list, parent, false);
            viewHolder.applianceName = (TextView) convertView.findViewById(R.id.appliance_name);
            viewHolder.applianceId = (TextView) convertView.findViewById(R.id.appliance_id);
            viewHolder.prize = (TextView) convertView.findViewById(R.id.prize);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.applianceId.setText(appliance.getApplianceID().substring(0,4));
        viewHolder.applianceName.setText(appliance.getApplianceName());
        viewHolder.prize.setText(String.valueOf(appliance.getPrize()));

        return convertView;
    }
}

package com.developer.abhinav.automationprizecalculator.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Model.ApplianceList;
import com.developer.abhinav.automationprizecalculator.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ApplianceList>{

    private Context mContext;
    private List<ApplianceList> applianceLists;

    public CustomAdapter(Context mContext, List<ApplianceList> appliancesLists){
        super(mContext, R.layout.custom_list, appliancesLists);
        this.mContext = mContext;
        this.applianceLists = appliancesLists;
    }

    private static class ViewHolder {
        TextView radioText;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ApplianceList applianceList = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list, parent, false);
            viewHolder.radioText = (TextView) convertView.findViewById(R.id.radioText);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.radioText.setText(applianceList.getApplianceName());
        if(applianceList.getSelected())
            viewHolder.radioText.setBackgroundColor(Color.parseColor("#B4D5FE"));
        else
            viewHolder.radioText.setBackgroundColor(Color.TRANSPARENT);

        return convertView;
    }
}

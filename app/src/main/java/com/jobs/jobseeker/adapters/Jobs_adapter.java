package com.jobs.jobseeker.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jobs.jobseeker.R;
import com.jobs.jobseeker.interfaces.Click_CardViewInterface;
import com.jobs.jobseeker.model.Jobs;

import java.util.ArrayList;

public class Jobs_adapter extends RecyclerView.Adapter<Jobs_adapter.MyViewHolder>{

    private final Click_CardViewInterface click_cardViewInterface;
    Context context;
    ArrayList<Jobs> list;

    public Jobs_adapter(Context context, ArrayList<Jobs> list, Click_CardViewInterface click_cardViewInterface) {
        this.context = context;
        this.list = list;
        this.click_cardViewInterface = click_cardViewInterface;
    }

    @Override
    public Jobs_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_card_item, parent,false);
        return new MyViewHolder(view, click_cardViewInterface);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Jobs jobs = list.get(position);
        holder.category.setText(jobs.getCategory());
        holder.company_name.setText(jobs.getCompany_name());
        holder.role.setText(jobs.getRole());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView category, company_name, role;
        public MyViewHolder(View itemView, Click_CardViewInterface click_cardViewInterface) {
            super(itemView);

            category = itemView.findViewById(R.id.jobCategory);
            company_name = itemView.findViewById(R.id.jobCompany);
            role = itemView.findViewById(R.id.jobRole);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(click_cardViewInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            click_cardViewInterface.onItemCLick(pos);
                        }
                    }
                    Log.d(TAG, "Show more details");
                }
            });
        }

    }
}

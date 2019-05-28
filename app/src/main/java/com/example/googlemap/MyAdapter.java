package com.example.googlemap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent , false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

        holder.heading.setText(listItem.getBusName());
        holder.desc1.setText(listItem.getAdminName());
        holder.desc2.setText(listItem.getAdminDesig());
        holder.desc3.setText(listItem.getMessage());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you Clicked  on "+listItem.getBusName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout linearLayout;
        public TextView heading;
        public TextView desc1;
        public TextView desc2;
        public TextView desc3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = (TextView) itemView.findViewById(R.id.heading);
            desc1 = (TextView) itemView.findViewById(R.id.desc1);
            desc2 = (TextView) itemView.findViewById(R.id.desc2);
            desc3 = (TextView) itemView.findViewById(R.id.desc3);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);



        }


    }
}

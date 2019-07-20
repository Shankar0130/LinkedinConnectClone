package com.shankaryadav.www.jsonparsingtesting;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.JsonViewHolder> {

    List<User> list;
    Context context;

    public JsonAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public JsonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (viewGroup.getContext ());

        View view = inflater.inflate (R.layout.item,viewGroup,false);


        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();

        lp.width = viewGroup.getMeasuredWidth ()/2;

        view.setLayoutParams (lp);

        return  new JsonViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonViewHolder jsonViewHolder, int i) {

        Picasso.get ().load (list.get (i).getImageURL ()).into (jsonViewHolder.profile);

        jsonViewHolder.name.setText (list.get (i).getName ());
        jsonViewHolder.email.setText (list.get (i).getEmailID ());
        jsonViewHolder.phone.setText (list.get (i). getMobile ());

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class JsonViewHolder extends RecyclerView.ViewHolder{

        CircleImageView profile;
        TextView name;
        TextView email;
        TextView phone;


        public JsonViewHolder(@NonNull View itemView) {
            super (itemView);

            profile = itemView.findViewById (R.id.profile);
            name = itemView.findViewById (R.id.name);
            email = itemView.findViewById (R.id.email);
            phone = itemView.findViewById (R.id.phone);
        }
    }
}

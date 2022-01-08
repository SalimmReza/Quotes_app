package com.example.quotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Quotes_recycler_adapter extends RecyclerView.Adapter<quoteViewHolder>{

    Context context;
    List<Quotes_response> list;
    Copy_listener listener;

    public Quotes_recycler_adapter(Context context, List<Quotes_response> list, Copy_listener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public quoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new quoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_quotess, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull quoteViewHolder holder, int position) {
        holder.title.setText(list.get(position).getText());
        holder.author.setText(list.get(position).getAuthor());
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.on_copy_clicked(list.get(holder.getAdapterPosition()).getText());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class quoteViewHolder extends RecyclerView.ViewHolder {

    TextView title, author;
    Button copy;


    public quoteViewHolder(@NonNull View itemView) {
        super(itemView);

        title= itemView.findViewById(R.id.title_id);
        author= itemView.findViewById(R.id.author_id);
        copy= itemView.findViewById(R.id.copy_id);
    }
}

package com.example.quotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Copy_listener {

    RecyclerView recyclerView;
    RequestManagers managers;
    Quotes_recycler_adapter adapter;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_view_home_id);

        managers=new RequestManagers(this);
        managers.get_all_quotes(listener);
        pd=new ProgressDialog(this);
        pd.setTitle("Loading....");
        pd.show();
    }

    private final Quote_response_listener listener= new Quote_response_listener() {
        @Override
        public void did_fetch(List<Quotes_response> responses, String message) {
                show_data(responses);
                pd.dismiss();
        }

        @Override
        public void did_error(String message) {
            pd.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void show_data(List<Quotes_response> responses) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2 , LinearLayoutManager.VERTICAL));
        adapter = new Quotes_recycler_adapter(MainActivity.this, responses,this );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void on_copy_clicked(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("Copied" , text);
        clipboardManager.setPrimaryClip(data);
        Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
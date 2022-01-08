package com.example.quotesapp;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RequestManagers {
        Context context;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://type.fit/")
                .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManagers(Context context) {
        this.context = context;
    }

    public void get_all_quotes(Quote_response_listener listener)
    {
        call_quotes call_quotes=retrofit.create(RequestManagers.call_quotes.class);
        Call<List<Quotes_response>> call= call_quotes.call_Quotes();
        call.enqueue(new Callback<List<Quotes_response>>() {
            @Override
            public void onResponse(Call<List<Quotes_response>> call, Response<List<Quotes_response>> response) {
                if (!response.isSuccessful())
                {
                    Toast.makeText(context, "Try again please!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.did_fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<Quotes_response>> call, Throwable t) {
                    listener.did_error(t.getMessage());
            }
        });
    }

     private interface call_quotes
     {
         @GET("api/quotes")
         Call<List<Quotes_response>> call_Quotes();
     }
}

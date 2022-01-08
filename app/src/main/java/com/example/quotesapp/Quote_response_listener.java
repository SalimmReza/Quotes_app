package com.example.quotesapp;

import java.util.List;

public interface Quote_response_listener {

    void did_fetch(List<Quotes_response> responses, String message);
    void did_error(String message);
}

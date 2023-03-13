package com.example.infra;

import com.example.infra.model.FundStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class FundStoreRepository {
    private final String dataUrl;
    private final ObjectMapper objectMapper;

    public FundStoreRepository(String url) {
        dataUrl = url;
        objectMapper = new ObjectMapper();
    }

    public FundStore fetch() {
        FundStore fundStore = null;
        try {
            URL url = new URL(this.dataUrl);
            fundStore =  objectMapper.readValue(url, FundStore.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fundStore;
    }
}

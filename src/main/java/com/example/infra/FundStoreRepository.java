package com.example.infra;

import com.example.exception.FundStoreMappingException;
import com.example.infra.model.FundStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class FundStoreRepository {
    private final String dataUrl;
    private final ObjectMapper objectMapper;

    public FundStoreRepository(String url, ObjectMapper objectMapper) {
        this.dataUrl = url;
        this.objectMapper = objectMapper;
    }

    public FundStore fetch() {
        try {
            URL url = new URL(this.dataUrl);
            return objectMapper.readValue(url, FundStore.class);
        } catch (IOException e) {
            throw new FundStoreMappingException(e.getMessage());
        }
    }
}

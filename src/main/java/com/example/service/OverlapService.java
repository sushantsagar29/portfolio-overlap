package com.example.service;

import com.example.domain.MutualFundMap;
import com.example.infra.FundStoreRepository;
import com.example.infra.model.FundStore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OverlapService {
    public MutualFundMap initializeMutualFundMap(String url, ObjectMapper objectMapper) {
        FundStoreRepository fundStoreRepository = new FundStoreRepository(url, objectMapper);
        FundStore fundStore = fundStoreRepository.fetch();
        return new MutualFundMap(fundStore);
    }
}

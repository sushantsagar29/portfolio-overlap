package com.example.service;

import com.example.domain.MutualFundMap;
import com.example.infra.FundStoreRepository;
import com.example.infra.model.FundStore;

public class OverlapService {
    public MutualFundMap initializeMutualFundMap(String url){
        FundStoreRepository fundStoreRepository = new FundStoreRepository(url);
        FundStore fundStore = fundStoreRepository.fetch();
        return new MutualFundMap(fundStore);
    }
}

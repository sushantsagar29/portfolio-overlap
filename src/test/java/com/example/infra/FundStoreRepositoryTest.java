package com.example.infra;

import com.example.infra.model.FundStore;
import com.example.constants.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundStoreRepositoryTest {
    FundStoreRepository fundStoreRepository = new FundStoreRepository(Constants.FUND_STORE_URL);

    @Test
    void shouldFetchFundStoreData() {
        FundStore actualResponse = fundStoreRepository.fetch();
        assertEquals(10, actualResponse.getFunds().size());
    }
}
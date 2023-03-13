package com.example.infra;

import com.example.infra.model.FundStore;
import com.example.constants.Constants;
import com.example.infra.model.MutualFundDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static com.example.domain.TestFixtures.*;
import static com.example.domain.TestFixtures.stocks2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FundStoreRepositoryTest {
    FundStore fundStore;
    FundStoreRepository fundStoreRepository;
    ObjectMapper objectMapperMock;

    @BeforeEach
    void setUp() {
        fundStore = new FundStore();
        MutualFundDto mutualFundDto1 = new MutualFundDto();
        mutualFundDto1.setName("fund1");
        mutualFundDto1.setStocks(stocks1);
        MutualFundDto mutualFundDto2 = new MutualFundDto();
        mutualFundDto2.setName("fund2");
        mutualFundDto2.setStocks(stocks2);
        fundStore.setFunds(Arrays.asList(mutualFundDto1, mutualFundDto2));

        objectMapperMock = Mockito.mock(ObjectMapper.class);
        fundStoreRepository = new FundStoreRepository(Constants.FUND_STORE_URL, objectMapperMock);
    }

    @Test
    void shouldFetchFundStoreData() throws IOException {
        Mockito.when(objectMapperMock.readValue(new URL(Constants.FUND_STORE_URL),FundStore.class)).thenReturn(fundStore);
        FundStore actualResponse = fundStoreRepository.fetch();
        assertEquals(2, actualResponse.getFunds().size());
        assertEquals("fund1", actualResponse.getFunds().get(0).getName());
        assertEquals("fund2", actualResponse.getFunds().get(1).getName());
    }
}
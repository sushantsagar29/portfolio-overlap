package com.example.domain;

import com.example.infra.model.MutualFundDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestFixtures {
    public static final Set<String> stocks1 = new HashSet<>(Arrays.asList(
            "stock1",
            "stock2"
    ));

    public static final Set<String> stocks2 = new HashSet<>(Arrays.asList(
            "stock3",
            "stock4"
    ));

    public static final Set<String> stocks3 = new HashSet<>(Arrays.asList(
            "stock1",
            "stock4"
    ));
}

package com.gabrielspassos.poc;

import com.intuit.karate.junit5.Karate;

public class AccountTest {

    @Karate.Test
    Karate testAllScenarios() {
        return Karate.run("Account").relativeTo(getClass());
    }
}

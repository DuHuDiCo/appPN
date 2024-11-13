package com.apppn.apppn.Utils;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class Functions {

    public boolean isTokenExpired(Instant tokenExpiryTime) {
        return Instant.now().isAfter(tokenExpiryTime);
    }

}

package com.jwt.jwtAuthentication.utils;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomPinGenerator {

	public int generateRandomSixDigitNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}

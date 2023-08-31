package org.simplepoc.avro.util;

import java.util.Random;

public class GenerateRandomNames {

    private static final String[] FIRST_NAMES = {"Ana", "Sara", "Adriano", "John", "Maria", "Ripley", "Leon", "Jose", "Jennifer", "Artoris", "Jim"};
    private static final String[] LAST_NAMES = {"Souza", "Raynor", "Lawrence", "Williams", "Costa", "Kruger", "Scot Kenedy", "Wilson", "Kerrigan", "Taylor", "Anderson", "Thomas", "Jason"};

    public static String generateRandomNames(){
        Random r = new Random();
        String firstName = FIRST_NAMES[r.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[r.nextInt(LAST_NAMES.length)];

        return firstName.concat(lastName);
    }

    public static String getRandomFirstName(){
        Random r = new Random();
        String firstName = FIRST_NAMES[r.nextInt(FIRST_NAMES.length)];

        return firstName;
    }
}

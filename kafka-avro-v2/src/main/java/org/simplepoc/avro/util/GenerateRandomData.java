package org.simplepoc.avro.util;

import java.util.Random;

public class GenerateRandomData {

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

    public static String getRandomLastName(){
        Random r = new Random();
        String lastName = LAST_NAMES[r.nextInt(LAST_NAMES.length)];

        return lastName;
    }

    public static int getRandomAge(){
        int min = 12, max = 100;
        Random r = new Random();
        return r.nextInt(max -min) + max;
    }

    public static Float getRandomHeight(){
        Float min = 120f, max = 210f;
        Random r = new Random();
        return (float) (r.nextInt((int) (max -min)) + max);
    }
}

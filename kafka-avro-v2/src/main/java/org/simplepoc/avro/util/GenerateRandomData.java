package org.simplepoc.avro.util;

import java.util.Random;

public class GenerateRandomData {

    private static final String[] FIRST_NAMES = {"Ana", "Sara", "Adriano", "John", "Maria", "Ripley", "Leon", "Jose", "Jennifer", "Artoris", "Jim"};
    private static final String[] LAST_NAMES = {"Souza", "Raynor", "Lawrence", "Williams", "Costa", "Kruger", "Scot Kenedy", "Wilson", "Kerrigan", "Taylor", "Anderson", "Thomas", "Jason"};
    private static final String[] EMAIL_DOMAIN = {"yahoo.com.br", "hotmail.com", "yahoo.com", "gmail.com", "uol.com"};

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
        return r.nextInt(max -min) + min;
    }

    public static Float getRandomHeight(){
        Float min = 120f, max = 210f;
        Random r = new Random();
        return (float) (r.nextInt((int) (max -min)) + min);
    }

    public static Float getRandomWeight(){
        Float min = 40f, max = 120f;
        Random r = new Random();
        return (float) (r.nextInt((int) (max -min)) + min);
    }


    public static String getRandomEmailDomain() {
        Random r = new Random();
        return EMAIL_DOMAIN[r.nextInt(EMAIL_DOMAIN.length)];
    }
}

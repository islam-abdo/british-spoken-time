package com.smartbear.britishspokentime;

import com.smartbear.britishspokentime.facade.SpokenTimeFacade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class BritishSpokenTimeApp implements CommandLineRunner {
    private final SpokenTimeFacade spokenTimeFacade;

    public BritishSpokenTimeApp(SpokenTimeFacade spokenTimeFacade) {
        this.spokenTimeFacade = spokenTimeFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(BritishSpokenTimeApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Spoken Time App!");
        System.out.print("Enter locale (Only en-GB is supported, all inputs will default to it for now): ");
        String localeInput = scanner.nextLine().trim();
        while (true) {
            System.out.print("Enter time in H:mm format (or 'exit' to quit): ");
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting...");
                break;
            }
            System.out.println(spokenTimeFacade.apply(input, Locale.UK.toString()));
            System.out.println(); // Blank line for spacing
        }
    }
}

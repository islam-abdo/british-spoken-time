package com.smartbear.britishspokentime;

import com.smartbear.britishspokentime.facade.BritishSpokenTimeFacade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BritishSpokenTimeApp implements CommandLineRunner {
    private final BritishSpokenTimeFacade britishSpokenTimeFacade;

    public BritishSpokenTimeApp(BritishSpokenTimeFacade britishSpokenTimeFacade) {
        this.britishSpokenTimeFacade = britishSpokenTimeFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(BritishSpokenTimeApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the British Spoken Time App!");
        while (true) {
            System.out.print("Enter time in HH:mm format (or 'exit' to quit): ");
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting...");
                break;
            }
            System.out.println(britishSpokenTimeFacade.apply(input));
            System.out.println(); // Blank line for spacing
        }
    }
}

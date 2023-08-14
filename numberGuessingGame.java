import java.util.Random;
import java.util.Scanner;

public class numberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minValue = 1;
        int maxValue = 100;
        int randomNum = random.nextInt(maxValue - minValue + 1) + minValue;
        int userScore = 100;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I chose the value between " + minValue + " and " + maxValue + ".");

        while (true) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess < randomNum) {
                System.out.println("Try higher.");
            } else if (userGuess > randomNum) {
                System.out.println("Try lower.");
            } else {
                int numberOfTries = (100 - userScore)/5;
                System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
                System.out.println("Your score: " + userScore);
                break;
            }

            userScore -= 5; 
        }

        scanner.close();
    }
}

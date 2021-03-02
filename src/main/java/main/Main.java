package main;

import coinTypes.Coin;
import coinUtils.CoinFactory;
import coinUtils.Coins;
import coinUtils.CurrencyRates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String INVALID_CHOICE = "Invalid Choice, please try again";
    private static final String USING_DEFAULT_RATE = "Could not get rate from API. Using default rate…";
    private static List<Result> conversionList;

    public static void main(String[] args) {
        System.out.println("Welcome to currency convertor\n");
        conversionList = new ArrayList<>();
        firstScreen();
    }

    private static void clearScreen() {
        // Todo: find a solution for clearing the screen in console
    }

    /***
     * Prints a menu that gives the user a choice of which conversion to use.
     */
    private static void firstScreen() {
        boolean go = false;
        Coins selectedCoin = null;
        Scanner input = new Scanner(System.in);

        System.out.println("Please choose an option (1/2):");
        System.out.println("1. Dollars to Shekels");
        System.out.println("2. Shekels to Dollars");
        System.out.println("3. Shekels to Euro");

        while (!go) {
            System.out.print("Your Choice : ");
            try {
                int choice = input.nextInt();

                switch (choice) {
                    case 1: {
                        selectedCoin = Coins.USD;
                        go = true;
                        break;
                    }

                    case 2: {
                        selectedCoin = Coins.ILS;
                        go = true;
                        break;
                    }

                    case 3: {
                        selectedCoin = Coins.EUR;
                        go = true;
                        break;
                    }

                    default: {
                        System.out.println(INVALID_CHOICE);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(INVALID_CHOICE);
                input.nextLine();
            }
        }
        clearScreen();
        secondScreen(selectedCoin);
    }

    //

    /***
     * Gets a user input for the amount of coins to convert to the target coin.
     * @param coin The coin to convert from.
     */
    private static void secondScreen(Coins coin) {
        boolean go = false;
        double amount = 0;
        Scanner input = new Scanner(System.in);

        while (!go) {
            System.out.print("Please enter an amount to convert : ");
            try {
                amount = input.nextDouble();
                go = true;
            } catch (InputMismatchException e) {
                System.out.println(INVALID_CHOICE);
                input.nextLine();
            }
        }
        clearScreen();
        thirdScreen(coin, amount);
    }

    /***
     * Prints the conversion result on the screen and Lets the user decide whether to repeat the process or not
     * @param coin The coin to convert from.
     * @param amount The amount of coins to convert to the target coin.
     */
    private static void thirdScreen(Coins coin, double amount) {
        Result result = null;
        double resultValue;

        switch (coin) {
            case USD: {
                try {
                    resultValue = CurrencyRates.getCurrencyConversion(coin,Coins.ILS,amount);
                } catch (IOException | InterruptedException e) {
                    System.out.println(USING_DEFAULT_RATE);
                    Coin ilsValue = CoinFactory.getCoinInstance(Coins.ILS);
                    resultValue = ilsValue.calculate(amount);
                }

                result = new Result(resultValue, "USD to ILS");
                break;
            }

            case ILS: {
                try {
                    resultValue = CurrencyRates.getCurrencyConversion(coin,Coins.USD,amount);
                } catch (IOException | InterruptedException e) {
                    System.out.println(USING_DEFAULT_RATE);
                    Coin usdValue = CoinFactory.getCoinInstance(Coins.USD);
                    resultValue = usdValue.calculate(amount);
                }

                result = new Result(resultValue, "ILS to USD");
                break;
            }

            case EUR: {
                try {
                    resultValue = CurrencyRates.getCurrencyConversion(coin,Coins.EUR,amount);
                } catch (IOException | InterruptedException e) {
                    System.out.println(USING_DEFAULT_RATE);
                    Coin eurValue = CoinFactory.getCoinInstance(Coins.EUR);
                    resultValue = eurValue.calculate(amount);
                }

                result = new Result(resultValue, "ILS to EUR");
                break;
            }
        }

        System.out.println(result);
        conversionList.add(result);

        boolean go = false;
        Scanner input = new Scanner(System.in);

        while (!go) {
            System.out.print("Start over (Y/N) ? ");
            switch (input.next().toUpperCase()) {
                case "Y": {
                    firstScreen();
                    go = true;
                    break;
                }

                case "N": {
                    fourthScreen();
                    go = true;
                    break;
                }

                default: {
                    System.out.println(INVALID_CHOICE);
                    go = false;
                }
            }
        }
    }

    /***
     * Prints the list of all previous conversions, prints a final message, saves the list to a file.
     */
    private static void fourthScreen() {
        System.out.println("Thanks for using out currency convertor");
        for (Result result : conversionList) {
            System.out.println(result);
        }

        try {
            FileManager.write(conversionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
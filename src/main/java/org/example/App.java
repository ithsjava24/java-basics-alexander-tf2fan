package org.example;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        var swedishLocale = Locale.of("sv", "SE");
        Locale.setDefault(swedishLocale);
        Scanner sc = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat("00");
        DecimalFormat formatterForOnlyOneNumber = new DecimalFormat("00.00");
        int[] priser = new int[24];
        int ArrayLength = priser.length;
        int firstValue;
        int maxPrice = 0;
        int maxPriceHour = 0;
        int maxPriceSecondHour = 1;
        int minPrice = 0;
        int minPriceHour = 0;
        int minPriceSecondHour = 1;
        double medelPrice = 0;
        String menu = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;

        System.out.println(menu);
        String menuInput;
        while (true) {
            System.out.println(menu);
                menuInput = sc.nextLine();
            switch (menuInput) {
                case "1" -> {
                    System.out.println("Skriv in el priset mellan dom här tidpunkterna");
                    for (int i = 0; i < ArrayLength; i++) {
                        System.out.println(formatter.format(i) + "-" + formatter.format(i + 1) + " h");
                        priser[i] = sc.nextInt();
                    }

                }
                case "2" -> {
                    firstValue = (priser[0]);
                    for (int i = 0; i < ArrayLength; i++) {
                        if ((priser[i]) < firstValue || (priser[i]) < minPrice) {
                            minPrice = (priser[i]);
                            minPriceHour = i;
                            minPriceSecondHour = (i + 1);
                        }
                        medelPrice = medelPrice + priser[i];
                    }
                    System.out.println("Lägsta pris: "
                            + formatter.format(minPriceHour)
                            + "-"
                            + formatter.format(minPriceSecondHour)
                            + ", "
                            + minPrice
                            + " öre/kwh");
                    System.out.println("Högsta pris: "
                            + formatter.format(maxPriceHour)
                            + "-"
                            + formatter.format(maxPriceSecondHour)
                            + ", "
                            + maxPrice
                            + " öre/kwh");
                    System.out.println("MedelPris: "
                            + formatterForOnlyOneNumber.format(medelPrice / 24)
                            + " öre/kwh");

                }
                case "3" -> {
                    for (int i = 0; i < ArrayLength; i++) {
                        System.out.println(formatter.format(i)
                                + "-"
                                + formatter.format(i + 1)
                                + " "
                                + priser[i]
                                + " öre");
                    }
                }
                case "4" -> {

                }
                case "e","E" -> {
                    break;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }

//    public static double[] Inmating(double[] priser) {
//        Scanner sc = new Scanner(System.in);
//        DecimalFormat formatter = new DecimalFormat("00");
//        System.out.println("Skriv in el priset mellan dom här tidpunkterna");
//        for(int i = 0; i < priser.length;i++) {
//            System.out.println(formatter.format(i)+"-"+formatter.format(i+1)+" h");
//
//            try {
//                priser[i] = sc.nextDouble();
//            } catch (InputMismatchException e) {
//                System.out.println("Error");
//            }
//        }
//        return priser;
//    }
}

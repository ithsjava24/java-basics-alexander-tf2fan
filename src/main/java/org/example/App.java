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
        int maxPrice;
        int maxPriceHour = 0;
        int maxPriceSecondHour = 1;
        int minPrice;
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

        String menuInput;
        while (true) {
            System.out.println(menu);
            menuInput = sc.nextLine();
            switch (menuInput) {
                case "1" -> {
                    System.out.println("Skriv in el priset mellan dom här tidpunkterna");
                    for (int i = 0; i < ArrayLength; i++) {
                        System.out.printf("%02d-%02d\n",i,i+1);
                        priser[i] = sc.nextInt();
                    }
                    System.out.println(sc.nextLine());
                }
                case "2" -> {
                    minPrice = Integer.MAX_VALUE;
                    maxPrice = Integer.MIN_VALUE;
                    for (int i = 0; i < ArrayLength; i++) {
                        if (priser[i] < minPrice) {
                            minPrice = priser[i];
                            minPriceHour = i;
                            minPriceSecondHour = (i + 1);
                        }
                        if (priser[i] > maxPrice) {
                            maxPrice = priser[i];
                            maxPriceHour = i;
                            maxPriceSecondHour = (i + 1);
                        }
                        medelPrice = medelPrice + priser[i];
                    }
                    System.out.println("Lägsta pris: "
                            + formatter.format(minPriceHour)
                            + "-"
                            + formatter.format(minPriceSecondHour)
                            + ", "
                            + minPrice
                            + " öre/kWh");
                    System.out.println("Högsta pris: "
                            + formatter.format(maxPriceHour)
                            + "-"
                            + formatter.format(maxPriceSecondHour)
                            + ", "
                            + maxPrice
                            + " öre/kWh");
                    System.out.println("Medelpris: "
                            + formatterForOnlyOneNumber.format(medelPrice / 24)
                            + " öre/kWh");

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
                    return;
                }
                default -> {
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

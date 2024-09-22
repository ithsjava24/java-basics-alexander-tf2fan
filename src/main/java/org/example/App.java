package org.example;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale swedishLocale = new Locale("sv","SV");
        Locale.setDefault(swedishLocale);
        int[] priser = new int[24];
        int ArrayLength = priser.length;
        int maxPrice = Integer.MAX_VALUE;
        int minPrice = Integer.MIN_VALUE;
        String maxPrice1stHourAnd2ndHour = "";
        String minPrice1stHourAnd2ndHour = "";
        double medelPrice = 0;
        String medelPriceSum = "";
        // Variabler för att sortera priserna case "3"
        int[] sortPrices = new int[24];
        int sortPricesNextSpot = 0;
        int hourSorter = 0;
        // Variabler för dom 4 billigaste timmarna case "4"
        int theFourCheapestHours = 0;
        int comparingFourHours = 0;
        int cheapestHoursTime = 0;
        float medelPris4h = 0;
        // Variabler för visualiseringen case "5"
        int maxPriceForChart = Integer.MIN_VALUE;
        float maxPriceIn6thOfTheValue = 0;
        int spaceFor6thOfTheMaxValue = 0;
        int starFor6thOfTheMaxValue = 0;
        // TODO: Gör så att dom variabler som går att internalize inuti case ska läggas in i dom.
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
                            minPrice1stHourAnd2ndHour = String.format("%02d-%02d",i,i+1);
                        }
                        if (priser[i] > maxPrice) {
                            maxPrice = priser[i];
                            maxPrice1stHourAnd2ndHour = String.format("%02d-%02d",i,i+1);
                        }
                        medelPrice = medelPrice + priser[i];
                    }
                    medelPrice = medelPrice / 24f;
                    medelPriceSum = String.format("%.2f",(medelPrice));
                    System.out.print("Lägsta pris: "+minPrice1stHourAnd2ndHour+", "+minPrice+" öre/kWh"+"\n");
                    System.out.print("Högsta pris: "+maxPrice1stHourAnd2ndHour+", "+maxPrice+" öre/kWh"+"\n");
                    System.out.print("Medelpris: "+medelPriceSum+" öre/kWh"+"\n");

                    // Vad du ser längre ner är kod som testerna vägrade att acceptera.
//                    System.out.println("Lägsta pris: "
//                            + formatter.format(minPriceHour)
//                            + "-"
//                            + formatter.format(minPriceSecondHour)
//                            + ", "
//                            + minPrice
//                            + " öre/kWh");
//                    System.out.println("Högsta pris: "
//                            + formatter.format(maxPriceHour)
//                            + "-"
//                            + formatter.format(maxPriceSecondHour)
//                            + ", "
//                            + maxPrice
//                            + " öre/kWh");
//                    System.out.println("Medelpris: "
//                            + formatterForOnlyOneNumber.format(medelPrice / 24)
//                            + " öre/kWh");

                }
                case "3" -> {
                    // Överför alla number från priser[] till sortPrices[]
                    sortPrices = Arrays.stream(priser).toArray();
                    // Sen sorterar vi alla number i sortPrices så den första numbered har lägst tal och sista har högst tal
                    Arrays.sort(sortPrices);
                    for(int i = 23;i > 0;i--) {
                        for(int j = 0;j < ArrayLength;j++) {
                            if(sortPrices[i] == priser[j]) {
                                hourSorter = j;
                                sortPricesNextSpot = priser[j];
                                System.out.printf("%02d-%02d %d öre\n",hourSorter,hourSorter+1,sortPricesNextSpot);
                            }
                        }
                    }
                }
                case "4" -> {
                    // Först så lägger jag till dom 4 första timernas pris i en int
                    theFourCheapestHours = (priser[0]+priser[1]+priser[2]+priser[3]);
                    // Sen har jag en int som man lägger in dom kommande 4 timmarna
                    for(int i = 0;i < ArrayLength - 4;i++) {
                        comparingFourHours = priser[i]+priser[i+1]+priser[i+2]+priser[i+3];
                        if(theFourCheapestHours > comparingFourHours) {
                            theFourCheapestHours = comparingFourHours;
                            cheapestHoursTime = i;
                            medelPris4h = theFourCheapestHours / 4f;
                        }
                    }
                    System.out.println("Påbörja laddning klockan "+cheapestHoursTime);
                    System.out.printf("Medelpris 4h: %.1f öre/kWh\n",medelPris4h);
                }
                case "5" -> {
                    /* Så vi ska ha visualisering av alla priser ut av alla tider. Har en idea. Exemplet
                    som dom visade har 6 punkter för att vissa hur högt priset är. Vilket menas att dom tog det
                    högsta priset som fanns, delade up den siffran i 6, och sen see hur många av dom delarna som
                    passade in i det priset som finns i den tidpunkten.
                    Nu vet jag hur jag ska kolla om hur många punkter en tidpunkt ska ha.
                     */
                    // Först så hitta jag den högsta värdet i array.
                    for (int i = 0;i < ArrayLength;i++) {
                        if(maxPriceForChart < priser[i]) {
                            maxPriceForChart = priser[i];
                        }
                    }
                    // Sen tar jag det värdet och lägger en 6th del av den i en variable
                    maxPriceIn6thOfTheValue = maxPriceForChart / 6f;
                    /* Nu vet jag hur jag ska lägga in punkterna och dom tomma ställena.

                     */
                    for (int i = 0;i < 6;i++) {
                        for (int j = 0;j < ArrayLength;j++) {

                        }
                    }
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

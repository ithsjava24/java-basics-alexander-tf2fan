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
                    medelPrice = 0;
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
                    medelPris4h = (float) theFourCheapestHours / 4;
                    for(int i = 0;i < 21;i++) {
                        comparingFourHours = priser[i]+priser[i+1]+priser[i+2]+priser[i+3];
                        if(theFourCheapestHours > comparingFourHours) {
                            theFourCheapestHours = comparingFourHours;
                            cheapestHoursTime = i;
                            medelPris4h = (float) theFourCheapestHours / 4;
                        }
                    }
                    System.out.print(String.format("Påbörja laddning klockan %d\n",cheapestHoursTime));
                    System.out.printf(String.format("Medelpris 4h: %.1f öre/kWh\n",medelPris4h));
                }
                case "5" -> {
                    // Misslyckad metod som inte ser fint ut.
                    visualsering(ArrayLength, priser);
                }
                case "e","E" -> {
                    return;
                }
                default -> {
                }
            }
        }
    }

    private static void visualsering(int ArrayLength, int[] priser) {
        // Variabler för visualiseringen case "5"
        int maxPriceForChart = Integer.MIN_VALUE;
        int minPriceForChart = Integer.MAX_VALUE;
        int convertPriceForChartIfMinus = 0;
        int maxPriceForChartLength = 0;
        int minPriceForChartLength = 0;
        boolean highestNumberHasMoreCharacter = true;
        StringBuilder amountOfSpace = new StringBuilder();
        StringBuilder tempString = new StringBuilder();
                    /* Så vi ska ha visualisering av alla priser ut av alla tider. Har en idea. Exemplet
                    som dom visade har 6 punkter för att vissa hur högt priset är. Vilket menas att dom tog det
                    högsta priset som fanns, delade up den siffran i 6, och sen see hur många av dom delarna som
                    passade in i det priset som finns i den tidpunkten.
                    Nu vet jag hur jag ska kolla om hur många punkter en tidpunkt ska ha.
                     */
        // Först så hitta jag den högsta värdet i array.
        for (int i = 0; i < ArrayLength; i++) {
            if (maxPriceForChart < priser[i]) {
                maxPriceForChart = priser[i];
            } else if (minPriceForChart > priser[i]) {
                minPriceForChart = priser[i];
            }
        }
        // Fixar 2 variabler som kollar hur många character båda siffrorna har. Också så måste jag konverta alla minus
        // Till plus variabler.
        if (maxPriceForChart < 0) {
            convertPriceForChartIfMinus = convertPriceForChartIfMinus - maxPriceForChart;
            maxPriceForChartLength = Integer.toString(convertPriceForChartIfMinus).length();
        }
        if (minPriceForChart < 0) {
            convertPriceForChartIfMinus = convertPriceForChartIfMinus - minPriceForChart;
            minPriceForChartLength = Integer.toString(convertPriceForChartIfMinus).length();
        }
        maxPriceForChartLength = Integer.toString(maxPriceForChart).length();
        minPriceForChartLength = Integer.toString(minPriceForChart).length();

        // Sen kollar jag vilken siffra har mest character i sig. Högsta eller lägsta siffran
        if(maxPriceForChartLength > minPriceForChartLength) {
            highestNumberHasMoreCharacter = true;
            for(int i = minPriceForChartLength;i < maxPriceForChartLength;i++) {
                amountOfSpace.append(" ");
            }
        } else if(maxPriceForChartLength < minPriceForChartLength){
            highestNumberHasMoreCharacter = false;
            for(int i = maxPriceForChartLength;i < minPriceForChartLength;i++){
                amountOfSpace.append(" ");
            }
        } else {
            for(int i = 0;i < maxPriceForChartLength;i++) {
                highestNumberHasMoreCharacter = true;
                amountOfSpace.append(" ");
            }
        }
        /* Nu vet jag hur jag ska lägga in punkterna och dom tomma ställena.
         */
        for (int i = 6;i > 0;i--) {
            tempString.setLength(0);
            for (int j = 0; j < ArrayLength; j++) {
                    if ((float) priser[j] / 6 * i <= (float) maxPriceForChart / 6 * i && (float) priser[j] / 6 * i > (float) maxPriceForChart / 6 * (i - 1)) {
                        tempString.append("  x");
                    }
            }
            if(i == 6 && highestNumberHasMoreCharacter) {
                System.out.println(+maxPriceForChart+ "|" + tempString.toString());
            } else if(i == 6) {
                System.out.println(amountOfSpace.toString() + minPriceForChart + "|" + tempString.toString());
            } else if(i == 1 && !highestNumberHasMoreCharacter) {
                System.out.println(minPriceForChart+"|" + tempString);
            } else if(i == 1 && highestNumberHasMoreCharacter) {
            System.out.println(amountOfSpace.toString() + minPriceForChart + "|" + tempString.toString());
            } else {
            System.out.println(amountOfSpace.toString()+"  |"+tempString.toString());
            }
        }
        System.out.println(amountOfSpace.toString()
                +"  |------------------------------------------------------------------------\n"
                +amountOfSpace.toString()
                +"  | 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23");
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

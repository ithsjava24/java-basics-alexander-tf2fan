package org.example;

import java.util.Locale;

public class App {

    public static void main(String[] args) {
        var locale = Locale.of("sv", "SE");
        Locale.setDefault(locale);


        String menu =  """
                        Elpriser
                        ========
                        1. Inmatning
                        2. Min, Max och Medel
                        3. Sortera
                        4. Bästa Laddningstid (4h)
                        e. Avsluta
                        """;

        System.out.println(menu);
    }
}

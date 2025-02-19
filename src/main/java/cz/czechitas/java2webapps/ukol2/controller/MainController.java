package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
public class MainController {


        private static List<String> readAllLines()throws IOException{
        //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
        //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8
        try (InputStream inputStream = classLoader.getResourceAsStream("citaty.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }
    //náhodné číslo pro výběr obrázku a citátu
    private final Random random = new Random();
    @GetMapping("/")

    public ModelAndView citaty() throws IOException {
        int nahodneCislo = random.nextInt(9) + 1;
        int nahodnyCitat = random.nextInt(7) + 1;
//readAllLines().get(index) zajistí, že to z Listu bere jen jeden řádek
        ModelAndView result = new ModelAndView("index");
        result.addObject("citat", readAllLines().get(nahodnyCitat));
        result.addObject("obrazek",String.format("/images/obrazek-%d.jpg", nahodneCislo));
        return result;
    }
}






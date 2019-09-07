package it.univipm.Gambini.Ragaini.Prog_OOP_CER;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Parser;

@SpringBootApplication
public class ProgOopCerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgOopCerApplication.class, args);
		String url = new String("http://data.europa.eu/euodp/data/api/3/action/package_show?id=vzo0vqtpcgMt3X8yBGTJ8Q");
		System.out.println("insert url dataset");
		Parser.main(url);
		System.out.println("done." + "check the folder");
	}

}

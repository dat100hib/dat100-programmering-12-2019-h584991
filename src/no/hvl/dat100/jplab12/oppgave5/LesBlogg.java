package no.hvl.dat100.jplab12.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Integer.*;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;
import no.hvl.dat100.jplab12.oppgave2.*;
import no.hvl.dat100.jplab12.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String MAPPE = System.getProperty("user.dir") + "/src/no/hvl/dat100/jplab12/tests/";

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String filnavn) {
		
		File fil = new File(MAPPE + filnavn);
		Blogg samling;
		
		try {
			Scanner leser = new Scanner(fil);
			String linje = leser.nextLine();
			int antallInnlegg = parseInt(linje);
			samling = new Blogg(antallInnlegg);
			for (int i = 0; i < antallInnlegg; i++) {
				String type = leser.nextLine();
				int id = parseInt(leser.nextLine());
				String bruker = leser.nextLine();
				String dato = leser.nextLine();
				int likes = parseInt(leser.nextLine());
				String tekst = leser.nextLine();
				System.out.println(type  + " " + TEKST);
				if (type.contentEquals(TEKST)) {
					Tekst innlegg = new Tekst(id, bruker, dato, likes, tekst);
					samling.leggTil(innlegg);
					
				}
				if (type.contentEquals(BILDE)) {
					String url = leser.nextLine();
					Bilde innlegg = new Bilde(id, bruker, dato, likes, tekst, url);
					samling.leggTil(innlegg);
				}
			}
			leser.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Filen som ble forsøkt lest, finnes ikke");
			samling = new Blogg(0);
		}
		return samling;
	}
}

package no.hvl.dat100.jplab12.oppgave3;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;

public class Blogg {

	// Oppretter objektvariabler.
	protected Innlegg[] innleggtabell;
	protected int nesteledig;

	// definerer to konstruktører for klassen, en med spesifisert lengde og en uten.
	public Blogg() {
		innleggtabell = new Innlegg[20];
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	// Definerer en metode for å finne antall element i samlingen av innlegg.
	public int getAntall() {
		// Returnerer nesteledig variabelen da den tilsvarer antallet element.
		return nesteledig;
	}
	
	// Definerer en get metode for innleggtabellen.
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	// Definerer en metode for å finne indeksen til et innlegg.
	public int finnInnlegg(Innlegg innlegg) {
		// Starter med å sette indeksen til -1, siden det er verdien som skal returneres dersom
		// innlegget ikke er med i innleggtabellen.
		int indeks = -1;
		
		// kjører en løkke som går gjennom alle elementene i tabellen som peker på et objekt.
		for (int i = 0; i < nesteledig; i++) {
			
			// Dersom innlegget har samme id som innlegget som det letes etter så endres
			// indeksvariabelen til innleggets indeks i samlingen.
			if (innleggtabell[i].erLik(innlegg)) {
				indeks = i;
			}
		}
		// Returnerer til slutt indeksen.
		return indeks;
	}

	// Definerer en metode som sjekker om et innlegg finnes i samlingen og returnerer en boolean.
	public boolean finnes(Innlegg innlegg) {
		// antar først at innlegget ikke finnes i listen og setter boolean til false
		boolean finnes = false;
		
		// går gjennom alle element i samlingen som peker på et objekt og dersom innlegget er likt
		// så oppdateres boolean til true
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				finnes = true;
			}
		}
		// Returnerer til slutt boolean.
		return finnes;
	}
	
	// Definerer en metode som sjekker om det er plass til flere element i samlingen.
	public boolean ledigPlass() {
		// Starter med å sette boolean til false.
		boolean ledigPlass = false;
		
		// dersom neste ledig er mindre enn samlingens totale lengde så er det plass
		if (nesteledig < innleggtabell.length) {
			// Opdaterer boolean til true
			ledigPlass = true;
		}
		// returnerer til slutt boolean.
		return ledigPlass;
	}
	
	// Definerer en metode som legger til et element i samlingen og returnerer en boolean.
	public boolean leggTil(Innlegg innlegg) {
		
		// starter med å sette en boolean til false
		boolean lagtTil = false;
		
		// dersom det ikke finnes it likt innlegg, og det er ledig plass.
		if (!finnes(innlegg) && ledigPlass()) {
			
			// Setter neste element til å peke på innsatt innlegg.
			innleggtabell[nesteledig] = innlegg;
			// Oppdaterer boolean til true
			lagtTil = true;
			// Inkrementerer neste ledig.
			nesteledig += 1;
		}
		// returnerer boolean.
		return lagtTil;
	}
	
	// Definerer en metode toString som lager en streng med all innformasjon.
	public String toString() {
		// Starter med å legge til samlingens lengde.
		String s = nesteledig + "\n";
		
		// Går så igjennom alle element som peker på et objekt og skriver dem med toString metoden
		// i de forskjellige klassene, og legger dem til strengen.
		for (int i = 0; i < nesteledig; i++) {
			s += innleggtabell[i].toString();
		}
		// Returnerer strengen.
		return s;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		int nyLengde = innleggtabell.length*2;
		Innlegg[] utvidetTabell = new Innlegg[nyLengde];
		for (int i = 0; i < nesteledig; i++) {
			utvidetTabell[i] = innleggtabell[i];
		}
		innleggtabell = utvidetTabell;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		
		boolean lagtTil = false;
		if (!finnes(innlegg) && ledigPlass()) {
			innleggtabell[nesteledig] = innlegg;
			lagtTil = true;
			nesteledig += 1;
		}
		else if (!finnes(innlegg)) {
			utvid();
			if 	(!finnes(innlegg) && ledigPlass()) {
				innleggtabell[nesteledig] = innlegg;
				lagtTil = true;
				nesteledig += 1;
			}
		}
		return lagtTil;
	}
	
	public boolean slett(Innlegg innlegg) {
		
		boolean slettet = false;
		int indeks = finnInnlegg(innlegg);
		int teller = 0;
		if (indeks >= 0 && finnes(innlegg)) {
			Innlegg[] nySamling = new Innlegg[innleggtabell.length];
			for (int i = 0; i < nesteledig; i++) {
				if (i != indeks) {
					nySamling[teller] = innleggtabell[i];
					teller++;
				}
			}
			innleggtabell = nySamling;
			nesteledig--;
			slettet = true;
		}
		return slettet;
	}
	
	public int[] search(String keyword) {
		int[] indekser = new int[innleggtabell.length];
		int nesteLedigeInt = 0;
		for (int i = 0; i < nesteledig; i++) {
			String s = innleggtabell[i].toString();
			String[] tabell = s.split("\n");
			if (tabell[5].contains(keyword)) {
				indekser[nesteLedigeInt] = i;
				nesteLedigeInt++;
			}
		}
		if (nesteLedigeInt > 0) {
			int[] indeksListe = new int[nesteLedigeInt];
			for (int i = 0; i < nesteLedigeInt; i++) {
				indeksListe[i] = indekser[i];
			}
			indekser = indeksListe;
		}
		
		return indekser;

	}
}
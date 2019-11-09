package no.hvl.dat100.jplab12.oppgave3;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;

public class Blogg {

	// TODO: objektvariable
	private Innlegg[] innleggtabell;
	private int nesteledig;

	public Blogg() {
		innleggtabell = new Innlegg[20];
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		int indeks = -1;
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				indeks = i;
			}
		}
		return indeks;
	}

	public boolean finnes(Innlegg innlegg) {
		boolean finnes = false;
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				finnes = true;
			}
		}
		return finnes;
	}

	public boolean ledigPlass() {
		boolean ledigPlass = false;
		if (nesteledig < innleggtabell.length) {
			ledigPlass = true;
		}
		return ledigPlass;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		boolean lagtTil = false;
		if (!finnes(innlegg) && ledigPlass()) {
			innleggtabell[nesteledig] = innlegg;
			lagtTil = true;
			nesteledig += 1;
		}
		return lagtTil;
	}
	
	public String toString() {
		String s = nesteledig + "\n";
		for (int i = 0; i < innleggtabell.length; i++) {
			s += innleggtabell[i].toString();
		}
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
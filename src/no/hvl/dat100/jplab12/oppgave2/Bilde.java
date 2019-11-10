package no.hvl.dat100.jplab12.oppgave2;

import no.hvl.dat100.jplab12.common.TODO;

public class Bilde extends Tekst {

	// Oppretter en ny objektvariabel url som protected i tilfelle den trengs av en subklasse senere.
	protected String url;
	
	// Initsialiserer konstruktørene slik som de forrige klassene, nå bare med url varaiabel i tillegg
	public Bilde(int id, String bruker, String dato, String tekst, String url) {
		this.id = id;
		this.bruker = bruker;
		this.dato = dato;
		this.tekst = tekst;
		this.likes = 0;
		this.url = url;
	}

	public Bilde(int id, String bruker, String dato, int likes, String tekst, String url) {
		this.id = id;
		this.bruker = bruker;
		this.dato = dato;
		this.likes = likes;
		this.tekst = tekst;
		this.url = url;
	}
	
	// Legger inn get og set metoder for url variabelen.
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		// oppdaterer toString metoden med ny variabel.
		String s = "BILDE\n" + id + "\n" + bruker + "\n" + dato +"\n" + likes + "\n" + tekst + "\n" + url + "\n";
		return s;
	}

	// Metoden nedenfor er kun for valgfri oppgave 6
	public String toHTML() {
		
		throw new UnsupportedOperationException(TODO.method());
				
	}
}

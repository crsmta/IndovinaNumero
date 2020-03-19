package it.polito.tdp.indovinanumero.model;

import java.awt.List;
import java.security.InvalidParameterException;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.Set;

import javafx.event.ActionEvent;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	private Set<Integer> numeriUsati;
	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
	}

	public void nuovaPartita() {
		//gestione dell'inizio di una nuova partita - Logica del gioco
		this.segreto = (int)(Math.random() * NMAX) + 1;
		this.tentativiFatti=0;
		this.inGioco = true; 
		this.listaNumeri.clear();
	}

	public int checkNumber(int tentativo) throws Exception {

		//System.out.println("tentativi fatti "+tentativiFatti+"\n");
		if (tentativo<1||tentativo>100)
			throw new InvalidParameterException("devi inserire un numero tra 1 e 100");
		if (!inGioco)
			throw new IllegalStateException("non sei in gioco");

		if (tentativiFatti==TMAX) 
			throw new TentativiEsauritiException("i tentativi sono esauriti");
		if (usato(tentativo))
			throw new NumeroUsatoException("numero già utilizzato");
		tentativiFatti++;

		if(tentativo == this.segreto) {
			this.inGioco = false;
			return 0;
		}	
		if(tentativo < this.segreto)
			return -1;
		else
			return 1;
	}

	private Boolean usato(int numero) {
		if (numeriUsati.contains(numero))
			return true;
		else {
			numeriUsati.add(numero);
			return false;	
		}

	}
	public int getTeentativiFatti() {
		return tentativiFatti;
	}
	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {

		return segreto;
	}
}

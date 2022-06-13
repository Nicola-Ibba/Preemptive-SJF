package main;

import java.util.ArrayList;
import java.util.List;

public class Processore {
	
	private List<Processo> processi;
	private int totProcessi;
	private double totaleWt;
	private double totaleTat;
	
	public Processore(List<Processo> processi) {
		this.processi = processi;
		totProcessi = processi.size();
		totaleWt = 0;
		totaleTat = 0;
	}
	
	private void calcWaitingTime() {
		
		List<Integer> remainingTime = new ArrayList<>();
		
		for(Processo p : processi) {
			remainingTime.add(p.getBt());	//memorizzo in una nuova lista i burst time così da poterci lavorare sopra
		}
		
		int complete = 0, t = 0, minBtMissing = Integer.MAX_VALUE, shortestProcessIndex = 0, finishTime;
		Processo shortestProcess;
		boolean check = false;
		
		//viene cercato il processo a cui manca meno tempo al suo completamento ad ogni singolo istante di tempo macchina t
		while (complete != totProcessi) {
			
			for (int y = 0; y < totProcessi; y++) {		//faccio una ricerca, tra tutti i processi, di quello che rispetta le condizioni dell'if
				
				Processo pr = processi.get(y);
				
				if (pr.getAt() <= t && remainingTime.get(y) < minBtMissing && remainingTime.get(y) > 0) {	//se il processo è arrivato prima dell'istante attuale t, se il rt dell'attuale processo è inferiore di quello assoluto e se è > 0 procedi con l'esecuzione del processo
					minBtMissing = remainingTime.get(y);	//il nuovo tempo minimo è il burst time del processo, cioé quanto gli manca alla fine della sua esecuzione (se qualche processo ha un burst time inferiore prende il suo posto)
					shortestProcessIndex = y;	//variabile in cui memorizzo l'indice del processo a cui manca meno tempo al suo completamento
					check = true;
				}
			}
			
			if(check == false) {	//potrebbe non essere arrivato ancora nessun processo all'istante t, quindi avanzo
				t++;
				continue;	//passo all'interazione successiva del while
			}
				
			shortestProcess = processi.get(shortestProcessIndex);
			remainingTime.set(shortestProcessIndex, remainingTime.get(shortestProcessIndex)-1);		//diminuisco il burst time del processo più corto (al suo completamento)
			minBtMissing = remainingTime.get(shortestProcessIndex);		//il nuovo tempo minimo corrisponde al burst time (appena diminuito) del processo a cui manca meno tempo
			
			if (minBtMissing == 0) {	//il processo ha terminato la sua esecuzione
				
				complete++;		//aumentiamo il counter dei processi che hanno terminato la loro esecuzione
				check = false;
				minBtMissing = Integer.MAX_VALUE;
				
				finishTime = t+1;	//istante di tempo in cui il processo ha terminato la sua esecuzione
				
				//calc il waiting time del processo che ha appena terminato la sua esecuzione
				shortestProcess.setWt(finishTime - shortestProcess.getBt() - shortestProcess.getAt());
			}
			
			t++;	//andiamo avanti di un istante di tempo macchina dopo aver completato tutte le operazioni
		}
	}
	
	private void calcTurnAroundTime() {
		
		for (Processo pr : processi) {
			
			pr.setTat(pr.getBt() + pr.getWt());
		}
	}
	
	public void eseguiProcessi() {
		
		calcWaitingTime();
		calcTurnAroundTime();
		
		for(Processo pr : processi) {
			totaleWt += pr.getWt();
			totaleTat += pr.getTat();
		}
	}
	
	@Override
	public String toString() {
		
		return "Waiting Time --> Totale: " + totaleWt + " | Medio: " + totaleWt/totProcessi + "\n"
				+ "Turn Around Time --> Totale: " + totaleTat + " | Medio: " + totaleTat/totProcessi;
	}

}

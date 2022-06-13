package main;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		List<Processo> processi = new ArrayList<>();
		
		processi.add(new Processo(1, 6, 2));
		processi.add(new Processo(2, 2, 5));
		processi.add(new Processo(3, 8, 1));
		processi.add(new Processo(4, 3, 0));
		processi.add(new Processo(5, 4, 4));
		
		Processore processore = new Processore(processi);
		
		processore.eseguiProcessi();
		
		for(Processo pr : processi) {
			
			System.out.println(pr + "\n");
		}
		
		System.out.println(processore);
	}

}

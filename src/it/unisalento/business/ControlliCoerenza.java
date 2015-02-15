package it.unisalento.business;

import it.unisalento.view.Dialogs.MessageBoxes;

public class ControlliCoerenza {
	
	public static boolean checkTitolo(String t){
		if (t.length()>40){
			MessageBoxes.alert("Attenzione", "Il titolo può essere lungo al massimo 40 caratteri");
			return false;
		}
		return true;
	}
	
	public static boolean checkCosto(String c){
		try{
			Integer.parseInt(c);
		} catch(Exception e){
			MessageBoxes.alert("Attenzione", "Costo non valido");
			return false;
		}
		return true;

	}
	
	public static boolean checkDisp(String d){
		try{
			Integer.parseInt(d);
		} catch(Exception e){
			MessageBoxes.alert("Attenzione", "Disponibilità non valida");
			return false;
		}
		return true;

	}
	
	public static boolean checkISBN(String i){
		
		if (i.length()!=13){
			MessageBoxes.alert("Attenzione", "ISBN non valido");
			return false;
		}
		try{
			Integer.parseInt(i);
		} catch(Exception e){
			MessageBoxes.alert("Attenzione", "ISBN non valido");
			return false;
		}
		return true;

	}
	
	public static boolean checkPagine(String d){
		try{
			Integer.parseInt(d);
		} catch(Exception e){
			MessageBoxes.alert("Attenzione", "Inserimento non valido");
			return false;
		}
		return true;

	}
	
}

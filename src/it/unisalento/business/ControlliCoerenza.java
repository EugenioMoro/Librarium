package it.unisalento.business;

import it.unisalento.view.Dialogs.MessageBoxes;

public class ControlliCoerenza {
	
	public static boolean checkTitolo(String t, boolean showMessage){
		if (t.length()>40){
			if (showMessage) MessageBoxes.alert("Attenzione", "Il titolo può essere lungo al massimo 40 caratteri");
			return false;
		}
		return true;
	}
	
	public static boolean checkCosto(String c, boolean showMessage){
		try{
			Integer.parseInt(c);
		} catch(Exception e){
			if(showMessage) MessageBoxes.alert("Attenzione", "Costo non valido");
			return false;
		}
		return true;

	}
	
	public static boolean checkDisp(String d, boolean showMessage){
		try{
			Integer.parseInt(d);
		} catch(Exception e){
			if (showMessage) MessageBoxes.alert("Attenzione", "Disponibilità non valida");
			return false;
		}
		return true;

	}
	
	public static boolean checkISBN(String i, boolean showMessage){
		
		if (i.length()!=13){
			if (showMessage) MessageBoxes.alert("Attenzione", "ISBN: lunghezza non valida");
			return false;
		}
		try{
			Float.parseFloat(i);
		} catch(Exception e){
			if (showMessage) MessageBoxes.alert("Attenzione", "ISBN non valido");
			return false;
		}
		return true;

	}
	
	public static boolean checkPagine(String d, boolean showMessage){
		try{
			Integer.parseInt(d);
		} catch(Exception e){
			if (showMessage) MessageBoxes.alert("Attenzione", "Inserimento non valido");
			return false;
		}
		return true;

	}
	
	
}

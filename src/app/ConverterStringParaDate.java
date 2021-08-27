package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterStringParaDate {
	
	public static void main(String[] args) throws ParseException {
		String string1 = "16/03/2020";
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(string1);
		System.out.println("Data Formatada: " + data1);
		
		String string2 = "2020-03-10 00:00:00";
		Date data2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(string2);
		System.out.println("Data Formatada: " + data2);
	}
	

}

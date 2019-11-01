package compec.ufam.proc.main;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import compec.ufam.proc.controller.ExcelReader;
import compec.ufam.proc.model.Colaborador;
import compec.ufam.proc.model.Concurso;
import compec.ufam.proc.pdf.ListaPresenca;
import compec.ufam.proc.pdf.RPA;

public class Processador {

	public static void main(String[] args) throws ParseException {
		
		File planilha = new File("data.xlsx");
		
		try {
			
			Concurso concurso = ExcelReader.readConcurso(planilha);	concurso.print();
			ArrayList<Colaborador> listaColaboradores = ExcelReader.read(planilha);
			
			System.out.println("--------------------------------");
			
			RPA.show(concurso, listaColaboradores);
			ListaPresenca.show(concurso, listaColaboradores);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package compec.ufam.docs.main;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import compec.ufam.docs.controller.ExcelReader;
import compec.ufam.docs.controller.Sorter;
import compec.ufam.docs.model.Colaborador;
import compec.ufam.docs.model.Concurso;
import compec.ufam.docs.pdf.ListaPresenca;
import compec.ufam.docs.pdf.RPA;

public class Processador {

	public static void main(String[] args) throws ParseException {
		
		if (args.length < 1) {
			System.err.println("x Informe o nome do arquivo via parâmetro");
			return;
		}
		
		File planilha = new File(args[0]);
		
		try {
			
			Concurso concurso = ExcelReader.readConcurso(planilha);	concurso.print();
			ArrayList<Colaborador> listaColaboradores = Sorter.sort(ExcelReader.read(planilha));
			
			System.out.println("--------------------------------");
			
			RPA.show(concurso, listaColaboradores);
			ListaPresenca.show(concurso, listaColaboradores);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

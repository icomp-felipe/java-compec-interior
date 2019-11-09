package compec.ufam.proc.main;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import compec.ufam.proc.controller.ExcelReader;
import compec.ufam.proc.controller.Sorter;
import compec.ufam.proc.model.Colaborador;
import compec.ufam.proc.model.Concurso;
import compec.ufam.proc.pdf.ListaPresenca;
import compec.ufam.proc.pdf.RPA;

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

package compec.ufam.proc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import compec.ufam.proc.model.Colaborador;
import compec.ufam.proc.model.Concurso;

public class ExcelReader {

	public static ArrayList<Colaborador> read(File planilha) throws Exception {
		
		ArrayList<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
		
		// Preparando o ambiente...
		FileInputStream stream     = new FileInputStream(planilha);
		XSSFWorkbook workbook      = new XSSFWorkbook(stream);
		XSSFSheet sheet            = workbook.getSheetAt(0);
		Iterator<Row> rowIterator  = sheet.iterator();
		
		for (int i=0; i<5; i++)
			rowIterator.next();
		
		// Varrendo as linhas da planilha...
		while (rowIterator.hasNext()) {
					
			// Carregando um Colaborador da planilha 
			Row row = rowIterator.next();
			Colaborador colaborador = new Colaborador(row);
			
			listaColaboradores.add(colaborador);
					
		}
				
		// Limpando a casa
		workbook.close();
				
		return listaColaboradores;
		
	}

	public static Concurso readConcurso(File planilha) throws Exception {
		
		if (!planilha.canRead())
			throw new IOException("x Falha ao ler planilha '" + planilha.getName() + "'!");
		
		// Preparando o ambiente...
		FileInputStream stream = new FileInputStream(planilha);
		XSSFWorkbook workbook  = new XSSFWorkbook(stream);
		XSSFSheet sheet        = workbook.getSheetAt(0);
		
		Row info_concurso = sheet.getRow(3);
		Row info_escola   = sheet.getRow(4);
		
		Concurso concurso = new Concurso(info_concurso,info_escola, planilha.getName());
		
		// Limpando a casa
		workbook.close();
		
		return concurso;
	}
	
	

}

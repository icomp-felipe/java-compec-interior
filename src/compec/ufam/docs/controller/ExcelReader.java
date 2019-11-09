package compec.ufam.docs.controller;

import java.io.*;
import java.util.*;
import compec.ufam.docs.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/** Classe responsável pela importação de dados da 'Planilha de Dados de Colaborador Eventual Externo',
 *  disponível para consulta em 'res/template/dados-colaboradores.xlsx'.
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class ExcelReader {

	/** Retorna um ArrayList com os dados da planilha do Excel.
	 *  Este método começa a ler os dados a partir da linha 8.
	 *  A sequência de leitura das colunas é definida no enum 'Coluna'
	 *  @see Coluna
	 *  @throws IOException quando a planilha não é encontrada
	 *  */
	public static ArrayList<Colaborador> read(File planilha) throws IOException {
		
		ArrayList<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
		
		// Preparando o ambiente...
		FileInputStream stream     = new FileInputStream(planilha);
		XSSFWorkbook workbook      = new XSSFWorkbook(stream);
		XSSFSheet sheet            = workbook.getSheetAt(0);
		Iterator<Row> rowIterator  = sheet.iterator();
		
		// Pulando as linhas de cabeçalho
		for (int i=0; i<5; i++)
			rowIterator.next();
		
		// Varrendo as linhas úteis da planilha...
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

	/** Extrai os dados do concurso (nome, data de realização, nomes da escola e do município)
	 *  a partir da planilha do Excel.
	 *  @throws IOException quando a planilha não é encontrada */
	public static Concurso readConcurso(File planilha) throws IOException {
		
		// Se a planilha não for encontrada, retorno uma exceção
		if (!planilha.canRead())
			throw new IOException("x Falha ao ler planilha '" + planilha.getName() + "'!");
		
		// Preparando o ambiente...
		FileInputStream stream = new FileInputStream(planilha);
		XSSFWorkbook workbook  = new XSSFWorkbook(stream);
		XSSFSheet sheet        = workbook.getSheetAt(0);
		
		// Recuperando as linhas úteis
		Row info_concurso = sheet.getRow(3);
		Row info_escola   = sheet.getRow(4);
		
		// Preparando o concurso com os dados da linha do Excel
		Concurso concurso = new Concurso(info_concurso,info_escola, planilha.getName());
		
		// Limpando a casa
		workbook.close();
		
		return concurso;
	}

}

package compec.ufam.docs.model;

import com.phill.libs.*;
import org.apache.poi.ss.usermodel.*;

/** Classe auxiliar que implementa um extrator de dados genérico de uma célula do Excel.
 *  @author Felipe André - fass@icomp.ufam.edd.br
 *  @version 1.0, 01/11/2019 */
public class CellDataExtractor {
	
	/** Extrai uma String de uma célula do Excel */
	protected static String parseString(Row row, int column) {
		
		final Cell cell = row.getCell(column); if (cell == null) return null;
		String raw_data = getCellContent(cell);
		String nome     = raw_data;
		
		return StringUtils.trim(nome);
	}
	
	/** Extrai o conteúdo de uma célula do Excel e o converte pra String */
	@SuppressWarnings("deprecation")
	protected static String getCellContent(Cell cell) {
		
		// Se deu algum erro, pego minha bike e vou embora!
		if (cell == null)
			return null;
		
		// Especifica um tratamento diferente para os diversos tipos de dados
		switch (cell.getCellType()) {
		
			// Nem precisa eu dizer nada né ¯\_(ツ)_/¯
			case STRING:
				return cell.getStringCellValue();
				
			case NUMERIC:
				cell.setCellType(CellType.STRING);
				return cell.getStringCellValue();
			
			/* Agora uma aulinha básica de Excel:
			 * For formula cells, excel stores two things. One is the Formula itself, the other is
			 * the "cached" value (the last value that the formula was evaluated as). f you want to
			 * get the last cached value (which may no longer be correct, but as long as Excel saved
			 * the file and you haven't changed it it should be), you'll want something like:     */
			case FORMULA:
				
				switch (cell.getCachedFormulaResultType()) {
				
					case STRING:
						return cell.getRichStringCellValue().toString();
					
					default:
						System.out.println("FORMULA NULL");
						return null;
					
				}
				
			default:
				System.out.println(cell.getCellType());
				return null;
				
		}
		
	}

}

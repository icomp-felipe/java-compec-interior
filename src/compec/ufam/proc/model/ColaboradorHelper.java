package compec.ufam.proc.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import com.phill.libs.CPFParser;
import com.phill.libs.PISParser;
import com.phill.libs.StringUtils;
import compec.ufam.proc.controller.Coluna;

public class ColaboradorHelper {
	
	/** Extrai de uma célula o nome do colaborador */
	protected static String parseNome(Row row) {
		return parseString(row,Coluna.NOME.ordinal());
	}

	/** Extrai de uma célula o número de CPF do colaborador */
	protected static String parseCPF(Row row) {
		
		final Cell cell = row.getCell(Coluna.CPF.ordinal()); if (cell == null) return null;
		String raw_data = getCellContent(cell);
		String cpf_full = StringUtils.extractNumbers(raw_data);
		
		// Adiciona os zeros
		cpf_full = String.format("%011d",Long.parseLong(cpf_full));
		
		if (!CPFParser.parse(cpf_full))
			System.err.println("x CPF inválido na linha " + row.getRowNum());
		
		return cpf_full;
	}
	
	/** Extrai de uma célula o número de CPF do colaborador */
	protected static String parsePIS(Row row) {
		
		final Cell cell = row.getCell(Coluna.PIS.ordinal()); if (cell == null) return null;
		String raw_data = getCellContent(cell);
		String pis_full = StringUtils.extractNumbers(raw_data);
		
		if (!PISParser.parse(pis_full))
			System.err.println("x PIS inválido na linha " + row.getRowNum());
		
		return pis_full;
	}
	
	/** Extrai de uma célula o número de RG */
	protected static String parseRG(Row row) {
		return parseString(row,Coluna.RG.ordinal());
	}
	
	/** Extrai de uma célula o órgão emissor do RG */
	protected static String parseOrgaoRG(Row row) {
		return parseString(row,Coluna.ORGAO_RG.ordinal());
	}
	
	/** Extrai de uma célula a função do colaborador no concurso */
	protected static Funcao parseFuncao(Row row) {
		
		String funcao_string = parseString(row,Coluna.FUNCAO.ordinal());
		
		if (funcao_string == null) {
			System.err.println("x Falha ao atribuir função na linha " + row.getRowNum());
			return null;
		}
		
		for (Funcao funcao: Funcao.values())
			if (funcao.getNome().equals(funcao_string))
				return funcao;
			
		System.err.println("x Falha ao atribuir função na linha " + row.getRowNum());
		
		return null;
	}
	
	/** Extrai de uma célula o nome do banco */
	protected static String parseBanco(Row row) {
		return parseString(row,Coluna.BANCO.ordinal());
	}
	
	/** Extrai de uma célula o número da agência */
	protected static String parseAgencia(Row row) {
		return parseString(row,Coluna.AGENCIA.ordinal());
	}
	
	/** Extrai de uma célula o número da conta */
	protected static String parseConta(Row row) {
		return parseString(row,Coluna.CONTA.ordinal());
	}
	
	/** Extrai uma String de uma célula */
	protected static String parseString(Row row, int column) {
		
		final Cell cell = row.getCell(column); if (cell == null) return null;
		String raw_data = getCellContent(cell);
		String nome     = raw_data;
		
		return StringUtils.trim(nome);
	}
	
	/** Extrai o conteúdo de uma célula do Excel */
	@SuppressWarnings("deprecation")
	private static String getCellContent(Cell cell) {
		
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

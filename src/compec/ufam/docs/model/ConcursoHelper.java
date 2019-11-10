package compec.ufam.docs.model;

import org.apache.poi.ss.usermodel.Row;

/** Classe responsável por validar dados e montar a classe Concurso.
 *  @see Concurso
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class ConcursoHelper extends CellDataExtractor {
	
	/** Recupera o nome da escola (já normalizado) */
	protected static String parseNomeEscola(Row row, String planilha) {
		
		// Extraindo dados
		String escola = parseString(row,2);
		
		// Erro se o dado não pôde ser recuperado
		if (escola == null) {
			System.err.println("x Não foi possível obter o nome da escola da planilha '" + planilha + "'");
			return null;
		}
		
		// Retornando dado formatado
		return escola.trim();
	}

	/** Recupera o nome do município */
	protected static String parseMunicipio(Row info_escola, String planilha) {
		
		// Extraindo dados
		String municipio = parseString(info_escola,7);
		
		// Erro se o dado não pôde ser recuperado
		if (municipio == null) {
			System.err.println("x Não foi possível obter o nome do município da planilha '" + planilha + "'");
			return null;
		}
		
		// Retornando dado formatado
		return municipio.trim();
	}

	/** Recupera o nome do concurso (extraído a partir do último caractere '-' da linha) */
	protected static String parseConcurso(String infos, String planilha) {

		// Erro se o dado não pôde ser recuperado
		if (infos == null) {
			System.err.println("x Falha ao extrair nome do concurso da planilha '" + planilha + "'");
			return null;
		}
		
		// Retornando dado formatado
		return infos.substring(10,infos.lastIndexOf('-')).trim();
	}

	/** Recupera a data de realização do concurso (a partir do último caractere ':' da linha) */
	protected static String parseData(String infos, String planilha) {
		
		// Erro se o dado não pôde ser recuperado
		if (infos == null) {
			System.err.println("x Falha ao extrair data do concurso da planilha '" + planilha + "'");
			return null;
		}
		
		// Retornando dado formatado
		return infos.substring(infos.lastIndexOf(':')+2).trim();
	}

}

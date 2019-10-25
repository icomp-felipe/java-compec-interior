package compec.ufam.proc.model;

import org.apache.poi.ss.usermodel.Row;

public class ConcursoHelper {
	
	protected static String parseNomeEscola(Row row, String planilha) {
		
		String escola = ColaboradorHelper.parseString(row,2);
		
		if (escola == null) {
			System.err.println("x Não foi possível obter o nome da escola da planilha '" + planilha + "'");
			return null;
		}
		
		return escola.trim();
	}

	public static String parseMunicipio(Row info_escola, String planilha) {
		
		String municipio = ColaboradorHelper.parseString(info_escola,7);
		
		if (municipio == null) {
			System.err.println("x Não foi possível obter o nome do município da planilha '" + planilha + "'");
			return null;
		}
		
		return municipio.trim();
	}

	public static String parseConcurso(String infos, String planilha) {

		if (infos == null) {
			System.err.println("x Falha ao extrair nome do concurso da planilha '" + planilha + "'");
			return null;
		}
		
		return infos.substring(10,infos.lastIndexOf('-')).trim();
	}

	public static String parseDara(String infos, String planilha) {
		
		if (infos == null) {
			System.err.println("x Falha ao extrair data do concurso da planilha '" + planilha + "'");
			return null;
		}
		
		return infos.substring(infos.lastIndexOf(':')+2).trim();
	}

}

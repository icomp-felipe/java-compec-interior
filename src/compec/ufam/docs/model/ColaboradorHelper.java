package compec.ufam.docs.model;

import com.phill.libs.*;
import com.phill.libs.br.CPFParser;
import com.phill.libs.br.PISParser;

import compec.ufam.docs.controller.*;
import org.apache.poi.ss.usermodel.*;

/** Classe responsável por validar dados e montar a classe Colaborador.
 *  @see Colaborador
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class ColaboradorHelper extends CellDataExtractor {

	/** Extrai de uma célula o nome do colaborador */
	protected static String parseNome(Row row) {
		return StringUtils.wipeMultipleSpaces(parseString(row,Coluna.NOME.ordinal()));
	}

	/** Extrai de uma célula o número de CPF do colaborador */
	protected static String parseCPF(Row row) {
		
		// Recuperando o número de CPF
		String raw_data = parseString(row,Coluna.CPF.ordinal());
		String cpf_full = StringUtils.extractNumbers(raw_data);
		
		// Adiciona os zeros
		cpf_full = String.format("%011d",Long.parseLong(cpf_full));
		
		// Aplicando validação
		if (!CPFParser.parse(cpf_full))
			System.err.println("x CPF inválido na linha " + getRow(row));
		
		return cpf_full;
	}
	
	/** Extrai de uma célula o número de CPF do colaborador */
	protected static String parsePIS(Row row) {
		
		// Recuperando o número de PIS
		String raw_data = parseString(row,Coluna.PIS.ordinal());
		String pis_full = StringUtils.extractNumbers(raw_data);
		
		// Aplicando validação
		if (!PISParser.parse(pis_full))
			System.err.println("x PIS inválido na linha " + getRow(row));
		
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
	
	/** Extrai de uma célula a função do colaborador no concurso.
	 *  Além disso, reconhece a função informada no Excel e a transforma
	 *  em um enum Funcao. */
	protected static Funcao parseFuncao(Row row) {
		
		// Recuperando o nome da função a partir da célula
		String funcao_string = parseString(row,Coluna.FUNCAO.ordinal());
		
		// Se a função for nula, saio aqui
		if (funcao_string == null) {
			System.err.println("x Falha ao atribuir função na linha " + getRow(row));
			return null;
		}
		
		// Busco e retorno o enum correspondente à função lida
		for (Funcao funcao: Funcao.values())
			if (funcao.getNome().equals(funcao_string))
				return funcao;
		
		// Se nenhuma função foi reconhecida, aviso
		System.err.println("x Falha ao atribuir função na linha " + getRow(row));
		
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
	
	/** Recupera o número de uma linha do Excel, contado a partir do início das linhas de dados */
	private static int getRow(Row row) {
		return row.getRowNum() - 6;
	}
	
}

package compec.ufam.docs.model;

import com.phill.libs.StringUtils;
import com.phill.libs.br.CPFParser;
import com.phill.libs.br.PISParser;

import org.apache.poi.ss.usermodel.Row;

/** Classe que representa um colaborador do sistema. Basicamente é montada com os dados de uma
 *  linha da planilha do Excel e disponível para consulta através dos métodos 'get'. Todos os
 *  dados aqui são formatados e verificados pela classe 'ColaboradorHelper'. Também possui
 *  um método 'print' útil para debug desta classe.
 *  @see ColaboradorHelper
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class Colaborador {
	
	private final String nome, cpf, pis, rg, orgao_rg, banco, agencia, cc;
	private final Funcao funcao;
	
	/** Construtor da classe, aqui os dados já são validados e formatados pela classe abaixo:
	 *  @see ColaboradorHelper */
	public Colaborador(Row row) {
		
		this.nome     = ColaboradorHelper.parseNome   (row);
		this.cpf      = ColaboradorHelper.parseCPF    (row);
		this.pis      = ColaboradorHelper.parsePIS    (row);
		this.rg       = ColaboradorHelper.parseRG     (row);
		this.orgao_rg = ColaboradorHelper.parseOrgaoRG(row);
		this.funcao   = ColaboradorHelper.parseFuncao (row);
		this.banco    = ColaboradorHelper.parseBanco  (row);
		this.agencia  = ColaboradorHelper.parseAgencia(row);
		this.cc       = ColaboradorHelper.parseConta  (row);
		
	}
	
	/*************** Bloco de Getters Genéricos *******************/
	
	/** Recupera o nome do colaborador (já normalizado) */
	public String getNome() {
		return StringUtils.BR.normaliza(this.nome);
	}
	
	/** Recupera o número de CPF do colaborador (com máscara de CPF) */
	public String getCPF() {
		return CPFParser.format(this.cpf);
	}
	
	/** Tenta converter o número de CPF, armazenado normalmente como String,
	 *  para long, caso haja alguma falha, o número '0' é retornado. */
	public long getCPFAsLong() {
		try { return Long.parseLong(this.cpf); }
		catch (Exception exception) { return 0L; }
	}
	
	/** Recupera o número de PIS do colaborador (com máscara de PIS) */
	public String getPIS() {
		return PISParser.format(this.pis);
	}
	
	/** Recupera o número de RG */
	public String getRG() {
		return this.rg;
	}
	
	/** Recupera o órgão emissor de RG */
	public String getOrgaoEmissor() {
		return this.orgao_rg;
	}
	
	/** Recupera o nome da função do colaborador no concurso */
	public String getFuncao() {
		return this.funcao.getNome();
	}
	
	/** Recupera o enum que representa a função do colaborador no concurso */
	public Funcao getFuncaoEnum() {
		return this.funcao;
	}
	
	/** Recupera o nome do banco */
	public String getBanco() {
		return this.banco;
	}
	
	/** Recupera o número da agência bancária */
	public String getAgencia() {
		return this.agencia;
	}
	
	/** Recupera o número da conta bancária */
	public String getConta() {
		return this.cc;
	}
	
	/*************** Bloco de Getters do JasperReports ************/
	
	/** Recupera o valor líquido por extenso */
	public String getExtenso() {
		return this.funcao.getExtenso();
	}
	
	/** Recupera o valor bruto */
	public double getValorBruto() {
		return this.funcao.getValorBruto();
	}
	
	/** Recupera o valor de desconto do INSS */
	public double getDescINSS() {
		return this.funcao.getDescINSS();
	}
	
	/** Recupera o valor de desconto de ISS */
	public double getDescISS() {
		return this.funcao.getDescISS();
	}
	
	/** Recupera o valor total dos descontos */
	public double getDescTotal() {
		return this.funcao.getDescTotal();
	}
	
	/** Recupera o valor líquido */
	public double getValorLiquido() {
		return this.funcao.getValorLiquido();
	}
	
	/** Imprime todas as informações desta classe */
	public void print() {
		
		System.out.println("----------| Colaborador |----------");
		System.out.println(":: Nome: " + getNome());
		System.out.println(":: CPF: " + getCPF());
		System.out.println(":: PIS: " + getPIS());
		System.out.println(":: RG: " + getRG());
		System.out.println(":: Órgao Emissor: " + getOrgaoEmissor());
		System.out.println(":: Função: " + getFuncao());
		System.out.println(":: Banco: " + getBanco());
		System.out.println(":: Agência: " + getAgencia());
		System.out.println(":: Conta: " + getConta());
		System.out.println("-----------------------------------");
		
	}

}

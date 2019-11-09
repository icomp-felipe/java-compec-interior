package compec.ufam.docs.model;

import org.apache.poi.ss.usermodel.Row;
import com.phill.libs.StringUtils;

/** Classe que representa um colaborador do sistema. Basicamente é montada com os dados de uma
 *  linha da planilha do Excel e disponível para consulta através dos métodos 'get'. Todos os
 *  dados aqui são formatados e verificados pela classe 'ColaboradorHelper'. Também possui
 *  um método 'print' útil para debug desta classe.
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
	
	/** Recupera o nome do colaborador (já normalizado) */
	public String getNome() {
		return StringUtils.firstLetterLowerCase(this.nome);
	}
	
	/** Recupera o número de CPF do colaborador (com máscara de CPF) */
	public String getCPF() {
		return StringUtils.parseCPF(this.cpf);
	}
	
	/** Tenta converter o número de CPF, armazenado normalmente como String,
	 *  para long, caso haja alguma falha, o número '0' é retornado. */
	public long getCPFAsLong() {
		try { return Long.parseLong(this.cpf); }
		catch (Exception exception) { return 0L; }
	}
	
	/** Recupera o número de PIS do colaborador (com máscara de PIS) */
	public String getPIS() {
		return StringUtils.parsePIS(this.pis);
	}
	
	public String getRG() {
		return this.rg;
	}
	
	public String getOrgaoEmissor() {
		return this.orgao_rg;
	}
	
	public String getFuncao() {
		return this.funcao.getNome();
	}
	
	public Funcao getFuncaoEnum() {
		return this.funcao;
	}
	
	public String getBanco() {
		return this.banco;
	}
	
	public String getAgencia() {
		return this.agencia;
	}
	
	public String getConta() {
		return this.cc;
	}
	
	
	public String getExtenso() {
		return this.funcao.getExtenso();
	}
	
	public double getValorBruto() {
		return this.funcao.getValorBruto();
	}
	
	public double getDescINSS() {
		return this.funcao.getDescINSS();
	}
	
	public double getDescISS() {
		return this.funcao.getDescISS();
	}
	
	public double getDescTotal() {
		return this.funcao.getDescTotal();
	}
	
	public double getValorLiquido() {
		return this.funcao.getValorLiquido();
	}
	
	
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

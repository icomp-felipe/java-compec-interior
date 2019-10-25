package compec.ufam.proc.model;

import org.apache.poi.ss.usermodel.Row;

import com.phill.libs.StringUtils;

public class Colaborador {
	
	private final String nome, cpf, pis, rg, orgao_rg, banco, agencia, cc;
	private final Funcao funcao;
	
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
	
	public String getNome() {
		return StringUtils.firstLetterLowerCase(this.nome);
	}
	
	public String getCPF() {
		return StringUtils.parseCPF(this.cpf);
	}
	
	public long getCPFAsLong() {
		try { return Long.parseLong(this.cpf); }
		catch (Exception exception) { return 0L; }
	}
	
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

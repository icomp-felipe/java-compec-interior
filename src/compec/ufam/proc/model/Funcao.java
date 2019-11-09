package compec.ufam.proc.model;

import com.phill.libs.StringUtils;

public enum Funcao {

	// Esta ordem é a utilizada na ordenação das listas
	COORD_LOCAL("Coordenador Local",450.51,49.56,22.53,72.09,378.42),
	APLICADOR("Aplicador",180.18,19.82,9.01,28.83,151.35),
	APOIO("Apoio",150.15,16.52,7.51,24.03,126.12),
	PORTEIRO("Porteiro",138.14,15.20,6.91,22.11,116.03),
	MOTORISTA("Motorista",363.16,39.95,18.16,58.11,305.05);
	
	private String nome, extenso;
	private double val_bruto, desc_inss, desc_iss, total_desc, val_liquido;
	
	Funcao(String nome, double val_bruto, double desc_inss, double desc_iss, double total_desc, double val_liquido) {
		
		this.nome        = nome;
		this.val_bruto   = val_bruto;
		this.desc_inss   = desc_inss;
		this.desc_iss    = desc_iss;
		this.total_desc  = total_desc;
		this.val_liquido = val_liquido;
		
		this.extenso = StringUtils.getExtenso(val_liquido);
		
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getExtenso() {
		return extenso;
	}

	public double getValorBruto() {
		return val_bruto;
	}

	public double getDescINSS() {
		return desc_inss;
	}

	public double getDescISS() {
		return desc_iss;
	}

	public double getDescTotal() {
		return total_desc;
	}

	public double getValorLiquido() {
		return val_liquido;
	}
	
}

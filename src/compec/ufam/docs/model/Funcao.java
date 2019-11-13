package compec.ufam.docs.model;

import com.phill.libs.StringUtils;

/** Enum que representa as funções em concurso dos colaboradores. Aqui fica a configuração
 *  de todos os valores (bruto,descontos,...) individuais de cada função, bem como o cálculo
 *  de valor líquido por extenso (a ser utilizado pelo RPA). Outro detalhe importante é que
 *  a disposição da declaração das funções impacta diretamente no algoritmo de ordenação
 *  implementado pela classe 'Sorter'
 *  @see Sorter
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public enum Funcao {

	// Esta ordem é a utilizada na ordenação das listas
	// ENUM    ("Nome da Função"      ,  BRUTO,  INSS,   ISS, TOTAL, LIQUID),
	COORD_LOCAL("Coordenador Local"   , 450.51, 49.56, 22.53, 72.09, 378.42),
	APLICADOR  ("Aplicador"           , 180.18, 19.82,  9.01, 28.83, 151.35),
	APOIO      ("Apoio"               , 150.15, 16.52,  7.51, 24.03, 126.12),
	PORTEIRO   ("Porteiro"            , 138.14, 15.20,  6.91, 22.11, 116.03),
	INTERPRETE ("Intérprete de Libras", 360.42, 39.65, 18.02, 57.67, 302.75),
	TRANSCRITOR("Transcritor"         , 270.32, 29.74, 13.52, 43.26, 227.06),
	LEDOR      ("Ledor"               , 330.39, 36.34, 16.52, 52.86, 277.53),
	AUX_ROTA   ("Aux. de Rota"        , 240.24, 26.43, 12.01, 38.44, 201.80),
	MOTORISTA  ("Motorista"           , 363.16, 39.95, 18.16, 58.11, 305.05);
	
	// Atributos do enum
	private String nome, extenso;
	private double val_bruto, desc_inss, desc_iss, total_desc, val_liquido;
	
	// Constrói o enum com os parâmetros informados e já calcula o valor líquido por extenso
	Funcao(String nome, double val_bruto, double desc_inss, double desc_iss, double total_desc, double val_liquido) {
		
		this.nome        = nome;
		this.val_bruto   = val_bruto;
		this.desc_inss   = desc_inss;
		this.desc_iss    = desc_iss;
		this.total_desc  = total_desc;
		this.val_liquido = val_liquido;
		
		this.extenso = StringUtils.getExtenso(val_liquido);
		
	}
	
	/** Recupera o nome da função */
	public String getNome() {
		return this.nome;
	}

	/** Recupera o valor bruto */
	public double getValorBruto() {
		return val_bruto;
	}
	
	/** Recupera o valor de desconto do INSS */
	public double getDescINSS() {
		return desc_inss;
	}

	/** Recupera o valor de desconto de ISS */
	public double getDescISS() {
		return desc_iss;
	}

	/** Recupera o valor total dos descontos */
	public double getDescTotal() {
		return total_desc;
	}
	
	/** Recupera o valor líquido */
	public double getValorLiquido() {
		return val_liquido;
	}
	
	/** Recupera o valor líquido por extenso */
	public String getExtenso() {
		return extenso;
	}

}

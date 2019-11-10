package compec.ufam.docs.model;

import com.phill.libs.StringUtils;
import org.apache.poi.ss.usermodel.Row;

/** Classe que representa um concurso do sistema. Basicamente é montada com os dados de uma
 *  linha da planilha do Excel e disponível para consulta através dos métodos 'get'. Todos os
 *  dados aqui são formatados e verificados pela classe 'ConcursoHelper'. Também possui
 *  um método 'print' útil para debug desta classe.
 *  @see ConcursoHelper
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class Concurso {
	
	private String concurso, data_concurso, escola, municipio;
	
	/** Construtor da classe, aqui os dados já são validados e formatados pela classe abaixo:
	 *  @see ConcursoHelper */
	public Concurso(Row info_concurso, Row info_escola, String planilha) {
		
		// Aqui tenho que extrair logo os dados, pq concurso e data estão na mesma linha
		String infos  = ConcursoHelper.parseString(info_concurso,0);
		
		this.concurso      = ConcursoHelper.parseConcurso(infos,planilha);
		this.data_concurso = ConcursoHelper.parseData(infos,planilha);
		
		this.escola    = ConcursoHelper.parseNomeEscola(info_escola,planilha);
		this.municipio = ConcursoHelper.parseMunicipio(info_escola,planilha);
		
	}
	
	/** Recupera o nome do concurso (já normalizado) */
	public String getNome() {
		return StringUtils.firstLetterLowerCase(this.concurso);
	}
	
	/** Recupera a data de realização do concurso */
	public String getDataConcurso() {
		return this.data_concurso;
	}
	
	/** Recupera o nome da escola (já normalizado) */
	public String getEscola() {
		return StringUtils.firstLetterLowerCase(this.escola);
	}
	
	/** Recupera o nome do município */
	public String getMunicipio() {
		return this.municipio;
	}
	
	/** Imprime todas as informações desta classe */
	public void print() {
		
		System.out.println("----------| Concurso |----------");
		System.out.println(":: Nome: " + getNome());
		System.out.println(":: Data: " + getDataConcurso());
		System.out.println(":: Local de Aplicação: " + getEscola());
		System.out.println(":: Município de Aplicação: " + getMunicipio());
		
	}
	
}

package compec.ufam.proc.model;

import org.apache.poi.ss.usermodel.Row;

public class Concurso {
	
	private String concurso, data_concurso, escola, municipio;
	
	public Concurso(Row info_concurso, Row info_escola, String planilha) {
		
		String infos  = ColaboradorHelper.parseString(info_concurso,0);
		
		this.concurso      = ConcursoHelper.parseConcurso(infos,planilha);
		this.data_concurso = ConcursoHelper.parseDara(infos,planilha);
		
		this.escola    = ConcursoHelper.parseNomeEscola(info_escola,planilha);
		this.municipio = ConcursoHelper.parseMunicipio(info_escola,planilha);
		
	}
	
	public String getNome() {
		return this.concurso;
	}
	
	public String getDataConcurso() {
		return this.data_concurso;
	}
	
	public String getEscola() {
		return this.escola;
	}
	
	public String getMunicipio() {
		return this.municipio;
	}
	
	public void print() {
		
		System.out.println("----------| Concurso |----------");
		System.out.println(":: Nome: " + getNome());
		System.out.println(":: Data: " + getDataConcurso());
		System.out.println(":: Local de Aplicação: " + getEscola());
		System.out.println(":: Município de Aplicação: " + getMunicipio());
		
	}
	
}

package compec.ufam.docs.controller;

import java.util.*;
import java.util.stream.*;
import compec.ufam.docs.model.*;

/** Classe responsável por agrupar e ordenar os dados para geração dos documentos necessários para a aplicação.
 *  Agrupa por função e por ordem alfabética de nome dos colaboradores. A ordem de agrupamento das funções é
 *  definida no enum 'compec.ufam.docs.model.Funcao'
 *  @see Funcao
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class Sorter {
	
	public static ArrayList<Colaborador> sort(ArrayList<Colaborador> listaColaboradores) {
		
		ArrayList <Colaborador> listaOrdenada = new ArrayList<Colaborador>(listaColaboradores.size());
		Comparator<Colaborador> comparator    = new NameComparator();
		
		// Aqui derivo várias listas de colaboradores em um Map, onde cada lista agrupa apenas colaboradores de uma mesma função
		Map<Funcao,List<Colaborador>> map_funcoes = listaColaboradores.stream().collect(Collectors.groupingBy(Colaborador::getFuncaoEnum));
		
		// Nesta etapa faco a ordenacao do Map por ordem crescente de número de funções (definido no próprio enum 'Funcao')
		Map<Funcao,List<Colaborador>> sorted = map_funcoes.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e2, LinkedHashMap::new));
		
		// Aqui ordeno todas as listas do Map por ordem alfabética de nomes de colaboradores
		sorted.forEach((funcao,lista) -> Collections.sort(lista,comparator));
				
		// Por fim, adiciono os elementos ordenados em uma nova lista
		sorted.forEach((funcao,lista) -> merge(lista,listaOrdenada));
		
		return listaOrdenada;
		
	}
	
	/** Apenas copia os dados de 'listaOrigem' para a 'listaDestino' */
	private static void merge(List<Colaborador> listaOrigem, ArrayList<Colaborador> listaDestino) {
		listaOrigem.forEach((colaborador) -> listaDestino.add(colaborador));
	}
	
	/** Implementa o comparador de 'Nome'. Trabalha com ordem alfabética do nome do candidato */
	private static class NameComparator implements Comparator<Colaborador> {

		@Override
		public int compare(Colaborador colaborador1, Colaborador colaborador2) {
			return colaborador1.getNome().compareToIgnoreCase(colaborador2.getNome());
		}
		
	}

}

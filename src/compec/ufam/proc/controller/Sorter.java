package compec.ufam.proc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import compec.ufam.proc.model.Colaborador;
import compec.ufam.proc.model.Funcao;

public class Sorter {
	
	public static ArrayList<Colaborador> sort(ArrayList<Colaborador> listaColaboradores) {
		
		ArrayList <Colaborador> listaOrdenada = new ArrayList<Colaborador>(listaColaboradores.size());
		Comparator<Colaborador> comparator    = new NameComparator();
		
		// Aqui derivo várias listas de colaboradores em um Map, onde cada lista agrupa apenas colaboradores de uma mesma função
		Map<Funcao,List<Colaborador>> map_funcoes = listaColaboradores.stream().collect(Collectors.groupingBy(Colaborador::getFuncaoEnum));
		
		// Nesta etapa faco a ordenacao do Map por ordem crescente de número de funções (definido no próprio enum 'Funcao')
		Map<Funcao,List<Colaborador>> sorted = map_funcoes.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e2, LinkedHashMap::new));
		
		// Aqui ordeno todas as listas do Map por ordem alfabética de nomes de colaboradores
		sorted.forEach((key,value) -> Collections.sort(value,comparator));
				
		// Por fim, adiciono os elementos ordenados em uma nova lista
		sorted.forEach((key,value) -> merge(listaOrdenada,value));
		
		return listaOrdenada;
		
	}
	
	/** Apenas copia os dados de 'listaMap' para a 'listaColaboradores' */
	private static void merge(ArrayList<Colaborador> listaColaboradores, List<Colaborador> listaMap) {
		listaMap.forEach((colaborador) -> listaColaboradores.add(colaborador));
	}
	
	/** Implementa o comparador de 'Nome'. Trabalha com ordem alfabética do nome do candidato */
	private static class NameComparator implements Comparator<Colaborador> {

		@Override
		public int compare(Colaborador colaborador1, Colaborador colaborador2) {
			return colaborador1.getNome().compareToIgnoreCase(colaborador2.getNome());
		}
		
	}

}

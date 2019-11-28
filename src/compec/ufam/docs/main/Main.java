package compec.ufam.docs.main;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.cli.*;

import compec.ufam.docs.controller.ExcelReader;
import compec.ufam.docs.controller.Sorter;
import compec.ufam.docs.model.Colaborador;
import compec.ufam.docs.model.Concurso;
import compec.ufam.docs.pdf.LC;
import compec.ufam.docs.pdf.ListaPresenca;
import compec.ufam.docs.pdf.RPA;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		Options options = new Options();
		
		Option input = new Option("i","input",true,"Especifica o caminho de origem da planilha de dados");
		input.setRequired(true);
		options.addOption(input);
		
		Option outputDir = new Option("o","output-dir",true,"Especifica o diretório de saída de relatórios");
		options.addOption(outputDir);
		
		Option ignoreErrors = new Option("f","ignore-errors",false,"Ignora validação de dados e força a geração de relatórios");
		options.addOption(ignoreErrors);
		
		Option lpOnly = new Option("lp","lp-only",false,"Gera apenas a Lista de Presença");
		options.addOption(lpOnly);
		
		Option rpaOnly = new Option("rpa","rpa-only",false,"Gera apenas o Recibo de Pagamento a Autônomo");
		options.addOption(rpaOnly);
		
		Option parseOnly = new Option("p","parse-only",false,"Apenas executa validação de dados no arquivo de entrada");
		options.addOption(parseOnly);
		
		Option lc = new Option("l","lc",false,"Gera uma LC");
		options.addOption(lc);
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter  formatter = new HelpFormatter();
		CommandLine line = parser.parse(options,args);
		
		try {
			line = parser.parse(options, args);
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
		
		// Recupera a quantidade de argumentos
		int qtdArgs = args.length;
		
		boolean apenasLP  = line.hasOption("lp");
		boolean apenasRPA = line.hasOption("rpa");
		boolean ignoraErr = line.hasOption("f");
		boolean apenasLC  = line.hasOption("lc");
		
		// Recuperando a planilha de entrada
		File planilha = new File(line.getOptionValue('i'));
		
		
		
		// Opções '-lp-only' e 'rpa-only' ativadas ao mesmo tempo
		if (apenasLP && apenasRPA) {
			System.err.println("x Decidir se quer gerar apenas Recido ou Lista de Presença");
			return;
		}
		
		
		
		// Opção '-parse-only' ativada
		if (line.hasOption('p')) {
			
			if (qtdArgs != 3) {
				System.err.println("x Utilização errada! Exemplo: '-i $file -parse-only'");
				return;
			}
			
		}
		
		
		
		
		Concurso concurso = null;
		ArrayList<Colaborador> listaColaboradores;
		
		try {
			concurso = ExcelReader.readConcurso(planilha);	concurso.print();
			listaColaboradores = Sorter.sort(ExcelReader.read(planilha));
			System.out.println("--------------------------------");
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		if (line.hasOption('p'))
			return;
		
		
		// Tem só a opção '-i $file'
		if (qtdArgs == 2) {
			try {
				RPA.show(concurso, listaColaboradores);
				ListaPresenca.show(concurso, listaColaboradores);
			}
			catch (Exception e) { e.printStackTrace(); }
			return;
		}
		
		
		
		
		
		
		// Imprimir pra arquivo de saída
		if (line.hasOption('o')) {
			
			File dirSaida = new File(line.getOptionValue('o'));
			
			if (apenasLC)
				try { LC.exportPDF(concurso,listaColaboradores,dirSaida); }
		    	catch (Exception e) { e.printStackTrace(); }
			
			else if (apenasRPA)
				try { RPA.exportPDF(concurso, listaColaboradores,dirSaida); }
			    catch (Exception e) { e.printStackTrace(); }
			
			else if (apenasLP)
				try { ListaPresenca.exportPDF(concurso, listaColaboradores,dirSaida); }
		    	catch (Exception e) { e.printStackTrace(); }
			
			else
				try {
					RPA.exportPDF(concurso, listaColaboradores,dirSaida);
					ListaPresenca.exportPDF(concurso, listaColaboradores,dirSaida);
				}
				catch (Exception e) { e.printStackTrace(); }
			
			return;
		}

		// Imprimir na tela, apenas
		else {
			
			if (apenasLC)
				try { LC.show(concurso,listaColaboradores); }
		    	catch (Exception e) { e.printStackTrace(); }
			
			else if (apenasRPA)
				try { RPA.show(concurso, listaColaboradores); }
			    catch (Exception e) { e.printStackTrace();    }
			
			else if (apenasLP)
				try { ListaPresenca.show(concurso, listaColaboradores); }
		    	catch (Exception e) { e.printStackTrace();              }
			
			else
				try {
					RPA.show(concurso, listaColaboradores);
					ListaPresenca.show(concurso, listaColaboradores);
				}
				catch (Exception e) { e.printStackTrace(); }
			
			return;
		}
		
	}

}

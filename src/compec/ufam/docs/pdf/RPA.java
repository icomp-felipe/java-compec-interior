package compec.ufam.docs.pdf;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import com.phill.libs.*;
import compec.ufam.docs.model.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.util.*;

/** Classe responsável pela geração de Recibos de Pagamento a Autônomo utilizando o JasperReports.
 *  @author Felipe André - fass@icomp.ufam.edu.br
 *  @version 1.0, 01/11/2019 */
public class RPA {

	/** Monta uma lista de recibos e exibe na tela.
	 *  @param concurso Informações do concurso
	 *  @param listaColaboradores contém a lista de colaboradores
	 *  @throws JRException quando há alguma falha na geração do relatório 
	 *  @throws IOException quando algum arquivo, tanto do sistema quanto a planilha, não são encontrados */
	public static void show(Concurso concurso, ArrayList<Colaborador> listaColaboradores) throws IOException, JRException {
		
		// Recuperando o relatório
		JasperPrint prints = getJasperPrint(concurso,listaColaboradores);
		
		// Preparando e exibindo o relatório
		JasperViewer jrv = new JasperViewer(prints,false);
		jrv.setTitle("Recibos - " + concurso.getEscola());
		jrv.setVisible(true);
		
	}
	
	/** Monta uma lista de recibos e imprime em um arquivo no diretório informado.
	 *  @param concurso Informações do concurso
	 *  @param listaColaboradores contém a lista de colaboradores
	 *  @param dirSaida é o diretório de saída do arquivo, o nome do PDF é montado automaticamente com base no nome da escola
	 *  @throws JRException quando há alguma falha na geração do relatório 
	 *  @throws IOException quando algum arquivo, tanto do sistema quanto a planilha, não são encontrados */
	public static void exportPDF(Concurso concurso, ArrayList<Colaborador> listaColaboradores, File dirSaida) throws IOException, JRException {
		
		// Erro caso o arquivo seja nulo
		if (dirSaida == null)
			throw new IOException("x Nome do diretório de saída de PDF's de recibos não definido");
		
		// Erro caso não seja possível escrever o arquivo PDF
		if (!dirSaida.canWrite())
			throw new IOException("x Não foi possível escrever no diretório '" + dirSaida.getName() + "'");
		
		// Montando o nome do arquivo de saída de acordo com o nome da escola
		String filename = String.format("%s/%s (Recibos).pdf",dirSaida.getAbsolutePath(),concurso.getEscola());
		File destino = new File(filename);
		System.out.println(":: Escrevendo arquivo " + filename);
		
		// Criando diretórios pais do arquivo
		destino.getParentFile().mkdirs();
		
		// Criando e exportando relatório em PDF
		JasperPrint prints = getJasperPrint(concurso,listaColaboradores);
		JasperExportManager.exportReportToPdfFile(prints,destino.getAbsolutePath());
		
	}
	
	/** Prepara o relatório.
	 *  @param concurso Informações do concurso
	 *  @param listaColaboradores contém a lista de colaboradores
	 *  @throws JRException quando há alguma falha na geração do relatório 
	 *  @throws IOException quando algum arquivo, tanto do sistema quanto a planilha, não são encontrados
	 *  @return objeto com o relatório preenchido */
	private static JasperPrint getJasperPrint(Concurso concurso, ArrayList<Colaborador> listaColaboradores) throws IOException, JRException  {
		
		// Leitura dos arquivos
		File     reportPath = ResourceManager.getResourceAsFile("reports/RPA.jasper");
		BufferedImage  logo = ImageIO.read(ResourceManager.getResourceAsFile("img/logo.jpg"));
		JasperReport report = (JasperReport) JRLoader.loadObject(reportPath);
		
		// Preparação dos parâmetros
		Map<String,Object> parameters = new HashMap<String,Object>();
		
		parameters.put("PAR_LOGO", logo);
		parameters.put("PAR_NOME_CONCURSO", concurso.getNome());
		parameters.put("PAR_NOME_INSTITUICAO", concurso.getEscola());
		parameters.put("PAR_MUNICIPIO", concurso.getMunicipio());
		parameters.put("PAR_DATA_CONCURSO", concurso.getDataConcurso());
		
		// Preparação dos dados
		JRBeanCollectionDataSource  listaBeans = new JRBeanCollectionDataSource(listaColaboradores,false);
		
		// Preenchendo o relatório
		JasperPrint  prints = JasperFillManager.fillReport(report, parameters, listaBeans);
		
		return prints;
	}
	
}

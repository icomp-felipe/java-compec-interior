package compec.ufam.docs.pdf;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import com.phill.libs.*;

import compec.ufam.docs.model.Colaborador;
import compec.ufam.docs.model.Concurso;

import java.awt.image.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.*;

public class RPA {

	public static void show(Concurso concurso, ArrayList<Colaborador> listaColaboradores) throws Exception {
		
		JasperPrint prints = getJasperPrint(concurso,listaColaboradores);
		
		/** Preparando e exibindo o relatório */
		JasperViewer jrv = new JasperViewer(prints,false);
		jrv.setTitle("Recibos - " + concurso.getEscola());
		jrv.setVisible(true);
		
	}
	
	public static void exportPDF(Concurso concurso, ArrayList<Colaborador> listaColaboradores, File dirSaida) throws IOException, JRException {
		
		// Erro caso o arquivo seja nulo
		if (dirSaida == null)
			throw new IOException("x Nome do diretório de saída de PDF's de recibos não definido");
		
		// Erro caso não seja possível escrever o arquivo PDF
		if (!dirSaida.canWrite())
			throw new IOException("x Não foi possível escrever no diretório '" + dirSaida.getName() + "'");
		
		String filename = String.format("%s/%s (Recibos).pdf",dirSaida.getAbsolutePath(),concurso.getEscola());
		File destino = new File(filename);
		System.out.println(":: Escrevendo arquivo " + filename);
		
		// Criando diretórios pais do arquivo
		destino.getParentFile().mkdirs();
		
		// Criando e exportando relatório em PDF
		JasperPrint prints = getJasperPrint(concurso,listaColaboradores);
		JasperExportManager.exportReportToPdfFile(prints,destino.getAbsolutePath());
		
	}
	
	private static JasperPrint getJasperPrint(Concurso concurso, ArrayList<Colaborador> listaColaboradores) throws IOException, JRException  {
		
		/** Leitura dos arquivos */
		File     reportPath = ResourceManager.getResourceAsFile("reports/RPA.jasper");
		BufferedImage  logo = ImageIO.read(ResourceManager.getResourceAsFile("img/logo.jpg"));
		JasperReport report = (JasperReport) JRLoader.loadObject(reportPath);
		
		/** Preparação dos parâmetros */
		Map<String,Object> parameters = new HashMap<String,Object>();
		
		parameters.put("PAR_LOGO", logo);
		parameters.put("PAR_NOME_CONCURSO", concurso.getNome());
		parameters.put("PAR_NOME_INSTITUICAO", concurso.getEscola());
		parameters.put("PAR_MUNICIPIO", concurso.getMunicipio());
		parameters.put("PAR_DATA_CONCURSO", concurso.getDataConcurso());
		
		/** Preparação dos dados */
		JRBeanCollectionDataSource  listaBeans = new JRBeanCollectionDataSource(listaColaboradores,false);
		
		/** Preenchendo o relatório */
		JasperPrint  prints = JasperFillManager.fillReport(report, parameters, listaBeans);
		
		return prints;
	}
	
}

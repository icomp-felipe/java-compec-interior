package compec.ufam.proc.pdf;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import com.phill.libs.*;

import compec.ufam.proc.model.Colaborador;
import compec.ufam.proc.model.Concurso;

import java.awt.image.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.*;

public class ListaPresenca {

	public static void show(Concurso concurso, ArrayList<Colaborador> listaColaboradores) throws Exception {
		
		JasperPrint prints = getJasperPrint(concurso,listaColaboradores);
		
		/** Preparando e exibindo o relatório */
		JasperViewer jrv = new JasperViewer(prints,false);
		jrv.setTitle("Lista de Presença - " + concurso.getEscola());
		jrv.setVisible(true);
		
	}
	
	public static void exportPDF(Concurso concurso, ArrayList<Colaborador> listaColaboradores) {

		try {
			
			/*String path = System.getProperty("user.home") + "/lista_presenca";		new File(path).mkdir();
			String destFileName = path + "/" + instituicao.getFileResume() + ".pdf";
			
			JasperPrint prints = getJasperPrint(concurso,instituicao);
			JasperExportManager.exportReportToPdfFile(prints, destFileName);*/
			
		}
		catch (Exception e) { e.printStackTrace(); }
		
	}
	
	private static JasperPrint getJasperPrint(Concurso concurso, ArrayList<Colaborador> listaColaboradores) throws Exception {
		
		/** Leitura dos arquivos */
		File     reportPath = ResourceManager.getResourceAsFile("reports/ListaPresenca.jasper");
		BufferedImage  logo = ImageIO.read(ResourceManager.getResourceAsFile("img/logo.jpg"));
		JasperReport report = (JasperReport) JRLoader.loadObject(reportPath);
		
		/** Preparação dos parâmetros */
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("PAR_LOGO", logo);
		parameters.put("PAR_NOME_CONCURSO", concurso.getNome());
		parameters.put("PAR_NOME_INSTITUICAO", concurso.getEscola());
		parameters.put("PAR_MUNICIPIO", concurso.getMunicipio());
		parameters.put("PAR_DATA_CONCURSO", concurso.getDataConcurso());
		parameters.put("PAR_OBS",readObservacoes());
		
		/** Preparação dos dados */
		JRBeanCollectionDataSource  listaBeans = new JRBeanCollectionDataSource(listaColaboradores,false);
		parameters.put("PAR_LISTA", listaBeans);
		
		/** Preenchendo o relatório */
		JasperPrint  prints = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		
		return prints;
	}
	
	private static String readObservacoes() {
		
		File obs = ResourceManager.getResourceAsFile("text/obs-lista-presenca.txt");
		
		return PhillFileUtils.readFileToString(obs);
	}
	
}

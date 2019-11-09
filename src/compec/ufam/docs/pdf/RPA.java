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
		
		/** Preparando e exibindo o relatório */
		JasperViewer jrv = new JasperViewer(prints,false);
		jrv.setTitle("Recibos - " + concurso.getEscola());
		jrv.setVisible(true);
		
	}
	
}

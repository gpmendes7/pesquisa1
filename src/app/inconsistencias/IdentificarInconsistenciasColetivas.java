package app.inconsistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.CSVNotificacao;
import modelo.Notificacao;
import modelo.NotificacaoParaCSV;

public class IdentificarInconsistenciasColetivas {
	
	private static FileWriter fileWriter1;
	private static FileWriter fileWriter2;
	private static FileWriter fileWriter3;
	private static FileWriter fileWriter4;
	private static FileWriter fileWriter5;
	
	private static final String ARQUIVO_TEXTO_INCONSISTENCIA1 = "./arquivos/txt/inconsistencias/coletivas/inconsistenciaEmDataNotificacao.txt";
	private static final String ARQUIVO_TEXTO_INCONSISTENCIA2 = "./arquivos/txt/inconsistencias/coletivas/inconsistenciaEmDataEncerramento.txt";
	private static final String ARQUIVO_TEXTO_INCONSISTENCIA3 = "./arquivos/txt/inconsistencias/coletivas/inconsistenciaEmDataInicioSintomas.txt";
	private static final String ARQUIVO_TEXTO_INCONSISTENCIA4 = "./arquivos/txt/inconsistencias/coletivas/inconsistenciaEmDataTeste.txt";
	private static final String ARQUIVO_TEXTO_INCONSISTENCIA5 = "./arquivos/txt/inconsistencias/coletivas/inconsistenciaEmDataInternacao.txt";
	
	private static final String ARQUIVO_CSV_INCONSISTENCIA1 = "./arquivos/csv/inconsistencias/coletivas/inconsistenciaEmDataNotificacao.csv";
	private static final String ARQUIVO_CSV_INCONSISTENCIA2 = "./arquivos/csv/inconsistencias/coletivas/inconsistenciaEmDataEncerramento.csv";
	private static final String ARQUIVO_CSV_INCONSISTENCIA3 = "./arquivos/csv/inconsistencias/coletivas/inconsistenciaEmDataInicioSintomas.csv";
	private static final String ARQUIVO_CSV_INCONSISTENCIA4 = "./arquivos/csv/inconsistencias/coletivas/inconsistenciaEmDataTeste.csv";
	private static final String ARQUIVO_CSV_INCONSISTENCIA5 = "./arquivos/csv/inconsistencias/coletivas/inconsistenciaEmDataInternacao.csv";
	
	private static List<NotificacaoParaCSV> notificacoesCSV1;
	private static List<NotificacaoParaCSV> notificacoesCSV2;
	private static List<NotificacaoParaCSV> notificacoesCSV3;
	private static List<NotificacaoParaCSV> notificacoesCSV4;
	private static List<NotificacaoParaCSV> notificacoesCSV5;
	
	

	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		fileWriter1 = new FileWriter(ARQUIVO_TEXTO_INCONSISTENCIA1);
		fileWriter2 = new FileWriter(ARQUIVO_TEXTO_INCONSISTENCIA2);
		fileWriter3 = new FileWriter(ARQUIVO_TEXTO_INCONSISTENCIA3);
		fileWriter4 = new FileWriter(ARQUIVO_TEXTO_INCONSISTENCIA4);
		fileWriter5 = new FileWriter(ARQUIVO_TEXTO_INCONSISTENCIA5);
		
		notificacoesCSV1 = new ArrayList<NotificacaoParaCSV>();
		notificacoesCSV2 = new ArrayList<NotificacaoParaCSV>();
		notificacoesCSV3 = new ArrayList<NotificacaoParaCSV>();
		notificacoesCSV4 = new ArrayList<NotificacaoParaCSV>();
		notificacoesCSV5 = new ArrayList<NotificacaoParaCSV>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
		
		String jpql = " select n1\n" +
				      " from Notificacao n1\n" +
			          " where n1.descartada = 0\n" +
			          " and exists (select n2\n" +
					  " from Notificacao n2\n" +
		              " where n2.paciente = n1.paciente\n" +
		              " and n2.descartada = 0\n" +
		              " and n2.numeroNotificacao != n1.numeroNotificacao)";
		
		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);
		
		List<Notificacao> notificacoesValidasComPacienteRepetido = query.getResultList();
		
		System.out.println("Total de notificações válidas com paciente repetido: " + notificacoesValidasComPacienteRepetido.size());
		
		long totalNotificacoesComInconsistencia = 0;
		while(!notificacoesValidasComPacienteRepetido.isEmpty()) { 
			  Notificacao notificacao = notificacoesValidasComPacienteRepetido.remove(0);
		  
			  List<Notificacao> notificacoesDuplicadas = notificacoesValidasComPacienteRepetido.stream() 
																		                       .filter(n -> n.getPaciente().equals(notificacao.getPaciente()))
																		                       .collect(Collectors.toList());
			  			
			  boolean temAlgumaInconsistencia = false;
			  for (Notificacao notificacaoDuplicada : notificacoesDuplicadas) {
				    if(temInconsistencia(notificacao, notificacaoDuplicada)) {
				    	totalNotificacoesComInconsistencia++;
				    	temAlgumaInconsistencia = true;
				    }    	
			  }
			  
			  if(temAlgumaInconsistencia) {
				  totalNotificacoesComInconsistencia++;
			  }
			  						  
			  notificacoesValidasComPacienteRepetido.removeAll(notificacoesDuplicadas); 
		}
		
		System.out.println("Total de notificações marcadas como válidas mas com alguma inconsistência: " + totalNotificacoesComInconsistencia);
		
		gerarArquivoCSV(ARQUIVO_CSV_INCONSISTENCIA1, notificacoesCSV1);
		gerarArquivoCSV(ARQUIVO_CSV_INCONSISTENCIA2, notificacoesCSV2);
		gerarArquivoCSV(ARQUIVO_CSV_INCONSISTENCIA3, notificacoesCSV3);
		gerarArquivoCSV(ARQUIVO_CSV_INCONSISTENCIA4, notificacoesCSV4);
		gerarArquivoCSV(ARQUIVO_CSV_INCONSISTENCIA5, notificacoesCSV5);
		
		em.close();
		emf.close();
		
		fileWriter1.close();
		fileWriter2.close();
		fileWriter3.close();
		fileWriter4.close();
		fileWriter5.close();
	}

	private static void gerarArquivoCSV(String nomeArquivo, List<NotificacaoParaCSV> notificacoesCSV)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		notificacoesCSV.add(0,
				new NotificacaoParaCSV("numeroNotificacao", "nomeCompleto", "cpf", "dataNotificacao",
						"dataInicioSintomas", "dataNascimento", "cep", "logradouro", "numero", "complemento", "bairro",
						"municipio", "estado", "estrangeiro", "passaporte", "paisOrigem", "profissionalSeguranca",
						"profissionalSaude", "cbo", "cns", "nomeMae", "sexo", "racaCor", "telefoneCelular",
						"telefoneContato", "febre", "tosse", "dorGarganta", "dispneia", "outrosSintomas",
						"descricaoOutros", "doencasRespiratorias", "doencasRenais", "fragilidadeImunologica",
						"diabetes", "imunosupressao", "doencasCardiacas", "gestanteAltoRisco", "origem", "latitude",
						"longitude", "cnes", "idade", "estadoTeste", "dataTeste", "tipoTeste", "resultadoTeste",
						"dataInternacao", "dataEncerramento", "evolucaoCaso", "classificacaoFinal", "descartada"));

		CSVNotificacao.criarCSV(nomeArquivo, notificacoesCSV);
	}

	private static boolean temInconsistencia(Notificacao notificacao, Notificacao notificacaoDuplicada) throws IOException {
		String evolucaoCaso1 = notificacao.getEvolucaoCaso();
	    String evolucaoCaso2 = notificacaoDuplicada.getEvolucaoCaso();
	    
		if(!evolucaoCaso1.equals(evolucaoCaso2)) {
			  if((evolucaoCaso1.equals("RECUPERADO") || evolucaoCaso1.equals("OBITO"))
			  && (evolucaoCaso2.equals("UTI") || evolucaoCaso2.equals("INTERNADO"))) {
				  return inconsistenciaEmDataNotificacao(notificacao, notificacaoDuplicada) ||			  
						 inconsistenciaEmDataEncerramento(notificacao, notificacaoDuplicada) ||
						 inconsistenciaEmDataInicioSintomas(notificacao, notificacaoDuplicada) ||
						 inconsistenciaEmDataTeste(notificacao, notificacaoDuplicada) || 
				         inconsistenciaEmDataInternacao(notificacao, notificacaoDuplicada);
			  }
		  }
		
		return false;
	}
	
	private static boolean inconsistenciaEmDataNotificacao(Notificacao notificacao, Notificacao notificacaoDuplicada) throws IOException {
		  Date dataNotificacao1 = notificacao.getDataNotificacao();
		  Date dataNotificacao2 = notificacaoDuplicada.getDataNotificacao();
		  			  
		  if(dataNotificacao1 != null && dataNotificacao2 != null
		     && ((dataNotificacao1).before(dataNotificacao2))) {
			  fileWriter1.write("***************************\n");
			  fileWriter1.write("Notificações com evolução caso diferentes e com data de notificação incosistentes entre si\n");
			  fileWriter1.write(notificacao + "\n");
			  fileWriter1.write(notificacaoDuplicada + "\n");
			  fileWriter1.write("***************************\n");
			  
			  notificacoesCSV1.add(gerarNotificacaoParaCSV(notificacao));
			  notificacoesCSV1.add(gerarNotificacaoParaCSV(notificacaoDuplicada));
			  
			  return true;
		  }
		  
		  return false;
	}

	private static boolean inconsistenciaEmDataEncerramento(Notificacao notificacao, Notificacao notificacaoDuplicada) throws IOException {
		  Date dataEncerramento1 = notificacao.getDataEncerramento();
		  Date dataEncerramento2 = notificacaoDuplicada.getDataEncerramento();
		  
		  if(dataEncerramento1 != null && dataEncerramento2 != null
		     && ((dataEncerramento1).before(dataEncerramento2))) {
				  fileWriter2.write("***************************\n");
				  fileWriter2.write("Notificações com evolução caso diferentes e com data de encerramento incosistentes entre si\n");
				  fileWriter2.write(notificacao + "\n");
				  fileWriter2.write(notificacaoDuplicada + "\n");
				  fileWriter2.write("***************************\n");
				  
				  notificacoesCSV2.add(gerarNotificacaoParaCSV(notificacao));
				  notificacoesCSV2.add(gerarNotificacaoParaCSV(notificacaoDuplicada));
				  
				  return true;
		  }
		  
		  return false;
	}
	
	private static boolean inconsistenciaEmDataInicioSintomas(Notificacao notificacao, Notificacao notificacaoDuplicada) throws IOException {
		  Date dataInicioSintomas1 = notificacao.getDataInicioSintomas();
		  Date dataInicioSintomas2 = notificacaoDuplicada.getDataInicioSintomas();
		  			  
		  if(dataInicioSintomas1 != null && dataInicioSintomas2 != null
		     && ((dataInicioSintomas1).before(dataInicioSintomas2))) {
			  fileWriter3.write("***************************\n");
			  fileWriter3.write("Notificações com evolução caso diferentes e com data de início sintomas incosistentes entre si\n");
			  fileWriter3.write(notificacao + "\n");
			  fileWriter3.write(notificacaoDuplicada + "\n");
			  fileWriter3.write("***************************\n");
			  
			  notificacoesCSV3.add(gerarNotificacaoParaCSV(notificacao));
			  notificacoesCSV3.add(gerarNotificacaoParaCSV(notificacaoDuplicada));
			  
			  return true;
		  }
		  
		  return false;
	}
	
	private static boolean inconsistenciaEmDataTeste(Notificacao notificacao, Notificacao notificacaoDuplicada) throws IOException {
		  Date dataTeste1 = notificacao.getDataTeste();
		  Date dataTeste2 = notificacaoDuplicada.getDataTeste();
		  			  
		  if(dataTeste1 != null && dataTeste2 != null
		     && ((dataTeste1).before(dataTeste2))) {
			  fileWriter4.write("***************************\n");
			  fileWriter4.write("Notificações com evolução caso diferentes e com data de teste incosistentes entre si\n");
			  fileWriter4.write(notificacao + "\n");
			  fileWriter4.write(notificacaoDuplicada + "\n");
			  fileWriter4.write("***************************\n");
			  
			  notificacoesCSV4.add(gerarNotificacaoParaCSV(notificacao));
			  notificacoesCSV4.add(gerarNotificacaoParaCSV(notificacaoDuplicada));
			  
			  return true;
		  }
		  
		  return false;
	}
	
	private static boolean inconsistenciaEmDataInternacao(Notificacao notificacao, Notificacao notificacaoDuplicada) throws IOException {
		  Date dataInternacao1 = notificacao.getDataInternacao();
		  Date dataInternacao2 = notificacaoDuplicada.getDataInternacao();
		  			  
		  if(dataInternacao1 != null && dataInternacao2 != null
		     && ((dataInternacao1).before(dataInternacao2))) {
			  fileWriter5.write("***************************\n");
			  fileWriter5.write("Notificações com evolução caso diferentes e com data de internação incosistentes entre si\n");
			  fileWriter5.write(notificacao + "\n");
			  fileWriter5.write(notificacaoDuplicada + "\n");
			  fileWriter5.write("***************************\n");
			  
			  notificacoesCSV5.add(gerarNotificacaoParaCSV(notificacao));
			  notificacoesCSV5.add(gerarNotificacaoParaCSV(notificacaoDuplicada));
			  
			  return true;
		  }
		  
		  return false;
	}
	
	public static NotificacaoParaCSV gerarNotificacaoParaCSV(Notificacao notificacao) {
		String dataNotificacao = notificacao.getDataNotificacao() != null ? notificacao.getDataNotificacao().toString()
				: null;
		String dataInicioSintomas = notificacao.getDataInicioSintomas() != null
				? notificacao.getDataInicioSintomas().toString()
				: null;
		String dataNascimento = notificacao.getDataNascimento() != null ? notificacao.getDataNascimento().toString()
				: null;
		String dataTeste = notificacao.getDataTeste() != null ? notificacao.getDataTeste().toString() : null;
		String dataInternacao = notificacao.getDataInternacao() != null ? notificacao.getDataInternacao().toString()
				: null;
		String dataEncerramento = notificacao.getDataEncerramento() != null
				? notificacao.getDataEncerramento().toString()
				: null;

		return new NotificacaoParaCSV(notificacao.getNumeroNotificacao(), notificacao.getNomeCompleto(),
				notificacao.getCpf(), dataNotificacao, dataInicioSintomas, dataNascimento, notificacao.getCep(),
				notificacao.getLogradouro(), notificacao.getNumero(), notificacao.getComplemento(),
				notificacao.getBairro(), notificacao.getMunicipio(), notificacao.getEstado(),
				notificacao.getEstrangeiro(), notificacao.getPassaporte(), notificacao.getPaisOrigem(),
				notificacao.getProfissionalSeguranca(), notificacao.getProfissionalSaude(), notificacao.getCbo(),
				notificacao.getCns(), notificacao.getNomeMae(), notificacao.getSexo(), notificacao.getRacaCor(),
				notificacao.getTelefoneCelular(), notificacao.getTelefoneContato(), notificacao.getFebre(),
				notificacao.getTosse(), notificacao.getDorGarganta(), notificacao.getDispneia(),
				notificacao.getOutrosSintomas(), notificacao.getDescricaoOutros(),
				notificacao.getDoencasRespiratorias(), notificacao.getDoencasRenais(),
				notificacao.getFragilidadeImunologica(), notificacao.getDiabetes(), notificacao.getImunosupressao(),
				notificacao.getDoencasCardiacas(), notificacao.getGestanteAltoRisco(), notificacao.getOrigem(),
				notificacao.getLatitude(), notificacao.getLongitude(), notificacao.getCnes(), notificacao.getIdade(),
				notificacao.getEstadoTeste(), dataTeste, notificacao.getTipoTeste(), notificacao.getResultadoTeste(),
				dataInternacao, dataEncerramento, notificacao.getEvolucaoCaso(), notificacao.getClassificacaoFinal(),
				notificacao.ehDescartada() ? "Sim" : "Não");
	}

}

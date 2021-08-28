package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;

public class VerificarNotificacoesInconsistentesPorEvolucaoCasoDiferente {
	
	private static FileWriter fileWriter1;
	private static FileWriter fileWriter2;
	private static FileWriter fileWriter3;
	private static FileWriter fileWriter4;
	private static FileWriter fileWriter5;
	
	public static void main(String[] args) throws IOException {
		fileWriter1 = new FileWriter("./inconsistencias/coletivas/inconsistenciaEmDataNotificacao.txt");
		fileWriter2 = new FileWriter("./inconsistencias/coletivas/inconsistenciaEmDataEncerramento.txt");
		fileWriter3 = new FileWriter("./inconsistencias/coletivas/inconsistenciaEmDataInicioSintomas.txt");
		fileWriter4 = new FileWriter("./inconsistencias/coletivas/inconsistenciaEmDataTeste.txt");
		fileWriter5 = new FileWriter("./inconsistencias/coletivas/inconsistenciaEmDataInternacao.txt");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
		
		String jpql= "select n1" + 
				     " from Notificacao n1" +
				     " where n1.dataNascimento is not null"  + 
				     " and exists (" +
				     " select n2 from Notificacao n2" +
				     " where n2.paciente = n1.paciente" +
				     " and n2.numeroNotificacao != n1.numeroNotificacao )";
		
		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);
		
		List<Notificacao> notificacoesDuplicadas = query.getResultList();
		
		while(!notificacoesDuplicadas.isEmpty()) { 
			  Notificacao notificacaoDuplicada = notificacoesDuplicadas.remove(0);
		  
			  List<Notificacao> notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento =
			  notificacoesDuplicadas.stream() 
			                        .filter(n -> n.getNomeCompleto().equals(notificacaoDuplicada.getNomeCompleto()) &&
			                        		n.getDataNascimento().equals(notificacaoDuplicada.getDataNascimento()))
			                        .collect(Collectors.toList());
			  						  
			  for (Notificacao notificacao : notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento) {
				    verificarInconsistenciasPorEvolucaoCasoDiferente(notificacaoDuplicada, notificacao);
			  }
			  						  
			  notificacoesDuplicadas.removeAll(notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento); 
		}
		 
		em.close();
		emf.close();
		
		fileWriter1.close();
		fileWriter2.close();
		fileWriter3.close();
		fileWriter4.close();
		fileWriter5.close();
	}

	private static void verificarInconsistenciasPorEvolucaoCasoDiferente(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		String evolucaoCaso1 = notificacaoDuplicada.getEvolucaoCaso();
	    String evolucaoCaso2 = notificacao.getEvolucaoCaso();
	    
		if(!evolucaoCaso1.equals(evolucaoCaso2)) {
			  if((evolucaoCaso1.equals("RECUPERADO") || evolucaoCaso1.equals("OBITO"))
			  && (evolucaoCaso2.equals("UTI") || evolucaoCaso2.equals("INTERNADO"))) {
				  inconsistenciaEmDataNotificacao(notificacaoDuplicada, notificacao);				  
				  inconsistenciaEmDataEncerramento(notificacaoDuplicada, notificacao);
				  inconsistenciaEmDataInicioSintomas(notificacaoDuplicada, notificacao);
				  inconsistenciaEmDataTeste(notificacaoDuplicada, notificacao);
				  inconsistenciaEmDataInternacao(notificacaoDuplicada, notificacao);
			  }
		  }
	}
	
	private static void inconsistenciaEmDataNotificacao(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		  Date dataNotificacao1 = notificacaoDuplicada.getDataNotificacao();
		  Date dataNotificacao2 = notificacao.getDataNotificacao();
		  			  
		  if(dataNotificacao1 != null && dataNotificacao2 != null
		     && ((dataNotificacao1).before(dataNotificacao2))) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso diferentes e com data de notificação incosistentes entre si");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter1.write("***************************\n");
			  fileWriter1.write("Notificações com evolução caso diferentes e com data de notificação incosistentes entre si\n");
			  fileWriter1.write(notificacaoDuplicada + "\n");
			  fileWriter1.write(notificacao + "\n");
			  fileWriter1.write("***************************\n");
		  }
	}

	private static void inconsistenciaEmDataEncerramento(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		  Date dataEncerramento1 = notificacaoDuplicada.getDataEncerramento();
		  Date dataEncerramento2 = notificacao.getDataEncerramento();
		  
		  if(dataEncerramento1 != null && dataEncerramento2 != null
		     && ((dataEncerramento1).before(dataEncerramento2))) {
				  /*
				  	  System.out.println("***************************");
					  System.out.println("Notificações com evolução caso diferentes e com data de encerramento incosistentes entre si");
					  System.out.println(notificacaoDuplicada);
					  System.out.println(notificacao);
					  System.out.println("***************************");
				  */
				  
				  fileWriter2.write("***************************\n");
				  fileWriter2.write("Notificações com evolução caso diferentes e com data de encerramento incosistentes entre si\n");
				  fileWriter2.write(notificacaoDuplicada + "\n");
				  fileWriter2.write(notificacao + "\n");
				  fileWriter2.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEmDataInicioSintomas(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		  Date dataInicioSintomas1 = notificacaoDuplicada.getDataInicioSintomas();
		  Date dataInicioSintomas2 = notificacao.getDataInicioSintomas();
		  			  
		  if(dataInicioSintomas1 != null && dataInicioSintomas2 != null
		     && ((dataInicioSintomas1).before(dataInicioSintomas2))) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso diferentes e com data de início sintomas incosistentes entre si");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter3.write("***************************\n");
			  fileWriter3.write("Notificações com evolução caso diferentes e com data de início sintomas incosistentes entre si\n");
			  fileWriter3.write(notificacaoDuplicada + "\n");
			  fileWriter3.write(notificacao + "\n");
			  fileWriter3.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEmDataTeste(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		  Date dataTeste1 = notificacaoDuplicada.getDataTeste();
		  Date dataTeste2 = notificacao.getDataTeste();
		  			  
		  if(dataTeste1 != null && dataTeste2 != null
		     && ((dataTeste1).before(dataTeste2))) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso diferentes e com data de teste incosistentes entre si");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter4.write("***************************\n");
			  fileWriter4.write("Notificações com evolução caso diferentes e com data de teste incosistentes entre si\n");
			  fileWriter4.write(notificacaoDuplicada + "\n");
			  fileWriter4.write(notificacao + "\n");
			  fileWriter4.write("***************************\n");
		  }
	}
	
	private static void inconsistenciaEmDataInternacao(Notificacao notificacaoDuplicada, Notificacao notificacao) throws IOException {
		  Date dataInternacao1 = notificacaoDuplicada.getDataInternacao();
		  Date dataInternacao2 = notificacao.getDataInternacao();
		  			  
		  if(dataInternacao1 != null && dataInternacao2 != null
		     && ((dataInternacao1).before(dataInternacao2))) {
			  /*
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso diferentes e com data de internação incosistentes entre si");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
			  */
			  
			  fileWriter5.write("***************************\n");
			  fileWriter5.write("Notificações com evolução caso diferentes e com data de internação incosistentes entre si\n");
			  fileWriter5.write(notificacaoDuplicada + "\n");
			  fileWriter5.write(notificacao + "\n");
			  fileWriter5.write("***************************\n");
		  }
	}

}

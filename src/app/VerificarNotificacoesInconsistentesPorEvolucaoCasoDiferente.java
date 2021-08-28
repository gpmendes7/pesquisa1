package app;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;

public class VerificarNotificacoesInconsistentesPorEvolucaoCasoDiferente {
	
	public static void main(String[] args) {
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
	}

	private static void verificarInconsistenciasPorEvolucaoCasoDiferente(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		String evolucaoCaso1 = notificacaoDuplicada.getEvolucaoCaso();
	    String evolucaoCaso2 = notificacao.getEvolucaoCaso();
	    
		if(!evolucaoCaso1.equals(evolucaoCaso2)) {
			  if((evolucaoCaso1.equals("RECUPERADO") || evolucaoCaso1.equals("OBITO"))
			  && (evolucaoCaso2.equals("UTI") || evolucaoCaso2.equals("INTERNADO"))) {
				  inconsistenciaEmDataNotificacao(notificacaoDuplicada, notificacao);				  
				  inconsistenciaEmDataEncerramento(notificacaoDuplicada, notificacao);
				  inconsistenciaEmDataInicioSintomas(notificacaoDuplicada, notificacao);
			  }
		  }
	}

	private static void inconsistenciaEmDataEncerramento(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  Date dataEncerramento1 = notificacaoDuplicada.getDataEncerramento();
		  Date dataEncerramento2 = notificacao.getDataEncerramento();
		  
		  if(dataEncerramento1 != null && dataEncerramento2 != null
		     && ((dataEncerramento1).before(dataEncerramento2))) {
				  System.out.println("***************************");
				  System.out.println("Notificações com evolução caso diferentes e com data de encerramento incosistentes entre si");
				  System.out.println(notificacaoDuplicada);
				  System.out.println(notificacao);
				  System.out.println("***************************");
		  }
	}

	private static void inconsistenciaEmDataNotificacao(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  Date dataNotificacao1 = notificacaoDuplicada.getDataNotificacao();
		  Date dataNotificacao2 = notificacao.getDataNascimento();
		  			  
		  if(dataNotificacao1 != null && dataNotificacao2 != null
		     && ((dataNotificacao1).before(dataNotificacao2))) {
			  System.out.println("***************************");
			  System.out.println("Notificações com evolução caso diferentes e com data de notificação incosistentes entre si");
			  System.out.println(notificacaoDuplicada);
			  System.out.println(notificacao);
			  System.out.println("***************************");
		  }
	}
	
	private static void inconsistenciaEmDataInicioSintomas(Notificacao notificacaoDuplicada, Notificacao notificacao) {
		  Date dataInicioSintomas1 = notificacaoDuplicada.getDataInicioSintomas();
		  Date dataInicioSintomas2 = notificacao.getDataInicioSintomas();
		  			  
		  if(dataInicioSintomas1 != null && dataInicioSintomas2 != null
		     && ((dataInicioSintomas1).before(dataInicioSintomas2))) {
			  System.out.println("***************************");
			  System.out.println("Notificações com evolução caso diferentes e com data de início sintomas incosistentes entre si");
			  System.out.println(notificacaoDuplicada);
			  System.out.println(notificacao);
			  System.out.println("***************************");
		  }
	}

}

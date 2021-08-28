package app;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;

public class VerificarNotificacoesInconsistentes {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
		
		String jpql= "select n1" + 
				     " from Notificacao n1" +
				     " where exists (" +
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
			  
			  notificacoesDuplicadas.removeAll(notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento); 
		}
		 
		em.close();
		emf.close();
	}

}

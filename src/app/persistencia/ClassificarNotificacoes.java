package app.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;

public class ClassificarNotificacoes {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pesquisa1");
		EntityManager em = emf.createEntityManager();
		
		String jpql= "select n from Notificacao n";
		
		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);
		
		List<Notificacao> notificacoes = query.getResultList();
		
		em.getTransaction().begin();
		
		int totalDeNotificacoesComCopias = 0;
		int totalCopias = 0;
		while(!notificacoes.isEmpty()) { 
			  Notificacao notificacao = notificacoes.remove(0);
		  
			  List<Notificacao> notificacoesCopia = notificacoes.stream() 
										                        .filter(n -> n.ehCopiaDe(notificacao))
										                        .collect(Collectors.toList());
			  
			  if(notificacoesCopia.size() > 0) {
				  totalDeNotificacoesComCopias++;
				  
				  for (Notificacao notificacaoCopia : notificacoesCopia) {
					  notificacaoCopia.setDescartada(true);
					  em.merge(notificacaoCopia);
				  }
				  
				  totalCopias += notificacoesCopia.size();
				  notificacoes.removeAll(notificacoesCopia); 
			  }		  						  		  
		}
		
		System.out.println("Total de notificações com cópias: " + totalDeNotificacoesComCopias);
		System.out.println("Total de cópias identificadas: " + totalCopias);
		
		em.getTransaction().commit();
		 
		em.close();
		emf.close();
	}


}

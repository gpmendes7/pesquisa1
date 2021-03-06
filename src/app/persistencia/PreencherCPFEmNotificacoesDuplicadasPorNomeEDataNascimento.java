package app.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Notificacao;

public class PreencherCPFEmNotificacoesDuplicadasPorNomeEDataNascimento {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pesquisa1");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select n1\n"
		            + " from Notificacao n1\n"
		            + " where n1.nomeCompleto != ''\n" 
		            + " and n1.dataNascimento is not null\n" 
		            + " and exists (\n"
		            + " select n2\n"
					+ " from Notificacao n2\n"
					+ " where n2.nomeCompleto = n1.nomeCompleto\n"
					+ " and n2.dataNascimento = n1.dataNascimento\n"
					+ " and n2.numeroNotificacao != n1.numeroNotificacao )";
		List<Notificacao> notificacoesDuplicadas = em.createQuery(jpql, Notificacao.class).getResultList();
		
		em.getTransaction().begin();
		
		while(!notificacoesDuplicadas.isEmpty()) {
			Notificacao notificacaoDuplicada = notificacoesDuplicadas.get(0);
			
			List<Notificacao> notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento 
			    = notificacoesDuplicadas.stream() 
			                            .filter(n -> n.getNomeCompleto().equals(notificacaoDuplicada.getNomeCompleto()) 
			                                 && n.getDataNascimento().equals(notificacaoDuplicada.getDataNascimento())) 
			                            .collect(Collectors.toList());
			
			String cpfSelecionado = null;
			for (Notificacao notificacao : notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento) {
				if(notificacao.temCPF()) {
					cpfSelecionado = notificacao.getCpf();
				}
			}
			
			for (Notificacao notificacao : notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento) {
				if(!notificacao.temCPF()) {
					notificacao.setCpf(cpfSelecionado);
					em.merge(notificacao);
				}
			}

			notificacoesDuplicadas.removeAll(notificacoesDuplicadasComMesmoNomeCompletoEDataNascimento);
		}

	    em.getTransaction().commit();

		em.close();
		emf.close();
	}

}

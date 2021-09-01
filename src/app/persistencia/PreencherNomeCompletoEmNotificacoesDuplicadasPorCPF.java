package app.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Notificacao;

public class PreencherNomeCompletoEmNotificacoesDuplicadasPorCPF {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select n1"
		            + " from Notificacao n1"
		            + " where n1.cpf != ''" 
		            + " and n1.dataNascimento is not null" 
		            + " and exists ("
		            + " select n2"
					+ " from Notificacao n2"
					+ " where n2.cpf = n1.cpf"
					+ " and n2.dataNascimento = n1.dataNascimento"
					+ " and n2.numeroNotificacao != n1.numeroNotificacao )";
		List<Notificacao> notificacoesDuplicadas = em.createQuery(jpql, Notificacao.class).getResultList();
		
		em.getTransaction().begin();
		
		while(!notificacoesDuplicadas.isEmpty()) {
			Notificacao notificacaoDuplicada = notificacoesDuplicadas.get(0);
			
			List<Notificacao> notificacoesDuplicadasComMesmoCPFEDataNascimento 
				= notificacoesDuplicadas.stream() 
			                            .filter(n -> n.getCpf().equals(notificacaoDuplicada.getCpf())
			                            	 && n.getDataNascimento().equals(notificacaoDuplicada.getDataNascimento()))
			                            .collect(Collectors.toList());
			
			String nomeSelecionado = null;
			for (Notificacao notificacao : notificacoesDuplicadasComMesmoCPFEDataNascimento) {
				if(notificacao.temNome() && !notificacao.temNomeInformadoComNumeros()) {
					nomeSelecionado = notificacao.getNomeCompleto();
				}
			}
			
			for (Notificacao notificacao : notificacoesDuplicadasComMesmoCPFEDataNascimento) {
				if(!notificacao.temNome() || notificacao.temNomeInformadoComNumeros()) {
					notificacao.setNomeCompleto(nomeSelecionado);
					em.merge(notificacao);
				}
			}

			notificacoesDuplicadas.removeAll(notificacoesDuplicadasComMesmoCPFEDataNascimento);
		}

	    em.getTransaction().commit();

		em.close();
		emf.close();
	}

}

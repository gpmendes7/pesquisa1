package app.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;
import modelo.Paciente;

public class AtualizarEvolucaoCasoEResultadoTestePacientes {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();
			
		String jpql = " select p\n" +
					  " from Paciente p\n" +
					  " where exists ( select n.paciente, count(n.numeroNotificacao)\n" + 
					  "			       from Notificacao n\n" +
					  "				   where n.paciente is not null\n" +
					  "	               and n.paciente = p.id\n" +
					  "	               group by n.paciente\n" + 
					  "				   having count(n.numeroNotificacao) > 1)";

		TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);

	    List<Paciente> pacientesComMaisDeUmaNotificacao = query.getResultList();
	    
	    System.out.println("Total de pacientes com mais de uma notificação: " + pacientesComMaisDeUmaNotificacao.size());
	    
	    
	    em.getTransaction().begin();
	    	
		for (Paciente paciente : pacientesComMaisDeUmaNotificacao) {
			List<Notificacao> notificacoes = paciente.getNotificacoes();
			paciente.setEvolucaoCaso(selecionarEvolucaoCasoComMaiorGrau(notificacoes));
			paciente.setResultadoTeste(selecionarResultadoTesteComMaiorGrau(notificacoes));
			em.merge(paciente);
		}
		
		em.getTransaction().commit();

		em.close();
		emf.close();
	}
	
	public static String selecionarEvolucaoCasoComMaiorGrau(List<Notificacao> notificacoes) {
		Notificacao notificacaoSelecionada = notificacoes.get(0);
		
		for (Notificacao notificacao : notificacoes) {
			int grauNotificacaoSelecionada = grauEvolucaoCaso(notificacaoSelecionada.getEvolucaoCaso());
			int grauNotificacao = grauEvolucaoCaso(notificacao.getEvolucaoCaso());
			if(grauNotificacaoSelecionada < grauNotificacao) {
				notificacaoSelecionada = notificacao;
			}
		}
		
		return notificacaoSelecionada.getEvolucaoCaso();
	}
	
	public static int grauEvolucaoCaso(String evolucaoCaso) {
		if(evolucaoCaso != null) {
			switch (evolucaoCaso.toUpperCase()) {
					case "OBITO": 
						return 4;
					case "UTI": 
						return 3;
					case "INTERNADO": 
						return 2;
					case "RECUPERADO": 
						return 1;
					default:
						return 0;
		   }
		}
		
		return 0;
	}
	
	public static String selecionarResultadoTesteComMaiorGrau(List<Notificacao> notificacoes) {
		Notificacao notificacaoSelecionada = notificacoes.get(0);
		
		for (Notificacao notificacao : notificacoes) {
			int grauNotificacaoSelecionada = grauResultadoTeste(notificacaoSelecionada.getResultadoTeste());
			int grauNotificacao = grauResultadoTeste(notificacao.getResultadoTeste());
			if(grauNotificacaoSelecionada < grauNotificacao) {
				notificacaoSelecionada = notificacao;
			}
		}
		
		return notificacaoSelecionada.getResultadoTeste();
	}
	
	public static int grauResultadoTeste(String resultadoTeste) {
		if(resultadoTeste != null) {
			switch (resultadoTeste.toUpperCase()) {
					case "POSITIVO": 
						return 3;
					case "NEGATIVO": 
						return 2;
					case "INCONCLUSIVO": 
						return 1;
					default:
						return 0;
		   }
		}
		
		return 0;
	}

}

package app;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Notificacao;

public class AjustarNotificacoesDuplicadasPorCPF {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();

		String jpql = "select n from Notificacao n";
		List<Notificacao> notificacoes = em.createQuery(jpql, Notificacao.class).getResultList();

		jpql = "select cpf" + " from Notificacao n" + " where n.cpf != ''" + " group by cpf" + " having count(cpf) > 1";
		List<String> cpfsDuplicados = em.createQuery(jpql, String.class).getResultList();

		em.getTransaction().begin();

		for (String cpfDuplicado : cpfsDuplicados) {
			List<Notificacao> notificacoesDuplicadasComCPF = notificacoes.stream()
					.filter(n -> n.getCpf().equals(cpfDuplicado)).collect(Collectors.toList());

			String nomeSelecionado = null;
			for (Notificacao notificacaoDuplicadaComCPF : notificacoesDuplicadasComCPF) {
				if (notificacaoDuplicadaComCPF.temNome() && !notificacaoDuplicadaComCPF.temNomeInformadoComNumeros()) {
					nomeSelecionado = notificacaoDuplicadaComCPF.getNomeCompleto();
				}
			}
			
			for (Notificacao notificacaoDuplicadaComCPF : notificacoesDuplicadasComCPF) {
				notificacaoDuplicadaComCPF.setNomeCompleto(nomeSelecionado);
				em.merge(notificacaoDuplicadaComCPF);
			}
		}

		em.getTransaction().commit();

		em.close();
		emf.close();
	}

}

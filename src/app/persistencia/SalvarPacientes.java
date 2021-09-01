package app.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Notificacao;
import modelo.Paciente;

public class SalvarPacientes {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();

		String jpql = "select n from Notificacao n";
		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);

		List<Notificacao> notificacoes = query.getResultList();
		List<Paciente> pacientes = new ArrayList<>();

		Map<String, List<Notificacao>> mapaNotificacoesChaves = new HashMap<String, List<Notificacao>>();

		for (Notificacao notificacao : notificacoes) {
			String chave =  notificacao.getCpf() + "," + notificacao.getNomeCompleto() + "," + notificacao.getDataNascimento();
			
			List<Notificacao> notificacoesChave = mapaNotificacoesChaves.get(chave);
			if (notificacoesChave == null) {
				notificacoesChave = new ArrayList<>();
				notificacoesChave.add(notificacao);
				mapaNotificacoesChaves.put(chave, notificacoesChave);
			} else {
				notificacoesChave.add(notificacao);
			}
		}
		
		em.getTransaction().begin();
		
		long totalNotificacoesDescartadas = 0;
		Set<String> chaves = mapaNotificacoesChaves.keySet();
		for (String chave : chaves) {
			List<Notificacao> notificacoesChave = mapaNotificacoesChaves.get(chave);
			Notificacao notificacaoPaciente = notificacoesChave.remove(0); 
			
			Paciente paciente = gerarPacienteDeNotificacao(notificacaoPaciente);
			em.persist(paciente);
			
			notificacaoPaciente.setPaciente(paciente);
			paciente.adicionarNotificacao(notificacaoPaciente);
			em.merge(notificacaoPaciente);
			
			for (Notificacao notificacao : notificacoesChave) {
				notificacao.setPaciente(paciente);
				paciente.adicionarNotificacao(notificacao);
				em.merge(notificacao);
			}	
			
			pacientes.add(paciente);
			totalNotificacoesDescartadas += notificacoesChave.size();
		}
		
		em.getTransaction().commit();
		
		System.out.println("Total de notificações: " + notificacoes.size());
		System.out.println("Total de notificações descartadas: " + totalNotificacoesDescartadas);
		System.out.println("Total de pacientes: " + pacientes.size());
		
		em.close();
		emf.close();
	}

	public static Paciente gerarPacienteDeNotificacao(Notificacao notificacao) {
		return new Paciente(notificacao.getNomeCompleto(), notificacao.getCpf(), notificacao.getDataNotificacao(),
				notificacao.getDataInicioSintomas(), notificacao.getDataNascimento(), notificacao.getCep(),
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
				notificacao.getEstadoTeste(), notificacao.getDataTeste(), notificacao.getTipoTeste(),
				notificacao.getResultadoTeste(), notificacao.getDataInternacao(), notificacao.getDataEncerramento(),
				notificacao.getEvolucaoCaso(), notificacao.getClassificacaoFinal());
	}
}

package app.copias;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

public class IdentificarNotificacoesCopias {

	private static FileWriter fileWriter;

	private static final String ARQUIVO_TEXTO_COPIAS = "./arquivos/txt/copias/copias.txt";
	private static final String ARQUIVO_CSV_COPIAS = "./arquivos/csv/copias/copias.csv";

	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		fileWriter = new FileWriter(ARQUIVO_TEXTO_COPIAS);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sivep");
		EntityManager em = emf.createEntityManager();

		String jpql = "select n from Notificacao n";

		TypedQuery<Notificacao> query = em.createQuery(jpql, Notificacao.class);

		List<Notificacao> notificacoes = query.getResultList();

		List<NotificacaoParaCSV> notificacoesCSV = new ArrayList<NotificacaoParaCSV>();

		int totalDeNotificacoesComCopias = 0;
		int totalCopias = 0;
		while (!notificacoes.isEmpty()) {
			Notificacao notificacao = notificacoes.remove(0);

			List<Notificacao> notificacoesCopia = notificacoes.stream().filter(n -> n.ehCopiaDe(notificacao))
					.collect(Collectors.toList());

			if (notificacoesCopia.size() > 0) {
				totalDeNotificacoesComCopias++;

				for (Notificacao notificacaoCopia : notificacoesCopia) {
					fileWriter.write("***************************\n");
					fileWriter.write(
							"Notificações com o mesmo nome completo, data de notificação, classificação final, evolução caso, data de internação e data de encerramento\n");
					fileWriter.write(notificacao + "\n");
					fileWriter.write(notificacaoCopia + "\n");
					fileWriter.write("***************************\n");
					
					notificacaoCopia.setDescartada(true);

					notificacoesCSV.add(gerarNotificacaoParaCSV(notificacao));
					notificacoesCSV.add(gerarNotificacaoParaCSV(notificacaoCopia));
				}

				totalCopias += notificacoesCopia.size();
				notificacoes.removeAll(notificacoesCopia);
			}
		}

		System.out.println("Total de notificações com cópias: " + totalDeNotificacoesComCopias);
		System.out.println("Total de cópias identificadas: " + totalCopias);

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

		CSVNotificacao.criarCSV(ARQUIVO_CSV_COPIAS, notificacoesCSV);

		em.close();
		emf.close();

		fileWriter.close();
	}

	public static boolean naoEhNuloENemVazio(String string) {
		return string != null && !string.equals("");
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

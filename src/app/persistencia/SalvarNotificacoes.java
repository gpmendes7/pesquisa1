package app.persistencia;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepCSV;
import csv.SivepCSVHandler;
import modelo.Notificacao;

public class SalvarNotificacoes {

	private static final String ARQUIVO_CSV_SIVEP = "./arquivos/csv/SIVEP_06-07-2021_23h42.csv";

	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pesquisa1");
		EntityManager em = emf.createEntityManager();

		List<SivepCSV> siveps = SivepCSVHandler.carregarCSV(ARQUIVO_CSV_SIVEP);
		System.out.println("Total de registros carregados: " + siveps.size());

		em.getTransaction().begin();
		
		for (SivepCSV sivep : siveps) {
			em.persist(gerarNotificacao(sivep));
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public static boolean naoEhNuloENemVazio(String string) {
		return string != null && !string.equals("");
	}

	public static Notificacao gerarNotificacao(SivepCSV sivep) throws ParseException {
		sivep.formatarCampos();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNotificacao = naoEhNuloENemVazio(sivep.getDataNotificacao()) ? sdf1.parse(sivep.getDataNotificacao()) : null;
		Date dataInicioSintomas = naoEhNuloENemVazio(sivep.getDataInicioSintomas()) ? sdf1.parse(sivep.getDataInicioSintomas()) : null;
		Date dataNascimento = naoEhNuloENemVazio(sivep.getDataNascimento()) ? sdf1.parse(sivep.getDataNascimento()) : null;
		Date dataTeste = naoEhNuloENemVazio(sivep.getDataTeste()) ? sdf1.parse(sivep.getDataTeste()) : null;
		Date dataEncerramento = naoEhNuloENemVazio(sivep.getDataEncerramento()) ? sdf1.parse(sivep.getDataEncerramento()) : null;

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dataInternacao = naoEhNuloENemVazio(sivep.getDataInternacao()) ? sdf2.parse(sivep.getDataInternacao()) : null;

		return new Notificacao(sivep.getNumeroNotificacao(), sivep.getNomeCompleto(), sivep.getCpf(), dataNotificacao,
				dataInicioSintomas, dataNascimento, sivep.getCep(), sivep.getLogradouro(), sivep.getNumero(),
				sivep.getComplemento(), sivep.getBairro(), sivep.getMunicipio(), sivep.getEstado(),
				sivep.getEstrangeiro(), sivep.getPassaporte(), sivep.getPaisOrigem(), sivep.getProfissionalSeguranca(),
				sivep.getProfissionalSaude(), sivep.getCbo(), sivep.getCns(), sivep.getNomeMae(), sivep.getSexo(),
				sivep.getRacaCor(), sivep.getTelefoneCelular(), sivep.getTelefoneContato(), sivep.getFebre(),
				sivep.getTosse(), sivep.getDorGarganta(), sivep.getDispneia(), sivep.getOutrosSintomas(),
				sivep.getDescricaoOutros(), sivep.getDoencasRespiratorias(), sivep.getDoencasRenais(),
				sivep.getFragilidadeImunologica(), sivep.getDiabetes(), sivep.getImunosupressao(),
				sivep.getDoencasCardiacas(), sivep.getGestanteAltoRisco(), sivep.getOrigem(), sivep.getLatitude(),
				sivep.getLongitude(), sivep.getCnes(), sivep.getIdade(), sivep.getEstadoTeste(), dataTeste,
				sivep.getTipoTeste(), sivep.getResultadoTeste(), dataInternacao, dataEncerramento,
				sivep.getEvolucaoCaso(), sivep.getClassificacaoFinal(), false);
	}
}

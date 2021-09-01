package csv;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import modelo.NotificacaoParaCSV;

public class CSVNotificacao {
	
	private static ColumnPositionMappingStrategy<NotificacaoParaCSV> strategy;
	
	static {
		strategy = new ColumnPositionMappingStrategy<NotificacaoParaCSV>();
		strategy.setType(NotificacaoParaCSV.class);
		
		String[] colunas = { "numeroNotificacao", "nomeCompleto", "cpf", "dataNotificacao", "dataInicioSintomas",
				"dataNascimento", "cep", "logradouro", "numero", "complemento", "bairro", "municipio", "estado",
				"estrangeiro", "passaporte", "paisOrigem", "profissionalSeguranca", "profissionalSaude", "cbo", "cns",
				"nomeMae", "sexo", "racaCor", "telefoneCelular", "telefoneContato", "febre", "tosse", "dorGarganta",
				"dispneia", "outrosSintomas", "descricaoOutros", "doencasRespiratorias", "doencasRenais",
				"fragilidadeImunologica", "diabetes", "imunosupressao", "doencasCardiacas", "gestanteAltoRisco",
				"origem", "latitude", "longitude", "cnes", "idade", "estadoTeste", "dataTeste", "tipoTeste",
				"resultadoTeste", "dataInternacao", "dataEncerramento", "evolucaoCaso", "classificacaoFinal", "descartada" };
		
		strategy.setColumnMapping(colunas);
	}

	public static List<NotificacaoParaCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<NotificacaoParaCSV> csvToBean = new CsvToBeanBuilder<NotificacaoParaCSV>(reader)
					.withMappingStrategy(strategy)
					.withType(NotificacaoParaCSV.class)
					.withSeparator(';')
					.withSkipLines(1)
					.build();
			List<NotificacaoParaCSV> notificacoes = csvToBean.parse();
			return notificacoes;
		}
	}

	public static void criarCSV(String nomeArquivo, List<NotificacaoParaCSV> notificacoes) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<NotificacaoParaCSV> beanToCsv = new StatefulBeanToCsvBuilder<NotificacaoParaCSV>(writer)
					.withMappingStrategy(strategy)
					.withSeparator(';')
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.build();

			beanToCsv.write(notificacoes);
		}
	}

}

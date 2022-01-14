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

public class SivepCSVHandler {

	private static ColumnPositionMappingStrategy<SivepCSV> strategy;

	static {
		strategy = new ColumnPositionMappingStrategy<csv.SivepCSV>();
		strategy.setType(csv.SivepCSV.class);

		String[] colunas = { "numeroNotificacao", "nomeCompleto", "cpf", "dataNotificacao", "dataInicioSintomas",
				"dataNascimento", "cep", "logradouro", "numero", "complemento", "bairro", "municipio", "estado",
				"estrangeiro", "passaporte", "paisOrigem", "profissionalSeguranca", "profissionalSaude", "cbo", "cns",
				"nomeMae", "sexo", "racaCor", "telefoneCelular", "telefoneContato", "febre", "tosse", "dorGarganta",
				"dispneia", "outrosSintomas", "descricaoOutros", "doencasRespiratorias", "doencasRenais",
				"fragilidadeImunologica", "diabetes", "imunosupressao", "doencasCardiacas", "gestanteAltoRisco",
				"origem", "latitude", "longitude", "cnes", "idade", "estadoTeste", "dataTeste", "tipoTeste",
				"resultadoTeste", "dataInternacao", "dataEncerramento", "evolucaoCaso", "classificacaoFinal" };

		strategy.setColumnMapping(colunas);
	}

	public static List<SivepCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<SivepCSV> csvToBean = new CsvToBeanBuilder<SivepCSV>(reader).withMappingStrategy(strategy)
					.withType(SivepCSV.class).withSeparator(';').withSkipLines(1).build();
			List<SivepCSV> siveps = csvToBean.parse();
			return siveps;
		}
	}

	public static void criarCSV(String nomeArquivo, List<SivepCSV> siveps)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<SivepCSV> beanToCsv = new StatefulBeanToCsvBuilder<SivepCSV>(writer)
					.withMappingStrategy(strategy).withSeparator(';').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.build();

			beanToCsv.write(siveps);
		}
	}

}

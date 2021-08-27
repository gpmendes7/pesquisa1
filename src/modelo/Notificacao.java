package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroNotificacao;
	private String nomeCompleto;
	private String cpf;
	private Date dataNotificacao;
	private Date dataInicioSintomas;
	private Date dataNascimento;
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String municipio;
	private String estado;
	private String estrangeiro;
	private String passaporte;
	private String paisOrigem;
	private String profissionalSeguranca;
	private String profissionalSaude;
	private String cbo;
	private String cns;
	private String nomeMae;
	private String sexo;
	private String racaCor;
	private String telefoneCelular;
	private String telefoneContato;
	private String febre;
	private String tosse;
	private String dorGarganta;
	private String dispneia;
	private String outrosSintomas;
	private String descricaoOutros;
	private String doencasRespiratorias;
	private String doencasRenais;
	private String fragilidadeImunologica;
	private String diabetes;
	private String imunosupressao;
	private String doencasCardiacas;
	private String gestanteAltoRisco;
	private String origem;
	private String latitude;
	private String longitude;
	private String cnes;
	private String idade;
	private String estadoTeste;
	private Date dataTeste;
	private String tipoTeste;
	private String resultadoTeste;
	private Date dataInternacao;
	private Date dataEncerramento;
	private String evolucaoCaso;
	private String classificacaoFinal;

	public Notificacao() {
	}

	public Notificacao(String numeroNotificacao, String nomeCompleto, String cpf, Date dataNotificacao,
			Date dataInicioSintomas, Date dataNascimento, String cep, String logradouro, String numero,
			String complemento, String bairro, String municipio, String estado, String estrangeiro, String passaporte,
			String paisOrigem, String profissionalSeguranca, String profissionalSaude, String cbo, String cns,
			String nomeMae, String sexo, String racaCor, String telefoneCelular, String telefoneContato, String febre,
			String tosse, String dorGarganta, String dispneia, String outrosSintomas, String descricaoOutros,
			String doencasRespiratorias, String doencasRenais, String fragilidadeImunologica, String diabetes,
			String imunosupressao, String doencasCardiacas, String gestanteAltoRisco, String origem, String latitude,
			String longitude, String cnes, String idade, String estadoTeste, Date dataTeste, String tipoTeste,
			String resultadoTeste, Date dataInternacao, Date dataEncerramento, String evolucaoCaso,
			String classificacaoFinal) {
		super();
		this.numeroNotificacao = numeroNotificacao;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNotificacao = dataNotificacao;
		this.dataInicioSintomas = dataInicioSintomas;
		this.dataNascimento = dataNascimento;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.estrangeiro = estrangeiro;
		this.passaporte = passaporte;
		this.paisOrigem = paisOrigem;
		this.profissionalSeguranca = profissionalSeguranca;
		this.profissionalSaude = profissionalSaude;
		this.cbo = cbo;
		this.cns = cns;
		this.nomeMae = nomeMae;
		this.sexo = sexo;
		this.racaCor = racaCor;
		this.telefoneCelular = telefoneCelular;
		this.telefoneContato = telefoneContato;
		this.febre = febre;
		this.tosse = tosse;
		this.dorGarganta = dorGarganta;
		this.dispneia = dispneia;
		this.outrosSintomas = outrosSintomas;
		this.descricaoOutros = descricaoOutros;
		this.doencasRespiratorias = doencasRespiratorias;
		this.doencasRenais = doencasRenais;
		this.fragilidadeImunologica = fragilidadeImunologica;
		this.diabetes = diabetes;
		this.imunosupressao = imunosupressao;
		this.doencasCardiacas = doencasCardiacas;
		this.gestanteAltoRisco = gestanteAltoRisco;
		this.origem = origem;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cnes = cnes;
		this.idade = idade;
		this.estadoTeste = estadoTeste;
		this.dataTeste = dataTeste;
		this.tipoTeste = tipoTeste;
		this.resultadoTeste = resultadoTeste;
		this.dataInternacao = dataInternacao;
		this.dataEncerramento = dataEncerramento;
		this.evolucaoCaso = evolucaoCaso;
		this.classificacaoFinal = classificacaoFinal;
	}

	@Override
	public String toString() {
		return "Notificacao [numeroNotificacao=" + numeroNotificacao + ", nomeCompleto=" + nomeCompleto + ", cpf=" + cpf
				+ ", dataNotificacao=" + dataNotificacao + ", dataInicioSintomas=" + dataInicioSintomas
				+ ", dataNascimento=" + dataNascimento + ", cep=" + cep + ", logradouro=" + logradouro + ", numero="
				+ numero + ", complemento=" + complemento + ", bairro=" + bairro + ", municipio=" + municipio
				+ ", estado=" + estado + ", estrangeiro=" + estrangeiro + ", passaporte=" + passaporte + ", paisOrigem="
				+ paisOrigem + ", profissionalSeguranca=" + profissionalSeguranca + ", profissionalSaude="
				+ profissionalSaude + ", cbo=" + cbo + ", cns=" + cns + ", nomeMae=" + nomeMae + ", sexo=" + sexo
				+ ", racaCor=" + racaCor + ", telefoneCelular=" + telefoneCelular + ", telefoneContato="
				+ telefoneContato + ", febre=" + febre + ", tosse=" + tosse + ", dorGarganta=" + dorGarganta
				+ ", dispneia=" + dispneia + ", outrosSintomas=" + outrosSintomas + ", descricaoOutros="
				+ descricaoOutros + ", doencasRespiratorias=" + doencasRespiratorias + ", doencasRenais="
				+ doencasRenais + ", fragilidadeImunologica=" + fragilidadeImunologica + ", diabetes=" + diabetes
				+ ", imunosupressao=" + imunosupressao + ", doencasCardiacas=" + doencasCardiacas
				+ ", gestanteAltoRisco=" + gestanteAltoRisco + ", origem=" + origem + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", cnes=" + cnes + ", idade=" + idade + ", estadoTeste=" + estadoTeste
				+ ", dataTeste=" + dataTeste + ", tipoTeste=" + tipoTeste + ", resultadoTeste=" + resultadoTeste
				+ ", dataInternacao=" + dataInternacao + ", dataEncerramento=" + dataEncerramento + ", evolucaoCaso="
				+ evolucaoCaso + ", classificacaoFinal=" + classificacaoFinal + "]";
	}

}

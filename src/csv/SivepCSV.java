package csv;

public class SivepCSV {

	private String numeroNotificacao;
	private String nomeCompleto;
	private String cpf;
	private String dataNotificacao;
	private String dataInicioSintomas;
	private String dataNascimento;
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
	private String dataTeste;
	private String tipoTeste;
	private String resultadoTeste;
	private String dataInternacao;
	private String dataEncerramento;
	private String evolucaoCaso;
	private String classificacaoFinal;

	public SivepCSV() {
	}

	public void formatarCampos() {
		this.logradouro = this.logradouro.replace(";", ",");
		this.complemento = this.complemento.replace(";", ",");
		this.descricaoOutros = this.descricaoOutros.replace(";", ",");
	}
	
	public SivepCSV(String numeroNotificacao, String nomeCompleto, String cpf, String dataNotificacao,
			String dataInicioSintomas, String dataNascimento, String cep, String logradouro, String numero,
			String complemento, String bairro, String municipio, String estado, String estrangeiro, String passaporte,
			String paisOrigem, String profissionalSeguranca, String profissionalSaude, String cbo, String cns,
			String nomeMae, String sexo, String racaCor, String telefoneCelular, String telefoneContato, String febre,
			String tosse, String dorGarganta, String dispneia, String outrosSintomas, String descricaoOutros,
			String doencasRespiratorias, String doencasRenais, String fragilidadeImunologica, String diabetes,
			String imunosupressao, String doencasCardiacas, String gestanteAltoRisco, String origem, String latitude,
			String longitude, String cnes, String idade, String estadoTeste, String dataTeste, String tipoTeste,
			String resultadoTeste, String dataInternacao, String dataEncerramento, String evolucaoCaso,
			String classificacaoFinal) {
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

	public String getNumeroNotificacao() {
		return numeroNotificacao;
	}

	public void setNumeroNotificacao(String numeroNotificacao) {
		this.numeroNotificacao = numeroNotificacao;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	public String getDataInicioSintomas() {
		return dataInicioSintomas;
	}

	public void setDataInicioSintomas(String dataInicioSintomas) {
		this.dataInicioSintomas = dataInicioSintomas;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstrangeiro() {
		return estrangeiro;
	}

	public void setEstrangeiro(String estrangeiro) {
		this.estrangeiro = estrangeiro;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getProfissionalSeguranca() {
		return profissionalSeguranca;
	}

	public void setProfissionalSeguranca(String profissionalSeguranca) {
		this.profissionalSeguranca = profissionalSeguranca;
	}

	public String getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(String profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getFebre() {
		return febre;
	}

	public void setFebre(String febre) {
		this.febre = febre;
	}

	public String getTosse() {
		return tosse;
	}

	public void setTosse(String tosse) {
		this.tosse = tosse;
	}

	public String getDorGarganta() {
		return dorGarganta;
	}

	public void setDorGarganta(String dorGarganta) {
		this.dorGarganta = dorGarganta;
	}

	public String getDispneia() {
		return dispneia;
	}

	public void setDispneia(String dispneia) {
		this.dispneia = dispneia;
	}

	public String getOutrosSintomas() {
		return outrosSintomas;
	}

	public void setOutrosSintomas(String outrosSintomas) {
		this.outrosSintomas = outrosSintomas;
	}

	public String getDescricaoOutros() {
		return descricaoOutros;
	}

	public void setDescricaoOutros(String descricaoOutros) {
		this.descricaoOutros = descricaoOutros;
	}

	public String getDoencasRespiratorias() {
		return doencasRespiratorias;
	}

	public void setDoencasRespiratorias(String doencasRespiratorias) {
		this.doencasRespiratorias = doencasRespiratorias;
	}

	public String getDoencasRenais() {
		return doencasRenais;
	}

	public void setDoencasRenais(String doencasRenais) {
		this.doencasRenais = doencasRenais;
	}

	public String getFragilidadeImunologica() {
		return fragilidadeImunologica;
	}

	public void setFragilidadeImunologica(String fragilidadeImunologica) {
		this.fragilidadeImunologica = fragilidadeImunologica;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getImunosupressao() {
		return imunosupressao;
	}

	public void setImunosupressao(String imunosupressao) {
		this.imunosupressao = imunosupressao;
	}

	public String getDoencasCardiacas() {
		return doencasCardiacas;
	}

	public void setDoencasCardiacas(String doencasCardiacas) {
		this.doencasCardiacas = doencasCardiacas;
	}

	public String getGestanteAltoRisco() {
		return gestanteAltoRisco;
	}

	public void setGestanteAltoRisco(String gestanteAltoRisco) {
		this.gestanteAltoRisco = gestanteAltoRisco;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEstadoTeste() {
		return estadoTeste;
	}

	public void setEstadoTeste(String estadoTeste) {
		this.estadoTeste = estadoTeste;
	}

	public String getDataTeste() {
		return dataTeste;
	}

	public void setDataTeste(String dataTeste) {
		this.dataTeste = dataTeste;
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public String getResultadoTeste() {
		return resultadoTeste;
	}

	public void setResultadoTeste(String resultadoTeste) {
		this.resultadoTeste = resultadoTeste;
	}

	public String getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(String dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public void setEvolucaoCaso(String evolucaoCaso) {
		this.evolucaoCaso = evolucaoCaso;
	}

	public String getClassificacaoFinal() {
		return classificacaoFinal;
	}

	public void setClassificacaoFinal(String classificacaoFinal) {
		this.classificacaoFinal = classificacaoFinal;
	}

	@Override
	public String toString() {
		return "Sivep [numeroNotificacao=" + numeroNotificacao + ", nomeCompleto=" + nomeCompleto + ", cpf=" + cpf
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

package localhost.filmesassistidos.model;

public class Filme {
	private Integer codigoFilme;
	private String nomeFilme;
	private String anoFilme;
	private String nomeDiretor;
	private Integer codigoGenero;
	private Integer codigoNacionalidade;
	private Integer filmeAssistido;

	public Integer getCodigoFilme() {
		return codigoFilme;
	}

	public void setCodigoFilme(Integer codigoFilme) {
		this.codigoFilme = codigoFilme;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public String getAnoFilme() {
		return anoFilme;
	}

	public void setAnoFilme(String anoFilme) {
		this.anoFilme = anoFilme;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public Integer getCodigoGenero() {
		return codigoGenero;
	}

	public void setCodigoGenero(Integer codigoGenero) {
		this.codigoGenero = codigoGenero;
	}

	public Integer getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	public void setCodigoNacionalidade(Integer codigoNacionalidade) {
		this.codigoNacionalidade = codigoNacionalidade;
	}

	public Integer getFilmeAssistido() {
		return filmeAssistido;
	}

	public void setFilmeAssistido(Integer filmeAssistido) {
		this.filmeAssistido = filmeAssistido;
	}
}

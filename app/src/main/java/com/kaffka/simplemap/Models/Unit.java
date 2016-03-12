package com.kaffka.simplemap.Models;

/* A model to deserialize our friend json below:
{
      "Cnes": 2752166,
      "CodSQCN": 237,
      "Nome": "AMA/UBS INTEGRADA AGUA RASA",
      "Servico": "AMA/UBS INTEGRADA AGUA RASA",
      "Logradouro": "R. SERRA DE JAIRE",
      "Numero": "1480",
      "Cep": "03175001",
      "Bairro": "ÁGUA RASA",
      "Horario": "Segunda a Sexta: 7h às 19h",
      "Geo": {
        "Latitude": -23.55364,
        "Longitude": -46.58018
      },
      "Tipo": 5,
      "Subtipo": "28",
      "UBSReferencia": false,
      "NomeTipo": "UBS/ Posto de Saúde/ Centro de Saúde",
      "NomeSubTipo": "UBS/ Posto de Saúde/ Centro de Saúde",
      "Telefone1": "2605-2156",
      "Telefone2": "2605-5307",
      "ExibirUBSReferencia": false,
      "InformacoesConcatenadas": "AMA/UBS INTEGRADA AGUA RASA"
    }
*/

final public class Unit {

    final private String Cnes;
    final private String CodSQCN;
    final private String Nome;
    final private String Servico;
    final private String Logradouro;
    final private String Numero;
    final private String Cep;
    final private String Bairro;
    final private String Horario;
    final private Geo Geo;
    final private String Tipo;
    final private String Subtipo;
    final private boolean UBSReferencia;
    final private String NomeTipo;
    final private String NomeSubTipo;
    final private String Telefone1;
    final private String Telefone2;
    final private boolean ExibirUBSReferencia;
    final private String InformacoesConcatenadas;

    public Unit(String cnes, String codSQCN, String nome, String servico, String logradouro, String numero, String cep, String bairro, String horario, com.kaffka.simplemap.Models.Geo geo, String tipo, String subtipo, boolean UBSReferencia, String nomeTipo, String nomeSubTipo, String telefone1, String telefone2, boolean exibirUBSReferencia, String informacoesConcatenadas) {
        Cnes = cnes;
        CodSQCN = codSQCN;
        Nome = nome;
        Servico = servico;
        Logradouro = logradouro;
        Numero = numero;
        Cep = cep;
        Bairro = bairro;
        Horario = horario;
        Geo = geo;
        Tipo = tipo;
        Subtipo = subtipo;
        this.UBSReferencia = UBSReferencia;
        NomeTipo = nomeTipo;
        NomeSubTipo = nomeSubTipo;
        Telefone1 = telefone1;
        Telefone2 = telefone2;
        ExibirUBSReferencia = exibirUBSReferencia;
        InformacoesConcatenadas = informacoesConcatenadas;
    }

    public String getCnes() {
        return Cnes;
    }

    public String getCodSQCN() {
        return CodSQCN;
    }

    public String getNome() {
        return Nome;
    }

    public String getServico() {
        return Servico;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public String getNumero() {
        return Numero;
    }

    public String getCep() {
        return Cep;
    }

    public String getBairro() {
        return Bairro;
    }

    public String getHorario() {
        return Horario;
    }

    public com.kaffka.simplemap.Models.Geo getGeo() {
        return Geo;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getSubtipo() {
        return Subtipo;
    }

    public boolean isUBSReferencia() {
        return UBSReferencia;
    }

    public String getNomeTipo() {
        return NomeTipo;
    }

    public String getNomeSubTipo() {
        return NomeSubTipo;
    }

    public String getTelefone1() {
        return Telefone1;
    }

    public String getTelefone2() {
        return Telefone2;
    }

    public boolean isExibirUBSReferencia() {
        return ExibirUBSReferencia;
    }

    public String getInformacoesConcatenadas() {
        return InformacoesConcatenadas;
    }

    public String buildSnippet() {
        return getBairro() + "\n" +
                getNomeTipo() + " " +
                getNomeSubTipo() + "\n" +
                getLogradouro() + ", " +
                getNumero() + " - " +
                getBairro() + "\nCEP: " + getCep() +
                "\nTelefone(s): " + getTelefone1() + ((getTelefone2().length() > 0) ? ", " + getTelefone2() : "") +
                "\nFuncionamento: " + getHorario();
    }
}

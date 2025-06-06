package br.com.avaliacao2_arthurguirao.dto;

public class BibliotecarioDTO {
    
    private String nome_bib, cpf_bib, login_bib, senha_bib, tipo_bib;
    private int id_bib;

    public String getNome_bib() {
        return nome_bib;
    }

    public void setNome_bib(String nome_bib) {
        this.nome_bib = nome_bib;
    }

    public String getCpf_bib() {
        return cpf_bib;
    }

    public void setCpf_bib(String cpf_bib) {
        this.cpf_bib = cpf_bib;
    }

    public String getLogin_bib() {
        return login_bib;
    }

    public void setLogin_bib(String login_bib) {
        this.login_bib = login_bib;
    }

    public String getSenha_bib() {
        return senha_bib;
    }

    public void setSenha_bib(String senha_bib) {
        this.senha_bib = senha_bib;
    }

    public String getTipo_bib() {
        return tipo_bib;
    }

    public void setTipo_bib(String tipo_bib) {
        this.tipo_bib = tipo_bib;
    }

    public int getId_bib() {
        return id_bib;
    }

    public void setId_bib(int id_bib) {
        this.id_bib = id_bib;
    }

    
}

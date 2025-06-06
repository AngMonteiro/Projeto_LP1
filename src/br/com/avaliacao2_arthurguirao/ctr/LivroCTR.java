package br.com.avaliacao2_arthurguirao.ctr;

import java.sql.ResultSet;
import br.com.avaliacao2_arthurguirao.dto.FornecedorDTO;
import br.com.avaliacao2_arthurguirao.dto.LivroDTO;
import br.com.avaliacao2_arthurguirao.dao.LivroDAO;
import br.com.avaliacao2_arthurguirao.dao.ConexaoDAO;

public class LivroCTR {
    
    LivroDAO livroDAO = new LivroDAO();
    
    public LivroCTR() {
    }
    
    public String inserirLivro(LivroDTO livroDTO, FornecedorDTO fornecedorDTO) {
        try {
            if (livroDAO.inserirLivro(livroDTO, fornecedorDTO)) {
                return "Livro cadastrado com sucesso!!";
            } else {
                return "Livro NÃO cadastrado!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Livro NÃO cadastrado!!";
        }
    }
    
    public String alterarLivro(LivroDTO livroDTO, FornecedorDTO fornecedorDTO) {
        try {
            if (livroDAO.alterarLivro(livroDTO, fornecedorDTO)) {
                return "Livro alterado com sucesso!!";
            } else {
                return "Livro NÃO alterado!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Livro NÃO alterado!!";
        }
    }
    
    public String excluirLivro(LivroDTO livroDTO) {
        try {
            if (livroDAO.excluirLivro(livroDTO)) {
                return "Livro excluído com sucesso!!";
            } else {
                return "Livro NÃO excluído!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Livro NÃO excluído!!";
        }
    }
    
    public ResultSet consultarLivro(LivroDTO livroDTO, int opcao) {
        ResultSet rs = null;
        rs = livroDAO.consultarLivro(livroDTO, opcao);
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}

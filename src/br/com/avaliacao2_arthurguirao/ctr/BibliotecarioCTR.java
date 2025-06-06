package br.com.avaliacao2_arthurguirao.ctr;

import java.sql.ResultSet;
import br.com.avaliacao2_arthurguirao.dto.BibliotecarioDTO;
import br.com.avaliacao2_arthurguirao.dao.BibliotecarioDAO;
import br.com.avaliacao2_arthurguirao.dao.ConexaoDAO;

public class BibliotecarioCTR {
    
    BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
    
    public BibliotecarioCTR() {
    }
    
    public String inserirBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        try {
            if (bibliotecarioDAO.inserirBibliotecario(bibliotecarioDTO)) {
                return "Bibliotecário cadastrado com sucesso!!!";
            } else {
                return "Bibliotecário NÃO cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Bibliotecário NÃO cadastrado!!!";
        }
    }
    
    public String alterarBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        try {
            if (bibliotecarioDAO.alterarBibliotecario(bibliotecarioDTO)) {
                return "Bibliotecário alterado com sucesso!!!";
            } else {
                return "Bibliotecário NÃO alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Bibliotecário NÃO alterado!!!";
        }
    }
    
    public String excluirBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        try {
            if (bibliotecarioDAO.excluirBibliotecario(bibliotecarioDTO)) {
                return "Bibliotecário excluído com sucesso!!!";
            } else {
                return "Bibliotecário NÃO excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Bibliotecário NÃO excluído!!!";
        }
    }
    
    public ResultSet consultarBibliotecario(BibliotecarioDTO bibliotecarioDTO, int opcao) {
        ResultSet rs = null;
        rs = bibliotecarioDAO.consultarBibliotecario(bibliotecarioDTO, opcao);
        return rs;
    }
    
    public String logarBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        return bibliotecarioDAO.logarBibliotecario(bibliotecarioDTO);
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}

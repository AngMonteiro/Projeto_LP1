package br.com.avaliacao2_arthurguirao.dao;

import java.sql.*;
import br.com.avaliacao2_arthurguirao.dto.LivroDTO;
import br.com.avaliacao2_arthurguirao.dto.FornecedorDTO;

public class LivroDAO {
    public LivroDAO() {
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirLivro(LivroDTO livroDTO, FornecedorDTO fornecedorDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into livro (nome_livro, desc_livro, cod_bar_livro, "
                    + "p_custo_livro, p_emprestimo_livro, id_for) values ("
                    + "'" + livroDTO.getNome_livro() + "', "
                    + "'" + livroDTO.getDesc_livro() + "', "
                    + "'" + livroDTO.getCod_bar_livro() + "', "
                    + livroDTO.getP_custo_livro() + ", "
                    + livroDTO.getP_emprestimo_livro() + ", "
                    + fornecedorDTO.getId_for() + ")";
            
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarLivro(LivroDTO livroDTO, FornecedorDTO fornecedorDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update livro set "
                    + "nome_livro = '" + livroDTO.getNome_livro() + "', "
                    + "desc_livro = '" + livroDTO.getDesc_livro() + "', "
                    + "cod_bar_livro = '" + livroDTO.getCod_bar_livro() + "', "
                    + "p_custo_livro = " + livroDTO.getP_custo_livro() + ", "
                    + "p_emprestimo_livro = " + livroDTO.getP_emprestimo_livro() + ", "
                    + "id_for = " + fornecedorDTO.getId_for() + " "
                    + "where id_livro = " + livroDTO.getId_livro();
            
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirLivro(LivroDTO livroDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from livro where id_livro = " + livroDTO.getId_livro();
            
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarLivro(LivroDTO livroDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            switch (opcao) {
                case 1:
                    comando = "Select l.* "
                            + "from livro l "
                            + "where l.nome_livro like '" + livroDTO.getNome_livro() + "%' "
                            + "order by l.nome_livro";
                    break;
                case 2:
                    comando = "Select l.*, f.nome_for, f.id_for "
                            + "from livro l, fornecedor f "
                            + "where l.id_for = f.id_for and l.id_livro = " + livroDTO.getId_livro();
                    break;
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
}

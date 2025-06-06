package br.com.avaliacao2_arthurguirao.dao;

import br.com.avaliacao2_arthurguirao.dto.BibliotecarioDTO;
import java.sql.*;

public class BibliotecarioDAO {
    
    public BibliotecarioDAO(){
        
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into bibliotecario (nome_bib, cpf_bib, "
                + "login_bib, senha_bib, tipo_bib) values ( "
                + "'" + bibliotecarioDTO.getNome_bib().toUpperCase() + "', "
                + "'" + bibliotecarioDTO.getCpf_bib() + "', "
                + "'" + bibliotecarioDTO.getLogin_bib() + "', "
                + "crypt('" + bibliotecarioDTO.getSenha_bib() + "', gen_salt('bf', 8)) , "
                + "'" + bibliotecarioDTO.getTipo_bib().toUpperCase() + "') ";
            
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarBibliotecario (BibliotecarioDTO bibliotecarioDTO){
            try {
                ConexaoDAO.ConnectDB();
                
                stmt = ConexaoDAO.con.createStatement();
                
                String comando = "";
                
                comando = "Update bibliotecario set "
                    + "nome_bib = '" + bibliotecarioDTO.getNome_bib().toUpperCase() + "', "
                    + "cpf_bib = '" + bibliotecarioDTO.getCpf_bib() + "', "
                    + "login_bib = '" + bibliotecarioDTO.getLogin_bib() + "', ";

                if(bibliotecarioDTO.getSenha_bib() != null){
                    comando += "senha_bib = crypt('" + bibliotecarioDTO.getSenha_bib() + "',gen_salt('bf', 8)), ";
                }

                comando += "tipo_bib = '" + bibliotecarioDTO.getTipo_bib().toUpperCase() + "' "
                        + "where id_bib = " + bibliotecarioDTO.getId_bib();
                
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
    
    public boolean excluirBibliotecario (BibliotecarioDTO bibliotecarioDTO) {
        try {
            ConexaoDAO.ConnectDB();

            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from bibliotecario where id_bib = " + bibliotecarioDTO.getId_bib();

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
    
    public ResultSet consultarBibliotecario (BibliotecarioDTO bibliotecarioDTO, int opcao) {
        try {
            
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch (opcao){
                case 1:
                    comando = "Select b.* "+
                    "from bibliotecario b "+
                    "where nome_bib ilike '" + bibliotecarioDTO.getNome_bib()+ "%' " +
                    "order by b.nome_bib";
                break;
                case 2:
                    comando = "Select b.* "+
                    "from bibliotecario b " +
                    "where b.id_bib = " + bibliotecarioDTO.getId_bib();
                break;
            } 
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }

    public String logarBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Select b.tipo_bib " +
                 "from bibliotecario b " +
                 "where b.login_bib = '" + bibliotecarioDTO.getLogin_bib() + "' " +
                 "and b.senha_bib = crypt('" + bibliotecarioDTO.getSenha_bib() + "', senha_bib)";
            
            rs = null;
            rs = stmt.executeQuery(comando);
            
            if(rs.next()){
                return rs.getString("tipo_bib");
            }
            else{
                return "";
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        } finally{
            ConexaoDAO.CloseDB();
        }
    }
}

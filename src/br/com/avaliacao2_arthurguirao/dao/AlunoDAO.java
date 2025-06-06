package br.com.avaliacao2_arthurguirao.dao;

import java.sql.*;
import br.com.avaliacao2_arthurguirao.dto.AlunoDTO;

public class AlunoDAO {
    
    public AlunoDAO(){}
    
    private ResultSet rs = null;
    private Statement stmt = null;

    public boolean inserirAluno(AlunoDTO alunoDTO) {
        try {

            ConexaoDAO.ConnectDB();

            stmt = ConexaoDAO.con.createStatement();

            String comando = "Insert into aluno (nome_alu, logradouro_alu, numero_alu, "
                + "bairro_alu, cidade_alu, estado_alu, cep_alu, cpf_alu, rg_alu) values ( "
                + "'" + alunoDTO.getNome_alu() + "', "
                + "'" + alunoDTO.getLogradouro_alu() + "', "
                + alunoDTO.getNumero_alu() + ", "
                + "'" + alunoDTO.getBairro_alu() + "', "
                + "'" + alunoDTO.getCidade_alu() + "', "
                + "'" + alunoDTO.getEstado_alu() + "', "
                + "'" + alunoDTO.getCep_alu() + "', "
                + "'" + alunoDTO.getCpf_alu() + "', "
                + "'" + alunoDTO.getRg_alu() + "') ";

            stmt.execute(comando.toUpperCase());

            ConexaoDAO.con.commit();

            stmt.close();
            return true;
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            return false;   
        }
        finally {
            ConexaoDAO.CloseDB();   
        }
    }

    public ResultSet consultarAluno(AlunoDTO alunoDTO, int opcao){
        try{
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select a.* "+
                            "from aluno a "+
                            "where nome_alu like '"+alunoDTO.getNome_alu()+"%' "+
                            "order by a.nome_alu";
                    break;
                case 2:
                    comando = "Select a.* "+
                            "from aluno a "+
                            "where a.id_alu = "+alunoDTO.getId_alu();
                    break;
                case 3:
                    comando = "Select a.id_alu, a.nome_alu "+
                            "from aluno a ";
                    break;
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }

    public boolean alterarAluno(AlunoDTO alunoDTO) {
        try {
            
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update aluno set "
            + "nome_alu = '" + alunoDTO.getNome_alu() + "', "
            + "logradouro_alu = '" + alunoDTO.getLogradouro_alu() + "', "
            + "numero_alu = " + alunoDTO.getNumero_alu() + ", "
            + "bairro_alu = '" + alunoDTO.getBairro_alu() + "', "
            + "cidade_alu = '" + alunoDTO.getCidade_alu() + "', "
            + "estado_alu = '" + alunoDTO.getEstado_alu() + "', "
            + "cep_alu = '" + alunoDTO.getCep_alu() + "', "
            + "cpf_alu = '" + alunoDTO.getCpf_alu() + "', "
            + "rg_alu = '" + alunoDTO.getRg_alu() + "' "
            + "where id_alu = " + alunoDTO.getId_alu();

            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean excluirAluno(AlunoDTO alunoDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from aluno where id_alu = " + alunoDTO.getId_alu();
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
}

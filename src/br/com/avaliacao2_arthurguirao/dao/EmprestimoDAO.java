package br.com.avaliacao2_arthurguirao.dao;

import br.com.avaliacao2_arthurguirao.dto.AlunoDTO;
import br.com.avaliacao2_arthurguirao.dto.EmprestimoDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

public class EmprestimoDAO {
    
    public EmprestimoDAO() {}
    
    // Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    // Manipular o banco de dados
    Statement stmt = null;
    Statement stmt1 = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    public boolean inserirEmprestimo(EmprestimoDTO emprestimoDTO, AlunoDTO alunoDTO, JTable livros) {
        try {
            //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            //Cria o Statement que é responsável por executar algo no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            
            String comando1 = "Insert into emprestimo (dat_emprestimo, val_emprestimo, "
                + "id_alu) values ( "
                + "to_date('" + date.format(emprestimoDTO.getDat_emprestimo()) + "', 'DD/MM/YYYY'), "
                + emprestimoDTO.getVal_emprestimo() + ", "
                + alunoDTO.getId_alu() + ")";

            //Executa o comando SQL no banco de Dados
            stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            
            for (int cont = 0; cont < livros.getRowCount(); cont++) {
                String comando2 = "Insert into livro_emprestimo (id_emprestimo, id_livro, "
                    + "val_livro, qtd_livro) values ( "
                    + rs.getInt("id_emprestimo") + ", "
                    + livros.getValueAt(cont, 0) + ", "
                    + livros.getValueAt(cont, 2) + ", "
                    + livros.getValueAt(cont, 3) + ");";

                stmt1.execute(comando2);
            }
            // Dá um commit no banco de dados
            ConexaoDAO.con.commit();
            // Fecha o statement
            stmt.close();
            stmt1.close();
            rs.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            // Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }
}

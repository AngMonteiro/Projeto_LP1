package br.com.avaliacao2_arthurguirao.ctr;

import br.com.avaliacao2_arthurguirao.dao.ConexaoDAO;
import br.com.avaliacao2_arthurguirao.dao.EmprestimoDAO;
import br.com.avaliacao2_arthurguirao.dto.EmprestimoDTO;
import br.com.avaliacao2_arthurguirao.dto.AlunoDTO;
import javax.swing.JTable;

public class EmprestimoCTR {
    
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    
    public EmprestimoCTR() {
    }
    
    public String inserirEmprestimo(EmprestimoDTO emprestimoDTO, AlunoDTO alunoDTO, JTable livros) {
        try {
            if (emprestimoDAO.inserirEmprestimo(emprestimoDTO, alunoDTO, livros)) {
                return "Empréstimo cadastrado com sucesso!!!";
            } else {
                return "Empréstimo NÃO cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Empréstimo NÃO cadastrado!!!";
        }
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}

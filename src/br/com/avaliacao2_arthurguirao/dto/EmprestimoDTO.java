package br.com.avaliacao2_arthurguirao.dto;

import java.util.Date;

public class EmprestimoDTO {
    
    private Date dat_emprestimo;
    private Double val_emprestimo;
    private int id_emprestimo;

    public Date getDat_emprestimo() {
        return dat_emprestimo;
    }

    public void setDat_emprestimo(Date dat_emprestimo) {
        this.dat_emprestimo = dat_emprestimo;
    }

    public Double getVal_emprestimo() {
        return val_emprestimo;
    }

    public void setVal_emprestimo(Double val_emprestimo) {
        this.val_emprestimo = val_emprestimo;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

}

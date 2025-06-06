package br.com.avaliacao2_arthurguirao.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.avaliacao2_arthurguirao.dto.FornecedorDTO;
import br.com.avaliacao2_arthurguirao.ctr.FornecedorCTR;
import br.com.avaliacao2_arthurguirao.dto.LivroDTO;
import br.com.avaliacao2_arthurguirao.ctr.LivroCTR;

public class LivroVIEW extends javax.swing.JInternalFrame {
    
    LivroDTO livroDTO = new LivroDTO();
    LivroCTR livroCTR = new LivroCTR();
    FornecedorDTO fornecedorDTO = new FornecedorDTO();
    FornecedorCTR fornecedorCTR = new FornecedorCTR();
    
    int gravar_alterar;
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_livro;
    DefaultTableModel modelo_jtl_consultar_fornecedor;
    
    public LivroVIEW() {
        initComponents();
        
        liberaCampos(false);
        
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_livro = (DefaultTableModel) jtl_consultar_livro.getModel();
        modelo_jtl_consultar_fornecedor = (DefaultTableModel) jtl_consultar_fornecedor.getModel();
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar(){
        try{
            livroDTO.setNome_livro(nome_livro.getText());
            livroDTO.setDesc_livro(desc_livro.getText());
            livroDTO.setCod_bar_livro(cod_bar_livro.getText());
            livroDTO.setP_custo_livro(Double.parseDouble(p_custo_livro.getText()));
            livroDTO.setP_emprestimo_livro(Double.parseDouble(p_emprestimo_livro.getText()));
            fornecedorDTO.setId_for(Integer.parseInt(String.valueOf(
                    jtl_consultar_fornecedor.getValueAt(
                    jtl_consultar_fornecedor.getSelectedRow(), 0))));

            JOptionPane.showMessageDialog(null,
                    livroCTR.inserirLivro(livroDTO, fornecedorDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }
    private void alterar(){
        try{
            livroDTO.setNome_livro(nome_livro.getText());
            livroDTO.setDesc_livro(desc_livro.getText());
            livroDTO.setCod_bar_livro(cod_bar_livro.getText());
            livroDTO.setP_custo_livro(Double.parseDouble(p_custo_livro.getText()));
            livroDTO.setP_emprestimo_livro(Double.parseDouble(p_emprestimo_livro.getText()));
            fornecedorDTO.setId_for(Integer.parseInt(String.valueOf(
                    jtl_consultar_fornecedor.getValueAt(
                    jtl_consultar_fornecedor.getSelectedRow(), 0))));

            JOptionPane.showMessageDialog(null,
                    livroCTR.alterarLivro(livroDTO, fornecedorDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }   
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Produto?", "Aviso", 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,
                        livroCTR.excluirLivro(livroDTO));
        }
    }
    private void liberaCampos(boolean a){
        nome_livro.setEnabled(a);
        desc_livro.setEnabled(a);
        cod_bar_livro.setEnabled(a);
        p_custo_livro.setEnabled(a);
        p_emprestimo_livro.setEnabled(a);
        pesquisa_nome_fornecedor.setEnabled(a);
        btnPesquisaFornecedor.setEnabled(a);
        jtl_consultar_fornecedor.setEnabled(a);
    }
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    private void limpaCampos(){
        nome_livro.setText("");
        desc_livro.setText("");
        cod_bar_livro.setText("");
        p_custo_livro.setText("");
        p_emprestimo_livro.setText("");
        pesquisa_nome_fornecedor.setText("");
        modelo_jtl_consultar_fornecedor.setNumRows(0);
    }
    private void preencheTabelaLivro(String nome_livro){
        try{
            modelo_jtl_consultar_livro.setNumRows(0);
            livroDTO.setNome_livro(nome_livro);
            rs = livroCTR.consultarLivro(livroDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_livro.addRow(new Object[]{
                    rs.getString("id_livro"),
                    rs.getString("nome_livro")
                });
            }
        }catch(Exception e){
            System.out.println("Erro ao preencheTabela"+e.getMessage());
        }finally{
            livroCTR.CloseDB();
        }
    }
    private void preencheCamposLivro (int id_livro){
        try{
            livroDTO.setId_livro(id_livro);
            rs = livroCTR.consultarLivro(livroDTO, 2);
            if(rs.next()){
                limpaCampos();
                nome_livro.setText(rs.getString("nome_for"));
                desc_livro.setText(rs.getString("desc_livro"));
                cod_bar_livro.setText(rs.getString("cod_bar_livro"));
                p_custo_livro.setText(rs.getString("p_custo_livro"));
                p_emprestimo_livro.setText(rs.getString("p_emprestimo_livro"));
                
                modelo_jtl_consultar_fornecedor.setNumRows(0);
                modelo_jtl_consultar_fornecedor.addRow(new Object[]{rs.getInt("id_for"),rs.getString("nome_for"),});
                jtl_consultar_fornecedor.setRowSelectionInterval(0, 0);
            

                gravar_alterar = 2;
                liberaCampos(true);
            }
            
        }catch(Exception e){
            System.out.println("Erro ao preencheCamposProduto: "+e.getMessage());
        }finally{
            livroCTR.CloseDB();
        }
    } 
    private void preencheTabelaFornecedor(String nome_for){
        try{
            modelo_jtl_consultar_fornecedor.setNumRows(0);
            fornecedorDTO.setNome_for(nome_for);
            rs = fornecedorCTR.consultarFornecedor(fornecedorDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_fornecedor.addRow(new Object[]{
                    rs.getString("id_for"),
                    rs.getString("nome_for")
                });
            }
        }catch(Exception e){
            System.out.println("Erro ao preencheTabelaFornecedor: "+e.getMessage());
        }finally{
            livroCTR.CloseDB();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome_livro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        desc_livro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cod_bar_livro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        p_custo_livro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        p_emprestimo_livro = new javax.swing.JTextField();
        pesquisa_nome_fornecedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnPesquisaFornecedor = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_livro = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnPesquisaLivro = new javax.swing.JToggleButton();
        pesquisa_nome_livro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_fornecedor = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Livro");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 7, -1, -1));

        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 43, -1, -1));

        nome_livro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_livroActionPerformed(evt);
            }
        });
        getContentPane().add(nome_livro, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 40, 350, -1));

        jLabel3.setText("Descrição:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 69, -1, -1));
        getContentPane().add(desc_livro, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 69, 350, -1));

        jLabel4.setText("Codigo:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 98, -1, -1));
        getContentPane().add(cod_bar_livro, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 98, 350, -1));

        jLabel5.setText("P. Custo:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));
        getContentPane().add(p_custo_livro, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 127, 104, -1));

        jLabel6.setText("P. Venda:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 130, -1, -1));
        getContentPane().add(p_emprestimo_livro, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 127, 180, -1));
        getContentPane().add(pesquisa_nome_fornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 270, -1));

        jLabel7.setText("Fornecedor:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        btnPesquisaFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/pesquisar.png"))); // NOI18N
        btnPesquisaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaFornecedorActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisaFornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 160, 40, 40));

        jtl_consultar_livro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_livro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_livroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_livro);
        if (jtl_consultar_livro.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_livro.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_livro.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 558, 240));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Consulta");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, -1, -1));

        jLabel9.setText("Nome:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, -1, -1));

        btnPesquisaLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/pesquisar.png"))); // NOI18N
        btnPesquisaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaLivroActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisaLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 40, 40, 40));
        getContentPane().add(pesquisa_nome_livro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 450, -1));

        jtl_consultar_fornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_fornecedor);
        if (jtl_consultar_fornecedor.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_fornecedor.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_fornecedor.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 420, 120));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, -1, -1));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_livroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_livroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_livroActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(gravar_alterar==1){
            gravar();
            gravar_alterar=0;
        }
        else{
            if(gravar_alterar==2){
                alterar();
                gravar_alterar=0;
            }
            else{
                JOptionPane.showMessageDialog(null, "erro no sistema!!!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_livro.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_livro.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaFornecedorActionPerformed
        preencheTabelaFornecedor(pesquisa_nome_fornecedor.getText());
    }//GEN-LAST:event_btnPesquisaFornecedorActionPerformed

    private void btnPesquisaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaLivroActionPerformed
        preencheTabelaLivro(pesquisa_nome_livro.getText());
    }//GEN-LAST:event_btnPesquisaLivroActionPerformed

    private void jtl_consultar_livroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_livroMouseClicked
        preencheCamposLivro(Integer.parseInt(String.valueOf(
        jtl_consultar_livro.getValueAt(
        jtl_consultar_livro.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_livroMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JToggleButton btnPesquisaFornecedor;
    private javax.swing.JToggleButton btnPesquisaLivro;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cod_bar_livro;
    private javax.swing.JTextField desc_livro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtl_consultar_fornecedor;
    private javax.swing.JTable jtl_consultar_livro;
    private javax.swing.JTextField nome_livro;
    private javax.swing.JTextField p_custo_livro;
    private javax.swing.JTextField p_emprestimo_livro;
    private javax.swing.JTextField pesquisa_nome_fornecedor;
    private javax.swing.JTextField pesquisa_nome_livro;
    // End of variables declaration//GEN-END:variables
}

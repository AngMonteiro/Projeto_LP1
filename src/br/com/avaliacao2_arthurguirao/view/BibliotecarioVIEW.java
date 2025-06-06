package br.com.avaliacao2_arthurguirao.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.avaliacao2_arthurguirao.dto.BibliotecarioDTO;
import br.com.avaliacao2_arthurguirao.ctr.BibliotecarioCTR;

public class BibliotecarioVIEW extends javax.swing.JInternalFrame {

    BibliotecarioDTO bibliotecarioDTO = new BibliotecarioDTO();
    BibliotecarioCTR bibliotecarioCTR = new BibliotecarioCTR();

    int gravar_alterar;

    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_bibliotecario;
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public BibliotecarioVIEW() {
        initComponents();
        
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        
        modelo_jtl_consultar_bibliotecario = (DefaultTableModel) jtl_consultar_bibliotecario.getModel();
    }
    
    private void gravar(){
        try{
            bibliotecarioDTO.setNome_bib(nome_bib.getText());
            bibliotecarioDTO.setCpf_bib(cpf_bib.getText());
            bibliotecarioDTO.setLogin_bib(login_bib.getText());
            bibliotecarioDTO.setSenha_bib(String.valueOf(senha_bib.getPassword()));
            bibliotecarioDTO.setTipo_bib(tipo_bib.getSelectedItem().toString());

            JOptionPane.showMessageDialog(null,
                bibliotecarioCTR.inserirBibliotecario(bibliotecarioDTO)
            );
        }
        catch(Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }
    
    private void alterar(){
        try{
            bibliotecarioDTO.setNome_bib(nome_bib.getText());
            bibliotecarioDTO.setCpf_bib(cpf_bib.getText());
            bibliotecarioDTO.setLogin_bib(login_bib.getText());
            bibliotecarioDTO.setTipo_bib(tipo_bib.getSelectedItem().toString());

            if((checkAlterarSenha.isSelected()) && (senha_bib.getPassword().length != 0)){
                bibliotecarioDTO.setSenha_bib(String.valueOf(senha_bib.getPassword()));
            }
            else{
                bibliotecarioDTO.setSenha_bib(null);
            } 
            
            JOptionPane.showMessageDialog(null, bibliotecarioCTR. alterarBibliotecario(bibliotecarioDTO));
        } catch (Exception e) {
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }
    
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Bibliotecario?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,
                bibliotecarioCTR.excluirBibliotecario(bibliotecarioDTO)
            );
        }
    }
    
    private void liberaCampos(boolean a){
        nome_bib.setEnabled(a);
        cpf_bib.setEnabled(a);
        login_bib.setEnabled(a);
        tipo_bib.setEnabled(a);
        senha_bib.setEnabled(a);
        checkAlterarSenha.setEnabled(a);
    }
    
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    
    private void limpaCampos(){
        nome_bib.setText("");
        cpf_bib.setText("");
        login_bib.setText("");
        senha_bib.setText("");
        checkAlterarSenha.setSelected(false);
    }
    
    private void preencheTabela(String nome_bib){
        try{
            modelo_jtl_consultar_bibliotecario.setNumRows(0);
            bibliotecarioDTO.setNome_bib(nome_bib);
            rs = bibliotecarioCTR.consultarBibliotecario(bibliotecarioDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_bibliotecario.addRow(new Object[]{
                    rs.getString("id_bib"),
                    rs.getString("nome_bib")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro SQL: " + e);
        } finally {
            bibliotecarioCTR.CloseDB();
        }
    }
    
    private void preencheCampos(int id_bib) {
        try {
            bibliotecarioDTO.setId_bib(id_bib);
            rs = bibliotecarioCTR.consultarBibliotecario(bibliotecarioDTO, 2);
            
            if(rs.next()){
                limpaCampos();

                nome_bib.setText(rs.getString("nome_bib"));
                cpf_bib.setText(rs.getString("cpf_bib"));
                login_bib.setText(rs.getString("login_bib"));
                tipo_bib.setSelectedItem(rs.getString("tipo_bib"));

                gravar_alterar = 2;
                liberaCampos(true);
                senha_bib.setEnabled(false);
                checkAlterarSenha.setSelected(false);
            }
        } catch (Exception e) {
            System.out.println("Erro SQL: " + e);   
        } finally {
            bibliotecarioCTR.CloseDB();
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nome_bib = new javax.swing.JTextField();
        cpf_bib = new javax.swing.JTextField();
        login_bib = new javax.swing.JTextField();
        tipo_bib = new javax.swing.JComboBox<>();
        checkAlterarSenha = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pesquisa_nome_bib = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_bibliotecario = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        senha_bib = new javax.swing.JPasswordField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Bibliotecário");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 76, -1, -1));

        jLabel3.setText("CPF:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 113, -1, -1));

        jLabel4.setText("Login:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 153, -1, -1));

        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 194, -1, -1));

        jLabel6.setText("Tipo:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 236, -1, -1));
        getContentPane().add(nome_bib, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 70, 324, -1));
        getContentPane().add(cpf_bib, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 110, 134, -1));
        getContentPane().add(login_bib, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 150, 324, -1));

        tipo_bib.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "COMUM" }));
        getContentPane().add(tipo_bib, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 233, 133, -1));

        checkAlterarSenha.setText("Alterar Senha");
        checkAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkAlterarSenhaMouseClicked(evt);
            }
        });
        checkAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAlterarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(checkAlterarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 190, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Consulta");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, -1, -1));

        jLabel8.setText("Nome:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, -1, -1));

        pesquisa_nome_bib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_bibActionPerformed(evt);
            }
        });
        getContentPane().add(pesquisa_nome_bib, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 187, -1));

        jtl_consultar_bibliotecario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_bibliotecario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_bibliotecarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_bibliotecario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 328, 137));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, -1));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, -1));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 43, 41));
        getContentPane().add(senha_bib, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAlterarSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkAlterarSenhaActionPerformed

    private void pesquisa_nome_bibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_bibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_bibActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        checkAlterarSenha.setEnabled(false);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        preencheTabela(pesquisa_nome_bib.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_bibliotecarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_bibliotecarioMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(
            jtl_consultar_bibliotecario.getValueAt(
                jtl_consultar_bibliotecario.getSelectedRow(), 0))));

        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_bibliotecarioMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_bibliotecario.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_bibliotecario.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar=0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void checkAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAlterarSenhaMouseClicked
        if(checkAlterarSenha.isSelected()){
            senha_bib.setEnabled(true);
        }
        else{
            senha_bib.setEnabled(false);
            senha_bib.setText(null);
        }
    }//GEN-LAST:event_checkAlterarSenhaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox checkAlterarSenha;
    private javax.swing.JTextField cpf_bib;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_bibliotecario;
    private javax.swing.JTextField login_bib;
    private javax.swing.JTextField nome_bib;
    private javax.swing.JTextField pesquisa_nome_bib;
    private javax.swing.JPasswordField senha_bib;
    private javax.swing.JComboBox<String> tipo_bib;
    // End of variables declaration//GEN-END:variables
}

package br.com.avaliacao2_arthurguirao.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.avaliacao2_arthurguirao.dto.AlunoDTO;
import br.com.avaliacao2_arthurguirao.ctr.AlunoCTR;
import br.com.avaliacao2_arthurguirao.dao.ConexaoDAO;

public class AlunoVIEW extends javax.swing.JInternalFrame {
    
    AlunoDTO alunoDTO = new AlunoDTO();
    AlunoCTR alunoCTR = new AlunoCTR();

    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_cliente;
    
    public AlunoVIEW() {
        initComponents();
        
        liberaCampos(false);
        liberaBotoes(true, false,false,false,true);
        modelo_jtl_consultar_cliente = (DefaultTableModel) jtl_consultar_aluno.getModel();  
    }
    
    private void preencherTabela(String nome_alu){
        try{
            modelo_jtl_consultar_cliente.setNumRows(0);
            alunoDTO.setNome_alu(nome_alu);
            rs = alunoCTR.consultarAluno(alunoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_cliente.addRow(new Object[]{
                    rs.getString("id_alu"),
                    rs.getString("nome_alu"),
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }
        finally{
            alunoCTR.CloseDB();
        }
    }

    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar(){
            try{
                alunoDTO.setNome_alu(nome_aluno.getText());
                alunoDTO.setLogradouro_alu(logradouro_aluno.getText());
                alunoDTO.setNumero_alu(Integer.parseInt(numero_aluno.getText()));
                alunoDTO.setBairro_alu(bairro_aluno.getText());
                alunoDTO.setCidade_alu(cidade_aluno.getText());
                alunoDTO.setEstado_alu(estado_aluno.getSelectedItem().toString());
                alunoDTO.setCep_alu(cep_aluno.getText());
                alunoDTO.setCpf_alu(cpf_aluno.getText());
                alunoDTO.setRg_alu(rg_aluno.getText());

                JOptionPane.showMessageDialog(null,
                    alunoCTR.inserirAluno(alunoDTO)
                );
            }
            catch(Exception e){
                System.out.println("Erro ao Gravar" + e.getMessage());
            }
    }
    
    private void alterar() {
        try {
            alunoDTO.setNome_alu(nome_aluno.getText());
            alunoDTO.setLogradouro_alu(logradouro_aluno.getText());
            alunoDTO.setNumero_alu(Integer.parseInt(numero_aluno.getText()));
            alunoDTO.setBairro_alu(bairro_aluno.getText());
            alunoDTO.setCidade_alu(cidade_aluno.getText());
            alunoDTO.setEstado_alu(estado_aluno.getSelectedItem().toString());
            alunoDTO.setCep_alu(cep_aluno.getText());
            alunoDTO.setCpf_alu(cpf_aluno.getText());
            alunoDTO.setRg_alu(rg_aluno.getText());

            JOptionPane.showMessageDialog(null,
                alunoCTR.alterarAluno(alunoDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Alterar: " + e.getMessage());
        }
    }

    
    private void excluir(){
        if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Cliente?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, alunoCTR.excluirAluno(alunoDTO));
        }
    }

    private void liberaCampos(boolean a){
        nome_aluno.setEnabled(a);
        logradouro_aluno.setEnabled(a);
        numero_aluno.setEnabled(a);
        bairro_aluno.setEnabled(a);
        cidade_aluno.setEnabled(a);
        estado_aluno.setEnabled(a);
        cep_aluno.setEnabled(a);
        cpf_aluno.setEnabled(a);
        rg_aluno.setEnabled(a);
    }
    
    private void limpaCampos(){
        nome_aluno.setText("");
        logradouro_aluno.setText("");
        numero_aluno.setText("");
        bairro_aluno.setText("");
        cidade_aluno.setText("");
        cep_aluno.setText("");
        cpf_aluno.setText("");
        rg_aluno.setText("");
    }
    
    private void liberaBotoes(boolean a,boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    
    private void preencheCampos(int id_alu){
    try{
        alunoDTO.setId_alu(id_alu);
        rs = alunoCTR.consultarAluno(alunoDTO,2);
        if(rs.next()){
            limpaCampos();
            nome_aluno.setText(rs.getString("nome_alu"));
            logradouro_aluno.setText(rs.getString("logradouro_alu"));
            numero_aluno.setText(rs.getString("numero_alu"));
            bairro_aluno.setText(rs.getString("bairro_alu"));
            cidade_aluno.setText(rs.getString("cidade_alu"));
            estado_aluno.setSelectedItem(rs.getString("estado_alu"));
            cep_aluno.setText(rs.getString("cep_alu"));
            cpf_aluno.setText(rs.getString("cpf_alu"));
            rg_aluno.setText(rs.getString("rg_alu"));

            gravar_alterar = 2;
            liberaCampos(true);
        }
    }
    catch(Exception erTab){
        System.out.println("Erro SQL: "+erTab);
    }
    finally{
        alunoCTR.CloseDB();
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

        label_0 = new javax.swing.JLabel();
        label_1 = new javax.swing.JLabel();
        nome_aluno = new javax.swing.JTextField();
        label_2 = new javax.swing.JLabel();
        label_3 = new javax.swing.JLabel();
        label_4 = new javax.swing.JLabel();
        logradouro_aluno = new javax.swing.JTextField();
        numero_aluno = new javax.swing.JTextField();
        cidade_aluno = new javax.swing.JTextField();
        cep_aluno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bairro_aluno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        estado_aluno = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cpf_aluno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rg_aluno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pesquisa_nome_aluno = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_aluno = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_0.setText("Nome:");
        getContentPane().add(label_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        label_1.setText("Logradouro:");
        getContentPane().add(label_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        nome_aluno.setNextFocusableComponent(logradouro_aluno);
        nome_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_alunoActionPerformed(evt);
            }
        });
        getContentPane().add(nome_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 85, 400, -1));

        label_2.setText("Número:");
        getContentPane().add(label_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        label_3.setText("Cidade:");
        getContentPane().add(label_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        label_4.setText("CEP:");
        getContentPane().add(label_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        logradouro_aluno.setNextFocusableComponent(numero_aluno);
        logradouro_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logradouro_alunoActionPerformed(evt);
            }
        });
        getContentPane().add(logradouro_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 119, 400, -1));

        numero_aluno.setNextFocusableComponent(bairro_aluno);
        getContentPane().add(numero_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 154, 75, -1));

        cidade_aluno.setNextFocusableComponent(cep_aluno);
        getContentPane().add(cidade_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 185, 270, -1));

        cep_aluno.setNextFocusableComponent(cpf_aluno);
        getContentPane().add(cep_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 216, 101, -1));

        jLabel1.setText("Bairro:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 157, -1, -1));

        bairro_aluno.setNextFocusableComponent(cidade_aluno);
        getContentPane().add(bairro_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 154, 276, -1));

        jLabel2.setText("Estado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        estado_aluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "pB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        estado_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_alunoActionPerformed(evt);
            }
        });
        getContentPane().add(estado_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 70, -1));

        jLabel3.setText("CPF:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        cpf_aluno.setNextFocusableComponent(rg_aluno);
        getContentPane().add(cpf_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 100, -1));

        jLabel4.setText("RG:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        rg_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rg_alunoActionPerformed(evt);
            }
        });
        getContentPane().add(rg_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Aluno");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 190, -1));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 100, 30));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 110, 30));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 110, 30));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 120, 30));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, 120, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Consulta");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, -1));

        jLabel7.setText("Nome:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, -1, -1));

        pesquisa_nome_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_alunoActionPerformed(evt);
            }
        });
        getContentPane().add(pesquisa_nome_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 250, -1));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 40, 40));

        jtl_consultar_aluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_alunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_aluno);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 366, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_alunoActionPerformed

    private void logradouro_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logradouro_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logradouro_alunoActionPerformed

    private void estado_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_alunoActionPerformed

    private void rg_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rg_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rg_alunoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
                liberaBotoes(false,true,true,false,true);
                gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(gravar_alterar==1){
            gravar();
            gravar_alterar=0;
        }
        else{
            if(gravar_alterar == 2) {
                alterar();
                gravar_alterar = 0;
            }
            else {
                JOptionPane.showMessageDialog(null, "Erro no Sistemaa!!!");
            }
        }
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true,false,false,false,true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void pesquisa_nome_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_alunoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        // TODO add your handling code here:
        preencherTabela(pesquisa_nome_aluno.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_alunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_alunoMouseClicked
        // TODO add your handling code here:
        preencheCampos(Integer.parseInt(String.valueOf(
        jtl_consultar_aluno.getValueAt(
        jtl_consultar_aluno.getSelectedRow(), 0))));
        liberaBotoes(false,true,true,true,true);
    }//GEN-LAST:event_jtl_consultar_alunoMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cliente.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar=0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_cliente.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_aluno;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cep_aluno;
    private javax.swing.JTextField cidade_aluno;
    private javax.swing.JTextField cpf_aluno;
    private javax.swing.JComboBox<String> estado_aluno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_aluno;
    private javax.swing.JLabel label_0;
    private javax.swing.JLabel label_1;
    private javax.swing.JLabel label_2;
    private javax.swing.JLabel label_3;
    private javax.swing.JLabel label_4;
    private javax.swing.JTextField logradouro_aluno;
    private javax.swing.JTextField nome_aluno;
    private javax.swing.JTextField numero_aluno;
    private javax.swing.JTextField pesquisa_nome_aluno;
    private javax.swing.JTextField rg_aluno;
    // End of variables declaration//GEN-END:variables
}

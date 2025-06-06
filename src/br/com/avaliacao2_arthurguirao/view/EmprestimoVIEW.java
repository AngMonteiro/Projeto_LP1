package br.com.avaliacao2_arthurguirao.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.avaliacao2_arthurguirao.dto.EmprestimoDTO;
import br.com.avaliacao2_arthurguirao.ctr.EmprestimoCTR;
import br.com.avaliacao2_arthurguirao.dto.LivroDTO;
import br.com.avaliacao2_arthurguirao.ctr.LivroCTR;
import br.com.avaliacao2_arthurguirao.dto.AlunoDTO;
import br.com.avaliacao2_arthurguirao.ctr.AlunoCTR;

import java.util.Date;

public class EmprestimoVIEW extends javax.swing.JInternalFrame {

    EmprestimoCTR emprestimoCTR = new EmprestimoCTR(); 
    EmprestimoDTO emprestimoDTO = new EmprestimoDTO(); 
    LivroCTR livroCTR = new LivroCTR(); 
    LivroDTO livroDTO = new LivroDTO(); 
    AlunoDTO alunoDTO = new AlunoDTO(); 
    AlunoCTR alunoCTR = new AlunoCTR(); 

    ResultSet rs; // Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_aluno; // Variável para guardar o modelo da tabela
    DefaultTableModel modelo_jtl_consultar_livro; // Variável para guardar o modelo da tabela
    DefaultTableModel modelo_jtl_consultar_livro_selecionado; // Variável para guardar o modelo da tabela
    
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    //Fecha método setPosicao()
    
    
    public void gravar() {
        emprestimoDTO.setDat_emprestimo(new Date());
        emprestimoDTO.setVal_emprestimo(Double.parseDouble(TotalEmprestimo.getText()));
        alunoDTO.setId_alu(Integer.parseInt(String.valueOf(jtl_consultar_aluno.getValueAt(jtl_consultar_aluno.getSelectedRow(), 0))));
        
        JOptionPane.showMessageDialog(null, 
            emprestimoCTR.inserirEmprestimo(emprestimoDTO, alunoDTO, jtl_consultar_livro_selecionado));
    }
    
    private void preencheTabelaAluno(String nome_alu){
        try{
            // Limpa todas as linhas
            modelo_jtl_consultar_aluno.setNumRows(0);

            alunoDTO.setNome_alu(nome_alu);
            rs = alunoCTR.consultarAluno(alunoDTO, 1);
            // Enquanto tiver linhas - faça
            while(rs.next()){
                modelo_jtl_consultar_aluno.addRow(new Object[]{
                    rs.getString("id_alu"),
                    rs.getString("nome_alu")
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: Aluno " + erTab);
        }
    } // Fecha método preencheTabelaAluno()
    
    private void preencheTabelaLivro(String nome_livro){
        try{
            // Limpa todas as linhas
            modelo_jtl_consultar_livro.setNumRows(0);

            livroDTO.setNome_livro(nome_livro);
            rs = livroCTR.consultarLivro(livroDTO, 1);
            // Enquanto tiver linhas - faça
            while(rs.next()){
                modelo_jtl_consultar_livro.addRow(new Object[]{
                    rs.getString("id_livro"),
                    rs.getString("nome_livro"),
                    rs.getDouble("p_emprestimo_livro")
                });
            }
        } catch(Exception erTab){
            System.out.println("Erro SQL: Produto " + erTab);
        }
    }
    // Fecha método preencheTabelaProduto(String nome_prod)
    
    private void adicionaProdutoSelecionado(int id_livro, String nome_livro, double p_emprestimo_livro){
        try{
            modelo_jtl_consultar_livro_selecionado.addRow(new Object[]{
                id_livro,
                nome_livro,
                p_emprestimo_livro
            });
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }
    } // Fecha método adicionaProdutoSelecionado()
    
    private void removeProdutoSelecionado(int linha_selecionada){
        try{
            if(linha_selecionada >= 0){
                modelo_jtl_consultar_livro_selecionado.removeRow(linha_selecionada);
                calculaTotalEmprestimo();
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
    } // Fecha método removeProdutoSelecionado()

    private void calculaTotalEmprestimo(){
        try{
            double total = 0;
            for(int cont = 0; cont < jtl_consultar_livro_selecionado.getRowCount(); cont++){
                total += (Double.parseDouble(String.valueOf(
                    jtl_consultar_livro_selecionado.getValueAt(cont, 2))) *
                    Integer.parseInt(String.valueOf(
                    jtl_consultar_livro_selecionado.getValueAt(cont, 3))));
            }
            TotalEmprestimo.setText(String.valueOf(total));
        } catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
    } // Fecha método calculaTotalEmprestimo()
    
    private void liberaCampos(boolean a){
        pesquisa_nome_aluno.setEnabled(a);
        btnPesquisarAluno.setEnabled(a);
        jtl_consultar_aluno.setEnabled(a);
        pesquisa_nome_livro.setEnabled(a);
        btnPesquisarLivro.setEnabled(a);
        jtl_consultar_livro.setEnabled(a);
        btnProAdd.setEnabled(a);
        btnProRem.setEnabled(a);
        jtl_consultar_livro_selecionado.setEnabled(a);
        TotalEmprestimo.setText("0.00");
    } // Fecha método liberaCampos(boolean a)
    
    private void limpaCampos(){
        pesquisa_nome_aluno.setText("");
        modelo_jtl_consultar_aluno.setNumRows(0);
        pesquisa_nome_livro.setText("");
        modelo_jtl_consultar_aluno.setNumRows(0);
        modelo_jtl_consultar_livro_selecionado.setNumRows(0);
    } // Fecha método limpaCampos()
    
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnSair.setEnabled(d);
    } // Fecha método liberaBotoes(boolean a, boolean b, boolean c, boolean d)
    
    private boolean verificaPreenchimento() {
        if(jtl_consultar_aluno.getSelectedRowCount() <= 0){
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um Aluno");
            jtl_consultar_aluno.requestFocus();
            return false;
        } else {
            if(jtl_consultar_livro_selecionado.getRowCount() <= 0){
                JOptionPane.showMessageDialog(null, "É necessário adicionar pelo menos um produto no pedido");
                jtl_consultar_livro_selecionado.requestFocus();
                return false;
            }

            int verifica = 0;
            for(int cont = 0; cont < jtl_consultar_livro_selecionado.getRowCount(); cont++){
                if(String.valueOf(jtl_consultar_livro_selecionado.getValueAt(cont, 3)).equalsIgnoreCase("null")){
                    verifica++;
                }
            }
            if(verifica > 0){
                JOptionPane.showMessageDialog(null, "A quantidade de cada produto vendido deve ser informado");
                jtl_consultar_livro_selecionado.requestFocus();
                return false;
            } else {
                return true;
            }
        }
    } // Fecha método verificaPreenchimento()
    
    //private void jtl_consultar_pro_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {
        
        // Testa se a tecla Enter foi pressionada
       
   // }
    
    public EmprestimoVIEW() {
        initComponents();
        
        liberaCampos(false);

        // Chama o método liberaBotoes
        liberaBotoes(true, false, false, true);

        // Atribui um modelo para manipular a tabela
        modelo_jtl_consultar_aluno = (DefaultTableModel) jtl_consultar_aluno.getModel();
        modelo_jtl_consultar_livro = (DefaultTableModel) jtl_consultar_livro.getModel();
        modelo_jtl_consultar_livro_selecionado = (DefaultTableModel) jtl_consultar_livro_selecionado.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pesquisa_nome_aluno = new javax.swing.JTextField();
        btnPesquisarAluno = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_aluno = new javax.swing.JTable();
        TotalEmprestimo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pesquisa_nome_livro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_livro_selecionado = new javax.swing.JTable();
        btnPesquisarLivro = new javax.swing.JButton();
        btnProAdd = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_livro = new javax.swing.JTable();
        btnProRem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Cliente:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        pesquisa_nome_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_alunoActionPerformed(evt);
            }
        });
        jPanel1.add(pesquisa_nome_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 280, -1));

        btnPesquisarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarAlunoActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisarAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 50, 40));

        jtl_consultar_aluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jScrollPane1.setViewportView(jtl_consultar_aluno);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 410, 220));

        TotalEmprestimo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalEmprestimo.setText("0.00");
        jPanel1.add(TotalEmprestimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Valor: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        jLabel5.setText("Descrição:");

        jtl_consultar_livro_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Valor", "QTD"
            }
        ));
        jtl_consultar_livro_selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_livro_selecionadoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_livro_selecionado);

        btnPesquisarLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarLivroActionPerformed(evt);
            }
        });

        btnProAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/prod_add.png"))); // NOI18N
        btnProAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProAddActionPerformed(evt);
            }
        });

        jtl_consultar_livro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ));
        jScrollPane3.setViewportView(jtl_consultar_livro);

        btnProRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/prod_rem.png"))); // NOI18N
        btnProRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProRemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pesquisa_nome_livro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnProAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnProRem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(pesquisa_nome_livro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProRem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao2_arthurguirao/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(btnNovo)
                .addGap(62, 62, 62)
                .addComponent(btnSalvar)
                .addGap(69, 69, 69)
                .addComponent(btnCancelar)
                .addGap(47, 47, 47)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo)
                    .addComponent(btnCancelar)
                    .addComponent(btnSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Empréstimo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(438, 438, 438)
                    .addComponent(jLabel2)
                    .addContainerGap(477, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jLabel2)
                    .addContainerGap(476, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisa_nome_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_alunoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false,true,true,true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_aluno.setNumRows(0);
        modelo_jtl_consultar_livro.setNumRows(0);
        liberaBotoes(true, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarAlunoActionPerformed
        preencheTabelaAluno(pesquisa_nome_aluno.getText());
    }//GEN-LAST:event_btnPesquisarAlunoActionPerformed

    private void btnPesquisarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarLivroActionPerformed
        preencheTabelaLivro(pesquisa_nome_livro.getText());
    }//GEN-LAST:event_btnPesquisarLivroActionPerformed

    private void btnProAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProAddActionPerformed
        adicionaProdutoSelecionado(
            Integer.parseInt(String.valueOf(jtl_consultar_livro.getValueAt(
                jtl_consultar_livro.getSelectedRow(), 0))),
            String.valueOf(jtl_consultar_livro.getValueAt(jtl_consultar_livro.getSelectedRow(), 1)),
            Double.parseDouble(String.valueOf(jtl_consultar_livro.getValueAt(
                jtl_consultar_livro.getSelectedRow(), 2)))
        );
    }//GEN-LAST:event_btnProAddActionPerformed

    private void btnProRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProRemActionPerformed
        removeProdutoSelecionado(jtl_consultar_livro_selecionado.getSelectedRow());
    }//GEN-LAST:event_btnProRemActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(verificaPreenchimento()){
            gravar();
            limpaCampos();
            liberaCampos(false);
            liberaBotoes(true, false, false, true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_livro_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_livro_selecionadoKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            calculaTotalEmprestimo();
        }
    }//GEN-LAST:event_jtl_consultar_livro_selecionadoKeyReleased

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalEmprestimo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarAluno;
    private javax.swing.JButton btnPesquisarLivro;
    private javax.swing.JButton btnProAdd;
    private javax.swing.JButton btnProRem;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtl_consultar_aluno;
    private javax.swing.JTable jtl_consultar_livro;
    private javax.swing.JTable jtl_consultar_livro_selecionado;
    private javax.swing.JTextField pesquisa_nome_aluno;
    private javax.swing.JTextField pesquisa_nome_livro;
    // End of variables declaration//GEN-END:variables
}

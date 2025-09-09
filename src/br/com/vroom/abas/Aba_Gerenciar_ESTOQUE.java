/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.vroom.abas;

import java.sql.*;
import br.com.vroom.dal_cad.Principal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// alinha abaixo importa recursos da biblioteca rs2xml
import net.proteanit.sql.DbUtils;

/**
 * Janela interna para gerenciar estoque. Esta classe permite ao usuário
 * adicionar, editar, remover e pesquisar veiculos no banco de dados. Ela se
 * integra com o módulo de conexão ao banco de dados e utiliza a biblioteca
 * rs2xml.jar para exibir os resultados das consultas em uma tabela. Os métodos
 * incluídos nesta classe permitem realizar operações CRUD (Create, Read,
 * Update, Delete) sobre os dados dos clientes.
 */
public class Aba_Gerenciar_ESTOQUE extends javax.swing.JInternalFrame {

    //usando variavel conexao do DAL(CAD)
    Connection conexao = null;
    /*
    PreparedStatement e ResultSet são frameworks do package java.sql
    Eles servem para preparar e executar as instruções SQL
     */
    PreparedStatement pst = null;
    ResultSet rs = null;

    /*
     * Construtor para a classe Aba_Gerenciar_ESTOQUE. Inicializa os componentes e estabelece uma conexão com o banco de dados.
     */
    public Aba_Gerenciar_ESTOQUE() {
        initComponents();
        conexao = Principal.conector();
    }

    /**
     * método para pesquisar veiculos pela marca
     */
    private void pesquisar_estoque() {
        String slq = "select * from tabveiculos where Marca like ?";
        try {
            pst = conexao.prepareStatement(slq);
            // Passa o conteúdo da caixa de pesquisa para o ? e adiciona o %
            pst.setString(1, txt_E_PESQUISAR.getText() + "%");
            rs = pst.executeQuery();
            // Usa a biblioteca importada (rs2xml) para preencher a tabela com os resultados da consulta
            tbl_Estoque.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Preenche os campos do formulário com o conteúdo da tabela.
     */
    public void preencher_campos() {
        int pree = tbl_Estoque.getSelectedRow();
        txt_id.setText(tbl_Estoque.getModel().getValueAt(pree, 0).toString());
        txt_marca.setText(tbl_Estoque.getModel().getValueAt(pree, 1).toString());
        txt_modelo.setText(tbl_Estoque.getModel().getValueAt(pree, 2).toString());
        txt_ano.setText(tbl_Estoque.getModel().getValueAt(pree, 3).toString());
        txt_renavam.setText(tbl_Estoque.getModel().getValueAt(pree, 4).toString());
        txt_placa.setText(tbl_Estoque.getModel().getValueAt(pree, 5).toString());
        txt_cor.setText(tbl_Estoque.getModel().getValueAt(pree, 6).toString());
        txt_valor.setText(tbl_Estoque.getModel().getValueAt(pree, 7).toString());

    }

    /**
     * Remove um veiculo do banco de dados.
     */
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este veículo?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tabveiculos where idveiculo=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_id.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Veículo removido com sucesso!");
                txt_id.setText(null);
                txt_marca.setText(null);
                txt_modelo.setText(null);
                txt_ano.setText(null);
                txt_renavam.setText(null);
                txt_placa.setText(null);
                txt_cor.setText(null);
                txt_valor.setText(null);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * Altera os dados de um veiculo no banco de dados.
     */
    private void alterar() {
        String sql = "UPDATE tabveiculos set Marca=?, Modelo=?, Ano=?, RENAVAM=?, Placa=?, Cor=?, valor=? where idveiculo=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(1, txt_marca.getText());
            pst.setString(2, txt_modelo.getText());
            pst.setString(3, txt_ano.getText());
            pst.setString(4, txt_renavam.getText());
            pst.setString(5, txt_placa.getText());
            pst.setString(6, txt_cor.getText());
            pst.setString(7, txt_valor.getText());
            pst.setString(8, txt_id.getText());

            // a linha abaixo atualiza a tabusuarios com os dados inseridos na Aba_Gerenciar_ESTOQUE
            // a estrutura abaixo confirma a inserção(insert into) dos dados na tabveiculos
            int alterado = pst.executeUpdate();
            // Confirma a alteração (UPDATE) dos dados na tabveiculos
            if (alterado > 0) {
                JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");
                /**
                 * Limpa os campos do formulário.
                 */
                txt_marca.setText(null);
                txt_modelo.setText(null);
                txt_ano.setText(null);
                txt_renavam.setText(null);
                txt_placa.setText(null);
                txt_cor.setText(null);
                txt_valor.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Estoque = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_E_PESQUISAR = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_modelo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ano = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_cor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_renavam = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_placa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_valor = new javax.swing.JTextField();
        but_C_UPDATE = new javax.swing.JButton();
        but_C_DELETE = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        tbl_Estoque = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbl_Estoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "marca", "modelo", "ano", "renavam", "placa", "cor", "valor"
            }
        ));
        tbl_Estoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_EstoqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Estoque);

        jLabel1.setText("Buscar pela Marca");

        txt_E_PESQUISAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_E_PESQUISARActionPerformed(evt);
            }
        });
        txt_E_PESQUISAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_E_PESQUISARKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/MIDDLE_45X45_Search.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Modelo");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Marca");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Ano");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Cor");

        txt_cor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_corActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Renavam");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setText("Placa");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Valor");

        but_C_UPDATE.setBackground(new java.awt.Color(204, 204, 204));
        but_C_UPDATE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/edit_icon_1.png"))); // NOI18N
        but_C_UPDATE.setToolTipText("EDITAR");
        but_C_UPDATE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_C_UPDATE.setMinimumSize(new java.awt.Dimension(80, 80));
        but_C_UPDATE.setPreferredSize(new java.awt.Dimension(80, 80));
        but_C_UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_C_UPDATEActionPerformed(evt);
            }
        });

        but_C_DELETE.setBackground(new java.awt.Color(204, 204, 204));
        but_C_DELETE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/DELETE_ICON (2).png"))); // NOI18N
        but_C_DELETE.setToolTipText("REMOVER");
        but_C_DELETE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_C_DELETE.setMinimumSize(new java.awt.Dimension(80, 80));
        but_C_DELETE.setPreferredSize(new java.awt.Dimension(80, 80));
        but_C_DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_C_DELETEActionPerformed(evt);
            }
        });

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idKeyReleased(evt);
            }
        });

        jLabel2.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txt_E_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cor, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_renavam, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(but_C_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(but_C_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_E_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_renavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(but_C_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_C_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setBounds(0, 0, 753, 495);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_EstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_EstoqueMouseClicked
        preencher_campos();

    }//GEN-LAST:event_tbl_EstoqueMouseClicked

    private void txt_E_PESQUISARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_E_PESQUISARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_E_PESQUISARActionPerformed

    private void txt_E_PESQUISARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_E_PESQUISARKeyReleased
        // chamar o método pesquisar clientes
        pesquisar_estoque();
    }//GEN-LAST:event_txt_E_PESQUISARKeyReleased

    private void txt_corActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_corActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_corActionPerformed

    private void but_C_UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_UPDATEActionPerformed
        alterar();
    }//GEN-LAST:event_but_C_UPDATEActionPerformed

    private void but_C_DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_DELETEActionPerformed
        remover();
    }//GEN-LAST:event_but_C_DELETEActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_C_DELETE;
    private javax.swing.JButton but_C_UPDATE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Estoque;
    private javax.swing.JTextField txt_E_PESQUISAR;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_cor;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_placa;
    private javax.swing.JTextField txt_renavam;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables
}

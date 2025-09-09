/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vroom.dal_cad;

/**
 *
 * @author irene
 */
// DAL - DATA ACCESS LAYER
// CAD - CAMADA DE ACESSO A DADOS
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Principal {

    //código que estabelece a conexão com o bando de dados(bdinfo)
    public static Connection conector() {

        // ARMAZENAMENTO DAS INFORMAÇÕES REFERENTES AO BANCO E ESTABELECIMENTO DA CONEXÃO COM BANCO DE DADOS
        try {
            String url = "jdbc:mysql://localhost:3306/bancoanm_1";
            String user = "host2";
            String password = "%";
            String driver = "jdbc:mysql://localhost:3306/bancoanm_1";
            //Class.forName(driver: "jdbc:mysql://localhost:3306/bancoa3");
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner L = new Scanner(System.in);
        Connection conexao = Principal.conector();
        PreparedStatement pst;
        ResultSet rs;

    }
}

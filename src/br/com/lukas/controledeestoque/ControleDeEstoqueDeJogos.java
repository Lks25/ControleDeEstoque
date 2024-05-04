package br.com.lukas.controledeestoque;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
        //execute o programa aqui
        JOptionPane.showMessageDialog(null, "BEM-VINDO AO CONTROLE DE ESTOQUE!");
        Produto produtos = new Produto();
        produtos.menu();
        
    }    

}

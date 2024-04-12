package br.com.lukas.controledeestoque;

import javax.swing.JOptionPane;

public class ControleDeEstoqueDeJogos {

    public static void main(String[] args) {
        
        //execute o programa aqui
        JOptionPane.showMessageDialog(null, "BEM-VINDO AO CONTROLE DE ESTOQUE!");
        Produto produtos = new Produto();
        produtos.menu();
        
    }    

}

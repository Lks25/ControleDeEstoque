package br.com.lukas.controledeestoque;

public interface Menu {
    
    public abstract void novo();
    public abstract void listarProdutos();
    public abstract void removerProdutos();
    public abstract void entradaEstoque();
    public abstract void saidaEstoque();
    public abstract void sair();
    
}
package br.com.lukas.controledeestoque;

//IMPLEMENTEI O SWING PARA DEIXAR A LÓGICA MAIS VISÍVEL E INTERATIVA:
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//A CLASSE 'Produto' IMPLEMENTA UMA INTERFACE ABSTRATA CHAMADA 'Menu':
public class Produto implements Menu {

    private String[] nomeDoJogo;
    private String[] generoDoJogo;
    private String[] desenvolvedor;
    private int[] classificacaoIndicativa;
    private float[] tamanhoDoJogo;
    private float[] precoDoJogo;
    private int[] estoque;
    private int tamanho;

    /*TODOS OS ARRAYS COMEÇAM COM TAMANHO 10 POR PADRÃO,
    COM VALOR INICIAL 0 OU NULL EM TODAS AS POSIÇÕES,
    PODENDO AUMENTAR O SEU TAMANHO AUTOMÁTICAMENTE ATRAVÉS 
    DE UM MÉTODO CASO SEJA NECESSÁRIO*/
    public Produto() {
        this.nomeDoJogo = new String[10];
        this.generoDoJogo = new String[10];
        this.desenvolvedor = new String[10];
        this.classificacaoIndicativa = new int[10];
        this.tamanhoDoJogo = new float[10];
        this.precoDoJogo = new float[10];
        this.estoque = new int[10];
        this.tamanho = 0;
    }

    /*MÉTODO DO MENU RECURSIVO DO PROGRAMA, ELE É INICIADO COM 5 OPÇÕES
    DE ESCOLHA E É ENCERRADO ATÉ QUE O USUÁRIO ESCOLHA A OPÇÃO 0 OU CLIQUE
    EM CANCELAR OU NO [X] DA JANELA NO CANTO SUPERIOR DIREITO*/
    public void menu() {

        int opcao = 0;
        boolean entradaValidada = false;

        do {
            try {
                String input = JOptionPane.showInputDialog(
                        null,
                        "<html>"
                        + "                   |MENU|       " + "<br>"
                        + "<br>"
                        + "[1] PARA NOVO PRODUTO" + "<br>"
                        + "[2] PARA LISTAR PRODUTOS" + "<br>"
                        + "[3] PARA REMOVER PRODUTOS" + "<br>"
                        + "[4] PARA ENTRADA ESTOQUE" + "<br>"
                        + "[5] PARA SAÍDA ESTOQUE" + "<br>"
                        + "[0] PARA SAIR" + "<br>"
                        + "</html>");

                //VALIDAÇÃO PARA ENCERRAR O PROGRAMA CASO O USUÁRIO CLIQUE EM CANCELAR OU NO [X] DA JANELA DA INTERFACE SWING:
                if (input == null) {
                    this.sair();
                    System.exit(0);
                }

                /*A VARIÁVEL 'opcao' RECEBE O VALOR DA VARIÁVEL 'input' CONVERTIDO EM INTEIRO PARA FAZER A
                VALIDAÇÃO DOS DEMAIS BLOCOS E POSTERIORMENTE SER USADO COMO REFERÊNCIA NO SWITCH CASE*/
                opcao = Integer.parseInt(input);

                if (opcao >= 0) {
                    entradaValidada = true;
                } else {
                    JOptionPane.showMessageDialog(null, "info: ENTRADA INVÁLIDA!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "info: ENTRADA INVÁLIDA!");
            }
        } while (!entradaValidada);

        switch (opcao) {
            case 1:
                this.novo();
                break;
            case 2:
                this.listarProdutos();
                break;
            case 3:
                this.removerProdutos();
                break;
            case 4:
                this.entradaEstoque();
                break;
            case 5:
                this.saidaEstoque();
                break;
            case 0:
                this.sair();
                break;
            default:
                JOptionPane.showMessageDialog(null, "info: OPÇÃO INVÁLIDA!");
                this.menu();
                break;
        }
    }

    /*MÉTODO PARA ATUALIZAR O VALOR DA QUANTIDADE DE ESTOQUE DO PRODUTO COM VALOR 0
    PARA EVITAR NÚMEROS NEGATIVOS NA QUANTIDADE EM ESTOQUE*/
    private void atualizarEstoque() {
        for (int i = 0; i < this.estoque.length; i++) {
            if (this.estoque[i] < 0) {
                this.estoque[i] = 0;
            }
        }
    }

    //MÉTODO PARA AUMENTAR O TAMANHO DOS ARRAYS CASO SEJA NECESSÁRIO:
    private void aumentarCapacidadeDosVetores() {
        if (this.tamanho == this.getNomeDoJogo().length) {
            String[] nomeDoJogo2 = new String[this.getNomeDoJogo().length * 2];
            String[] generoDoJogo2 = new String[this.getGeneroDoJogo().length * 2];
            String[] desenvolvedor2 = new String[this.getDesenvolvedor().length * 2];
            int[] classificacaoIndicativa2 = new int[this.getClassificacaoIndicativa().length * 2];
            float[] tamanhoDoJogo2 = new float[this.getTamanhoDoJogo().length * 2];
            float[] precoDoJogo2 = new float[this.getPrecoDoJogo().length * 2];
            int[] estoque2 = new int[this.getEstoque().length * 2];
            for (int i = 0; i < this.getNomeDoJogo().length; i++) {
                nomeDoJogo2[i] = this.nomeDoJogo[i];
                generoDoJogo2[i] = this.generoDoJogo[i];
                desenvolvedor2[i] = this.desenvolvedor[i];
                classificacaoIndicativa2[i] = this.classificacaoIndicativa[i];
                tamanhoDoJogo2[i] = this.tamanhoDoJogo[i];
                precoDoJogo2[i] = this.precoDoJogo[i];
                estoque2[i] = this.estoque[i];
            }
            this.nomeDoJogo = nomeDoJogo2;
            this.generoDoJogo = generoDoJogo2;
            this.desenvolvedor = desenvolvedor2;
            this.classificacaoIndicativa = classificacaoIndicativa2;
            this.tamanhoDoJogo = tamanhoDoJogo2;
            this.precoDoJogo = precoDoJogo2;
            this.estoque = estoque2;
        }
    }

    /*MÉTODOS ABSTRATOS DA INTERFACE*/
    //MÉTODO PARA ADICIONAR UM NOVO PRODUTO A LISTA DO ESTOQUE:
    @Override
    public void novo() {

        this.aumentarCapacidadeDosVetores();

        //VARIÁVEIS LOCAIS DE VALIDAÇÃO E SUPORTE:
        boolean classificacaoValidada = false;
        boolean tamanhoValidado = false;
        boolean precoValidado = false;

        if (this.tamanho < this.nomeDoJogo.length) {
            String nomeDoJogo;
            boolean nomeValidado;
            do {
                nomeDoJogo = "";
                nomeValidado = false;
                nomeDoJogo = JOptionPane.showInputDialog(null, "INFORME O NOME DO JOGO: ");
                for (int i = 0; i < this.getNomeDoJogo().length; i++) {
                    if (nomeDoJogo.equals(this.getNomeDoJogo()[i])) {
                        JOptionPane.showMessageDialog(null, "info: ESTE NOME JÁ CONSTA NO BANCO DE DADOS DO ESTOQUE!");
                        break;
                    }
                    nomeValidado = true;
                }
            } while (!nomeValidado);
            this.nomeDoJogo[this.tamanho] = nomeDoJogo;
            this.generoDoJogo[this.tamanho] = JOptionPane.showInputDialog(null, "INFORME O GÊNERO DO JOGO: ");
            this.desenvolvedor[this.tamanho] = JOptionPane.showInputDialog(null, "INFORME O DESENVOLVEDOR: ");

            do {
                try {
                    this.classificacaoIndicativa[this.tamanho] = Integer.parseInt(JOptionPane.showInputDialog(null, "CLASSIFICAÇÃO INDICATIVA [10/12/14/16/18]: "));
                    if (this.classificacaoIndicativa[this.tamanho] != 10 && this.classificacaoIndicativa[this.tamanho] != 12 && this.classificacaoIndicativa[this.tamanho] != 14 && this.classificacaoIndicativa[this.tamanho] != 16 && this.classificacaoIndicativa[this.tamanho] != 18) {
                        JOptionPane.showMessageDialog(null, "info: CLASSIFICAÇÃO INVÁLIDA!");
                    } else {
                        classificacaoValidada = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "info: CLASSIFICAÇÃO INVÁLIDA!");
                }
            } while (!classificacaoValidada);

            do {
                try {
                    this.tamanhoDoJogo[this.tamanho] = Float.parseFloat(JOptionPane.showInputDialog(null, "TAMANHO DO JOGO: "));
                    if (this.tamanhoDoJogo[this.tamanho] <= 0) {
                        JOptionPane.showMessageDialog(null, "info: TAMANHO INVÁLIDO!");
                    } else {
                        tamanhoValidado = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "info: TAMANHO INVÁLIDO!");
                }
            } while (!tamanhoValidado);

            do {
                try {
                    this.precoDoJogo[this.tamanho] = Float.parseFloat(JOptionPane.showInputDialog(null, "PREÇO DO JOGO: "));
                    if (this.precoDoJogo[this.tamanho] <= 0) {
                        JOptionPane.showMessageDialog(null, "info: PREÇO INVÁLIDO!");
                    } else {
                        precoValidado = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "info: PREÇO INVÁLIDO!");
                }
            } while (!precoValidado);
            JOptionPane.showMessageDialog(null, "info: NOVO PRODUTO ADICIONADO AO ESTOQUE!");
            this.tamanho++;
        }
        this.menu();
    }

    //MÉTODO PARA MOSTRAR TODOS OS PRODUTOS CADASTRADOS NO ESTOQUE COM TODAS AS SUAS INFORMAÇÕES:
    @Override
    public void listarProdutos() {
        JTextArea textArea = new JTextArea(20, 30);
        Font boldFont = new Font(textArea.getFont().getFontName(), Font.BOLD, textArea.getFont().getSize());
        textArea.setFont(boldFont); // Define a fonte como negrito

        String mensagem = "";
        for (int i = 0; i < this.tamanho; i++) {
            mensagem += "NOME DO JOGO: " + this.getNomeDoJogo()[i] + "\n"
                    + "GÊNERO: " + this.getGeneroDoJogo()[i] + "\n"
                    + "DESENVOLVEDOR: " + this.getDesenvolvedor()[i] + "\n"
                    + "CLASSIFICAÇÃO INDICATIVA: [+" + this.getClassificacaoIndicativa()[i] + "]" + "\n"
                    + "TAMANHO: " + this.getTamanhoDoJogo()[i] + " GB" + "\n"
                    + "PREÇO: " + this.getPrecoDoJogo()[i] + " R$" + "\n"
                    + "EM ESTOQUE: " + this.getEstoque()[i] + "\n\n";
        }
        textArea.setText(mensagem);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "LISTA DE PRODUTOS", JOptionPane.INFORMATION_MESSAGE);
        this.menu();
    }

    //MÉTODO PARA EXCLUIR UM PRODUTO QUE ESTÁ CADASTRADO NO ESTOQUE:
    @Override
    public void removerProdutos() {
        String nomeDoProduto;
        boolean nomeDoProdutoValidado = false;

        nomeDoProduto = JOptionPane.showInputDialog(null, "INFORME O NOME DO PRODUTO PARA REMOVER: ");
        for (int i = 0; i < this.getNomeDoJogo().length; i++) {
            if (nomeDoProduto.equals(nomeDoJogo[i])) {
                nomeDoProdutoValidado = true;
            }
        }

        if (nomeDoProdutoValidado) {
            for (int i = 0; i < this.getNomeDoJogo().length; i++) {
                if (nomeDoProduto.equals(this.nomeDoJogo[i])) {
                    this.nomeDoJogo[i] = null;
                    this.generoDoJogo[i] = null;
                    this.desenvolvedor[i] = null;
                    this.precoDoJogo[i] = 0.00f;
                    this.classificacaoIndicativa[i] = 0;
                    this.tamanhoDoJogo[i] = 0.00f;
                    for (int j = i; j < this.tamanho; j++) {
                        this.nomeDoJogo[j] = this.nomeDoJogo[j + 1];
                        this.generoDoJogo[j] = this.generoDoJogo[j + 1];
                        this.desenvolvedor[j] = this.desenvolvedor[j + 1];
                        this.precoDoJogo[j] = this.precoDoJogo[j + 1];
                        this.classificacaoIndicativa[j] = this.classificacaoIndicativa[j + 1];
                        this.tamanhoDoJogo[j] = this.tamanhoDoJogo[j + 1];
                    }
                }
            }
            this.tamanho--;
        } else {
            JOptionPane.showMessageDialog(null, "info: PRODUTO NÃO LOCALIZADO!");
        }
        this.menu();
    }

    //MÉTODO PARA ADICIONAR QUANTIDADES/UNIDADES A UM PRODUTO CADASTRADO NO ESTOQUE:
    @Override
    public void entradaEstoque() {

        String nomeDoProduto;
        int quantidade = 0;
        boolean quantidadeValidada = false;
        boolean produtoEncontrado = false;

        do {
            try {
                do {
                    quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "INFORME A QUANTIDADE DE ENTRADA: "));
                    if (quantidade <= 0) {
                        JOptionPane.showMessageDialog(null, "info: QUANTIDADE INVÁLIDA!");
                    } else {
                        quantidadeValidada = true;
                    }
                } while (!quantidadeValidada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "info: QUANTIDADE INVÁLIDA!");
            }
        } while (!quantidadeValidada);

        nomeDoProduto = JOptionPane.showInputDialog(null, "INFORME O NOME DO PRODUTO: ");

        for (int i = 0; i < this.tamanho; i++) {
            if (nomeDoProduto.equals(this.nomeDoJogo[i]) && quantidade > 0) {
                this.estoque[i] += quantidade;
                produtoEncontrado = true;
                JOptionPane.showMessageDialog(null, "info: QUANTIDADE INSERIDA COM SUCESSO!");
            }
        }

        if (produtoEncontrado == false && quantidade > 0) {
            JOptionPane.showMessageDialog(null, "info: PRODUTO NÃO ENCONTRADO!");
        }

        this.menu();
    }

    //MÉTODO PARA DIMINUIR QUANTIDADES/UNIDADES DE UM PRODUTO CADASTRADO NO ESTOQUE:
    @Override
    public void saidaEstoque() {

        String nomeDoProduto = "";
        int quantidade = 0;
        boolean quantidadeValidada = false;
        boolean produtoEncontrado = false;

        do {
            try {
                quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "INFORME A QUANTIDADE DE SAÍDA: "));
                if (quantidade < 0) {
                    JOptionPane.showMessageDialog(null, "info: QUANTIDADE INVÁLIDA!");
                } else {
                    quantidadeValidada = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "info: QUANTIDADE INVÁLIDA!");
            }
        } while (!quantidadeValidada);

        nomeDoProduto = JOptionPane.showInputDialog(null, "INFORME O NOME DO PRODUTO: ");

        for (int i = 0; i < this.tamanho; i++) {
            if (nomeDoProduto.equals(this.nomeDoJogo[i]) && quantidade > 0 && quantidade <= this.getEstoque()[i]) {
                this.estoque[i] -= quantidade;
                produtoEncontrado = true;
                JOptionPane.showMessageDialog(null, "info: QUANTIDADE RETIRADA COM SUCESSO!");
            } else if (nomeDoProduto.equals(this.nomeDoJogo[i]) && quantidade > this.getEstoque()[i]) {
                produtoEncontrado = true;
                JOptionPane.showMessageDialog(null, "info: QUANTIDADE INSUFICIENTE EM ESTQOUE PARA RETIRADA");
                JOptionPane.showMessageDialog(null, "ESTOQUE: " + this.getEstoque()[i]);
            }
        }

        if (produtoEncontrado == false && quantidade > 0) {
            JOptionPane.showMessageDialog(null, "info: PRODUTO NÃO ENCONTRADO!");
        }

        this.atualizarEstoque();
        this.menu();
    }

    //MÉTODO PARA FINALIZAR/ENCERRAR O PROGRAMA:
    @Override
    public void sair() {
        JOptionPane.showMessageDialog(null, "info: ENCERRANDO CONTROLE DE ESTOQUE...");
    }

    //MÉTODOS ACESSORES E MODIFICADORES(GETTERS/SETTERS):
    public String[] getNomeDoJogo() {
        return nomeDoJogo;
    }

    public void setNomeDoJogo(String[] nomeDoJogo) {
        this.nomeDoJogo = nomeDoJogo;
    }

    public String[] getGeneroDoJogo() {
        return generoDoJogo;
    }

    public void setGeneroDoJogo(String[] generoDoJogo) {
        this.generoDoJogo = generoDoJogo;
    }

    public String[] getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String[] desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public int[] getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(int[] classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public float[] getPrecoDoJogo() {
        return precoDoJogo;
    }

    public void setPrecoDoJogo(float[] precoDoJogo) {
        this.precoDoJogo = precoDoJogo;
    }

    public int[] getEstoque() {
        return estoque;
    }

    private void setEstoque(int[] estoque) {
        this.estoque = estoque;
    }

    public float[] getTamanhoDoJogo() {
        return this.tamanhoDoJogo;
    }

    public void setTamanhoDoJogo(float[] tamanhoDoJogo) {
        this.tamanhoDoJogo = tamanhoDoJogo;
    }

}

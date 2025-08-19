package joguinho;
import javax.swing.*;
import java.util.Random;

public class Main {

    public static void main (String[] args){

        // Cria a janela principal do jogo
        JFrame tela = new JFrame();

        // Configurações da janela
        tela.setBounds(10,10,695,600); // posição e tamanho
        tela.setTitle("Brick Breaker"); // título da janela
        tela.setResizable(false); // não pode redimensionar a tela
        tela.setVisible(true); // torna a janela visível
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha clicando no x

        // Configurando o que aparece na janela
        Menu menu = new Menu(); // traz o painel do menu e o cria
        tela.setContentPane(menu); // transforma o menu em conteúdo principal da janela
        tela.setVisible(true); // torna a janela do menu visível

        // Configurações dos botões

        //Botão da dificuldade fácil
        menu.getBotaoFacil().addActionListener(e -> {
            Jogar gamePlay = new Jogar(3,7, 12); // passa os parâmetros necessários (num de linhas, colunas e a velocidade do jogo)

            // Permite voltar ao menu
            gamePlay.setJogarListener(() -> {
                tela.setContentPane(menu); // troca de volta para o menu
                tela.revalidate();
                tela.repaint();
                menu.requestFocusInWindow(); // garante que as funções do teclado ocorram corretamente
            });

            tela.setContentPane(gamePlay); // mostra o painel do jogo
            // Atualiza o layout e a pintura da tela
            tela.revalidate();
            tela.repaint();
            gamePlay.requestFocusInWindow();
        });

        /*
            Os botões e as dificuldades média, difícil e aleatória são feitos
            da mesma maneira, só alteram os parâmetros para mudar o número de
            pedras e a velocidade
        */
        
        // Botão da dificuldade média
        menu.getBotaoMedio().addActionListener(e -> {
            Jogar gamePlay = new Jogar(5,9, 8);

            gamePlay.setJogarListener(() -> {
                tela.setContentPane(menu);
                tela.revalidate();
                tela.repaint();
                menu.requestFocusInWindow();
            });

            tela.setContentPane(gamePlay);
            tela.revalidate();
            tela.repaint();
            gamePlay.requestFocusInWindow();
        });

        // Botão da dificuldade difícil
        menu.getBotaoDificil().addActionListener(e -> {
            Jogar gamePlay = new Jogar(7,11, 6);

            gamePlay.setJogarListener(() -> {
                tela.setContentPane(menu);
                tela.revalidate();
                tela.repaint();
                menu.requestFocusInWindow();
            });

            tela.setContentPane(gamePlay);
            tela.revalidate();
            tela.repaint();
            gamePlay.requestFocusInWindow();
        });

        // Botão da dificuldade aleatória
        menu.getbotaoAleatorio().addActionListener(e -> {
            // Define um número aleatório de linhas e colunas para cada partida, usando random
            Random random = new Random();
            int min = 1;
            int max = 20;

            int linhas = random.nextInt(max - min + 1) + min;
            int colunas = random.nextInt(max - min + 1) + min;

            // A partir daqui é igual aos outros
            Jogar gamePlay = new Jogar(linhas, colunas, 10);

            gamePlay.setJogarListener(() -> {
                tela.setContentPane(menu);
                tela.revalidate();
                tela.repaint();
                menu.requestFocusInWindow();
            });

            tela.setContentPane(gamePlay);
            tela.revalidate();
            tela.repaint();
            gamePlay.requestFocusInWindow();

        });

    }

}

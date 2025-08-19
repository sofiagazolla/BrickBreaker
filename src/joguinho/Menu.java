package joguinho;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    
    // Rótulo para o título
    private JLabel tituloLabel;

    // Botões das diferentes dificuldades
    private JButton botaoFacil;
    private JButton botaoMedio;
    private JButton botaoDificil;
    private JButton botaoAleatorio;


    public Menu() {
        setBackground(Cores.fundo); // Define a cor do fundo
        setLayout(new BorderLayout()); // Define layout do painel principal, permitindo que a tela seja dividida em regiões

        // Título no topo
        tituloLabel = new JLabel("Brick Breaker"); // Define o texto
        tituloLabel.setFont(new Font("serif", Font.BOLD, 48)); // Define a fonte
        tituloLabel.setForeground(Cores.fonte); // Define a cor da fonte
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER); // Alinha ao centro
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(70, 10, 40, 10)); // Permite espaçamento

        add(tituloLabel, BorderLayout.NORTH); // Adiciona o título

        // Painel central com os botões, usando BoxLayout vertical
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Cores.fundo);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS)); // Faz com que os botões fiquem um em cima do outro

        // Cria os botões, cada um com sua cor
        botaoFacil = criaBotao("Easy", Cores.facil);
        botaoMedio = criaBotao("Average", Cores.medio);
        botaoDificil = criaBotao("Hard", Cores.roxoEscuro);
        botaoAleatorio = criaBotao("Random", Cores.verdinho);

        // Centraliza os botões
        botaoFacil.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoMedio.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoDificil.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAleatorio.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona eles ao painel, com espaçamento entre eles
        painelBotoes.add(botaoFacil);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 20)));
        painelBotoes.add(botaoMedio);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 20)));
        painelBotoes.add(botaoDificil);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 20)));
        painelBotoes.add(botaoAleatorio);

        add(painelBotoes, BorderLayout.CENTER); // Adiciona o painel de botões ao centro da tela

    }

    // Método para criar botões
    private JButton criaBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto); // Cria um botão com texto

        botao.setMaximumSize(new Dimension(200, 50)); // Define o tamanho
        botao.setBackground(corFundo); // Define a cor 
        botao.setForeground(Color.WHITE); // Define a cor da fonte
        botao.setFont(new Font("serif", Font.BOLD, 24)); // Define a fonte
        botao.setFocusPainted(false); // Tira o contorno padrão do swing
        botao.setOpaque(true); // Deixa o fundo opaco
        botao.setBorderPainted(false); // Tira a borda

        Color corHover = corFundo.brighter(); // Define uma cor para o eveito hover

        // Altera a cor quando o mouse passa por cima (cria o hover)
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corHover); // Quando o mouse passa por cima do botão, ele fica mais claro
            }

            public void mouseExited (java.awt.event.MouseEvent evt){
                botao.setBackground(corFundo); // Quando ele sai, volta para a cor original
            }
        });

        return botao;
    }

    // Getters, como os atributos são privados
    public JButton getBotaoFacil() { return botaoFacil; }
    public JButton getBotaoMedio() { return botaoMedio; }
    public JButton getBotaoDificil() { return botaoDificil; }
    public JButton getbotaoAleatorio() { return botaoAleatorio; }
}

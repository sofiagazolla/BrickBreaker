package joguinho;
import javax.swing.*;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Jogar extends JPanel implements KeyListener, ActionListener {

    private boolean jogar = false; // Indica se a bola está em movimento
    private int pontos = 0;

    private int linhas; // Quantidade de linhas de pedras
    private int colunas; // Quantidade de colunas de pedras
    private int totalPedras; // Quantidade total de pedras

    private final Timer timer; // Dispara ActionEvent no tempo do delay
    private int delay; // É o intervalo entre as atualizações da tela
    
    private final Random random = new Random(); // Gera a posição inicial aleatória da bola

    private int posicaoJogadorX = 310; // Posição do jogador (pedal) no eixo X

    private int posicaoBolaX;
    private int posicaoBolaY;
    private int direcaoXBola = -1; // É negativa então vai para a esquerda (se fosse positiva iria para a direita)
    private int direcaoYBola = -2; // É negativa para que a bola suba (se fosse positiva ela desceria)

    private GeradorMapa mapa; // Cria um mapa usando o gerador de mapa

    private JogarListener listener; // Listener para voltar ao menu

    public Jogar(int linhas, int colunas, int delay){
        mapa = new GeradorMapa(linhas,colunas);

        // Recebe os parâmetros específicos para essa dificuldade
        this.linhas = linhas;
        this.colunas = colunas;
        this.totalPedras = linhas * colunas;
        this.delay = delay;

        // Define a posição inicial da bola
        posicaoBolaX = 100 + random.nextInt(500);
        posicaoBolaY = 300 + random.nextInt(100);

        addKeyListener(this); // Configuração para receber eventos do teclado
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); 
        
        // Cria e inicia o timer do jogo
        timer = new Timer(delay, this);
        timer.start();
    }

    // Mostra a bola, o pedal e o fundo
    @Override
    public void paint(Graphics g){

        // Define a cor e o tamanho do fundo
        g.setColor(Cores.fundo);
        g.fillRect(1,1,692,592);

        mapa.draw((Graphics2D)g); // Desenha as pedras

        // Cria as bordas laterais e superior
        g.setColor(Color.black);
        g.fillRect(0,0,5,595);
        g.fillRect(0,695,695,5);
        g.fillRect(691,0,5,595);

        // Estabelece a cor e o tamanho do pedal
        g.setColor(Cores.verdinho); 
        g.fillRect(posicaoJogadorX, 550, 100, 8);

        // Estabelece a cor e o tamanho da bola  
        g.setColor(Cores.roxoEscuro);
        g.fillOval(posicaoBolaX, posicaoBolaY, 20,20);

        // Cria e escreve o score
        g.setColor(Cores.fonte);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + pontos, 520, 30);

        if (totalPedras <= 0 || posicaoBolaY > 570) {
            // O jogo acabou, seja por game over ou vitória
            jogar = false;
            // Faz a bola parar de se mover
            direcaoXBola = 0;
            direcaoYBola = 0;

            // Define a cor e a mensagem
            Color corFundoCaixa = (totalPedras <= 0) ? Cores.ganhou : Cores.perdeu; // a cor do fundo depende da opção
            String linha1 = (totalPedras <= 0) ? "You Won!" : "Game Over :("; // a mensagem também
            String linha2 = "Score: " + pontos;
            String linha3 = "Press Enter to return to the menu";

            // Define posição e tamanho da caixa
            int caixaX = 150;
            int caixaY = 220;
            int caixaLargura = 400;
            int caixaAltura = 150;
            int centroX = caixaX + caixaLargura / 2;

            g.setColor(corFundoCaixa); // Cor da caixa
            g.fillRoundRect(caixaX, caixaY, caixaLargura, caixaAltura, 30, 30); // Passa as dimensões

            // Linha 1 (vitória ou derrota)
            g.setColor(Cores.fonte);
            Font fonte1 = new Font("serif", Font.BOLD, 30);
            g.setFont(fonte1);
            FontMetrics fm1 = g.getFontMetrics(fonte1);
            int larguraLinha1 = fm1.stringWidth(linha1);
            g.drawString(linha1, centroX - larguraLinha1 / 2, caixaY + 45);

            // Linha 2 (pontuação)
            Font fonte2 = new Font("serif", Font.BOLD, 28);
            g.setFont(fonte2);
            FontMetrics fm2 = g.getFontMetrics(fonte2);
            int larguraLinha2 = fm2.stringWidth(linha2);
            g.drawString(linha2, centroX - larguraLinha2 / 2, caixaY + 85);

            // Mensagem 3 (instrução)
            Font fonte3 = new Font("serif", Font.PLAIN, 20);
            g.setFont(fonte3);
            FontMetrics fm3 = g.getFontMetrics(fonte3);
            int larguraLinha3 = fm3.stringWidth(linha3);
            g.drawString(linha3, centroX - larguraLinha3 / 2, caixaY + 120);
        }

        g.dispose(); // Libera os recursos do Graphics

    }

    // Loop do jogo, que é executado pelo Timer
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(jogar) { // Interação da bola e do pedal
            if(new Rectangle(posicaoBolaX,posicaoBolaY,20,30).intersects(new Rectangle(posicaoJogadorX,550,100,8))) {
                direcaoYBola = -direcaoYBola;
            }

            // Interação da bola e das pedras
            for (int i = 0; i < mapa.mapa.length; i++){ //  O mapa é chamado duas vezes pois nós temos o mapa do gerador e o mapa daqui
                for (int j = 0; j < mapa.mapa[0].length; j++){
                    if (mapa.mapa[i][j] > 0){
                        //  Acessamos os valores de GeradorMapa (pois eles são private)
                        int pedraX = j * mapa.larguraPedra + 80;
                        int pedraY = i * mapa.alturaPedra + 50;
                        int larguraPedra = mapa.larguraPedra;
                        int alturaPedra = mapa.alturaPedra;

                        Rectangle retanguloBola = new Rectangle(posicaoBolaX, posicaoBolaY, 20, 20);
                        Rectangle retanguloPedra = new Rectangle(pedraX, pedraY, larguraPedra, alturaPedra);

                        // Isso fica dentro do loop para que ela só possa interagir com uma pedrinha por vez
                        if (retanguloBola.intersects(retanguloPedra)) {
                            mapa.setBrickValue(0,i,j); // Destrói a pedra
                            totalPedras--; // Diminui o total de pedras
                            pontos += 5; // Aumenta os pontos

                            // Decide se a colisão foi lateral ou vertical
                            if(posicaoBolaX + 19 <= retanguloPedra.x || posicaoBolaX + 1 >= retanguloPedra.x + retanguloPedra.width) {
                                direcaoXBola = -direcaoXBola;
                            } else {
                                direcaoYBola = -direcaoYBola;
                            }
                        }
                    }
                }
            }

                // Move a bola
                posicaoBolaX += direcaoXBola;
                posicaoBolaY += direcaoYBola;

                // Cria o efeito de bater na parede e voltar
                if (posicaoBolaX < 0){
                    direcaoXBola = -direcaoXBola;
                }
                if (posicaoBolaY < 0) {
                    direcaoYBola = -direcaoYBola;
                }

                if (posicaoBolaX > 670){
                    direcaoXBola = -direcaoXBola;
                }

        }

        repaint(); // Redesenha a tela com as atualizações
    }


    // Define o retorno ao menu
    public void setJogarListener(JogarListener listener){
        this.listener = listener;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (posicaoJogadorX >= 600) { // Para que o pedal não ultrapasse as bordas
                posicaoJogadorX = 600;
            } else{
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (posicaoJogadorX < 10) { // Para que o pedal não ultrapasse as bordas
                posicaoJogadorX = 10;
            } else{
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!jogar) {
                if (listener != null) {
                    listener.returnMenu(); // Volta ao menu ao dar Enter
                }
            }
        }
    }

    // Começa o jogo quando o jogador se mexe e move para a direção apropriada
    public void moveRight(){
        jogar = true; 
        posicaoJogadorX += 20;
    }

    public void moveLeft(){
        jogar = true;
        posicaoJogadorX -= 20;
    }

    // Não são usados, mas devem ser sobrescritos por conta das implementações

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
}

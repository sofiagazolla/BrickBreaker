package joguinho;
import java.awt.*;

public class GeradorMapa {

    public int [][] mapa; // Matriz que representa as pedras
    
    // Dimensões das pedras
    public int larguraPedra;
    public int alturaPedra;

    public GeradorMapa(int linhas, int colunas){
        mapa = new int[linhas][colunas]; // Cria a matriz com as dimensões recebidas do main
        
        // Percorre toda a matriz, preenchendo ela com 1 (o que significa que a pedra está lá)
        for (int i = 0; i < mapa.length; i++){
            for (int j = 0; j < mapa[0].length; j++){
                mapa[i][j] = 1;
            }
        }

        //Define o tamanho de cada pedra
        larguraPedra = 540 / colunas;
        alturaPedra = 150 / linhas;

    }

    // Desenha todas as pedras 
    public void draw(Graphics2D g){

        for (int i = 0; i < mapa.length; i++){
            for (int j = 0; j < mapa[0].length; j++){
               if (mapa[i][j] > 0){ // Se tem uma pedra na cédula da matriz
                   g.setColor(Cores.pedras); // Define a cor delas
                   g.fillRect(j*larguraPedra + 80, i * alturaPedra + 50, larguraPedra, alturaPedra); // Preenche as pedras

                   g.setStroke(new BasicStroke(3)); // Divide as pedras (um stroke são as linhas entre as pedrinhas)
                   g.setColor(Cores.fundo); // Define a cor igual à do fundo
                   g.drawRect(j * larguraPedra + 80, i * alturaPedra + 50, larguraPedra, alturaPedra); // Desenha o cortorno de cada pedra

                }
            }
        }

    }

    // Altera o valor de uma cédula específica da matriz (para 0, quando ela é quebrada)
    public void setBrickValue (int valor, int linha, int coluna){
        mapa[linha][coluna] = valor;
    }

}

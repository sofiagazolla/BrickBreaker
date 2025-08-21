<h1 align="center">🧱 Brick Breaker em Java</h1>


<p align="center">
  <img src="https://github.com/user-attachments/assets/e7f953b7-5686-4b10-826b-39563bfc7dd5" alt="brickbreaker" width="600">
</p>

---

Esse jogo foi desenvolvido utilizando a **biblioteca Java Swing** para a interface gráfica.  
O objetivo é simples: **destruir todos os tijolos (bricks)** controlando a barra inferior e evitando que a bolinha caia.

A lógica do jogo funciona assim:  
- A **barra** é movimentada pelo jogador através das teclas direcionais.  
- A **bolinha** se move constantemente, mudando de direção ao colidir com paredes, com a barra ou com os tijolos.  
- O **mapa de tijolos** é gerado dinamicamente pela classe `GeradorMapa`, que desenha e gerencia cada tijolo.  
- Ao destruir todos os tijolos, o jogador **vence**; se a bolinha cair, o jogo chama o método de **Game Over**.  

---

## 🎮 Como jogar

1. **Clonar o repositório**

```bash
git clone https://github.com/seu-usuario/brick-breaker-java.git
```

2. **Compilar o projeto**

```bash
javac src/joguinho/*.java
```

3. **Executar o jogo**

```bash
java -cp src joguinho.Main
```

---

## 📁 Arquivos do jogo

Os arquivos necessários para o jogo são os seguintes:

```
📁 joguinho
 ├── Main.java          # Classe principal que inicia o jogo
 ├── Menu.java          # Tela inicial e menu do jogo
 ├── Jogar.java         # Lógica principal do jogo (movimento, colisões, pontuação)
 ├── JogarListener.java # Captura os inputs do jogador (teclado)
 ├── GeradorMapa.java   # Gera e controla os tijolos do jogo
 └── Cores.java         # Define cores utilizadas na interface
```

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// IMPLEMENTS -> INTERFACES
// EXTENDS -> CLASE PADRE (HERENCIA)

public class Board extends JPanel implements ActionListener {
  private final int WIDTH = 300;
  private final int HEIGHT = 300;
  private final int DOT_SIZE = 10;
  private final int ALL_DOTS = 900;
  private final int RAND_POS = 30;
  private final int DELAY = 120;

  private final int x[] = new int [ALL_DOTS];
  private final int y[] = new int [ALL_DOTS];

  private int dots;
  private int apple_x;
  private int apple_y;
  private int score = 0;

  private boolean leftDirection = false;
  private boolean rigthDirection = true;
  private boolean upDirection = false;
  private boolean downDirection = false;
  private boolean inGame = true;

  private Image ball;
  private Image apple;
  private Image head;
  private Timer timer;

  public Board(){
    initBoard();
  }

  // metodos de disenio

  private void initBoard(){
    addKeyListener(new TAdapter());
    setBackground(Color.black);
    setFocusable(true);

    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    loadImages();
    initGame();
  }

  private void loadImages(){
    ImageIcon iid = new ImageIcon("img/dot.png");
    ball = iid.getImage();

    ImageIcon iia = new ImageIcon("img/apple.jpg");
    apple = iia.getImage();

    ImageIcon iih = new ImageIcon("img/head.png");
    head = iih.getImage();
  }

  private void initGame(){
    dots = 3;

    for(int z = 0; z<dots; z++){
      x[z] = 50 -z * 10;
      y[z] = 50;
    }

    locateApple();

    timer = new Timer(DELAY, this);
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    doDrawing(g);
  }

  private void doDrawing(Graphics g){
    if (inGame){
      g.drawImage(apple, apple_x, apple_y, this);

      for(int z = 0; z < dots; z++){
        if(z==0){
          g.drawImage(head, x[z], y[z], this);
        }else{
          g.drawImage(ball, x[z], y[z], this);
        }
      }
      Toolkit.getDefaultToolkit().sync();

      String msg = "Score: " + score;

      g.setColor(Color.white);
		  g.setFont(new Font("Verdana", Font.BOLD, 20));
      g.drawString(msg, 20, 30);
    }else{
      gameOver(g);
    }
  }

  private void gameOver(Graphics g){
    String msg1 = "Game Over";
    String msg2 = "Final Score: " + score;
    Font small = new Font("Helvitica", Font.BOLD, 30);
    FontMetrics metr = getFontMetrics(small);

    g.setFont(small);
    g.setColor(Color.white);
    g.drawString(msg1, (WIDTH - metr.stringWidth(msg1))/2, HEIGHT/3 + 20);
    g.drawString(msg2, (WIDTH - metr.stringWidth(msg2))/2, HEIGHT/3 + 100);

    // int n = JOptionPane.showConfirmDialog(
		// 	    this,
		// 	    "Would you like to play again?",
		// 	    "Game Over",
		// 	    JOptionPane.YES_NO_OPTION);
		// if (n == JOptionPane.YES_OPTION) {
    //   initGame();
		// } else {
		// 	System.exit(ABORT);
		// }
  }

  private void move(){
    for (int z = dots; z>0; z--){
       x[z] = x[(z-1)];
       y[z] = y[(z-1)];
    }

    if(leftDirection){x[0] -= DOT_SIZE;}
    if(rigthDirection){x[0] += DOT_SIZE;}
    if(upDirection){y[0] -= DOT_SIZE;}
    if(downDirection){y[0] += DOT_SIZE;}

  }

  private void checkApple(){
    if ((x[0]== apple_x) && (y[0]== apple_y)){
      dots++;
      score++;
      locateApple();
    }
  }

  private void locateApple(){
    int random = (int) (Math.random() * RAND_POS);
    apple_x = (random * DOT_SIZE);
    random = (int) (Math.random() * RAND_POS);
    apple_y = (random * DOT_SIZE);
  }

  private void checkCollision(){
      for (int z = dots; z>0; z--) {
        if ((z > 4) && (x[0] == x[z])  && (y[0] == y[z])) {
          inGame= false;
        }
      }

      if (y[0] >= HEIGHT){y[0] = 0;}
      if (y[0] < 0 ){y[0] = HEIGHT;}
      if (x[0] >= WIDTH){x[0] = 0;}
      if (x[0] < 0 ){x[0] = WIDTH;}
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if (inGame){
        checkApple();
        checkCollision();
        move();
    }
    repaint();
  }

  private class TAdapter extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
      int key = e.getKeyCode();

      if ((key == KeyEvent.VK_LEFT) && (!rigthDirection)){
        leftDirection = true;
        upDirection = false;
        downDirection = false;
      }

      if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)){
        rigthDirection = true;
        upDirection = false;
        downDirection = false;
      }

      if ((key == KeyEvent.VK_UP) && (!downDirection)){
        upDirection = true;
        rigthDirection = false;
        leftDirection = false;
      }

      if ((key == KeyEvent.VK_DOWN) && (!upDirection)){
        downDirection = true;
        rigthDirection = false;
        leftDirection = false;
      }
    }
  }
}
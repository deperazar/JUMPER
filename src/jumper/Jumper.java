/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.*;
import javax.swing.Timer;


public class Jumper extends JFrame{

    public Jumper(){
        add(new Newpanel());
    }
    
    public static void main(String[] args) {
        Jumper game= new Jumper();
        game.setTitle("JUMPER");
        game.setSize(1000, 700);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}    
    class Newpanel extends JPanel implements ActionListener{
        
        private Timer time;
        private int xj;//posición en x jumper
        private int yj;//posición en y jumper
        private int xm;//posición en x monster
        private int ym;//posición en y monster
        private int c1;//existencia de moneda 1
        private int c2;//existencia de moneda 2
        private int c3;//existencia de moneda 3
        private int z;//cambio de marcha de monster
        private int i;//detectar key released
        private int j;//cambio en standing1
        private int n;//mide el salto
        private double t;//tiempo salto
        private int v;//velocidad
        private int ymax;//altura max del salto
        
        
        private int secuencia;
        
        public Newpanel(){
            this.xj=0;
            this.yj=0;
            this.xm=0;
            this.ym=0;
            this.i=0;
            this.j=0;
            this.n=0;
            this.c1=0;
            this.c2=0;
            this.c3=0;
            this.v=0;
            this.z=0;
            this.t=0;
            this.ymax=0;
            this.time=new Timer(50,this);  
            
            this.time.start();
            addKeyListener(new TAdapter());
            setFocusable(true);
        
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            Image background=loadImage("blue_background.png");
            Image monster=loadImage("enemy_standing.png");
            Image cloudes=loadImage("clouds.png");
            Image ground1=loadImage("ground_loop.png");
            Image ground2=loadImage("ground_loop.png");
            Image stone=loadImage("stone.png");
            Image jumperrunning= loadImage("walking.png");
            Image standing= loadImage ("standing.png");
            Image coin= loadImage("coin.png");
            Image finish= loadImage("finish.png");
            
            
      
            for (int k = 0; k < 46; k++) {
                g.drawImage(background, 0, 0,k*22,800, null);
                
            }
            for (int k = 0; k < 3; k++) {
                g.drawImage(cloudes, k*335, 200, null);
                g.drawImage(cloudes,-100+ k*400,150,400,92, null);
                
            }
            for (int k = 0; k < 5; k++) {
                g.drawImage(ground1,k*200,600,200,90,null);
            }
            for (int k = 0; k < 6; k++) {
                g.drawImage(ground2,600+k*200,200,200,90,null);
            }
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l < 6; l++) {
                   g.drawImage(stone, 55*k, 55*l, this);
                }
 
            }
   
           
            
            if(i==0){//Tecla sin presionar
                if(j%5==0){
                    g.drawImage(standing,50+xj,540+yj,120+xj,600+yj,0,0,137,133,null);
                }else{
                    g.drawImage(standing,50+xj,540+yj,120+xj,600+yj,140,0,273,133,null);
                } 
            }else{
                g.drawImage(jumperrunning,50+xj,540+yj,110+xj,600+yj, (115*this.secuencia) , 0, 115+(115*this.secuencia), 134, this);
            }
            
            if(j%5==0){
                    g.drawImage(monster,800+xm,540+ym,860+xm,600+ym,0,0,101,100,null);
                }else{
                    g.drawImage(monster,800+xm,540+ym,860+xm,600+ym,101,0,203,100,null);
                }
            
            if(c1==0){
                if(j%2==0){
                    g.drawImage(coin, 200, 400, 255, 455, 0, 0, 55, 55, this);
                    
                }
                else{
                    g.drawImage(coin, 220, 400, 235, 455, 0, 0, 55, 55, this);
                    
                }
            }
            if(c2==0){
                if(j%2==0){
                    g.drawImage(coin, 500, 400, 555, 455, 0, 0, 55, 55, this);
                }
                else{
                    g.drawImage(coin, 520, 400, 535, 455, 0, 0, 55, 55, this);
                }      
            }
            if(c3==0){
                if(j%2==0){
                    g.drawImage(coin, 700, 400, 755, 455, 0, 0, 55, 55, this);
                }
                else{
                    g.drawImage(coin, 720, 400, 735, 455, 0, 0, 55, 55, this);
                }      
            }
            g.drawImage(finish, 800, 300, 200,300 , this);
            if(xj>950){
                time.stop();
                System.out.println("YOU WIN");
            }

        }
        
        
        private class TAdapter extends KeyAdapter{
            @Override
            public void keyReleased(KeyEvent e){
                i=0;

            }
            @Override
            public void keyPressed(KeyEvent e){
                int key= e.getKeyCode();
                
                if(key == KeyEvent.VK_SPACE){
                }
                if(key == KeyEvent.VK_LEFT){
                    xj-=10;
                    i=1;
                }           
                if(key == KeyEvent.VK_RIGHT){
                    xj+=10;
                    i=1;
                }
                if(key == KeyEvent.VK_UP){
                    n=1;
                }
                if(key == KeyEvent.VK_DOWN){
                    yj+=5;
                }
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(z==0){
                xm-=20;
                if(xm==-800){
                    z=1;
                }
            }
            else if(z==1){
                xm+=20;
                 if(xm==0){
                     z=0;
                 }
            }
            this.j+=1;
            this.t+=0;
            if(n==1){
                this.v=10;
                this.t+=0.05;// 
                if(this.t>(v/9.8)){//bajada

                    if(this.yj>0){
                        this.yj=1;    
                        this.n=0;
                        this.t=0;
                    }else if(this.yj<0){
                        this.yj=(int) (this.ymax+30*((4.9)*Math.pow((t-v/9.8), 2)));
                        
                    }
                    
                }else{//Subida
                    this.yj=(int) -(30*(v*t-(4.9*t*t)));
                    ymax=this.yj;
                   
                }  
                if(this.secuencia==3){
                    this.secuencia=0;
                }
                else{
                    this.secuencia++;
                }
                repaint();
            }
            
            if(this.secuencia==3){
                this.secuencia=0;
            }
            else{
                this.secuencia++;
            }
            checkcollision();
            repaint();
        }
        
        
        @Override
        public Rectangle getBounds(){
            return new Rectangle(50+xj,540+yj,55,60);
        }
        
        public void checkcollision(){
            Rectangle character= this.getBounds();
            Rectangle enemy1=new Rectangle(800+xm,540+ym, 60, 60);
            Rectangle coin1=new Rectangle(220,400,55,55);
            Rectangle coin2=new Rectangle(520,400,55,55);
            Rectangle coin3=new Rectangle(720,400,55,55);      
            if(character.intersects(enemy1)){
                System.out.println("YOU ARE DEAD");
                time.stop();
            }
            else if (character.intersects (coin1)){
                System.out.println("JUST GOT MONEY");
                c1=1;
            }
            else if (character.intersects (coin2)){
                System.out.println("JUST GOT MONEY");
                c2=1;
            }
            else if (character.intersects (coin3)){
                System.out.println("JUST GOT MONEY");
                c3=1;
            }
        }
        
        public Image loadImage(String imageName){
            ImageIcon ii= new ImageIcon(imageName);
            Image image =ii.getImage();
            return image;
        }
}
    

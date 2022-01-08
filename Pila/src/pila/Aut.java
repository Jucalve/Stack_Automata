/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ASUS
 */
public class Aut extends JPanel{
    public char [][] aut;
    public int cad, cady;
    public void Aut() throws IOException{
        char[] aux=null;
        String au2=null;
        int i=0;
        JFrame v = new JFrame();
        Aut panel=new Aut();
        v.getContentPane().add(panel);
        v.pack();
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FileReader ftrut=null;
        try {
            ftrut=new FileReader("DesInst.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Aut.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br=new BufferedReader(ftrut);
        try {
            au2=br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Aut.class.getName()).log(Level.SEVERE, null, ex);
        }/**/
        aux=au2.toCharArray();
        panel.aut=new char[aux.length][aux.length];
        int j=0;
        do{
            aux=au2.toCharArray();
                i=0;
                panel.aut[0][j]=aux[1];
                panel.aut[1][j]=aux[3];  
                while(aux[i+3]!=','){
                    i++;
                }
                panel.cad=i+3;
                i=1;
                while(aux[i+panel.cad]!=')'){
                    panel.aut[i+1][j]=aux[i+panel.cad]; 
                    i++;
                }
                panel.aut[i+1][j]='@';
                i=0;
                j++;
        }while((au2=br.readLine())!=null);
        panel.cady=j;
        /*for(int w=0; w<panel.cady; w++){
            for(int qp=0; qp<panel.aut.length-1; qp++){
                System.out.print(panel.aut[qp][w]);
            }
            System.out.println("");
        }/**/
        v.setVisible(true);
        panel.repaint();
    }
    
    public void paint(Graphics g){
        int fila=0;
        int x=190, y=150, r1=15, r2=80, qp, aurl=0, ac=8;
        int a=r1/2, b=r2/2;
        char ant='E';
        for (int rojo = 0 ; rojo < this.getHeight() ; rojo++)
        {
            Color col;
            if(rojo%256<180) col = new Color (30, 255-rojo%180, 10);
            else col = new Color (30, (255-180)+rojo%180, 10);
            g.setColor (col);
            g.drawLine (0, fila, this.getWidth(), fila);
            fila++;
            
        }
        g.setColor(Color.black);
        g.fillRect( 20, y, 90,  5);
        g.fillOval(110, y-a, r1, r1);
        
        g.drawOval(135, y, r2-10,  r2-10);
        g.fillOval(125, y+20, 15,  15);
        g.setColor(Color.gray);
        g.fillOval(130, y-b, r2, r2);
        g.setColor(Color.black);
        
        g.drawOval(130, y-b, r2, r2);
        g.fillRect(x+20, y, 90,  5);
        g.fillOval(x+110, y-a, r1, r1);
        g.drawOval(x+130, y-b, r2, r2);
        
        g.drawOval(x+135, y, r2-10,  r2-10);
        g.fillOval(x+125, y+20, 15,  15);
        g.setColor(Color.gray);
        g.fillOval(x+130, y-b, r2, r2);
        g.setColor(Color.black);
        
        g.fillRect(2*x+20, y, 90,  5);
        g.fillOval(2*x+110, y-a, r1, r1);
        g.drawOval(2*x+130, y-b, r2, r2);
        g.setColor(Color.gray);
        g.fillOval(2*x+130, y-b, r2, r2);
        g.setColor(Color.black);
        g.drawString("("+ant+"/E,Z)", 33, y+20);
        for(int w=0; w<cady; w++){
            if(w==0) ant='E';
            else ant=aut[1][w-1];
            if(aut[0][w]=='q' && aut[1][w]=='0'){
                g.drawString("("+ant+"/0,", x-50, y+90+15*aurl);
                qp=2;
                while(aut[qp][w]!='@'){ g.drawString(aut[qp][w]+"", x-39+qp*8, y+90+15*aurl); qp++;}
                g.drawString(")", x-39+qp*8, y+90+15*aurl);
                aurl++;
            }
            if(aut[0][w]=='q' && aut[1][w]=='1'){
                g.drawString("("+ant+"/1,", x+33, y+20);
                qp=2;
                while(aut[qp][w]!='@'){ g.drawString(aut[qp][w]+"", x+42+qp*8, y+20); qp++;}
                g.drawString(")", x+42+qp*8, y+20);
            }
        }/**/
        aurl=0;
        for(int w=0; w<cady; w++){
            if(w==0) ant='E';
            else ant=aut[1][w-1];
            if(aut[0][w]=='p' && aut[1][w]=='1'){
                g.drawString("("+ant+"/1,", 2*x-50, y+90+15*aurl);
                qp=2;
                while(aut[qp][w]!='@'){ g.drawString(aut[qp][w]+"", 2*x-39+qp*8, y+90+15*aurl); qp++;}
                g.drawString(")", 2*x-39+qp*8, y+90+15*aurl);
                aurl++;
            }
        }
        if(aut[0][cady-1]=='f' && aut[1][cady-1]=='E' && aut[3][cady-1]=='@' && aut[2][cady-1]=='Z'){
            g.fillRect(3*x+20, y, 90,  5);
            g.fillOval(3*x+110, y-a, r1, r1);
            g.drawOval(3*x+130, y-b, r2, r2);
            g.setColor(Color.gray);
            g.fillOval(3*x+130, y-b, r2, r2);
            g.setColor(Color.black);
            g.drawOval(3*x+130+5, y-b+5, r2-10, r2-10);
            g.drawString("(1/E,", 2*x+30, y+20);
            g.drawString("(E/E,", 3*x+30, y+20);
                qp=2;
                while(aut[qp][cady-1]!='@'){
                    g.drawString(aut[qp][cady-1]+"", 2*x+40+qp*8, y+20);
                    g.drawString(aut[qp][cady-1]+"", 3*x+40+qp*8, y+20);
                    qp++;
                }
                g.drawString(")", 2*x+40+qp*8, y+20);
                g.drawString(")", 3*x+40+qp*8, y+20);
                aurl++;
                ac=1;
        }
        else{
            aurl=0;
            g.drawOval(2*x+135, y, r2-10,  r2-10);
            g.fillOval(2*x+125, y+20, 15,  15);
            g.setColor(Color.gray);
            g.fillOval(2*x+130, y-b, r2, r2);
            g.setColor(Color.black);
            g.fillRect(3*x+20, y, 90,  5);
            g.fillOval(3*x+110, y-a, r1, r1);
            g.drawOval(3*x+130, y-b, r2, r2);
            g.setColor(Color.gray);
            g.fillOval(3*x+130, y-b, r2, r2);
            g.setColor(Color.black);
            g.drawOval(3*x+130+5, y-b+5, r2-10, r2-10);
            g.drawString("("+aut[1][cady-1]+"/"+aut[1][cady-2]+",", 2*x+30, y+20);
            g.drawString("("+aut[1][cady-1]+"/"+aut[1][cady-2]+",", 3*x-50, y+90+15*aurl);
                qp=2;
                while(aut[qp][cady-1]!='@'){
                    g.drawString(aut[qp][cady-1]+"", 3*x-39+qp*8, y+90+15*aurl);
                    g.drawString(aut[qp][cady-1]+"", 2*x+40+qp*8, y+20);
                    qp++;
                }
                g.drawString(")", 2*x+40+qp*8, y+20);
                g.drawString(")", 3*x-39+qp*8, y+90+15*aurl);
                ac=3;
                aurl++;
        }
        Font f = new Font("lol", Font.BOLD, 22);
        g.setFont(f);
        g.drawString("push", x-45, y-45);
        g.drawString("q", x-30, y+5);
        g.drawString("pop", 2*x-45, y-45);
        g.drawString("p", 2*x-30, y+5);
        if(ac==1){
            g.drawString("fin", 4*x-45, y-45);
            g.drawString("f0", 3*x-30, y+5);
            g.drawString("f1", 4*x-30, y+5);
            g.drawString("cadena aceptada", 2*x-50, y-90);
        }
        else{
            g.drawString("fin", 3*x-45, y-45);
            g.drawString("f0", 3*x-30, y+5);
            g.drawString("f1", 4*x-30, y+5);
            g.drawString("cadena NO aceptada", 2*x-50, y-90);
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 300);
    }
}

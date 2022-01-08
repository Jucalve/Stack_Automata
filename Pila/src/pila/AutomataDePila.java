/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.paint.Color;
import javax.swing.*;
/**
 *
 * @author ASUS
 */
public class AutomataDePila extends JPanel{
    public char [] arr;
    public char [] pila;
    char au;
    public Pila p=new Pila();
    public int x=30, y=40, limi=0, tamp=1, h, w;
    public static int t=900, limite=10;
    Font f = new Font("lol", Font.BOLD, 22);
    Font f1 = new Font("lol", Font.BOLD, 15);
    ImageIcon fi1=new ImageIcon(getClass().getResource("/pila/br0.png"));
    ImageIcon fi2=new ImageIcon(getClass().getResource("/pila/br1.png"));
    /**C:\Users\ASUS\Documents\NetBeansProjects\Automata de Pila/src
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileWriter ftrut=null;
        try {
            ftrut=new FileWriter("DesInst.txt");
        } catch (IOException ex) {
            Logger.getLogger(AutomataDePila.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw;
        PrintWriter wr;
        bw = new BufferedWriter(ftrut);        
        wr = new PrintWriter(bw);
        JFrame v = new JFrame();
        int bruuh=1, i=0, ta=2, res, rant, ranau;
        char aux;
        AutomataDePila panel = new AutomataDePila();
        v.getContentPane().add(panel);
        v.pack();
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //panel.h=panel.getHeight();
        //panel.w=panel.;
        
        res=JOptionPane.showConfirmDialog(null, "Desea que la cadena se genere aleatoriamente?");
        if(res==0){
            rant=2+(int)(Math.random()*100)%limite;
            panel.arr=new char[rant];
            ranau=(int)(Math.random()*100)%rant;
            //System.out.println("# 0s = "+ranau);
            //System.out.println("# 1s = "+(rant-ranau));
            for(int w=0; w<ranau; w++) panel.arr[w]='0';
            for(int w=ranau; w<rant; w++) panel.arr[w]='1';
        }
        else if(res==1){
            panel.arr=JOptionPane.showInputDialog(null,"Introduzca la cadena a evaluar").toCharArray();
        }
        else{
            JOptionPane.showMessageDialog(null, "Adios");
            System.exit(0);
        }
        panel.pila=new char[panel.arr.length+1];
        panel.pila[0]='Z';
        Pila p2=new Pila();
        panel.p.push('Z');
        panel.au='q';
        v.setVisible(true);
        while(i<panel.arr.length+1 && ta>1 && panel.au!='f'){
            if(bruuh==1){try
            {
                Thread.sleep(t*bruuh+1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }}
            
            panel.limi = i;
            if(bruuh==0){
                //System.out.print("("+panel.au+",");
                wr.append("("+panel.au+",");
                for(int j=i-1; j<panel.arr.length; j++){ 
                    //System.out.print(panel.arr[j]);
                    wr.append(panel.arr[j]);
                }
                //System.out.print(",");
                wr.append(",");
                while(!panel.p.isEmpty()){
                    aux=panel.p.pop();
                    p2.push(aux);
                    //System.out.print(aux);
                    wr.append(aux);
                }
                while(!p2.isEmpty()) panel.p.push(p2.pop());
                //System.out.println(")"+(char)880);
                wr.append(")"+(char)880);
                wr.println();
                if(panel.arr[i-1]=='0' && panel.au=='q'){
                    panel.pila[panel.tamp]='X';
                    //aux='X';
                    panel.p.push('X');
                    //System.out.println("X");
                    panel.tamp++;
                    //System.out.println("bruh"+panel.tamp);
                }
                else if(panel.arr[i-1]=='1'){
                    panel.tamp--;
                    //System.out.println("p");
                    panel.au='p';
                    panel.p.pop();
                }
                else panel.au='f';{
                    ta=panel.p.size();
                    //System.out.println("f");
                }
            }
            //System.out.println(panel.arr[i]);
            if(bruuh==0){try
            {
                Thread.sleep(t);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }}
            bruuh=0;
            panel.repaint();
            i++;
        }
        panel.au='f';
        
                //System.out.print("("+panel.au+",");
                wr.append("("+panel.au+",");
                if(i==panel.arr.length+1){
                    //System.out.print("E");
                    wr.append("E");
                }
                else for(int j=i-1; j<panel.arr.length; j++){
                    //System.out.print(panel.arr[j]);
                    wr.append(panel.arr[j]);
                }
                //System.out.print(",");
                wr.append(",");
                if(panel.p.isEmpty()){
                    //System.out.print("E");
                    wr.append("E");
                }
                else{
                while(!panel.p.isEmpty()){
                    aux=panel.p.pop();
                    p2.push(aux);
                    //System.out.print(aux);
                    wr.append(aux);
                }
                while(!p2.isEmpty()) panel.p.push(p2.pop());}
                //System.out.println(")");
                wr.append(")");
                wr.close();
                
        String mess;
        panel.repaint();
        if(panel.p.isEmpty()) mess="La cadena no pertenece al Lenguaje";
        else if(panel.p.size()==1 && i==panel.arr.length+1) mess="La cadena pertenece al Lenguaje";
        else mess="La cadena no pertenece al Lenguaje";
        System.out.println(mess);
        JOptionPane.showMessageDialog(null, mess);
        Aut a=new Aut();
        try {
            a.Aut();
            //System.exit(bruuh);
            // TODO code application logic here
        } catch (IOException ex) {
            Logger.getLogger(AutomataDePila.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void paint(Graphics g){
        Pila p2=new Pila();
        int i=0;
        char qp;
        super.paint(g);
        int fila = 0;
        
        for (int rojo = 0 ; rojo < this.getHeight() ; rojo++)
        {
            Color col;
            if(rojo%256<180) col = new Color (255-rojo%180, 30, 10);
            else col = new Color ((255-180)+rojo%180, 30, 10);
            //g.setColor();
            g.setColor (col);
            g.drawLine (0, fila, this.getWidth(), fila);
            fila++;
            
        }
        g.setColor(Color.black);
        g.setFont(f1);
        
        while(!p.isEmpty()){
            qp=p.pop();
            p2.push(qp);
            g.drawString(qp+" ", x+2, (y+115)+i*20);
            i++;
        }
        while(!p2.isEmpty()){
            qp=p2.pop();
            p.push(qp);
        }
        g.setFont(f);
        if(limi<arr.length)
            for(i=limi; i<arr.length; i++) g.drawString(arr[i]+" ", x+(i-limi)*24, y);
        g.setColor(java.awt.Color.black);
        g.drawImage(fi1.getImage(), x-4, y+10, this);
        g.fillRect(x+2, y+22, 8, 15);
        g.setColor(java.awt.Color.green);
        g.fillRect(x-9, y+40, 30, 30);
        g.setColor(java.awt.Color.black);
        g.drawString(au+"", x-1, y+59);
        g.fillRect(x+2, y+72, 8, 15);
        g.drawImage(fi2.getImage(), x-4, y+87, this);
        
        
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static class Pila {

    private final int MAXIMO = 100;
    private char[] V;
    private int tope;

    public Pila() {
        this.V = new char[MAXIMO];
        this.tope = -1;
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == MAXIMO - 1;
    }

    public void push(char a) {
        if (isFull()) {
            System.out.println("Pila Llena!");
        } else {
            tope++;
            V[tope] = a;
        }
    }

    public char pop() {
        char a = (char) Integer.MIN_VALUE;
        if (isEmpty()) {
            System.out.println("Pila Vacia!");
        } else {
            a = V[tope];
            tope--;
        }
        return a;
    }

    public void empty(Pila B) {
        while (!B.isEmpty()) {
            this.push(B.pop());
        }
    }

    public int size() {
        return tope + 1;
    }
    
    public int top() {
        return V[tope];
    }

    public void show() {
        Pila X = new Pila();
        while (!isEmpty()) {
            char a = pop();
            System.out.print("" + a);
            X.push(a);
        }
        this.empty(X);
    }
}
    
}
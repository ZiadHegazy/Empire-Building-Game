package view;
import java.applet.*;
import java.awt.Color;
import java.io.*;
import java.awt.Font;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.*;

import engine.Game;
import units.Army;
public class GamePlay extends JFrame {
	Timer t;
	Listener l;
	boolean win;
	boolean lost;
	boolean battleoption;
	boolean manual;
	boolean build ;
	Army army1;
	Army army2;
	String targetcity;
	Graph g;
	Game g1;
	boolean start=true;
	String name="Enter your name";
	String city;
	ArrayList <Object> comp=new ArrayList<>();
	boolean map=false;
	boolean incity=false;
	boolean displayArmy=false;
	String logbattle="";
	ArrayList<int[]> approx=new ArrayList<>();
	public GamePlay() {
		l=new Listener(this);
		t=new Timer(70,l);
		g=new Graph(this);
		this.setTitle("Empire Building");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setResizable(false);
		g.setBounds(0,0,this.getWidth(),this.getHeight());
		g.full();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		int xstart=170;
		int ystart=65;
		while(xstart<=this.getWidth()-282) {
			while(ystart<=this.getHeight()-390) {
				approx.add(new int[]{xstart,ystart});
				ystart+=110;
			}
			ystart=65;
			xstart+=160;
		}
		
	    playMusic("./sounds/Game.wav");
	    
	  this.setLayout(null);
		this.add(g);
		this.repaint();
	}
	public int[] findAprrox(int x,int y) {
		int biggerx=-1;
		int biggery=-1;
		int smallerx=-1;
		int smallery=0;
		for(int i=0;i<approx.size();i++) {
			if(approx.get(i)[0]>x) {
				biggerx=approx.get(i)[0];
				smallerx=biggerx-160;
				break;
			}
		}
		for(int i=0;i<approx.size();i++) {
			if(approx.get(i)[1]>y) {
				biggery=approx.get(i)[1];
				smallery=biggery-110;
				break;
			}
		}
		int besty=0;
		int bestx=0;
		if(biggerx==-1)
			bestx=this.getWidth()-412;
		else if(Math.abs(biggerx-x)<=Math.abs(smallerx-x))
			bestx=biggerx;
		else
			bestx=smallerx;
		if(biggery==-1)
			besty=this.getHeight()-390;
		else if(Math.abs(biggery-y)<=Math.abs(smallery-y))
			besty=biggery;
		else
			besty=smallery;
		
		return new int[] {bestx,besty};
	}
	 long playSound(String path) throws IOException {

	    try {
	      AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	      Clip clip = AudioSystem.getClip();
	      clip.open(audio);
	      clip.start();
	      return clip.getMicrosecondLength();
	    } catch (UnsupportedAudioFileException | LineUnavailableException e) {
	      e.printStackTrace();
	    }
	    return 0;
	  }
	 private void playMusic(String path) {

		    try {
		      AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
		      Clip clip;
		      clip = AudioSystem.getClip();
		      clip.open(audio);
		      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		      gainControl.setValue(-10.0f);
		      clip.start();
		      clip.loop(Clip.LOOP_CONTINUOUSLY);
		    } catch (UnsupportedAudioFileException | LineUnavailableException e) {
		      e.printStackTrace();
		    } catch (IOException e) {

		    }
		  }
	 
	public static void main(String[] args) {
		GamePlay g=new GamePlay();
		
	}

}

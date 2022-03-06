package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Farm;
import buildings.Market;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Status;

public class Graph extends JPanel {
	GamePlay g2;
	boolean drag;
	boolean fight;
	ArrayList<int[]> hisstart=new ArrayList<>();
	String type;
	String edit;
	JTextArea j1=new JTextArea();
	JScrollPane pane=new JScrollPane(j1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	ImageIcon butCairo = new ImageIcon("images\\Cairo.png");
	ImageIcon butSparta = new ImageIcon("images\\Sparta.png");
	ImageIcon butRome = new ImageIcon("images\\Rome.png");
	ImageIcon butStart = new ImageIcon("images\\Start.png");
	ImageIcon End = new ImageIcon("images\\end(2).png");
	ImageIcon ShowArmy = new ImageIcon("images\\ShowArmies.png");
	ImageIcon Auto = new ImageIcon("images\\Auto.png");
	ImageIcon Returnn = new ImageIcon("images\\retrun.png");
	ImageIcon LaySiege = new ImageIcon("images\\Lay-Siege.png");
	ImageIcon Manual = new ImageIcon("images\\Manual.png");
	ImageIcon March = new ImageIcon("images\\March.png");
	ImageIcon MarchRom = new ImageIcon("images\\MarchRom.png");
	ImageIcon SpartaCairo = new ImageIcon("images\\SpartaCairo.png");
	ImageIcon CairoSparta = new ImageIcon("images\\CairoSparta.png");

	JTextArea f2=new JTextArea("Enter your name");
	
	
	JFrame f1=new JFrame();
	JFrame f3=new JFrame();
	JButton b1=new JButton("");
	JButton b2=new JButton("");
	JButton b3=new JButton("");
	JButton b4=new JButton("");

	JButton endturn;
	JButton buld;
	JPanel p1=new JPanel();
	BufferedImage i=null;
	BufferedImage i2=null;
//	BufferedImage i3=null;
	BufferedImage i4=null;
//	BufferedImage i5=null;
//	BufferedImage i6=null;
	BufferedImage i7=null;
	BufferedImage i8=null;
	BufferedImage i9=null;
	BufferedImage i10=null;
	BufferedImage i11=null;
	BufferedImage iFarm=null;
	BufferedImage i2Farm=null;
	BufferedImage i3Farm=null;
	BufferedImage econBuilding = null;
	BufferedImage MiliBuilding = null;
	BufferedImage BattleOption = null; 
	
	BufferedImage iMarket=null;
	BufferedImage i2Market=null;
	BufferedImage i3Market=null;
	
	BufferedImage EgyBar1=null;
	BufferedImage EgyBar2=null;
	BufferedImage EgyBar3=null;

	
	BufferedImage tmp = null;
	BufferedImage ShowArm = null;
//	BufferedImage Romebut=null;
//	BufferedImage Spartabut=null;
	BufferedImage NameField = null;
	BufferedImage Choose = null;
	BufferedImage Vic = null;
	BufferedImage Def = null;
	
	BufferedImage WorldMap = null;
	BufferedImage Armies = null;
	BufferedImage Build = null;
	
	BufferedImage EgyView = null;
	BufferedImage cityWar = null;
	
	BufferedImage EgyArchery1 = null;
	BufferedImage EgyArchery2 = null;
	BufferedImage EgyArchery3 = null;
	
	BufferedImage EgyStable1 = null;
	BufferedImage EgyStable2 = null;
	BufferedImage EgyStable3 = null;
	
	public Graph(GamePlay g1) {
		g2=g1;
		try {
			i=ImageIO.read(new File("images\\Background.jpg"));
			
			i2=ImageIO.read(new File("images\\Map.jpg"));
		//	i3=ImageIO.read(new File("images\\end(2).png"));
			i4=ImageIO.read(new File("images\\city.jpg"));
//			i5=ImageIO.read(new File("images\\Start.png"));
//			i6=ImageIO.read(new File("images\\Cairo.png"));
			i7=ImageIO.read(new File("images\\CityCairo.png"));
			i8=ImageIO.read(new File("images\\CitySparta.png"));
			i9=ImageIO.read(new File("images\\Rome-3.png"));
			i10=ImageIO.read(new File("images\\Bar11.png"));
			i11=ImageIO.read(new File("images\\Map2.png"));
			iFarm=ImageIO.read(new File("images\\Egypt-farm-1.png"));
			i2Farm=ImageIO.read(new File("images\\egypt-farm-2.png"));
			i3Farm=ImageIO.read(new File("images\\egypt-farm-3.png"));
			
			iMarket=ImageIO.read(new File("images\\market-1-egy.png"));
			i2Market=ImageIO.read(new File("images\\market-level-2.png"));
			i3Market=ImageIO.read(new File("images\\market-egy-3.png"));
			cityWar=ImageIO.read(new File("images\\CityWar.jpg"));
			BattleOption = ImageIO.read(new File("images\\BattleOption.jpg"));
			
			ShowArm=ImageIO.read(new File("images\\ShowArmies.png"));
//			Romebut=ImageIO.read(new File("images\\Rome.png"));
//			Spartabut=ImageIO.read(new File("images\\Sparta.png"));
			NameField=ImageIO.read(new File("images\\Name.png"));
			Choose=ImageIO.read(new File("images\\Choose.png"));
			Vic = ImageIO.read(new File("images\\victory.jpg"));
			Def = ImageIO.read(new File("images\\Defeated.jpg"));
			
			EgyView=ImageIO.read(new File("images\\EgyView.png"));	
			WorldMap=ImageIO.read(new File("images\\World-Map.png"));
			Armies=ImageIO.read(new File("images\\ARMIES.png"));
			Build=ImageIO.read(new File("images\\BUILD.png"));
			
			EgyBar1= ImageIO.read(new File("images\\EgyBarracks.png"));
			EgyBar2= ImageIO.read(new File("images\\EgyBarracks2.png"));
			EgyBar3= ImageIO.read(new File("images\\EgyBarracks3.png"));
			
			EgyArchery1= ImageIO.read(new File("images\\EgyArchery1.png"));
			EgyArchery2= ImageIO.read(new File("images\\EgyArchery2.png"));
			EgyArchery3= ImageIO.read(new File("images\\EgyArchery3.png"));
			
			
			EgyStable1= ImageIO.read(new File("images\\EgyStable1.png"));
			EgyStable2= ImageIO.read(new File("images\\EgyStable2.png"));
			EgyStable3= ImageIO.read(new File("images\\EgyStable3.png"));
			
			econBuilding= ImageIO.read(new File("images\\Econ.png"));
			MiliBuilding= ImageIO.read(new File("images\\Milit.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JScrollBar j2=new JScrollBar();
		//j2.addAdjustmentListener(g2.l);
		//j1.add(j2);
	}
	public void full() {
		int ystart=190;
		int xstart=g2.getWidth()-130;
		while(xstart>=0) {
			hisstart.add(new int[] {xstart,ystart});
			ystart+=140;
			if(ystart>=g2.getHeight()-110) {
				xstart-=120;
				ystart=190;
			}	
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setLayout(null);
		
		
		this.endturn=new JButton("");
		endturn.setIcon(End);
		endturn.setActionCommand("endturn");
		//this.endturn.setIcon(new ImageIcon(i3));
		endturn.setContentAreaFilled(false);
		endturn.setBorderPainted(false);
		endturn.setForeground(Color.RED);
		this.endturn.setBounds((int)(this.getWidth()/1.2),(this.getHeight()/2 -50),250,90);
		this.endturn.addActionListener(this.g2.l);
		this.endturn.addMouseListener(this.g2.l);
		if(g2.win) {
			g.drawImage(Vic,0,0,g2.getWidth(),g2.getHeight(),this);
			
		}else if(g2.lost){
			g.drawImage(Def,0,0,g2.getWidth(),g2.getHeight(),this);
			
		}
		else if(g2.manual) {
			g.drawImage(cityWar,0,0,g2.getWidth(),g2.getHeight(),this);
//			g.drawImage(i4,0,0,g2.getWidth(),g2.getHeight(),this);
			
			g.setFont(new Font("Garamond",Font.BOLD,25));
			g.setColor(Color.WHITE);
			g.drawImage(i11,0,0,g2.getWidth(),g2.getHeight(),this);
			g.drawString("Turn:" ,g2.getWidth()/28-50,g2.getHeight()/23);
			g.drawString(this.g2.g1.getCurrentTurnCount()+"/50",g2.getWidth()/28-50 +10,g2.getHeight()/15) ;
			g.drawString("Gold: "+this.g2.g1.getPlayer().getTreasury(),(int)(g2.getWidth()/5.3), g2.getHeight()/10+40);
			g.drawString("Food: "+this.g2.g1.getPlayer().getFood(),g2.getWidth()/17,g2.getHeight()/10+40);
			g.drawString("Name: "+this.g2.name,g2.getWidth()/17,g2.getHeight()/19);
			
			j1.setBounds((int)(g2.getWidth()/2.53),g2.getHeight()/23,500,100);
			j1.setBackground(Color.black);
			j1.setForeground(Color.green);
			j1.setText(g2.logbattle);
//			g.drawImage(i3,j1.getX()+1020,j1.getY()-100,200,100,this);
			
			j1.setEditable(false);
			j1.setFont(new Font("Arial",Font.PLAIN,20));
			this.add(pane);
		
			//this.add(j1);
			g.setFont(new Font("Arial",Font.PLAIN,15));
			g.setColor(Color.cyan);
			for(int k=0;k<g2.army1.getUnits().size();k++) {
				String s="";
				JButton b1=new JButton();
//				b1.setBorderPainted(false);
				b1.setContentAreaFilled(false);
				if(g2.army1.getUnits().get(k) instanceof Archer) {
					s="Archer";
					if(g2.army1.getCameFrom().equals("Cairo"))
						b1.setIcon(new ImageIcon("images\\egyArcher1.png"));
					if(g2.army1.getCameFrom().equals("Rome"))
						b1.setIcon(new ImageIcon("images\\archers-roma-1.png"));
					if(g2.army1.getCameFrom().equals("Sparta"))
						b1.setIcon(new ImageIcon("images\\sparta-archer1.png"));
					
				
				}
				else if(g2.army1.getUnits().get(k) instanceof Cavalry) {
					s="Cavalry";
					if(g2.army1.getCameFrom().equals("Cairo"))
						b1.setIcon(new ImageIcon("images\\EgyCavlary.png"));
					if(g2.army1.getCameFrom().equals("Sparta"))
					b1.setIcon(new ImageIcon("images\\CavSparta1.png"));
					if(g2.army1.getCameFrom().equals("Rome"))
						b1.setIcon(new ImageIcon("images\\CavRome1.png"));
					
				
				}
				else if(g2.army1.getUnits().get(k) instanceof Infantry) {
					s="Infantry";
					if(g2.army1.getCameFrom().equals("Cairo"))
						b1.setIcon(new ImageIcon("images\\egy-infantry-1.png"));
					if(g2.army1.getCameFrom().equals("Rome"))
						b1.setIcon(new ImageIcon("images\\Romeinfant.png"));
					if(g2.army1.getCameFrom().equals("Sparta"))
						b1.setIcon(new ImageIcon("images\\SpartaInfant2.png"));
				
				}
				b1.setBorder(BorderFactory.createLineBorder(Color.GREEN));
				b1.setActionCommand("/"+k+"/"+g2.army1.getUnits().get(k).getX()+"/"+g2.army1.getUnits().get(k).getY());
				b1.setFont(new Font("Arial",Font.PLAIN,12));
				b1.setBackground(Color.yellow);
				b1.addActionListener(g2.l);
				b1.addMouseListener(g2.l);
				JLabel l3=new JLabel("Type: "+s);    
				l3.setFont(new Font("Arial",Font.PLAIN,12));
				JLabel l1=new JLabel("Level: "+g2.army1.getUnits().get(k).getLevel());
				l1.setFont(new Font("Arial",Font.PLAIN,12));
				JLabel l2=new JLabel("SoldierCount: "+g2.army1.getUnits().get(k).getCurrentSoldierCount()+"/"+g2.army1.getUnits().get(k).getMaxSoldierCount());
				l2.setFont(new Font("Arial",Font.PLAIN,12));
				b1.setBounds(g2.army1.getUnits().get(k).getX(),g2.army1.getUnits().get(k).getY(),110,50);
				b1.setLayout(null);
				l3.setBounds(0,0,110,15);
				l1.setBounds(0,18,110,15);
				l2.setBounds(0,35,110,15);
				//b1.add(l1);
				//b1.add(l2);
				//b1.add(l3);
				//l1.requestFocus();
				//l2.requestFocus();
			//	l3.requestFocus();
				this.add(b1);
			}
			for(int k=0;k<g2.army2.getUnits().size();k++) {
				String s="";
				JButton b1=new JButton();
				b1.setForeground(Color.RED);
				b1.setBorder(BorderFactory.createLineBorder(Color.red));
				b1.setContentAreaFilled(false);
//				b1.setBorderPainted(false);
				if(g2.army2.getUnits().get(k) instanceof Archer) {
					s="Archer";
					if(g2.army2.getCurrentLocation().equals("Sparta"))
						b1.setIcon(new ImageIcon("images\\sparta-archerr.png"));
					if(g2.army2.getCurrentLocation().equals("Cairo"))
						b1.setIcon(new ImageIcon("images\\egyArcher.png"));
					if(g2.army2.getCurrentLocation().equals("Rome"))
						b1.setIcon(new ImageIcon("images\\archers-roma-2.png"));
				
				}
				
				if(g2.army2.getUnits().get(k) instanceof Cavalry) {
					s="Cavalry";
					if(g2.army2.getCurrentLocation().equals("Sparta"))
						b1.setIcon(new ImageIcon("images\\CavSparta1.png"));
					
					if(g2.army2.getCurrentLocation().equals("Rome"))
					b1.setIcon(new ImageIcon("images\\CavRome.png"));
					
					if(g2.army2.getCurrentLocation().equals("Cairo"))
						b1.setIcon(new ImageIcon("images\\EgyCavlary1.png"));
			}
				
				
				if(g2.army2.getUnits().get(k) instanceof Infantry) {
					s="Infantry";
					if(g2.army2.getCurrentLocation().equals("Sparta"))
						b1.setIcon(new ImageIcon("images\\SpartaInfant.png"));
					if(g2.army2.getCurrentLocation().equals("Rome"))
					b1.setIcon(new ImageIcon("images\\Romeinfant2.png"));
					if(g2.army2.getCurrentLocation().equals("Cairo"))
						b1.setIcon(new ImageIcon("images\\egy-infantry.png"));
						
				}
				
				b1.setActionCommand(k+"/"+g2.army2.getUnits().get(k).getX()+"/"+g2.army2.getUnits().get(k).getY());
				b1.setFont(new Font("Arial",Font.PLAIN,12));
				b1.setFont(new Font("Arial",Font.PLAIN,12));
				b1.setBackground(Color.white);
				b1.addActionListener(g2.l);
				b1.addMouseListener(g2.l);
				JLabel l3=new JLabel("Type: "+s);
				l3.setForeground(Color.black);
				l3.setFont(new Font("Arial",Font.PLAIN,12));
				JLabel l1=new JLabel("Level: "+g2.army2.getUnits().get(k).getLevel());
				l1.setFont(new Font("Arial",Font.PLAIN,12));
				l1.setForeground(Color.black);
				JLabel l2=new JLabel("SoldierCount: "+g2.army2.getUnits().get(k).getCurrentSoldierCount()+"/"+g2.army2.getUnits().get(k).getMaxSoldierCount());
				l2.setFont(new Font("Arial",Font.PLAIN,12));
				l2.setForeground(Color.black);
				b1.setBounds(g2.army2.getUnits().get(k).getX(),g2.army2.getUnits().get(k).getY(),110,50);
				b1.setLayout(null);
				l3.setBounds(0,0,110,15);
				l1.setBounds(0,18,110,15);
				l2.setBounds(0,35,110,15);
				//b1.add(l1);
				//b1.add(l2);
				//b1.add(l3);
				//l1.requestFocus();
				//l2.requestFocus();
				//l3.requestFocus();
				this.add(b1);
			}
			
		}
		else if(this.g2.battleoption) {
			g.drawImage(BattleOption,0,0,g2.getWidth(),g2.getHeight(),this);
			
			JLabel a1=null;
			JButton b1=new JButton("Lay Siege");
			JButton b2=new JButton("Auto Resolve");
			JButton b3=new JButton("Manual Battle");
			JButton b4=new JButton("Return");
			
			
			b4.setIcon(Returnn);
			b4.setBounds((int)(this.getWidth()/2 + 0.35*this.getWidth()/2),(int)(g2.getHeight()/1.4) ,319,102);
			b4.setContentAreaFilled(false);
			b4.setBorderPainted(false);
			b4.addActionListener(g2.l);
			if(g2.l.marching.getCurrentStatus().equals(Status.BESIEGING))
				b1.setEnabled(false);
			b1.setBounds((int)(this.getWidth()/2.6),(int)(g2.getHeight()/2.7),453,126);
			b2.setBounds((int)(this.getWidth()/2.6),(int)(g2.getHeight()/2.7 +150),453,126);
			b3.setBounds((int)(this.getWidth()/2.6),(int)(g2.getHeight()/2.7+300),453,126);
			
			b1.setContentAreaFilled(false);
			b2.setContentAreaFilled(false);
			b3.setContentAreaFilled(false);
			b1.setBorderPainted(false);
			b2.setBorderPainted(false);
			b3.setBorderPainted(false);
			
			b1.setIcon(LaySiege);
			b2.setIcon(Auto);
			b3.setIcon(Manual);
			
			b1.setBackground(Color.yellow);
			b2.setBackground(Color.yellow);
			b3.setBackground(Color.yellow);
			b1.addActionListener(g2.l);
			b2.addActionListener(g2.l);
			b3.addActionListener(g2.l);
			if(!fight)
				a1=new JLabel("Your Army reached "+g2.targetcity+". Choose what you want to do");
			else if(fight) {
				a1=new JLabel("You have to fight now" +" choose how do you want to fight");
				b2.setBounds((int)(this.getWidth()/2-225),g2.getHeight()/4+100,453,126);
				b3.setBounds((int)(this.getWidth()/2-225),g2.getHeight()/4+100+300,453,126);
			}
			a1.setBounds(g2.getWidth()/4-10,g2.getHeight()/4-20,1000,150);
			a1.setFont(new Font("Garamond",Font.BOLD,40));
			a1.setForeground(Color.WHITE);
			add(a1);
			if(!fight) {
				add(b1);
				add(b4);
			}	
			add(b2);
			add(b3);
			b1.requestFocus();
			b2.requestFocus();
			b3.requestFocus();
			b4.requestFocus();
			a1.requestFocus();
		
		
		}
		else if(this.g2.start) {
			g.drawImage(i,0,0,g2.getWidth(),g2.getHeight(),this);
			
			g.setFont(new Font("Garamond",Font.PLAIN,50));
			g.setColor(Color.blue);
			//g.drawString("Player Name",50, 200);
			g.drawImage(NameField,170,g2.getHeight()/5,400,150,this);
			//g.drawString("Choose your city", 200, 400);
			g.drawImage(Choose,170,g2.getHeight()/4+100,400,50,this);
			f2.setFont(new Font("Garamond",Font.PLAIN,25));			
			f2.setBounds(g2.getWidth()/8  ,(int)(g2.getHeight()/4.8) +48,250,30);
			f2.setBackground(new Color(11,44,111));
			f2.addMouseListener(g2.l);
			f2.setForeground(Color.WHITE);
			this.add(f2);
			
			b1.setActionCommand("Start Game");
//			g.drawImage(i5,(this.getWidth()/2)+(this.getWidth()/4) ,this.getHeight()-200,300,100,this);
			b1.setIcon(butStart);
			b1.setBounds( this.getWidth()-(int)(0.27*this.getWidth()) ,(this.getHeight()-(int)(0.18*this.getHeight())),400,100);
	
			b1.setContentAreaFilled(false);
			b1.setBorderPainted(false);

			
			b2.setActionCommand("Cairo");
			
			b2.setIcon(butCairo);
//			g.drawImage(i6,200,500,300,100,this);
			b2.setBounds(200,g2.getHeight()/4 +g2.getHeight()/6,340,100);
			b2.setContentAreaFilled(false);
			b2.setBorderPainted(false);
			//this.validate();
			
			
			b3.setActionCommand("Sparta");
			b3.setBounds(200,g2.getHeight()/4+(int)(g2.getHeight()/3.5),340,100);
			b3.setIcon(butSparta);
//			g.drawImage(Spartabut,200,610,300,100,this);
			b3.setContentAreaFilled(false);
			b3.setBorderPainted(false);
			
			b4.setActionCommand("Rome");
			b4.setIcon(butRome);
//			g.drawImage(Romebut,200,720,300,100,this);
			b4.setBounds(200,g2.getHeight()/4 +(int)(g2.getHeight()/2.5) ,340,100);
			b4.setContentAreaFilled(false);
			b4.setBorderPainted(false);
			
			b1.setBackground(new Color(153,92,0));
			b2.setBackground(new Color(153,92,0));
			b3.setBackground(new Color(153,92,0));
			b4.setBackground(new Color(153,92,0));
			b1.addActionListener(g2.l);
			b2.addActionListener(g2.l);
			b3.addActionListener(g2.l);
			b4.addActionListener(g2.l);
			this.add(b1);
			this.add(b2);
			this.add(b3);
			this.add(b4);
			f2.requestFocus();
/*			b1.requestFocus();
			b2.requestFocus();
			b3.requestFocus();
			b4.requestFocus();*/
			g2.name=f2.getText();
		}else if(this.g2.map) {
			this.remove(f2);
			g.setFont(new Font("Garamond",Font.BOLD,30));
			g.setColor(Color.WHITE);
			g.drawImage(i2,0,0,g2.getWidth(),g2.getHeight(),this);
			g.drawString("Turn:" ,g2.getWidth()/28,g2.getHeight()/12);
			g.drawString(this.g2.g1.getCurrentTurnCount()+"/50",g2.getWidth()/28 +10,g2.getHeight()/12+30) ;
			g.drawString("Gold: "+this.g2.g1.getPlayer().getTreasury(),(int)(g2.getWidth()/1.8) , g2.getHeight()/10-5);
			g.drawString("Food: "+this.g2.g1.getPlayer().getFood(),	(int)((g2.getWidth()/2.7 )) ,g2.getHeight()/10-5);
			g.drawString("Name: "+this.g2.name,g2.getWidth()/10 +5,g2.getHeight()/10-5);
			
			JButton b1=new JButton();
			b1.setActionCommand("Cairo");
			b1.setContentAreaFilled(false);
			b1.setBorderPainted(false);
			b1.setBounds(this.getWidth()/2-500,this.getHeight()/2+100,240,250);
			g.drawImage(i7,this.getWidth()/2-470,this.getHeight()/2+70,200,200,this);
			
			
			JButton b2=new JButton();
			b2.setActionCommand("Sparta");		
			b2.setContentAreaFilled(false);
			b2.setBorderPainted(false);
			b2.setBounds(this.getWidth()/2-380,this.getHeight()/2-360,200,200);
			g.drawImage(i8,this.getWidth()/2-370,this.getHeight()/2 -300,200,200,this);
			
			JButton b3=new JButton();
			b3.setActionCommand("Rome");
			b3.setContentAreaFilled(false);
			b3.setBorderPainted(false);
			g.drawImage(i9,this.getWidth()/2+230,this.getHeight()/2-92,200,200,this);
			b3.setBounds(this.getWidth()/2+220,this.getHeight()/2-70,220,240);
			
			b1.addActionListener(this.g2.l);
			b2.addActionListener(this.g2.l);
			b3.addActionListener(this.g2.l);
			this.add(b1);
			this.add(b2);
			this.add(b3);
			g.drawString("9",(b2.getX()+b3.getX())/2,(b2.getY()+b3.getY())/2 +100);
			g.drawString("6",(b1.getX()+b3.getX())/2,(b1.getY()+b3.getY())/2 +100);
			g.drawString("5",(b1.getX()+b2.getX())/2 +200,(b1.getY()+b3.getY())/2);
			this.add(this.endturn);
//			g.drawImage(i3,this.getWidth()-310,(this.getHeight()/2 -50) ,250,100,this);
			JButton b4=new JButton("");
			b4.setIcon(ShowArmy);
		//	g.drawImage(ShowArm,50,370,200,150,this);		
			b4.setContentAreaFilled(false);
			b4.setBorderPainted(false);
			b4.addActionListener(this.g2.l);
			b4.setBackground(Color.yellow);
			b4.setActionCommand("Show Armies");
			b4.addMouseListener(this.g2.l);
			b4.setBounds(50,400,200,100);
			this.add(b4);
			
			for(int i1 = 0 ; i1<this.g2.g1.getPlayer().getControlledArmies().size() ; i1++){
				
				JButton MarchingArmy = new JButton() ;
						MarchingArmy.setContentAreaFilled(false);
						MarchingArmy.setIcon(March);
						
					if(!this.g2.g1.getPlayer().getControlledArmies().get(i1).getTarget().equals("")){
						MarchingArmy.setBounds(this.g2.g1.getPlayer().getControlledArmies().get(i1).getX(),this.g2.g1.getPlayer().getControlledArmies().get(i1).getY(),150,80);

						if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getMarchFrom().equals("Cairo")){
							if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getTarget().equals("Sparta")){
							MarchingArmy.setIcon(CairoSparta);						
							MarchingArmy.setBounds(this.g2.g1.getPlayer().getControlledArmies().get(i1).getX(),this.g2.g1.getPlayer().getControlledArmies().get(i1).getY(),80,150);
							}
							else MarchingArmy.setIcon(March);
						}
						if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getMarchFrom().equals("Rome")){
							MarchingArmy.setIcon(MarchRom);
						}
						if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getMarchFrom().equals("Sparta")){
							if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getTarget().equals("Cairo")){
								MarchingArmy.setIcon(SpartaCairo);
								MarchingArmy.setBounds(this.g2.g1.getPlayer().getControlledArmies().get(i1).getX(),this.g2.g1.getPlayer().getControlledArmies().get(i1).getY(),80,150);
							}else MarchingArmy.setIcon(March);
						}
						
						
						
					}
					
					
					add(MarchingArmy);
					}
			
			
			if(this.g2.displayArmy) {
			    f3=new JFrame();
				p1.removeAll();
				f3.setSize(700,400);
				p1.setSize(700,400);
				p1.setLayout(new BorderLayout());
				f3.setLocation((g2.getWidth()-700)/2,(g2.getHeight()-400)/2 -50);
				f3.setTitle("Armies");
				f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f3.setLayout(new BorderLayout());
				//f3.addMouseListener(this.g2.l);
				//f3.addMouseMotionListener(this.g2.l);
				for(int j=0;j<this.g2.g1.getPlayer().getControlledArmies().size();j++) {
					if(g2.g1.getPlayer().getControlledArmies().get(j).getUnits().size()==0) {
						g2.g1.getPlayer().getControlledArmies().remove(g2.g1.getPlayer().getControlledArmies().get(j));
					}
				}
				int x=this.g2.g1.getPlayer().getControlledArmies().size();
				JButton[] arr=new JButton[x];
				if(arr.length==0) {
					JLabel l1=new JLabel("You do not have any army yet");
					l1.setFont(new Font("Arial",Font.PLAIN,40));
					p1.add(l1);
				}else {
					if(arr.length>6)
						f3.setExtendedState(JFrame.MAXIMIZED_BOTH);
					p1.setSize(new Dimension(f3.getWidth(),f3.getHeight()));
					p1.setLayout(new FlowLayout(FlowLayout.LEFT,100,50));
					for(int j=0;j<arr.length;j++) {
						arr[j]=new JButton("Army"+(j+1));
						arr[j].setPreferredSize(new Dimension(100,100));
						arr[j].setForeground(Color.white);
						arr[j].setIcon(new ImageIcon("images\\units.png"));
						arr[j].setContentAreaFilled(false);
						arr[j].setBorderPainted(false);
						JLabel k = new JLabel("ARMY "+(j+1));
						k.setForeground(Color.WHITE);
						k.setFont(new Font("Garamond Bold",Font.PLAIN,16));
						arr[j].add(k);
						p1.add(arr[j]);
						arr[j].addActionListener(this.g2.l);
					}
				}
				f3.add(p1);
				f3.setVisible(true);
				this.g2.displayArmy=false;
			}
		}else if(this.g2.incity) {
			this.add(this.endturn);
			if(g2.l.thecity.equals("Cairo"))
				g.drawImage(EgyView,0,0,this.getWidth(),this.getHeight(),this);		
				else
			g.drawImage(i4,0,0,this.getWidth(),this.getHeight(),this);		
			g.drawImage(i10,0,0,g2.getWidth(),g2.getHeight(),this);
//			g.drawImage(i3,this.getWidth()-300,(this.getHeight()/2 -50) ,250,100,this);
			
			g.setFont(new Font("Garamond",Font.BOLD,30));
			g.setColor(Color.WHITE);

			g.drawString("Turn:" ,g2.getWidth()/28,g2.getHeight()/12);
			g.drawString(this.g2.g1.getCurrentTurnCount()+"/50",g2.getWidth()/28 +10,g2.getHeight()/12+30) ;
			g.drawString("Gold: "+this.g2.g1.getPlayer().getTreasury(),(int)(g2.getWidth()/1.8) , g2.getHeight()/10-5);
			g.drawString("Food: "+this.g2.g1.getPlayer().getFood(),	(int)((g2.getWidth()/2.7 )) ,g2.getHeight()/10-5);
			g.drawString("Name: "+this.g2.name,g2.getWidth()/10 +5,g2.getHeight()/10-5);
			JButton build=new JButton();
			build.setActionCommand("Build");
			build.setContentAreaFilled(false);
			build.setBorderPainted(false);
			g.drawImage(Build, 0, 600, 200, 100, this);
			build.setBackground(Color.yellow);
			build.setBounds(0, 600, 200, 100);
			build.addActionListener(this.g2.l);
			build.addMouseListener(this.g2.l);
			this.add(build);
			for(int j=0;j<this.g2.g1.getPlayer().getControlledCities().size();j++) {
				if(this.g2.g1.getPlayer().getControlledCities().get(j).getName().equals(this.g2.l.thecity)) {
					for(int k=0;k<this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().size();k++) {
						JButton b=new JButton();
						String s="";
						if(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==1)
							b.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY(),100,100);
							else if (this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==2)
								b.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY(),150,150);
							else if (this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==3)
								b.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY(),180,180);

						if(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Farm)
							{s="Farm";
							if(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==1)
							tmp = iFarm;
							else if (this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==2)
								tmp= i2Farm ;
							else if (this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==3)
								tmp= i3Farm ;
							}else if(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Market)
							{	
								s="Market";
								if(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==1)
									tmp = iMarket;
									else if (this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==2)
										tmp= i2Market ;
									else if (this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getLevel()==3)
										tmp= i3Market ;
							}
						
						else
							s="Stable";
						b.setActionCommand(s);
					    b.setBorderPainted(false);
						b.setContentAreaFilled(false);
						Image aa = tmp.getScaledInstance(b.getHeight(), b.getWidth(), Image.SCALE_SMOOTH);
						b.setIcon(new ImageIcon(aa));
						this.buld.setIcon(new ImageIcon(tmp));
						buld.setContentAreaFilled(false);
						buld.setBorderPainted(false);
						b.addActionListener(this.g2.l);
						//b.addMouseListener(g2.l);
						b.addMouseMotionListener(g2.l);
						if(edit!=null && edit.equals(s)) {
							b.setEnabled(false);
							JButton b1=new JButton("Information");
							b1.setIcon(new ImageIcon("images\\Information.png"));
							b1.setBackground(new Color(242,208,170));

							//b1.setContentAreaFilled(false);
							//b1.setBorderPainted(false);
							b1.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY(),100,30);
							b1.addActionListener(this.g2.l);
							this.add(b1);
							JButton b2=new JButton("Upgrade");
							b2.setIcon(new ImageIcon("images\\Upgrade.png"));
							b2.setBackground(new Color(242,208,170));

							//b2.setContentAreaFilled(false);
							//b2.setBorderPainted(false);
							
						    b2.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY()+40,100,30);
							b2.addActionListener(this.g2.l);
							this.add(b2);
							
							JLabel l1=new JLabel(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getUpgradeCost()+"");
							l1.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX()+100,this.g2.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY()+30,50,50);
							l1.setFont(new Font("Arial",Font.PLAIN,20));
							if(this.g2.l.b.getLevel()!=3)
								this.add(l1);
						}	
						
						this.add(b);
					}
					for(int k=0;k<this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().size();k++) {
						String s="";
						if(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Barracks)
							{s="Barracks";
						if(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==1)
							tmp = EgyBar1;
						
							else if (this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==2)
								tmp= EgyBar2 ;
							else if (this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==3)
								tmp=  EgyBar3;
							}else if(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof ArcheryRange)
							{	s="ArcheryRange";
							if(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==1)
								tmp = EgyArchery1;
								else if (this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==2)
									tmp= EgyArchery2 ;
								else if (this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==3)
									tmp=  EgyArchery3;
							
							}else{
							s="Stable";
							if(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==1)
								tmp = EgyStable1;
								else if (this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==2)
									tmp= EgyStable2 ;
								else if (this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getLevel()==3)
									tmp=  EgyStable3;
							
							}
				
						
						JButton b=new JButton();
						b.setActionCommand(s);
						b.setBorderPainted(false);
						b.setContentAreaFilled(false);
						b.setIcon(new ImageIcon(tmp));
						this.buld.setIcon(new ImageIcon(tmp));
						buld.setContentAreaFilled(false);
						buld.setBorderPainted(false);
						b.addActionListener(this.g2.l);
						if(edit!=null && edit.equals(s)) {
							b.setEnabled(false);
							JButton b1=new JButton("Information");
							b1.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getY(),100,30);
							b1.addActionListener(this.g2.l);
							b1.setIcon(new ImageIcon("images\\Information.png"));
							b1.setBackground(new Color(242,208,170));
							//b1.setContentAreaFilled(false);
							//b1.setBorderPainted(false);
							
							this.add(b1);
							JButton b2=new JButton("Upgrade");
							b2.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getY()+40,100,30);
							b2.addActionListener(this.g2.l);
							b2.setIcon(new ImageIcon("images\\Upgrade.png"));
							b2.setBackground(new Color(242,208,170));

							//b2.setContentAreaFilled(false);
							//b2.setBorderPainted(false);
							this.add(b2);
							JLabel l1=new JLabel(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getUpgradeCost()+"");
							l1.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getX()+100,this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getY()+30,50,50);
							l1.setFont(new Font("Arial",Font.PLAIN,20));
							if(this.g2.l.b.getLevel()!=3)
								this.add(l1);
							JButton b3=new JButton("Recruit");
							b3.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getY()+40+40,100,30);
							b3.addActionListener(this.g2.l);
							this.add(b3);
							b3.setIcon(new ImageIcon("images\\Recruit.png"));
							b3.setBackground(new Color(242,208,170));
							//b3.setContentAreaFilled(false);
							//b3.setBorderPainted(false);
						}	
						
						b.setBounds(this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getX(),this.g2.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getY(),150,100);
						this.add(b);
						
					}
					break;
				}
			}	
			JButton army=new JButton();
			army.setActionCommand("Armies");
			army.setContentAreaFilled(false);
			army.setBorderPainted(false);
			army.setBackground(Color.yellow);
			g.drawImage(Armies,0,500,200,100,this);		
			army.setBounds(0,500,200,100);
			army.addActionListener(this.g2.l);
			army.addMouseListener(this.g2.l);
			this.add(army);
			if(drag) {
				buld.setBounds(this.g2.l.xmouse-20,this.g2.l.ymouse-20,150,100);
				buld.addMouseListener(this.g2.l);
				buld.setEnabled(false);
				//buld.addMouseMotionListener(this.g2.l);
				this.add(buld);
				

			}
			
			JButton map=new JButton();
			map.setActionCommand("World Map");
			map.setBorderPainted(false);
			map.setContentAreaFilled(false);
			map.setBackground(Color.yellow);
			map.setBounds(0,400,200,100);
			map.addActionListener(this.g2.l);
			map.addMouseListener(this.g2.l);
			this.add(map);
			g.drawImage(WorldMap,0,400,200,100,this);		
			if(this.g2.displayArmy) {    
				p1.removeAll();
				p1.repaint();
				f1.setSize(700,400);
				f1.setLocation((g2.getWidth()-700)/2,(g2.getHeight()-400)/2 -50);
				f1.setTitle("Armies");
				f1.addMouseListener(this.g2.l);
				f1.addMouseMotionListener(this.g2.l);
				int x=0;
				for(int i1=0;i1<this.g2.g1.getPlayer().getControlledArmies().size();i1++) {
					if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getUnits().size()==0) {
						this.g2.g1.getPlayer().getControlledArmies().remove(this.g2.g1.getPlayer().getControlledArmies().get(i1));
					}
				}
				for(int i1=0;i1<this.g2.g1.getPlayer().getControlledArmies().size();i1++) {
					if(this.g2.g1.getPlayer().getControlledArmies().get(i1).getCurrentLocation().equals(this.g2.l.thecity))
						x++;
				}
				JButton[] arr=new JButton[x];
				if(arr.length>4)
					f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
				p1.setLayout(new FlowLayout(FlowLayout.LEFT,100,50));
				JButton b1=new JButton("Defending Army");
				b1.setIcon(new ImageIcon("images\\Defending.png")); 
				b1.setContentAreaFilled(false);
				b1.setBorderPainted(false);
				b1.setPreferredSize(new Dimension(200,100));
				b1.addActionListener(this.g2.l);
				p1.add(b1);
				for(int j=0;j<arr.length;j++) {
					arr[j]=new JButton("Army"+(j+1));
					arr[j].setPreferredSize(new Dimension(100,100));
					arr[j].setForeground(Color.white);
					arr[j].setIcon(new ImageIcon("images\\units.png"));
					arr[j].setContentAreaFilled(false);
					arr[j].setBorderPainted(false);
					JLabel S = new JLabel("ARMY "+(j+1));
					S.setForeground(Color.WHITE);
					S.setFont(new Font("Garamond Bold",Font.PLAIN,16));
	
					arr[j].add(S);
					
					p1.add(arr[j]);
					arr[j].addActionListener(this.g2.l);
				}
				this.g2.displayArmy=false;
				f1.add(p1);
				f1.setVisible(true);
			}
		}
		
	}

}

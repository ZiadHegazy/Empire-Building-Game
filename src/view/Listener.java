package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class Listener implements ActionListener,MouseListener,MouseMotionListener,AdjustmentListener {
     GamePlay g;
     String num="";
     boolean attack; 
     Unit attacker;
     Unit defender;
     boolean onmove2;
     boolean turn=true;
     boolean onemove1;
     int reachx;
     int reachy;
     int count=0;
     int count2=0;
     int xx=80;
     int yy=4;
     int yyx=12;
     Sound sound = new Sound();

     
     JFrame p4=new JFrame();
     JTextArea jtemp=new JTextArea();
     ArrayList<Army> armyincity=new ArrayList();
     JButton ok=new JButton("OK");
     boolean target=false;
     Army marching=null;
     String thecity="";
     JFrame f=new JFrame();
     JFrame f1=new JFrame();
     JOptionPane mess=new JOptionPane();
     int xmouse;
     int ymouse;
     Unit unit;
     Army marchingT=null;
     boolean bolrel;
     boolean onbutton=false;
     JButton inf=new JButton("Information",new ImageIcon("images\\Information.png"));
     JButton rel=new JButton("Relocate",new ImageIcon("images\\Relocate.png"));
     
     JButton tar=new JButton("Target City",new ImageIcon("images\\Target.png"));
     JButton frm=new JButton("Farm");
     JButton market=new JButton("Market");
     JButton arch=new JButton("ArcheryRange");
     JButton bar=new JButton("Barracks");
     JButton stb=new JButton("Stable");
     JButton engage=new JButton("Fight",new ImageIcon("images\\Fight.png"));
     JPanel p1=new JPanel();
     JLabel l1=new JLabel();
     JLabel l2=new JLabel();
     JLabel l3=new JLabel();
     JButton bUnit=null;
     boolean buy=false;
     Building b;
     BufferedImage econBuilding = null;
	 BufferedImage MiliBuilding = null;
	 BufferedImage Message = null;
	 
	 
	 
	 
	public Listener(GamePlay g1) {
		g=g1;
		frm.addMouseListener(this);
		market.addMouseListener(this);
		arch.addMouseListener(this);
		bar.addMouseListener(this);
		stb.addMouseListener(this);
		try {
			econBuilding= ImageIO.read(new File("images\\Econ.png"));
			Message =  ImageIO.read(new File("images\\message.png"));


			MiliBuilding= ImageIO.read(new File("images\\Milit.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p4.setUndecorated(true);
	}
	public  boolean find(int x,int y,String s) {
		int xtemp=0;
		int ytemp=0;
		if(s.equals("up")) {
			ytemp=y-70;
			xtemp=x;
		}else if(s.equals("down")) {
			ytemp=y+70;
			xtemp=x;
		}
		else if(s.equals("right")) {
			ytemp=y;
			xtemp=x+120;
		}else if(s.equals("left")) {
			ytemp=y;
			xtemp=x-120;
		}else if(s.equals("upleft")) {
			ytemp=y-70;
			xtemp=x-120;
		}else if(s.equals("downleft")) {
			ytemp=y+70;
			xtemp=x-120;
		}else if(s.equals("upright")) {
			ytemp=y-70;
			xtemp=x+120;
		}
		else if(s.equals("downright")) {
			ytemp=y+70;
			xtemp=x+120;
		}
		for(int i=0;i<g.army1.getUnits().size();i++) {
			if(xtemp==g.army1.getUnits().get(i).getX() && ytemp==g.army1.getUnits().get(i).getY() ) {
				return false;
			}
		}
		for(int i=0;i<g.army2.getUnits().size();i++) {
			if(xtemp==g.army2.getUnits().get(i).getX() && ytemp==g.army2.getUnits().get(i).getY() ) {
				return false;
			}
		}
		return true;
	}
	public void mouseExited(MouseEvent arg0) {
		onbutton=false;
		buy=false;
		g.name=g.g.f2.getText();
		if(arg0.getComponent().equals(g.g.f2)){
			g.name=g.g.f2.getText();
			count=0;
		}
		if(g.manual &&(arg0.getComponent().equals(g) || arg0.getComponent().equals(g.g.endturn))) {
			p4.dispose();
			g.g.repaint();
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		onbutton=true;
		if(arg0.getComponent().equals(f) || arg0.getComponent().equals(frm) || arg0.getComponent().equals(market) || arg0.getComponent().equals(arch) || arg0.getComponent().equals(stb) || arg0.getComponent().equals(bar) )
			buy=true;
		if(arg0.getComponent().equals(g.g.f2)){
			g.name=g.g.f2.getText();
			count=0;
		}
		if(g.manual &&(arg0.getComponent().equals(g) || arg0.getComponent().equals(g.g.endturn))) {
			p4.dispose();
			g.g.repaint();
		}
		if(g.manual && ! arg0.getComponent().equals(g)) {
			String temp="";
			if(arg0.getComponent() instanceof JButton)
				temp=((JButton)arg0.getComponent()).getActionCommand();
			if(!temp.equals("endturn") && !temp.equals("")) {
				int i=0;
				String sI="";
				if(temp.charAt(0)=='/')
					i++;
				for(;i<temp.length() && temp.charAt(i)!='/';i++)
		    		sI+=temp.charAt(i);
		    	i++;
		    	String sX="";
		    	for(;i<temp.length() && temp.charAt(i)!='/';i++)
		    		sX+=temp.charAt(i);
		    	i++;
		    	String sY="";
		    	for(;i<temp.length() && temp.charAt(i)!='/';i++)
		    		sY+=temp.charAt(i);
		    	int ind=Integer.parseInt(sI);
		    	int x=Integer.parseInt(sX);
		    	int y=Integer.parseInt(sY);
		    	Unit u=null;
		    	String data="";
		    	if(temp.charAt(0)=='/') {
		    		u=g.army1.getUnits().get(ind);
		    		if(u instanceof Archer)
		    			data=data+"Type: "+"Archer";
		    		if(u instanceof Infantry)
		    			data=data+"Type: "+"Infantry";
		    		if(u instanceof Cavalry)
		    			data=data+"Type: "+"Cavalry";
		    		data =data+"\n" + "Level: "+u.getLevel()+"\n" + "Soldier Count:"+u.getCurrentSoldierCount()+"/"+u.getMaxSoldierCount();
		    		jtemp.setForeground(Color.cyan);
 
		    	}else if(temp.charAt(0)!='/') {
		    		data="Enemy" +"\n";
		    		u=g.army2.getUnits().get(ind);
		    		if(u instanceof Archer)
		    			data=data+"Type: "+"Archer";
		    		if(u instanceof Infantry)
		    			data=data+"Type: "+"Infantry";
		    		if(u instanceof Cavalry)
		    			data=data+"Type: "+"Cavalry";
		    		data =data+"\n" + "Level: "+u.getLevel()+"\n" + "Soldier Count:"+u.getCurrentSoldierCount()+"/"+u.getMaxSoldierCount();
		    		jtemp.setForeground(Color.RED);
 
		    	}
		    	p4.setLocation(x,y-50);
		    	p4.setSize(110,70);
		    	jtemp.setBackground(Color.black);
			
		    	jtemp.setText(data);
		    	jtemp.setEditable(false);
		    	jtemp.setBounds(0,0,110,50);
		    	p4.add(jtemp,BorderLayout.CENTER);
		    	p4.setVisible(true);
			}
			
		}
	}
		
	public void mouseClicked(MouseEvent arg0) {
		if(target)
			JOptionPane.showMessageDialog(g,"Please target a city","Alert",JOptionPane.WARNING_MESSAGE);
		if(this.g.g.edit!=null ) {
			this.g.g.edit=null;
			b=null;
			this.g.g.removeAll();
			this.g.g.repaint();
		}
		b=null;
		int[] arr=this.g.findAprrox(xmouse-20, ymouse-20);
		if(this.g.g.drag && (arg0.getComponent().equals(g))) {
			this.g.g.drag=false;
			for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
				if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
					for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().size();k++) {
						if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).equals(this.g.g1.getPlayer().getC()))
							continue;
						if(arr[0]==this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getX() && arr[1]==this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k).getY()) {
							JOptionPane.showMessageDialog(g.g,"You Cannot put it here","Alert",JOptionPane.WARNING_MESSAGE);
							this.g.g.drag=true;
							g.g.repaint();
							break;
						}		
					}
					for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().size();k++) {
						if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).equals(this.g.g1.getPlayer().getC()))
								continue;
						if(arr[0]==this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getX() && arr[1]==this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k).getY() ) {
							JOptionPane.showMessageDialog(g.g,"You Cannot put it here","Alert",JOptionPane.WARNING_MESSAGE);
							this.g.g.drag=true;
							g.g.repaint();
							break;
						}
					}
			  }
			}
			if(!this.g.g.drag) {
				this.g.g1.getPlayer().getC().setX(arr[0]);
				this.g.g1.getPlayer().getC().setY(arr[1]);
				this.g.g1.getPlayer().setC(null);
				this.g.g.removeAll();
				this.g.g.repaint();
				arch.setEnabled(true);
		    	bar.setEnabled(true);
		    	stb.setEnabled(true);
				for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
					if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
						for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().size();k++) {
							if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Barracks )
									bar.setEnabled(false);
							if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof ArcheryRange )
								arch.setEnabled(false);
							if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Stable )
								stb.setEnabled(false);
						}	
					}	
				}
				for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
					if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
						for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().size();k++) {
							if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Farm )
									frm.setEnabled(false);
							if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Market )
								this.market.setEnabled(false);
						}	
					}	
				}
				try {
					if(sound.clip!=null)
						sound.stopSound();
					sound.playSound("./sounds/Build.wav");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				f.repaint();
				f.show();
				count=0;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		xmouse=arg0.getXOnScreen();
		ymouse=arg0.getYOnScreen();
		if(g.start) {
			g.name=g.g.f2.getText();
			count=0;
		}
		if(g.g.drag) {
			g.g.repaint();
			count=0;
		}
		else if(!arg0.getComponent().equals(f)) {
			buy=false;
		}
		else if(f!=null && f.isActive() && arg0.getComponent().equals(f)) {
			buy=true;
		}else {
			buy=false;
			//unit=null;
		}	
			
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s=arg0.getActionCommand();
		if(g.g!=null && g.g.f2 !=null)
			g.name=g.g.f2.getText();
		if(g.start) {
			if(s!=null && s.equals("Cairo")) {
				g.g.b3.setEnabled(true);
				g.g.b2.setEnabled(false);
				g.g.b4.setEnabled(true);
				g.g.repaint();
				count=0;
				g.city=s;
			}else if(s!=null && s.equals("Rome")) {
				g.g.b3.setEnabled(true);
				g.g.b2.setEnabled(true);
				g.g.b4.setEnabled(false);
				g.g.repaint();
				count=0;
				g.city=s;
			}else if(s!=null && s.equals("Sparta")) {
				g.g.b3.setEnabled(false);
				g.g.b2.setEnabled(true);
				g.g.b4.setEnabled(true);
				g.g.repaint();
				count=0;
				g.city=s;	
			}else if(s!=null && s.equals("Start Game")) {
				if((g.city==null || g.name.equals("Enter your name")) && count==0) {
					 count++;
					 JOptionPane.showMessageDialog(g,"you must choose a city and a name","Alert",JOptionPane.WARNING_MESSAGE); 
			    }else if (g.city!=null && !g.name.equals("Enter your name")) {
			    	count=0;
					g.start=false;
					g.map=true;
					g.g.removeAll();
					g.g.repaint();
					g.repaint();
					try {
						g.g1=new Game(g.name,g.city);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		else if(s!=null && !s.equals(g.city) && !target &&  (s.equals("Rome") || s.equals("Cairo") || s.equals("Sparta"))) {
				JOptionPane.showMessageDialog(g,"You can not open a city you do not control","Alert",JOptionPane.WARNING_MESSAGE);
		}else if(s!=null && s.equals("Show Armies")) {
			if(this.g.g.f3!=null)
				this.g.g.f3.dispose();
			//this.g.g.f3=null;
			g.displayArmy=true;
			g.g.repaint();	
		}else if(s!=null && !bolrel && g.map && !g.manual &&  s.substring(0,4).equals("Army")) {
			count=0;
			String s2="";
			for(int i=s.length()-1;s.charAt(i)!='y';i--) {
	    		s2=s.charAt(i)+s2;
	    	}
			num=s2+"";
			Component[] arr=p1.getComponents();
			ArrayList<Component> arr2=new ArrayList<>();
			for(int i=0;i<arr.length;i++) {
				arr2.add(arr[i]);
			}
			marching=this.g.g1.getPlayer().getControlledArmies().get(Integer.parseInt(num)-1);
			inf.addActionListener(this.g.l);
			tar.addActionListener(this.g.l);
			engage.addActionListener(g.l);
			tar.setText("Target City");
			tar.setActionCommand("Target City");
			for(int i=0;i<this.g.g.p1.getComponentCount();i++) {
				if(this.g.g.p1.getComponent(i) instanceof JButton && ((JButton)this.g.g.p1.getComponent(i)).getText() .equals(s)) {
					inf.setBounds(this.g.g.p1.getComponent(i).getX()-100,this.g.g.p1.getComponent(i).getY()+5,100,50);
					tar.setBounds(this.g.g.p1.getComponent(i).getX()-100,this.g.g.p1.getComponent(i).getY()+65,100,50);
					inf.setContentAreaFilled(false);
					tar.setContentAreaFilled(false);
					tar.setIcon(new ImageIcon("images\\Target.png"));
					this.g.g.p1.getComponent(i).setEnabled(false);
					this.g.g.p1.setLayout(null);
					this.g.g.p1.add(inf);
					inf.requestFocus();
					this.g.g.p1.add(tar);
					tar.requestFocus();
					boolean flag=true;
					String s3=g.l.marching.getCurrentLocation();
					for(int j=0;j<g.g1.getPlayer().getControlledCities().size();j++) {
						if(g.g1.getPlayer().getControlledCities().get(j).getName().equals(s3) && !s3.equals("onRoad")) {
							flag=false;
							break;
						}	
					}
					if(marching.getCurrentLocation().equals("onRoad"))
						flag=false;
					if(flag) {
						engage.setBounds(this.g.g.p1.getComponent(i).getX()-100,this.g.g.p1.getComponent(i).getY()-50,100,50);
						this.g.g.p1.add(engage);
						engage.requestFocus();
					}else {
						this.g.g.p1.remove(engage);
						this.g.g.p1.repaint();
					}
				}else {
					this.g.g.p1.getComponent(i).setEnabled(true);
				}
			}
		   }else if(s!=null && this.g.map &&  s.equals("Information")) {
		    f.setVisible(false);
			f.setSize(500,500);
			f.setLocation((g.getWidth()-500)/2,(g.getHeight()-500)/2);
			f.setTitle(s+" Information");
			if(marching!=null && marching.getUnits().size()>6)
				f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			JTextArea l1=new JTextArea();
			l1.setFont(new Font("Arial",Font.PLAIN,20));
			l1.setEditable(false);
			String stat="Status: "+marching.getCurrentStatus();
			String loc="Location: "+marching.getCurrentLocation();
			if(!marching.getTarget().equals(""))
				loc="Location: on the road to "+marching.getTarget();
			String distance="";
			if(marching.getDistancetoTarget()!=-1 && ! marching.getCurrentStatus().equals(Status.BESIEGING))
				distance="Distance to target: "+marching.getDistancetoTarget();
			String sieg="";
			if(marching.getCurrentStatus()==Status.BESIEGING) {
				int z=0;
				for(int i=0;i<g.g1.getAvailableCities().size();i++) {
					if(g.g1.getAvailableCities().get(i).getName().equals(marching.getCurrentLocation()))
						z=g.g1.getAvailableCities().get(i).getTurnsUnderSiege();
				}
				sieg="Sieging Turns: "+z;
			}
			String s2=stat+"\n"+loc;
			if(!sieg.equals(""))
				s2+="\n"+sieg;
			if(!distance.equals(""))
				s2+="\n"+distance;
			l1.setText(s2);
			p1.removeAll();
				p1.setLayout(new BorderLayout());
				p1.setSize(new Dimension(f.getX(),f.getY()));
				p1.add(l1,BorderLayout.NORTH);
				JPanel p2=new JPanel();
				p2.setPreferredSize(new Dimension(f.getWidth(),f.getHeight()-200));
				
				JTextArea[] arr=new JTextArea[marching.getUnits().size()];
				if(arr.length==0) {
					JLabel l2=new JLabel("The army donot have units yet");
					l2.setFont(new Font("Arial",Font.PLAIN,35));
					p2.add(l1);
				}else {
					p2.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
					for(int i=0;i<arr.length;i++) {
						arr[i]=new JTextArea();
						arr[i].setEditable(false);
						arr[i].setFont(new Font("Arial",Font.PLAIN,15));
						Unit temp=marching.getUnits().get(i);
						String type="";
						if(temp instanceof Archer)
							type="Archer";
						if(temp instanceof Infantry)
							type="Infantry";
						if(temp instanceof Cavalry)
							type="Cavalry";
						String lev=temp.getLevel()+"";
						String curr=temp.getCurrentSoldierCount()+"";
						String max=temp.getMaxSoldierCount()+"";
						arr[i].setText("Unit "+(i+1)+"\n"+"Type: "+type +"\n"+"Level: "+lev+"\n"+"Current Soldiers: "+curr+"\n"+"Max Soldiers: "+max);
						p2.add(arr[i]);
					}
				}
				p1.add(p2);
				f.add(p1);
				f.setVisible(true);
			}
		    else if(s!=null && count==0 &&  s.equals("Target City")) {
		    	boolean flag=false;
		    	if(marching!=null && ! marching.getTarget().equals("") && count==0) {
		    		JOptionPane.showMessageDialog(g,"This army already have a target","Alert",JOptionPane.WARNING_MESSAGE);
		    		count++;
		    		flag=true;
		    	}
		    	
		    	else if(marching!=null && marching.getCurrentStatus().equals(Status.BESIEGING)) {
		    		for(int i=0;i<g.g1.getAvailableCities().size();i++) {
		    			if(g.g1.getAvailableCities().get(i).getName().equals(marching.getCurrentLocation())) {
		    				if(g.g1.getAvailableCities().get(i).getTurnsUnderSiege()==3 && count==0) {
		    					JOptionPane.showMessageDialog(g,"This army reached 3 sieging turns","Alert",JOptionPane.WARNING_MESSAGE);
		    					count++;
		    					flag=true;
		    					break;
		    				}
		    			}
		    		}
		    	}
		    	else if(s.equals(marching.getCurrentLocation()) && count==0) {
		    		JOptionPane.showMessageDialog(g,"This army is already in this city ","Alert",JOptionPane.WARNING_MESSAGE);
		    		count++;
		    		flag=true;
		    	}
		    	if(!flag) {
		    		this.g.g.f3.dispose();
		    		if(count==0) {
		    			JOptionPane.showMessageDialog(g,"Choose a city to target","informing",JOptionPane.WARNING_MESSAGE);
		    			count++;
		    		}	
			    	this.g.g.repaint();
			    	target=true;
			    	
		    	}
		    }else if (s!=null  && s.equals("Fight")){
		    	g.battleoption=true;
		    	g.targetcity=marching.getCurrentLocation();
		    	g.g.removeAll();
		    	g.g.repaint();
		    }else if(s!=null && !target && s.equals(g.city)) {
				g.map=false;
				g.start=false;
				g.incity=true;
				thecity=s;
				g.g.removeAll();
				g.g.repaint();
				g.repaint();
				
			}else if(s!= null && (s.equals("Cairo") || s.equals("Rome") || s.equals("Sparta")) &&  target){
				boolean flag=false;
				for(int i=0;i<this.g.g1.getPlayer().getControlledCities().size();i++) {
					if(this.g.g1.getPlayer().getControlledCities().get(i).getName().equals(s)) {
						JOptionPane.showMessageDialog(g,"You can not target a city you control","Alert",JOptionPane.WARNING_MESSAGE);
						flag=true;
						break;
					}	
				}
				for(int i=0;i<this.g.g1.getPlayer().getControlledArmies().size();i++) {
					if(this.g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals(s)) {
						JOptionPane.showMessageDialog(g,"The city is already targeted by another army","Alert",JOptionPane.WARNING_MESSAGE);
						flag=true;
						break;
					}	
				}
				if(marching.getCurrentLocation().equals(s)) {
					JOptionPane.showMessageDialog(g,"The Army already in this city","Alert",JOptionPane.WARNING_MESSAGE);
					flag=true;
				}
				if(!flag) {
					if(armyincity.contains(marching))
						armyincity.remove(marching);
					this.g.g1.targetCity(marching,s);
					JOptionPane.showMessageDialog(g,"You targeted "+s,"Confirm",JOptionPane.INFORMATION_MESSAGE);
					target=false;
		
					g.g.removeAll();
					if(s.equals("Rome")&&marching.getCurrentLocation().equals("Cairo"))
					{	marching.setX(g.g.getWidth()/2-300);
					 marching.setY(g.g.getHeight()/2+90);
					}
					else if(s.equals("Sparta")&&marching.getCurrentLocation().equals("Cairo"))
					{	marching.setX(g.g.getWidth()/2-370);
					 marching.setY(g.g.getHeight()/2+60);
					}
					else if(s.equals("Cairo")&&marching.getCurrentLocation().equals("Rome"))
					{	marching.setX(g.g.getWidth()/2+180);
					 marching.setY(g.g.getHeight()/2+20);
					}
					else if(s.equals("Sparta")&&marching.getCurrentLocation().equals("Rome"))
					{	marching.setX(g.g.getWidth()/2+200);
					 marching.setY(g.g.getHeight()/2-50);
					}
					else if(s.equals("Cairo")&&marching.getCurrentLocation().equals("Sparta"))
					{	marching.setX(g.g.getWidth()/2-330);
					 marching.setY(g.g.getHeight()/2-240);
					}
					else if(s.equals("Rome")&&marching.getCurrentLocation().equals("Sparta"))
					{	marching.setX(g.g.getWidth()/2-320);
					 marching.setY(g.g.getHeight()/2-240);
					}
					yy=4;
					yyx=12;
					
					
					marching=null;
					g.g.repaint();
					
				}
				
		}else if(s!=null && !this.g.g.drag &&   s.equals("World Map")) {
			g.map=true;
			g.incity=false;
			g.start=false;
			g.g.edit=null;
			g.displayArmy=false;
			
			
			g.g.removeAll();
			g.g.repaint();
			g.repaint();
			
			
	    	
		}else if(s!=null && !this.g.g.drag && !target &&!g.manual &&  s.equals("endturn")) {
			target=false;
			this.g.g1.endTurn();
			this.g.g.edit=null;
			this.g.g.removeAll();
			this.g.g.repaint();
			this.g.repaint();
			
			yy++;
			yyx--;
			
			int Syy= (yy)*(yy)/4;
			int Syyx= (yyx)*(yyx)/2	;
			for (int i = 0;  i<g.g1.getPlayer().getControlledArmies().size(); i++) {
				g.g.removeAll();

				if(g.g1.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)){
					if(g.g1.getPlayer().getControlledArmies().get(i).getMarchFrom().equals("Cairo")){
						if(g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals("Rome")){
							g.g1.getPlayer().getControlledArmies().get(i).setX(g.g1.getPlayer().getControlledArmies().get(i).getX()+100);
							g.g1.getPlayer().getControlledArmies().get(i).setY(g.g1.getPlayer().getControlledArmies().get(i).getY()-20);
						}
						if(g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals("Sparta")){
							g.g1.getPlayer().getControlledArmies().get(i).setX(g.g1.getPlayer().getControlledArmies().get(i).getX()+5);
							g.g1.getPlayer().getControlledArmies().get(i).setY(g.g1.getPlayer().getControlledArmies().get(i).getY()-60);
						}
					}
					if(g.g1.getPlayer().getControlledArmies().get(i).getMarchFrom().equals("Rome")){
						if(g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals("Cairo")){
							g.g1.getPlayer().getControlledArmies().get(i).setX(g.g1.getPlayer().getControlledArmies().get(i).getX()-100);
							g.g1.getPlayer().getControlledArmies().get(i).setY(g.g1.getPlayer().getControlledArmies().get(i).getY()+20);
						}
						if(g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals("Sparta")){
							g.g1.getPlayer().getControlledArmies().get(i).setX(g.g1.getPlayer().getControlledArmies().get(i).getX()-60);
							g.g1.getPlayer().getControlledArmies().get(i).setY(g.g1.getPlayer().getControlledArmies().get(i).getY()-Syyx);
						}
					}	
					if(g.g1.getPlayer().getControlledArmies().get(i).getMarchFrom().equals("Sparta")){
						if(g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals("Cairo")){
							g.g1.getPlayer().getControlledArmies().get(i).setX(g.g1.getPlayer().getControlledArmies().get(i).getX()-5);
							g.g1.getPlayer().getControlledArmies().get(i).setY(g.g1.getPlayer().getControlledArmies().get(i).getY()+60);
						}
						if(g.g1.getPlayer().getControlledArmies().get(i).getTarget().equals("Rome")){
							g.g1.getPlayer().getControlledArmies().get(i).setX(g.g1.getPlayer().getControlledArmies().get(i).getX()+xx);
							g.g1.getPlayer().getControlledArmies().get(i).setY(g.g1.getPlayer().getControlledArmies().get(i).getY()+Syy);
						}
					}
					
				}
				
				g.g.repaint();
			}
			for(int i=0; g.g1 !=null && i<g.g1.getPlayer().getControlledArmies().size();i++) {
				if(g.g1.getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0 && !g.g1.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING) && !armyincity.contains(g.g1.getPlayer().getControlledArmies().get(i))) {
					marching=g.g1.getPlayer().getControlledArmies().get(i);
					g.targetcity=marching.getCurrentLocation();
					this.armyincity.add(marching);
					int a=JOptionPane.showConfirmDialog(g,"Your army reached "+g.g1.getPlayer().getControlledArmies().get(i).getCurrentLocation()+" do you want to fight ","Confirm",JOptionPane.INFORMATION_MESSAGE);
					if(a==JOptionPane.YES_OPTION) {
						g.battleoption=true;
						g.g.removeAll();
						g.g.repaint();
					}
				}
					
			}
			for(int i=0; g.g1 !=null && i<g.g1.getAvailableCities().size();i++) {
				if(g.g1.getAvailableCities().get(i).getTurnsUnderSiege()==3) {
					for(int j=0;j<g.g1.getPlayer().getControlledArmies().size();j++) {
						if(g.g1.getPlayer().getControlledArmies().get(j).getCurrentLocation().equals(g.g1.getAvailableCities().get(i).getName())) {
							marching=g.g1.getPlayer().getControlledArmies().get(j);
							g.battleoption=true;
							g.g.fight=true;
							g.g.removeAll();
							g.g.repaint();
						}
					}
				}
			}
		}
		else if(s!=null && !this.g.g.drag && s.equals("Armies")) {
			this.g.displayArmy=true;
			this.g.g.repaint();
		}
		else if(s!=null && !bolrel &&  s.equals("Defending Army")) {
			Army a=null;
			for(int i=0;i<g.g1.getAvailableCities().size();i++) {
				if(g.g1.getAvailableCities().get(i).getName().equals(thecity))
					a=g.g1.getAvailableCities().get(i).getDefendingArmy();
			}
			marching=a;
			JButton[] arr=new JButton[a.getUnits().size()];
			f.dispose();
			f=new JFrame();
			f.setLayout(new BorderLayout());
			p1.removeAll();
			f.setSize(500,500);
			f.setLocation((g.getWidth()-500)/2,(g.getHeight()-500)/2);
			f.setTitle(s+" Information");
			
			if(arr.length>6)
				f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			p1.setLayout(new BorderLayout());
			p1.setPreferredSize(new Dimension(f.getX(),f.getY()));
			if(arr.length==0) {
				JLabel l1=new JLabel("the army do not have units yet");
				l1.setFont(new Font("Arial",Font.PLAIN,35));
				p1.add(l1);
			}else {
				p1.setLayout(new FlowLayout(FlowLayout.LEFT,100,50));
				for(int i=0;i<arr.length;i++) {
					arr[i]=new JButton("Unit"+(i+1));
					arr[i].setLayout(null);
					if(marching.getCurrentLocation().equals("Cairo")){
						if(marching.getUnits().get(i) instanceof Archer)
							arr[i].setIcon(new ImageIcon("images\\ArcherIconCairo.jpg"));
						if(marching.getUnits().get(i) instanceof Infantry)
							arr[i].setIcon(new ImageIcon("images\\InfantryIconCairo.jpg"));
						if(marching.getUnits().get(i) instanceof Cavalry)
							arr[i].setIcon(new ImageIcon("images\\CavalryIconCairo.jpg"));
						
					}if(marching.getCurrentLocation().equals("Rome")){
						if(marching.getUnits().get(i) instanceof Archer)
							arr[i].setIcon(new ImageIcon("images\\ArcherIconRome.jpg"));
						if(marching.getUnits().get(i) instanceof Infantry)
							arr[i].setIcon(new ImageIcon("images\\InfantryIconRome.jpg"));
						if(marching.getUnits().get(i) instanceof Cavalry)
							arr[i].setIcon(new ImageIcon("images\\CavalryIconRome.jpg"));
						
					}if(marching.getCurrentLocation().equals("Sparta")){
						if(marching.getUnits().get(i) instanceof Archer)
							arr[i].setIcon(new ImageIcon("images\\ArcherIconSparta.jpg"));
						if(marching.getUnits().get(i) instanceof Infantry)
							arr[i].setIcon(new ImageIcon("images\\InfantryIconSparta.jpg"));
						if(marching.getUnits().get(i) instanceof Cavalry)
							arr[i].setIcon(new ImageIcon("images\\CavalryIconSparta.jpg"));
						
					}
					
					arr[i].setPreferredSize(new Dimension(100,100));
					arr[i].setBackground(Color.BLACK);
					arr[i].addActionListener(g.l);
					JLabel type=new JLabel();
					type.setFont(new Font("Garamond",Font.BOLD,20));
					type.setForeground(Color.white);
					if(marching.getUnits().get(i) instanceof Archer)
						type.setText("Archer");
					if(marching.getUnits().get(i) instanceof Infantry)
						type.setText("Infantry");
					if(marching.getUnits().get(i) instanceof Cavalry)
						type.setText("Cavalry");
					JLabel lev = new JLabel();
					lev.setText(""+marching.getUnits().get(i).getLevel());
					lev.setFont(new Font("Garamond",Font.BOLD,35));
					lev.setForeground(Color.WHITE);
					lev.setBounds(70,60,30,40);
					type.setBounds(0,0,100,20);
					arr[i].add(type);
					arr[i].add(lev);
					p1.add(arr[i]);
				}
			}
			f.add(p1);
			f.setVisible(true);
		}
	    else if(s!=null && this.g.incity && !bolrel && !g.map && !g.manual &&  s.substring(0,4).equals("Army")) {
			Army a=null;
			String s2="";
			for(int i=s.length()-1;s.charAt(i)!='y';i--) {
	    		s2=s.charAt(i)+s2;
	    	}
			int x=Integer.parseInt(s2+"")  ;
			int y=0;
			for(y=0;x!=0 && y<this.g.g1.getPlayer().getControlledArmies().size();y++) {
				if(this.g.g1.getPlayer().getControlledArmies().get(y).getCurrentLocation().equals(thecity)) {
					x-=1;
				}
				if(x==0)
					break;
			}
			marching=this.g.g1.getPlayer().getControlledArmies().get(y);
			JButton[] arr=new JButton[marching.getUnits().size()];
			//if(f!=null)
				//f.dispose();
			//f=new JFrame();
			f.setLayout(new BorderLayout());
			p1.removeAll();
			f.setSize(500,500);
			f.setLocation((g.getWidth()-500)/2,(g.getHeight()-500)/2);
			f.setTitle(s+" Information");
			if(arr.length>6)
				f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			p1.setLayout(new BorderLayout());
			p1.setPreferredSize(new Dimension(f.getX(),f.getY()));
			
			if(arr.length==0) {
				JLabel l1=new JLabel("the army do not have units yet");
				l1.setFont(new Font("Arial",Font.PLAIN,35));
				p1.add(l1);
			}else {
				p1.setLayout(new FlowLayout(FlowLayout.LEFT,100,50));
				for(int i=0;i<arr.length;i++) {
					arr[i]=new JButton("Unit"+(i+1));
					arr[i].setLayout(null);
					
					if(marching.getCurrentLocation().equals("Cairo")){
						if(marching.getUnits().get(i) instanceof Archer)
							arr[i].setIcon(new ImageIcon("images\\ArcherIconCairo.jpg"));
						if(marching.getUnits().get(i) instanceof Infantry)
							arr[i].setIcon(new ImageIcon("images\\InfantryIconCairo.jpg"));
						if(marching.getUnits().get(i) instanceof Cavalry)
							arr[i].setIcon(new ImageIcon("images\\CavalryIconCairo.jpg"));
						
					}if(marching.getCurrentLocation().equals("Rome")){
						if(marching.getUnits().get(i) instanceof Archer)
							arr[i].setIcon(new ImageIcon("images\\ArcherIconRome.jpg"));
						if(marching.getUnits().get(i) instanceof Infantry)
							arr[i].setIcon(new ImageIcon("images\\InfantryIconRome.jpg"));
						if(marching.getUnits().get(i) instanceof Cavalry)
							arr[i].setIcon(new ImageIcon("images\\CavalryIconRome.jpg"));
						
					}if(marching.getCurrentLocation().equals("Sparta")){
						if(marching.getUnits().get(i) instanceof Archer)
							arr[i].setIcon(new ImageIcon("images\\ArcherIconSparta.jpg"));
						if(marching.getUnits().get(i) instanceof Infantry)
							arr[i].setIcon(new ImageIcon("images\\InfantryIconSparta.jpg"));
						if(marching.getUnits().get(i) instanceof Cavalry)
							arr[i].setIcon(new ImageIcon("images\\CavalryIconSparta.jpg"));
						
					}
					
					arr[i].setPreferredSize(new Dimension(100,100));
					arr[i].setBackground(Color.BLACK);
					arr[i].addActionListener(g.l);
					JLabel type=new JLabel();
					type.setFont(new Font("Garamond",Font.BOLD,20));
					type.setForeground(Color.white);
					if(marching.getUnits().get(i) instanceof Archer)
						type.setText("Archer");
					if(marching.getUnits().get(i) instanceof Infantry)
						type.setText("Infantry");
					if(marching.getUnits().get(i) instanceof Cavalry)
						type.setText("Cavalry");
					JLabel lev = new JLabel();
					lev.setText(""+marching.getUnits().get(i).getLevel());
					lev.setFont(new Font("Garamond",Font.BOLD,35));
					lev.setForeground(Color.WHITE);
					lev.setBounds(70,60,30,40);
					type.setBounds(0,0,100,20);
					arr[i].add(type);
					arr[i].add(lev);
					arr[i].setPreferredSize(new Dimension(100,100));
					arr[i].addActionListener(g.l);
					p1.add(arr[i]);
				}
			}
			f.add(p1);
			f.setVisible(true);
		}
	    else if(s!=null && !g.manual &&  s.substring(0,4).equals("Unit")) {
	    	String s2="";
	    	for(int i=s.length()-1;s.charAt(i)!='t';i--) {
	    		s2=s.charAt(i)+s2;
	    	}
	    	count=0;
	    	int x=Integer.parseInt(s2+"");
	    	x=x-1;
	    	unit=marching.getUnits().get(x);
	    	inf.addActionListener(this.g.l);
	    	rel.addActionListener(this.g.l);
	    	tar.setText("Initiate");
	    	tar.setIcon(new ImageIcon("images\\Initiate.png"));
	    	tar.setBackground(Color.LIGHT_GRAY);
	    	tar.setActionCommand("Initiate");
			tar.addActionListener(this.g.l);
			for(int i=0;i<p1.getComponentCount();i++) {
				if(p1.getComponent(i) instanceof JButton && ((JButton)p1.getComponent(i)).getText() .equals(s)) {
					inf.setBounds(p1.getComponent(i).getX()-100,p1.getComponent(i).getY(),100,50);
					rel.setBounds(p1.getComponent(i).getX()-100,p1.getComponent(i).getY()+60,100,50);
					tar.setBounds(p1.getComponent(i).getX()-100,p1.getComponent(i).getY()-50+60+60,100,50);
					inf.setContentAreaFilled(false);
					rel.setContentAreaFilled(false);
					tar.setContentAreaFilled(false);
					boolean flag=false;
					for(int j=0;j<g.g1.getAvailableCities().size();j++) {
						if(g.g1.getAvailableCities().get(j).getName().equals(thecity)) {
							if(g.g1.getAvailableCities().get(j).getDefendingArmy().equals(marching)) {
								flag=true;
								break;
							}
						}
					}
					p1.setLayout(null);
					p1.add(inf);
					p1.add(rel);
					if(flag) {
						inf.setBounds(p1.getComponent(i).getX()-100,p1.getComponent(i).getY()-50,100,50);
						rel.setBounds(p1.getComponent(i).getX()-100,p1.getComponent(i).getY()-50+60,100,50);
						p1.add(tar);
						tar.requestFocus();
					}	
					inf.requestFocus();
					rel.requestFocus();
				}
				f.add(p1);
				//f.repaint();
				f.setVisible(true);
			}
	    }
	    else if(s!=null && unit!=null && b==null && s.equals("Information")) {
	    	//p1.removeAll();
	    	//p1.setSize(new Dimension(300,200));
	    	//p1.setLayout(new BorderLayout());
	    	if(f!=null) {
	    		f1.dispose();
		    	f1=new JFrame();
	    	}
	    	f1.setSize(300,200);
	    	f1.setLocation(inf.getX()-50+f.getX(),inf.getY()+f.getY());
	    	f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	String s1="";
	    	if(unit instanceof Archer)
	    		s1="Type: "+"Archer"+"\n"+"Level: "+unit.getLevel()+"\n"+ "Soldier Count: "+unit.getCurrentSoldierCount()+"\n" +"Max Soldier Count: "+unit.getMaxSoldierCount();
	    	if(unit instanceof Infantry)
	    		s1="Type: "+"Infantry"+"\n"+"Level: "+unit.getLevel()+"\n"+ "Soldier Count: "+unit.getCurrentSoldierCount()+"\n" +"Max Soldier Count: "+unit.getMaxSoldierCount();
	    	if(unit instanceof Cavalry)
	    		s1="Type: "+"Cavalry"+"\n"+"Level: "+unit.getLevel()+"\n"+ "Soldier Count: "+unit.getCurrentSoldierCount()+"\n" +"Max Soldier Count: "+unit.getMaxSoldierCount();
	    	JTextArea j1=new JTextArea(s1);
	    	j1.setEditable(false);
	    	j1.setFont(new Font("Arial",Font.PLAIN,20));
	    	j1.setPreferredSize(new Dimension(300,200));
	    	f1.add(j1);
	    	//f1.add(p1);
	    	f1.setVisible(true);
	    	unit=null;
	    	p1.remove(inf);
	    	p1.remove(rel);
	    	p1.remove(tar);
	    	p1.repaint();
	    	
	    }else if(s!=null && unit!=null && s.equals("Initiate")) {
	    	f.dispose();
	    	this.g.g.removeAll();
	    	this.g.g.repaint();
	    	City c=null;
	    	for(int i=0;i<this.g.g1.getPlayer().getControlledCities().size();i++) {
	    		if(this.g.g1.getPlayer().getControlledCities().get(i).getName().equals(thecity))
	    			c=this.g.g1.getPlayer().getControlledCities().get(i);
	    	}
	    	this.g.g1.getPlayer().initiateArmy(c, unit);
	    	unit=null;
	    	this.g.g.removeAll();
	    	this.g.displayArmy=true;
	    	this.g.g.repaint();
	    }
	    else if(s!=null && unit!=null && !bolrel&& s.equals("Relocate")) {
	    	f.hide();
	    	if(g.g1.getPlayer().getControlledArmies().size()==0 && count==0) {
	    		count++;
	    		JOptionPane.showMessageDialog(g,"You do not have armies to relocate to","Alert",JOptionPane.WARNING_MESSAGE);
	    	}else if(g.g1.getPlayer().getControlledArmies().size()!=0) {
	    		JOptionPane.showMessageDialog(g,"Please choose an army","Confirm",JOptionPane.INFORMATION_MESSAGE);
		    	bolrel=true;
	    	}
	    	
	    }
	    else if(s!=null && unit!=null && bolrel && s.equals("Defending Army")) {
	    	boolean succ=true;
	    	for(int i=0;i<g.g1.getAvailableCities().size();i++) {
				if(g.g1.getAvailableCities().get(i).getName().equals(thecity))
					marchingT=g.g1.getAvailableCities().get(i).getDefendingArmy();
			}
	    	if(marching.equals(marchingT))
	    		JOptionPane.showMessageDialog(g,"Choose another army not the same army","Alert",JOptionPane.WARNING_MESSAGE);
	    	else {
	    		try {
					marchingT.relocateUnit(unit);
				} catch (MaxCapacityException e) {
					succ=false;
					JOptionPane.showMessageDialog(g,"this army already have 10 units","Alert",JOptionPane.WARNING_MESSAGE);
				}
				bolrel=false;
				unit=null;
				if(succ)
					JOptionPane.showMessageDialog(g,"You relocated the unit successfully","Confirm",JOptionPane.INFORMATION_MESSAGE);
				for(int i1=0;i1<this.g.g1.getPlayer().getControlledArmies().size();i1++) {
					if(this.g.g1.getPlayer().getControlledArmies().get(i1).getUnits().size()==0) {
						this.g.g1.getPlayer().getControlledArmies().remove(this.g.g1.getPlayer().getControlledArmies().get(i1));
					}
				}
				this.g.displayArmy=true;
				this.g.g.removeAll();
				this.g.g.repaint();
				
	    	}
	    }
	    else if(s!=null && unit!=null && bolrel && !g.manual && s.substring(0,4).equals("Army")) {
	    	boolean succ=true;
	    	String s2="";
			for(int i=s.length()-1;s.charAt(i)!='y';i--) {
	    		s2=s.charAt(i)+s2;
	    	}
	    	int x=Integer.parseInt(s2)  ;
			int y=0;
			for(y=0;x!=0 && y<this.g.g1.getPlayer().getControlledArmies().size();y++) {
				if(this.g.g1.getPlayer().getControlledArmies().get(y).getCurrentLocation().equals(thecity)) {
					x-=1;
				}
				if(x==0)
					break;
			}
			marchingT=this.g.g1.getPlayer().getControlledArmies().get(y);
			if(marching.equals(marchingT)) {
				JOptionPane.showMessageDialog(g,"Choose another army not the same army","Alert",JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					marchingT.relocateUnit(unit);
				} catch (MaxCapacityException e) {
					succ=false;
					JOptionPane.showMessageDialog(g,"this army already have 10 units","Alert",JOptionPane.WARNING_MESSAGE);
				}
				bolrel=false;
				unit=null;
				if(succ)
					JOptionPane.showMessageDialog(g,"You relocated the unit successfully","Confirm",JOptionPane.INFORMATION_MESSAGE);
			}
			for(int i1=0;i1<this.g.g1.getPlayer().getControlledArmies().size();i1++) {
				if(this.g.g1.getPlayer().getControlledArmies().get(i1).getUnits().size()==0) {
					this.g.g1.getPlayer().getControlledArmies().remove(this.g.g1.getPlayer().getControlledArmies().get(i1));
				}
			}
			this.g.g.removeAll();
			this.g.displayArmy=true;
			this.g.g.repaint();
			
	    }
	    else if(s!=null && !this.g.g.drag && s.equals("Build")) {
	    	p1.removeAll();
	    	f.setLocation(200,g.getHeight()-264);
	    	f.setSize(new Dimension(650,250));
	    	f.setResizable(false);
	    	f.setLayout(new BorderLayout());
	    	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	f.addMouseListener(g.l);
	    	JButton b1=new JButton("Economical Buildings");
	    	JButton b2=new JButton("Military Buildings");
	    	b1.addActionListener(g.l);
	    	b2.addActionListener(g.l);
	    	p1.setPreferredSize(new Dimension(f.getWidth(),f.getHeight()));
	    	p1.setLayout(null);
	    	b1.setBounds(0,10,200,50);
	    	b2.setBounds(210,10,200,50);
	    	b1.setIcon(new ImageIcon(econBuilding));
	    	b2.setIcon(new ImageIcon(MiliBuilding));
	    	b1.setContentAreaFilled(false);
	    	b2.setContentAreaFilled(false);
	    	b1.setOpaque(false);
	    	b2.setOpaque(false);
//	    	ImageIcon image = new ImageIcon("images\\Econ.png");
//	    	JLabel imageLabel = new JLabel(image); 
//	    	imageLabel.setBounds(0,10,200,50);
//	    	imageLabel.setVisible(true);
//	    	f.add(imageLabel);
	    	p1.add(b1);
	    	p1.add(b2);
	    	
	    	
	    	b1.requestFocus();
	    	b2.requestFocus();
	    	f.add(p1);
	    	f.setVisible(true);
	    	
	    }
	    else if(s!=null && s.equals("Economical Buildings")) {
	    	frm.setBounds(0,70,100,100);
	    	frm.setIcon(new ImageIcon("images\\Egypt-farm-1.png") );
	    	frm.setContentAreaFilled(false);

	    	market.setBounds(150,70,100,100);
	    	market.setIcon(new ImageIcon("images\\market-1-egy.png") );
	    	market.setContentAreaFilled(false);

	    	l1.setBounds(0,180,100,20);
	    	l2.setBounds(150,180,100,20);
	    	market.addActionListener(g.l);
	    	frm.addActionListener(g.l);
	    	frm.setEnabled(true);
	    	market.setEnabled(true);
	    	market.setEnabled(true);
	    	l1.setText("1000");
	    	l2.setText("1500");
	    	l1.setFont(new Font("Arial",Font.PLAIN,25));
	    	l2.setFont(new Font("Arial",Font.PLAIN,25));
	    	l3.setFont(new Font("Arial",Font.PLAIN,25));
	    	p1.remove(l3);
	    	p1.remove(arch);
	    	p1.remove(bar);
	    	p1.remove(stb);
	    	p1.add(frm);
	    	p1.add(market);
	    	p1.add(l1);
	    	p1.add(l2);
	    	for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
				if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
					for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().size();k++) {
						if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Farm)
								frm.setEnabled(false);
						if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Market)
							market.setEnabled(false);
					}	
				}	
			}
	    	f.add(p1);
	    	f.repaint();
	    	
	    }
	    else if(s!=null && s.equals("Military Buildings")) {
	    	arch.setBounds(0,70,150,100);
	    	arch.setIcon(new ImageIcon("images\\EgyArchery1.png") );
	    	arch.setContentAreaFilled(false);
	    	
	    	bar.setBounds(200,70,100,100);
	    	bar.setIcon(new ImageIcon("images\\EgyBarracks.png") );
	    	bar.setContentAreaFilled(false);
		    
	    	stb.setBounds(350,70,100,100);
	    	stb.setIcon(new ImageIcon("images\\EgyStable1.png") );
	    	stb.setContentAreaFilled(false);
	    	
	    	arch.addActionListener(g.l);
	    	bar.addActionListener(g.l);
	    	stb.addActionListener(g.l);
	    	arch.setEnabled(true);
	    	stb.setEnabled(true);
	    	bar.setEnabled(true);
	    	l1.setBounds(0,180,100,20);
	    	l2.setBounds(200,180,100,20);
	    	l3.setBounds(350,180,100,20);
	    	l1.setFont(new Font("Arial",Font.PLAIN,25));
	    	l2.setFont(new Font("Arial",Font.PLAIN,25));
	    	l3.setFont(new Font("Arial",Font.PLAIN,25));
	    	l1.setText("1500");
	    	l2.setText("2000");
	    	l3.setText("2500");
	    	p1.remove(frm);
	    	p1.remove(market);
	    	p1.add(arch);
	    	p1.add(stb);
	    	p1.add(bar);
	    	p1.add(l1);
	    	p1.add(l2);
	    	p1.add(l3);
	    	for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
				if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
					for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().size();k++) {
						if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Barracks)
								bar.setEnabled(false);
						if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof ArcheryRange)
							arch.setEnabled(false);
						if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Stable)
							stb.setEnabled(false);
					}	
				}	
			}
	    	f.add(p1);
	    	f.repaint();
	    	
	    }
	    else if(s!=null && buy && f!=null && f.isShowing()  && (s.equals("Farm") || s.equals("Market") || s.equals("ArcheryRange") || s.equals("Barracks") || s.equals("Stable"))) {
	    	buy=false;
	    	try {
				g.g1.getPlayer().build(s, thecity);
				g.g.drag=true;
				g.g.buld=null;
				g.g.buld=new JButton(s);
				g.g.type=s;
				g.g.repaint();
				f.hide();
			} catch (NotEnoughGoldException e) {
				if(f!=null)
					f.dispose();
				JOptionPane.showMessageDialog(g,"You do not have enough gold","Alert",JOptionPane.WARNING_MESSAGE); 
				
			}
	    	
	    }
	    else if(s!=null && !buy && !this.g.g.drag && (s.equals("Farm") || s.equals("Market")) ) {
	    	for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
				if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
					
					for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().size();k++) {
						if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Farm && s.equals("Farm")) {
							this.g.g.edit="Farm";
							b=this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k);
						}
						else if(this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k) instanceof Market && s.equals("Market")) {
							this.g.g.edit="Market";
							b=this.g.g1.getPlayer().getControlledCities().get(j).getEconomicalBuildings().get(k);
						}
					}	
				}
			}
	    	this.g.g.removeAll();
	    	this.g.g.repaint();
	    }
	    else if(s!=null && !buy && !g.g.drag && (s.equals("ArcheryRange") || s.equals("Barracks") || s.equals("Stable")) ) {
	    	for(int j=0;j<this.g.g1.getPlayer().getControlledCities().size();j++) {
				if(this.g.g1.getPlayer().getControlledCities().get(j).getName().equals(thecity)) {
					
					for(int k=0;k<this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().size();k++) {
						if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof ArcheryRange && s.equals("ArcheryRange")) {
							this.g.g.edit="ArcheryRange";
							b=this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k);
						}
						else if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Stable && s.equals("Stable")) {
							this.g.g.edit="Stable";
							b=this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k);
						}
						else if(this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k) instanceof Barracks && s.equals("Barracks")) {
							this.g.g.edit="Barracks";
							b=this.g.g1.getPlayer().getControlledCities().get(j).getMilitaryBuildings().get(k);
						}
					}	
				}
			}
	    	this.g.g.removeAll();
	    	this.g.g.repaint();
	    }else if(s!=null && s.equals("Recruit") && b!=null) {
	    	if(b.isCoolDown())
	    		JOptionPane.showMessageDialog(g,"The Building is under cooldown","Alert",JOptionPane.WARNING_MESSAGE);
	    	else {
	    		f.dispose();
		    	f=new JFrame();
		    	f.setLocation(b.getX()-180,b.getY()-100);
		    	f.setSize(530,250);
		    	p1.setPreferredSize(new Dimension(530,250));
		    	p1.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		    	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	p1.removeAll();
		    	//f.setVisible(true);
		    	int i=0;
		    	for(i=0;i<((MilitaryBuilding)b).getCurrentRecruit() ;i++) {
		    		JLabel a1=new JLabel();
		    		a1.setLayout(null);
		    		a1.setPreferredSize(new Dimension(150,100));
		    		a1.setFont(new Font("Arial",Font.PLAIN,18));
		    		String type="";
		    		String level="";
		    		if( (MilitaryBuilding) b instanceof ArcheryRange)
		    			type="Archer";
		    		if( (MilitaryBuilding) b instanceof Stable)
		    			type="Cavalry";
		    		if( (MilitaryBuilding) b instanceof Barracks)
		    			type="Infantry";
		    		level=b.getLevel()+"";
		    		JLabel l1=new JLabel(type);
		    		JLabel l2=new JLabel(level);
		    		l1.setForeground(Color.white);
		    		l2.setForeground(Color.white);
		    		l2.setFont(new Font("Garamond",Font.BOLD,23));
		    		l1.setFont(new Font("Garamond",Font.BOLD,20));
		    		l2.setBounds(85,60,30,40);
		    		l1.setBounds(0,0,150,20);
		    		a1.setIcon(new ImageIcon("images\\"+type+"Icon"+thecity+".jpg"));
		    		a1.add(l1);
		    		a1.add(l2);
		    		p1.add(a1);
		    		
		    	}
		    	int j=i;
		    	for(;i<3;i++) {
		    		JTextArea a1=new JTextArea("Empty");
		    		a1.setPreferredSize(new Dimension(150,100));
		    		a1.setFont(new Font("Arial",Font.PLAIN,18));
		    		a1.setEditable(false);
		    		p1.add(a1);
		    	}
		    	JButton b1=new JButton("Make a Unit");
		    	b1.setIcon(new ImageIcon("images\\MakeUnit.png"));
		    	b1.setContentAreaFilled(false);
		    	//b1.setBorderPainted(false);
		    	b1.setPreferredSize(new Dimension(200,50));
		    	b1.addActionListener(g.l);
		    	if(j==3)
		    		b1.setEnabled(false);
		    	p1.add(b1);
		    	String ss=((MilitaryBuilding) b).getRecruitmentCost()+"";
	    		JLabel l1=new JLabel("Cost :"+ss);
	    		l1.setFont(new Font("Arial",Font.PLAIN,20));
	    		l1.setPreferredSize(new Dimension(240,50));
	    		p1.add(l1);
		    	f.setLayout(new BorderLayout());
		    	f.add(p1);
		    	f.setVisible(true);
	    	}
	    	
	    }else if(s!=null && s.equals("Make a Unit")) {
	    		String type="";
	    		boolean flag=false;
	    		if(((MilitaryBuilding) b) instanceof ArcheryRange)
	    			type="Archer";
	    		if(((MilitaryBuilding) b) instanceof Stable)
	    			type="Cavalry";
	    		if(((MilitaryBuilding) b) instanceof Barracks)
	    			type="Infantry";
				try {
					this.g.g1.getPlayer().recruitUnit(type, thecity);
				} catch (NotEnoughGoldException e) {
					f.dispose();
					JOptionPane.showMessageDialog(g,"You do not have enough gold","Alert",JOptionPane.WARNING_MESSAGE);
					
					flag=true;
				}  catch (BuildingInCoolDownException e) {
					f.dispose();
					JOptionPane.showMessageDialog(g,"The Building is under cooldown","Alert",JOptionPane.WARNING_MESSAGE);
					
					flag=true;
				} catch (MaxRecruitedException e) {
					f.dispose();
					JOptionPane.showMessageDialog(g,"You made 3 recruits already","Alert",JOptionPane.WARNING_MESSAGE);
					
					flag=true;
				}
				if(!flag) {
					//if(f!=null)
						//f.dispose();
					this.g.g.removeAll();
					this.g.g.repaint();
					//f=new JFrame();
					p1.removeAll();
			    	//f.setLocation(b.getX()-200,b.getY()-120);
			    	//f.setSize(530,360);
			    	//f.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
			    	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    	p1.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
			    	int i=0;
			    	for(i=0;i<((MilitaryBuilding)b).getCurrentRecruit() ;i++) {
			    		JLabel a1=new JLabel();
			    		a1.setLayout(null);
			    		
			    		a1.setPreferredSize(new Dimension(150,100));
			    		a1.setFont(new Font("Arial",Font.PLAIN,18));
			    		String type2="";
			    		String level="";
			    		if( (MilitaryBuilding) b instanceof ArcheryRange)
			    			type2="Archer";
			    		if( (MilitaryBuilding) b instanceof Stable)
			    			type2="Cavalry";
			    		if( (MilitaryBuilding) b instanceof Barracks)
			    			type2="Infantry";
			    		level=b.getLevel()+"";
			    		JLabel l1=new JLabel(type2);
			    		JLabel l2=new JLabel(level);
			    		l1.setForeground(Color.white);
			    		l2.setForeground(Color.white);
			    		l2.setFont(new Font("Garamond",Font.BOLD,23));
			    		l1.setFont(new Font("Garamond",Font.BOLD,20));
			    		l2.setBounds(85,60,30,40);
			    		l1.setBounds(0,0,150,20);
			    		a1.setIcon(new ImageIcon("images\\"+type2+"Icon"+thecity+".jpg"));
			    		a1.add(l1);
			    		a1.add(l2);
			    		p1.add(a1);
			    		
			    	}
			    	int j=i;
			    	for(;i<3;i++) {
			    		JTextArea a1=new JTextArea("Empty");
			    		a1.setPreferredSize(new Dimension(150,100));
			    		a1.setFont(new Font("Arial",Font.PLAIN,18));
			    		a1.setEditable(false);
			    		p1.add(a1);
			    	}
			    	JButton b1=new JButton("Make a Unit");
			    	b1.setIcon(new ImageIcon("images\\MakeUnit.png"));
			    	b1.setContentAreaFilled(false);
			    	b1.setBorderPainted(false);
			    	b1.setPreferredSize(new Dimension(200,50));
			    	b1.addActionListener(g.l);
			    	if(j==3)
			    		b1.setEnabled(false);
			    	p1.add(b1);
			    	String ss=((MilitaryBuilding) b).getRecruitmentCost()+"";
		    		JLabel l1=new JLabel("Cost: "+ss);
		    		l1.setFont(new Font("Arial",Font.PLAIN,20));
		    		l1.setPreferredSize(new Dimension(240,50));
		    		p1.add(l1);
			    	f.add(p1);
			    	f.setVisible(true);
				}
							
	    }else if(s!=null && s.equals("Upgrade")) {
	    	try {
				
				this.g.g1.getPlayer().upgradeBuilding(b);
				
				try {
					if(sound.clip!=null)
						sound.stopSound();
					g.playSound("./sounds/Build.wav");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (BuildingInCoolDownException e) {
				JOptionPane.showMessageDialog(g,"The Building is in cooldown","Alert",JOptionPane.WARNING_MESSAGE);
			} catch (MaxLevelException e) {
				JOptionPane.showMessageDialog(g,"The Building reached max level","Alert",JOptionPane.WARNING_MESSAGE);
			} catch (NotEnoughGoldException e) {
				JOptionPane.showMessageDialog(g,"You do not have enough gold","Alert",JOptionPane.WARNING_MESSAGE);
			}
	    	this.g.g.removeAll();
	    	this.g.g.repaint();
	    }
	    else if(s!=null && unit==null && b!=null && s.equals("Information")) {
	    	p1.removeAll();
	    	//f1.dispose();
	    	f.dispose();
	    	f=new JFrame();
	    	f.setSize(200,100);
	    	f.setLocation(b.getX()-20,b.getY());
	    	f.setLayout(new BorderLayout());
	    	p1.setLayout(new BorderLayout());
	    	p1.setPreferredSize(new Dimension(f.getWidth(),f.getHeight()));
	    	JLabel l1=new JLabel("Type: "+this.g.g.edit);
	    	JLabel l2=new JLabel("Level: "+b.getLevel());
	    	l1.setPreferredSize(new Dimension(200,45));
	    	l2.setPreferredSize(new Dimension(200,45));
	    	l1.setFont(new Font("Arial",Font.PLAIN,20));
	    	l2.setFont(new Font("Arial",Font.PLAIN,20));
	    	p1.add(l1,BorderLayout.NORTH);
	    	p1.add(l2);
	    	f.add(p1);
	    	f.setVisible(true);
	    }else if(s!=null && g.battleoption &&  s.equals("Lay Siege")) {
	    	g.incity=false;
	    	g.map=true;
	    	g.battleoption=false;
	    	g.g.removeAll();
	    	g.g.repaint();
	    	Army temp=null;
	    	City c=null;
	    	for(int i=0;i<g.g1.getPlayer().getControlledArmies().size();i++) {
	    		if(g.g1.getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(g.targetcity)) {
	    			temp=g.g1.getPlayer().getControlledArmies().get(i);
	    			break;
	    		}
	    	}
	    	for(int i=0;i<g.g1.getAvailableCities().size();i++) {
	    		if(g.g1.getAvailableCities().get(i).getName().equals(g.targetcity)) {
	    			c=g.g1.getAvailableCities().get(i);
	    		}
	    	}
	    	try {
	    		if(c!=null) {
	    			g.g1.getPlayer().laySiege(temp, c);
	    			JOptionPane.showMessageDialog(g,"You layed siege on "+g.targetcity,"Confirm",JOptionPane.INFORMATION_MESSAGE);
	    		}	
			} catch (TargetNotReachedException e) {
				JOptionPane.showMessageDialog(g,"You did not reach the target yet","Alert",JOptionPane.WARNING_MESSAGE);
			} catch (FriendlyCityException e) {
				JOptionPane.showMessageDialog(g,"You can not siege a city you control","Alert",JOptionPane.WARNING_MESSAGE);
			}
	    	
	    }else if(s!=null && s.equals("Auto Resolve")) {
	    	City c=null;
	    	for(int i=0;i<g.g1.getAvailableCities().size();i++) {
	    		if(g.g1.getAvailableCities().get(i).getName().equals(marching.getCurrentLocation())) {
	    			c=g.g1.getAvailableCities().get(i);
	    			try {
						g.g1.autoResolve(marching,c.getDefendingArmy());
					} catch (FriendlyFireException e) {
						JOptionPane.showMessageDialog(g,"You can not attack your own army","Alert",JOptionPane.WARNING_MESSAGE);
					}
	    			if(marching.getUnits().size()==0) {
	    				g.map=true;
	    				g.battleoption=false;
	    				g.incity=false;
	    				g.g.removeAll();
	    				g.g.repaint();
	    				JOptionPane.showMessageDialog(g,"You have lost the battle","Confirm",JOptionPane.INFORMATION_MESSAGE);
	    				armyincity.remove(marching);
	    			}else if(c.getDefendingArmy().getUnits().size()==0) {
	    				g.map=false;
	    				g.battleoption=false;
	    				g.incity=true;
	    				thecity=marching.getCurrentLocation();
	    				g.g.removeAll();
	    				g.g.repaint();
	    				JOptionPane.showMessageDialog(g,"You have won the battle","Confirm",JOptionPane.INFORMATION_MESSAGE);
	    				
	    			}
	    			
	    		}	
	    	}
	    	
	    }else if(s!=null && s.equals("Manual Battle")) {
	    	g.manual=true;
	    	g.map=false;
	    	g.incity=false;
	    	g.army1=marching;
	    	for(int i=0,j=g.g.hisstart.size()-1;i<g.army1.getUnits().size() ;i++,j--) {
	    		g.army1.getUnits().get(i).setX(g.g.hisstart.get(j)[0]);
	    		g.army1.getUnits().get(i).setY(g.g.hisstart.get(j)[1]);
	    	}
	    	for(int i=0;i<g.g1.getAvailableCities().size();i++) {
	    		if(g.g1.getAvailableCities().get(i).getName().equals(marching.getCurrentLocation())) {
	    			g.army2=g.g1.getAvailableCities().get(i).getDefendingArmy();
	    			break;
	    		}
	    	}
	    	for(int i=0;i<g.army2.getUnits().size();i++) {
	    		g.army2.getUnits().get(i).setX(g.g.hisstart.get(i)[0]);
	    		g.army2.getUnits().get(i).setY(g.g.hisstart.get(i)[1]);
	    	}
	    	g.g.removeAll();
	    	g.g.pane.setBounds((int)(g.getWidth()/2.53) ,g.getHeight()/23,500,100);
			g.g.add(g.g.pane);
	    	g.g.repaint();
	    	attack=false;
	    	g.t.start();
	    }
	    else if(s!=null && s.equals("Return")) {
	    	g.map=true;
	    	g.battleoption=false;
	    	this.count2=1;
	    	g.g.removeAll();
	    	g.g.repaint();
	    	
	    }
	    else if(s!=null && turn && g.manual && s.charAt(0)!='/' && !this.attack) {
	    	JOptionPane.showMessageDialog(g, "You must choose one of your units","Alert",JOptionPane.INFORMATION_MESSAGE);
	    }
	    else if(s!=null && turn &&  g.manual && s.charAt(0)=='/' && !this.attack) {
	    	g.t.start();
	    	String sI="";
	    	int i=1;
	    	for(;i<s.length() && s.charAt(i)!='/';i++)
	    		sI+=s.charAt(i);
	    	i++;
	    	String sX="";
	    	for(;i<s.length() && s.charAt(i)!='/';i++)
	    		sX+=s.charAt(i);
	    	i++;
	    	String sY="";
	    	for(;i<s.length() && s.charAt(i)!='/';i++)
	    		sY+=s.charAt(i);
	    	int ind=Integer.parseInt(sI);
	    	int x=Integer.parseInt(sX);
	    	int y=Integer.parseInt(sY);
	    	attack=true;
	    	attacker=g.army1.getUnits().get(ind);
	    	
	    }else if(s!=null && turn && !s.equals("endturn") && s.charAt(0)=='/' && g.manual &&  attack ) {
	    	JOptionPane.showMessageDialog(g,"You can not attack your units","Alert",JOptionPane.WARNING_MESSAGE);
	    }
	    else if(s!=null && turn && !s.equals("endturn") && s.charAt(0)!='/' && g.manual &&  attack) {
	    	String sI="";
	    	int i=0;
	    	for(i=0;i<s.length() && s.charAt(i)!='/';i++)
	    		sI+=s.charAt(i);
	    	i++;
	    	String sX="";
	    	for(;i<s.length() && s.charAt(i)!='/';i++)
	    		sX+=s.charAt(i);
	    	i++;
	    	String sY="";
	    	for(;i<s.length() && s.charAt(i)!='/';i++)
	    		sY+=s.charAt(i);
	    	int ind=Integer.parseInt(sI);
	    	int x=Integer.parseInt(sX);
	    	int y=Integer.parseInt(sY);
	    	defender=g.army2.getUnits().get(ind);
	    	int temp=defender.getCurrentSoldierCount();
	    	boolean flag=true;
	    	try {
				attacker.attack(defender);
			} catch (FriendlyFireException e) {
				JOptionPane.showMessageDialog(g,"You can not attack a unit from your army","Alert",JOptionPane.WARNING_MESSAGE);
				flag=false;
			}
	    	if(attacker instanceof Archer && flag) {
	    		if(defender instanceof Archer)
	    			g.logbattle=g.logbattle+"\n"+ "Archer Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Infantry)
	    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Cavalry)
	    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		onemove1=false;
	    		turn=false;
	    		attack=false;
	    		g.g.removeAll();
	    		g.g.repaint();
	    	}else if (flag && attacker.getX()==defender.getX() && (attacker.getY()==defender.getY()+70 || attacker.getY()==defender.getY()-70)){
	    		if(defender instanceof Archer)
	    			g.logbattle=g.logbattle+"\n"+"Archer Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Infantry)
	    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Cavalry)
	    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		onemove1=false;
	    		turn=false;
	    		attack=false;
	    		g.g.removeAll();
	    		g.g.repaint();
	    		
	    	}else if(flag && attacker.getY()==defender.getY() && (attacker.getX()==defender.getX()-120 || attacker.getX()==defender.getX()+120)) {
	    		if(defender instanceof Archer)
	    			g.logbattle=g.logbattle+"\n"+"Archer Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Infantry)
	    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Cavalry)
	    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		onemove1=false;
	    		turn=false;
	    		attack=false;
	    		g.g.removeAll();
	    		g.g.repaint();
	    	}else if(flag && (attacker.getY()-70==defender.getY() || attacker.getY()+70==defender.getY()) && (attacker.getX()+120==defender.getX() || attacker.getX()-120==defender.getX()) ) {
	    		if(defender instanceof Archer)
	    			g.logbattle=g.logbattle+"\n"+"Archer Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Infantry)
	    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		if(defender instanceof Cavalry)
	    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
	    		onemove1=false;
	    		turn=false;
	    		attack=false;
	    		g.g.removeAll();
	    		g.g.repaint();
	    	}else if(flag) {
	    		defender.setCurrentSoldierCount(temp);
	    		onemove1=true;
	    	    try {
	    	    	if(sound.clip!=null)
						sound.stopSound();
					g.playSound("./sounds/Battle.wav");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	    		if(this.find(defender.getX(),defender.getY(),"right") && defender.getX()+120<=g.getWidth() && defender.getX()!=g.getWidth()-130) {
					reachx=defender.getX()+120;
				    reachy=defender.getY();
				}else if(this.find(defender.getX(),defender.getY(),"left") && defender.getX()-120>=0) {
					reachx=defender.getX()-120;
				    reachy=defender.getY();
				}else if(this.find(defender.getX(),defender.getY(),"up") && defender.getY()!=190) {
					reachx=defender.getX();
				    reachy=defender.getY()-70;
				}else if(this.find(defender.getX(),defender.getY(),"down") && defender.getY()!=g.getHeight()-110) {
					reachx=defender.getX();
				    reachy=defender.getY()+70;
				}else if(this.find(defender.getX(),defender.getY(),"upright") && defender.getY()!=190 && defender.getX()+120<=g.getWidth() && defender.getX()!=g.getWidth()-130 ) {
					reachx=defender.getX()+120;
				    reachy=defender.getY()-70;
				}else if(this.find(defender.getX(),defender.getY(),"upleft") && defender.getY()!=190 && defender.getX()-120>=0 ) {
					reachx=defender.getX()-120;
				    reachy=defender.getY()-70;  
				}else if(this.find(defender.getX(),defender.getY(),"downleft") && defender.getY()!=g.getHeight()-110 && defender.getX()-120>=0 ) {
					reachx=defender.getX()-120;
				    reachy=defender.getY()+70;  
				}else if(this.find(defender.getX(),defender.getY(),"downright") && defender.getY()!=g.getHeight()-110 && defender.getX()+120<=g.getWidth() && defender.getX()!=g.getWidth()-130) {
					reachx=defender.getX()+120;
				    reachy=defender.getY()+70;  
				}else {
					try {
						attacker.attack(defender);
					} catch (FriendlyFireException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(defender instanceof Archer)
		    			g.logbattle=g.logbattle+"\n"+"Archer Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
		    		if(defender instanceof Infantry)
		    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
		    		if(defender instanceof Cavalry)
		    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
		    		onemove1=false;
		    		turn=false;
		    		attack=false;
		    		g.g.removeAll();
		    		g.g.repaint();
					
				}
	    	}
	    	if(onemove1==false) {
	    		g.t.setDelay(200);
	    		g.g.repaint();
	    		Random r=new Random();
				 x=r.nextInt(g.army2.getUnits().size());
				defender=g.army2.getUnits().get(x);
				 y=r.nextInt(g.army1.getUnits().size());
				attacker=g.army1.getUnits().get(y);
				 temp=attacker.getCurrentSoldierCount();
				if(defender instanceof Archer) {
					try {
						defender.attack(attacker);
					} catch (FriendlyFireException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(attacker instanceof Archer)
			    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Infantry)
			    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Cavalry)
			    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
					g.g.removeAll();
					g.g.repaint();
					turn=true;
					attack=false;
				}else if (attacker.getX()==defender.getX() && (attacker.getY()==defender.getY()+70 || attacker.getY()==defender.getY()-70)){
					try {
						defender.attack(attacker);
					} catch (FriendlyFireException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(attacker instanceof Archer)
			    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Infantry)
			    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Cavalry)
			    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
					g.g.removeAll();
					g.g.repaint();
					turn=true;
					attack=false;
		    		
		    	}else if(attacker.getY()==defender.getY() && (attacker.getX()==defender.getX()-120 || attacker.getX()==defender.getX()+120)) {
		    		try {
						defender.attack(attacker);
					} catch (FriendlyFireException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		if(attacker instanceof Archer)
			    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Infantry)
			    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Cavalry)
			    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
					g.g.removeAll();
					g.g.repaint();
					turn=true;
					attack=false;
		    	}else if((attacker.getY()==defender.getY()+70 || attacker.getY()==defender.getY()-70  ) && (attacker.getX()==defender.getX()-120 || attacker.getX()==defender.getX()+120)) {
		    		try {
						defender.attack(attacker);
					} catch (FriendlyFireException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		if(attacker instanceof Archer)
			    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Infantry)
			    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Cavalry)
			    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
					g.g.removeAll();
					g.g.repaint();
					turn=true;
					attack=false;
		    	}else {
					onmove2=true; 
					 try {
							if(sound.clip!=null)
								sound.stopSound();
							g.playSound("./sounds/Battle.wav");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					if(this.find(attacker.getX(),attacker.getY(),"right") && attacker.getX()+120<=g.getWidth() && attacker.getX()!=g.getWidth()-130) {
						reachx=attacker.getX()+120;
					    reachy=attacker.getY();
					}else if(this.find(attacker.getX(),attacker.getY(),"left") && attacker.getX()-120>=0) {
						reachx=attacker.getX()-120;
					    reachy=attacker.getY();
					}else if(this.find(attacker.getX(),attacker.getY(),"up") && attacker.getY()!=190) {
						reachx=attacker.getX();
					    reachy=attacker.getY()-70;
					}else if(this.find(attacker.getX(),attacker.getY(),"down") && attacker.getY()!=g.getHeight()-110) {
						reachx=attacker.getX();
					    reachy=attacker.getY()+70;
					}else if(this.find(attacker.getX(),attacker.getY(),"upright") && attacker.getY()!=190 && attacker.getX()+120<=g.getWidth() && attacker.getX()!=g.getWidth()-130 ) {
						reachx=attacker.getX()+120;
					    reachy=attacker.getY()-70;
					}else if(this.find(attacker.getX(),attacker.getY(),"upleft") && attacker.getY()!=190 && attacker.getX()-120>=0 ) {
						reachx=attacker.getX()-120;
					    reachy=attacker.getY()-70;  
					}else if(this.find(attacker.getX(),attacker.getY(),"downleft") && attacker.getY()!=g.getHeight()-110 && attacker.getX()-120>=0 ) {
						reachx=attacker.getX()-120;
					    reachy=attacker.getY()+70;  
					}else if(this.find(attacker.getX(),attacker.getY(),"downright") && attacker.getY()!=g.getHeight()-110 && attacker.getX()+120<=g.getWidth() && attacker.getX()!=g.getWidth()-130) {
						reachx=attacker.getX()+120;
					    reachy=attacker.getY()+70;  
					}else {
						try {
							defender.attack(attacker);
						} catch (FriendlyFireException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(attacker instanceof Archer)
				    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
				    	if(attacker instanceof Infantry)
				    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
				    	if(attacker instanceof Cavalry)
				    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
						g.g.removeAll();
						g.g.repaint();
						turn=true;
						onmove2=false;
						attack=false;
					}
				}
	    	}
	    }else if(s!=null && s.equals("endturn") && g.manual && turn) {
	    	JOptionPane.showMessageDialog(g,"You must attack one unit first","Alert",JOptionPane.WARNING_MESSAGE);
	    }else if(s!=null && !turn && !s.equals("endturn") && g.manual) {
	    	JOptionPane.showMessageDialog(g,"You have already attacked end your turn","Alert",JOptionPane.WARNING_MESSAGE);
	    }
		if( g.g1 !=null && g.g1.isGameOver()) {
			if(this.g.g1.isLost()) {
				g.lost=true;
				g.g.removeAll();
				g.g.repaint();
			}else {
				g.win=true;
				g.g.removeAll();
				g.g.repaint();
			}
		}
		if(onemove1) {
			if(attacker.getY()>reachy)
				attacker.setY(attacker.getY()-70);
			else if(attacker.getY()<reachy)
				attacker.setY(attacker.getY()+70);
			else if(attacker.getX()<reachx) 
				attacker.setX(attacker.getX()+120);
			else if(attacker.getX()>reachx) 
				attacker.setX(attacker.getX()-120);
			g.g.removeAll();
			g.g.repaint();
		}
		if(onmove2) {
			
			if(defender.getY()>reachy)
				defender.setY(defender.getY()-70);
			else if(defender.getY()<reachy)
				defender.setY(defender.getY()+70);
			else if(defender.getX()<reachx) 
				defender.setX(defender.getX()+120);
			else if(defender.getX()>reachx) 
				defender.setX(defender.getX()-120);
			g.g.removeAll();
			g.g.repaint();
		}
		if( attacker!=null && onemove1 && attacker.getY()==reachy && attacker.getX()==reachx) {
            int temp=defender.getCurrentSoldierCount();
			try {
				attacker.attack(defender);
			} catch (FriendlyFireException e) {
				
			}
			if(defender instanceof Archer)
    			g.logbattle=g.logbattle+"\n"+"Archer Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
    		if(defender instanceof Infantry)
    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
    		if(defender instanceof Cavalry)
    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+defender.getLevel()+" from defending army lost "+(temp-defender.getCurrentSoldierCount())+" Soldier";
    		onemove1=false;
    		turn=false;
    		attack=false;
			g.g.removeAll();
			g.g.repaint();
			Random r=new Random();
			int x=r.nextInt(g.army2.getUnits().size());
			defender=g.army2.getUnits().get(x);
			int y=r.nextInt(g.army1.getUnits().size());
			attacker=g.army1.getUnits().get(y);
			 temp=attacker.getCurrentSoldierCount();
			if(defender instanceof Archer) {
				try {
					defender.attack(attacker);
				} catch (FriendlyFireException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(attacker instanceof Archer)
		    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Infantry)
		    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Cavalry)
		    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
				g.t.setDelay(200);
		    	g.g.removeAll();
				g.g.repaint();
				turn=true;
				attack=false;
			}else if (attacker.getX()==defender.getX() && (attacker.getY()==defender.getY()+70 || attacker.getY()==defender.getY()-70)){
				try {
					defender.attack(attacker);
				} catch (FriendlyFireException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(attacker instanceof Archer)
		    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Infantry)
		    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Cavalry)
		    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
				g.g.removeAll();
				g.g.repaint();
				turn=true;
				attack=false;
	    		
	    	}else if(attacker.getY()==defender.getY() && (attacker.getX()==defender.getX()-120 || attacker.getX()==defender.getX()+120)) {
	    		try {
					defender.attack(attacker);
				} catch (FriendlyFireException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if(attacker instanceof Archer)
		    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Infantry)
		    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Cavalry)
		    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
				g.g.removeAll();
				g.g.repaint();
				turn=true;
				attack=false;
	    	}else if((attacker.getY()==defender.getY()+70 || attacker.getY()==defender.getY()-70  ) && (attacker.getX()==defender.getX()-120 || attacker.getX()==defender.getX()+120)) {
	    		try {
					defender.attack(attacker);
				} catch (FriendlyFireException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if(attacker instanceof Archer)
		    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Infantry)
		    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
		    	if(attacker instanceof Cavalry)
		    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
				g.g.removeAll();
				g.g.repaint();
				turn=true;
				attack=false;
	    	}else {
				onmove2=true; 
				 try {
						if(sound.clip!=null)
							sound.stopSound();
						g.playSound("./sounds/Battle.wav");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(this.find(attacker.getX(),attacker.getY(),"right") && attacker.getX()+120<=g.getWidth() && attacker.getX()!=g.getWidth()-130) {
					reachx=attacker.getX()+120;
				    reachy=attacker.getY();
				}else if(this.find(attacker.getX(),attacker.getY(),"left") && attacker.getX()-120>=0) {
					reachx=attacker.getX()-120;
				    reachy=attacker.getY();
				}else if(this.find(attacker.getX(),attacker.getY(),"up") && attacker.getY()!=190) {
					reachx=attacker.getX();
				    reachy=attacker.getY()-70;
				}else if(this.find(attacker.getX(),attacker.getY(),"down") && attacker.getY()!=g.getHeight()-110) {
					reachx=attacker.getX();
				    reachy=attacker.getY()+70;
				}else if(this.find(attacker.getX(),attacker.getY(),"upright") && attacker.getY()!=190 && attacker.getX()+120<=g.getWidth() && attacker.getX()!=g.getWidth()-130 ) {
					reachx=attacker.getX()+120;
				    reachy=attacker.getY()-70;
				}else if(this.find(attacker.getX(),attacker.getY(),"upleft") && attacker.getY()!=190 && attacker.getX()-120>=0 ) {
					reachx=attacker.getX()-120;
				    reachy=attacker.getY()-70;  
				}else if(this.find(attacker.getX(),attacker.getY(),"downleft") && attacker.getY()!=g.getHeight()-110 && attacker.getX()-120>=0 ) {
					reachx=attacker.getX()-120;
				    reachy=attacker.getY()+70;  
				}else if(this.find(attacker.getX(),attacker.getY(),"downright") && attacker.getY()!=g.getHeight()-110 && attacker.getX()+120<=g.getWidth() && attacker.getX()!=g.getWidth()-130) {
					reachx=attacker.getX()+120;
				    reachy=attacker.getY()+70;  
				}else {
					try {
						defender.attack(attacker);
					} catch (FriendlyFireException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(attacker instanceof Archer)
			    		g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Infantry)
			    		g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
			    	if(attacker instanceof Cavalry)
			    		g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
					g.g.removeAll();
					g.g.repaint();
					turn=true;
					onmove2=false;
					attack=false;
				}
			}
		}
		if( defender!=null && onmove2 && defender.getY()==reachy && defender.getX()==reachx) {
            int temp=attacker.getCurrentSoldierCount();
			try {
				defender.attack(attacker);
			} catch (FriendlyFireException e) {
				
			}
			if(attacker instanceof Archer)
    			g.logbattle=g.logbattle+"\n"+"Archer Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
    		if(attacker instanceof Infantry)
    			g.logbattle=g.logbattle+"\n"+"Infantry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
    		if(attacker instanceof Cavalry)
    			g.logbattle=g.logbattle+"\n"+"Cavalry Level "+attacker.getLevel()+" from your army lost "+(temp-attacker.getCurrentSoldierCount())+" Soldier";
    		onmove2=false;
    		turn=true;
    		attack=false;
			g.g.removeAll();
			g.g.repaint();
		}
		if(g.manual && g.army1.getUnits().size()==0) {
			g.t.stop();
			JOptionPane.showMessageDialog(g,"You have lost the battle","Confirm",JOptionPane.INFORMATION_MESSAGE);
			g.battleoption=false;
			g.g1.getPlayer().getControlledArmies().remove(g.army1);
			g.manual=false;
			g.map=true;
			g.logbattle="";
			g.incity=false;
			g.g.removeAll();
			g.g.repaint();
		}
		if(g.manual && g.army2.getUnits().size()==0) {
			g.t.stop();
			JOptionPane.showMessageDialog(g,"You have won the battle you now control "+g.army1.getCurrentLocation(),"Confirm",JOptionPane.INFORMATION_MESSAGE);
			g.manual=false;
			g.map=false;
			g.g1.getPlayer().getControlledArmies().remove(g.army1);
			g.incity=true;
			g.logbattle="";
			g.battleoption=false;
			thecity=g.army1.getCurrentLocation();
			for(int i=0;i<this.g.g1.getAvailableCities().size();i++) {
				if(thecity.equals(g.g1.getAvailableCities().get(i).getName())) {
					g.g1.getPlayer().getControlledCities().add(g.g1.getAvailableCities().get(i));
					g.g1.getAvailableCities().get(i).setDefendingArmy(g.army1);
				}
			}
			g.g.removeAll();
			g.g.repaint();
		}
     }
	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}	    

package ges;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class Requetes extends JFrame implements ActionListener {
	Statement st;
	ResultSet rst;
	Connectage cn=new Connectage();
	JButton jbclasse1,jbclasse2,jbfille,jboy,jbtotal;
	JTable tb;
	JScrollPane sp;
	JLabel lbtitre,lbtitre2,lb,lb2,lbclass1,lbnv,lb5,lbinsti,lbclass2,lbsexe2;
	JComboBox jcb_sexe2,jcb_classe,jcb_classe2,jcb_nivo;
	
	public Requetes(){
		this.setTitle("chcode_appli");
		this.setSize(1160,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setBackground(Color.LIGHT_GRAY);
		pn.setLayout(null);
		add(pn);
		
		lb=new JLabel("TOTAL =");
		lb.setBounds(300,520,150,30);
		lb.setFont(new Font("Arial",Font.BOLD,20));
		pn.add(lb);
		
		lb2=new JLabel("0");
		lb2.setBounds(400,520,150,30);
		lb2.setFont(new Font("Arial",Font.BOLD,20));
		lb2.setForeground(Color.blue);
		pn.add(lb2);
       //titre
		lbtitre=new JLabel("LISTE DES ELEVES PAR CLASSE");
		lbtitre.setBounds(100,10,350,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,15));
		lbtitre.setForeground(Color.blue);
		pn.add(lbtitre);
		
		///classe
		lbclass1=new JLabel("CLASSE");
		lbclass1.setBounds(90,50,150,30);
		lbclass1.setFont(new Font("Arial",Font.BOLD,14));
		//lb3.setForeground(Color.blue);
		pn.add(lbclass1);
		
		jcb_classe=new JComboBox();
		jcb_classe.addItem("6eme");
		jcb_classe.addItem("5eme");
		jcb_classe.addItem("4eme");
		jcb_classe.addItem("3eme");
		jcb_classe.addItem("2ndeS");
		jcb_classe.addItem("2ndeL");
		jcb_classe.addItem("1ereS");
		jcb_classe.addItem("1ereL");
		jcb_classe.addItem("TD");
		jcb_classe.addItem("TA");
		jcb_classe.setBounds(170,50,100,25);
		pn.add(jcb_classe);
		
		
		jbclasse1=new JButton("OK");
		jbclasse1.setBounds(290,50,70,25);
		jbclasse1.addActionListener(this);
		pn.add(jbclasse1);
		
		///classe
		//titre
				lbtitre2=new JLabel("LISTE DES ELEVES PAR CLASSE ET PAR GENRE");
				lbtitre2.setBounds(60,80,370,30);
				lbtitre2.setFont(new Font("Arial",Font.BOLD,15));
				lbtitre2.setForeground(Color.blue);
				pn.add(lbtitre2);
		lbclass2=new JLabel("CLASSE");
		lbclass2.setBounds(20,120,150,30);
		lbclass2.setFont(new Font("Arial",Font.BOLD,14));
		//lb3.setForeground(Color.blue);
		pn.add(lbclass2);
		
		jcb_classe2=new JComboBox();
		jcb_classe2.addItem("6eme");
		jcb_classe2.addItem("5eme");
		jcb_classe2.addItem("4eme");
		jcb_classe2.addItem("3eme");
		jcb_classe2.addItem("2ndeS");
		jcb_classe2.addItem("2ndeL");
		jcb_classe2.addItem("1ereS");
		jcb_classe2.addItem("1ereL");
		jcb_classe2.addItem("TD");
		jcb_classe2.addItem("TA");
		jcb_classe2.setBounds(90,120,80,25);
		pn.add(jcb_classe2);
		//
		///sexe2
				lbsexe2=new JLabel("SEXE");
				lbsexe2.setBounds(200,120,70,25);
				lbsexe2.setFont(new Font("Arial",Font.BOLD,14));
				//lb3.setForeground(Color.blue);
				pn.add(lbsexe2);
				
				jcb_sexe2=new JComboBox();
				jcb_sexe2.addItem("MASCULIN");
				jcb_sexe2.addItem("FEMININ");
				jcb_sexe2.setBounds(250,120,110,25);
				pn.add(jcb_sexe2);
		//
		
		jbclasse2=new JButton("OK");
		jbclasse2.setBounds(370,120,70,25);
		jbclasse2.addActionListener(this);
		pn.add(jbclasse2);
		//
		jbfille=new JButton("Total filles");
		jbfille.setBounds(500,20,150,25);
		jbfille.addActionListener(this);
		pn.add(jbfille);
		//
		//
		jboy=new JButton("Total gar�ons");
		jboy.setBounds(680,20,150,25);
		jboy.addActionListener(this);
		pn.add(jboy);
		//
		//
		jbtotal=new JButton("TOTAL");
		jbtotal.setBounds(860,20,150,25);
		jbtotal.addActionListener(this);
		pn.add(jbtotal);
		
		
				//
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("MATRICULE");
		df.addColumn("NOM");
		df.addColumn("PRENOM");
		df.addColumn("DATNAISSANCE");
		df.addColumn("SEXE");
		df.addColumn("CONTACT TUTEUR");
		df.addColumn("CLASSE");
		tb.setModel(df);
		pn.add(sp);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requetes ctn=new Requetes();
		ctn.setVisible(true);

	}
	private void init(){
		tb=new JTable();
		sp=new JScrollPane();
		sp.setViewportView(tb);
		sp.setBounds(20,160,1100,350);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//classe1
		if(e.getSource()==jbclasse1){
			String a=jcb_classe.getSelectedItem().toString();
			
			DefaultTableModel df=new DefaultTableModel();
			df.addColumn("MATRICULE");
			df.addColumn("NOM           ");
			df.addColumn("DATNAISSANCE");
			df.addColumn("SEXE");
			df.addColumn("CONCTACT TUTEUR");
			df.addColumn("CLASSE");
			tb.setModel(df);
			
	//String rq="select matricule,nom,prenom,daten,lieudn,sexe,nationalite,from general";
			String rq="select * from vueleve2 where classe='"+a+"' order by nom";
			
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(rq);
				while(rst.next()){
					df.addRow(new Object[]{
	rst.getString("matricule"),rst.getString("nom"),rst.getString("daten"),
	rst.getString("sexe"),rst.getString("tuteur"),rst.getString("classe")
	
					});
				}
				
			}
			catch(SQLException ex){
				
			}
			String kk="select count(*)  as som from vueleve2 where classe='"+a+"'";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(kk);
				if(rst.next()){
					lb2.setText(rst.getString("som"));
				}
			}
			catch(SQLException ex){
				
			}
		}///
		
		//classe2
		if(e.getSource()==jbclasse2){
String a=jcb_classe2.getSelectedItem().toString(),b=jcb_sexe2.getSelectedItem().toString();
			
			DefaultTableModel df=new DefaultTableModel();
			df.addColumn("MATRICULE");
			df.addColumn("NOM           ");
			df.addColumn("DATNAISSANCE");
			df.addColumn("SEXE");
			df.addColumn("CONCTACT TUTEUR");
			df.addColumn("CLASSE");
			tb.setModel(df);
			
			String rq="select * from vueleve2 where classe='"+a+"' and sexe='"+b+"'";
			
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(rq);
				while(rst.next()){
					df.addRow(new Object[]{
	rst.getString("matricule"),rst.getString("nom"),rst.getString("daten"),
	rst.getString("sexe"),rst.getString("tuteur"),rst.getString("classe")
	
					});
				}
				
			}
			catch(SQLException ex){
				
			}
			String kk="select count(*)  as som from vueleve2 where classe='"+a+"'  and sexe='"+b+"'";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(kk);
				if(rst.next()){
					lb2.setText(rst.getString("som"));
				}
			}
			catch(SQLException ex){
				
			}
		}///
		//total filles
		if(e.getSource()==jbfille){
			DefaultTableModel df=new DefaultTableModel();
			df.addColumn("MATRICULE");
			df.addColumn("NOM           ");
			df.addColumn("DATNAISSANCE");
			df.addColumn("SEXE");
			df.addColumn("CONCTACT TUTEUR");
			df.addColumn("CLASSE");
			tb.setModel(df);
			
	//String rq="select matricule,nom,prenom,daten,lieudn,sexe,nationalite,from general";
			String rq="select * from vueleve2 where sexe='FEMININ'";
			
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(rq);
				while(rst.next()){
					df.addRow(new Object[]{
	rst.getString("matricule"),rst.getString("nom"),rst.getString("daten"),
	rst.getString("sexe"),rst.getString("tuteur"),rst.getString("classe")
	
					});
				}
				
			}
			catch(SQLException ex){
				
			}
			String kk="select count(*)  as som from vueleve2 where sexe='FEMININ'";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(kk);
				if(rst.next()){
					lb2.setText(rst.getString("som"));
				}
			}
			catch(SQLException ex){
				
			}
		}
		//total gar�ons
		if(e.getSource()==jboy){
			DefaultTableModel df=new DefaultTableModel();
			df.addColumn("MATRICULE");
			df.addColumn("NOM           ");
			df.addColumn("DATNAISSANCE");
			df.addColumn("SEXE");
			df.addColumn("CONCTACT TUTEUR");
			df.addColumn("CLASSE");
			tb.setModel(df);
			
	//String rq="select matricule,nom,prenom,daten,lieudn,sexe,nationalite,from general";
			String rq="select * from vueleve2 where sexe='MASCULIN'";
			
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(rq);
				while(rst.next()){
					df.addRow(new Object[]{
	rst.getString("matricule"),rst.getString("nom"),rst.getString("daten"),
	rst.getString("sexe"),rst.getString("tuteur"),rst.getString("classe")
	
					});
				}
				
			}
			catch(SQLException ex){
				
			}
			String kk="select count(*)  as som from vueleve2 where sexe='MASCULIN'";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(kk);
				if(rst.next()){
					lb2.setText(rst.getString("som"));
				}
			}
			catch(SQLException ex){
				
			}
		}
		//total
		if(e.getSource()==jbtotal){
			DefaultTableModel df=new DefaultTableModel();
			df.addColumn("MATRICULE");
			df.addColumn("NOM           ");
			df.addColumn("DATNAISSANCE");
			df.addColumn("SEXE");
			df.addColumn("CONCTACT TUTEUR");
			df.addColumn("CLASSE");
			tb.setModel(df);
			
	//String rq="select matricule,nom,prenom,daten,lieudn,sexe,nationalite,from general";
			String rq="select * from vueleve2 order by classe ";
			
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(rq);
				while(rst.next()){
					df.addRow(new Object[]{
	rst.getString("matricule"),rst.getString("nom"),rst.getString("daten"),
	rst.getString("sexe"),rst.getString("tuteur"),rst.getString("classe")
	
					});
				}
				
			}
			catch(SQLException ex){
				
			}
			String kk="select count(*)  as som from vueleve2 ";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(kk);
				if(rst.next()){
					lb2.setText(rst.getString("som"));
				}
			}
			catch(SQLException ex){
				
			}
		}
	
	}

}

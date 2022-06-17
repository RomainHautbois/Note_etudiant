package ges;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Gestion  extends JFrame implements ActionListener {
	Connectage cn=new Connectage();
	Verifmatri vf=new Verifmatri();
	Statement st;
	ResultSet rst;
	JLabel lbtitre,lbmat,lbnom,lbdn,lbsexe,lbclasse,lbr,lbimage,lbtitre2,lbmoy1,lbtuteur,
	lbmoy2,lbmoy3,lbmoyan,lbmoyan2,lbconst,lbconst2;
	JRadioButton rb1,rb2;
	JButton jbajout,jbmodif,jbsupp,jbrech,jbajpht,jbnv,jbapropos,jbr,jbrqcollective;
	JTextField tfmat,tfnom,tfdaten,tfmoy1,tfmoy2,tfmoy3,tfmoyan,tfr,tftuteur,
	tfsexe;
	JPanel pn;
	JComboBox jcb_classe;
	JTextArea tfa;
	//defilemet
	JLabel idl,noml,agel;
	JTextField idt,nomt,aget;
	JButton first,last,next,previous;
	String a;
	///
    int pos=0;
	
	public static Connection getConnection(){
		Connection cn;
		try{
			cn=DriverManager.getConnection("jdbc:mysql://localhost/sourcedb","root","");
		 return cn;
		}
		catch(Exception ex){
			
		}
		return null;
	}
	
	///
	
	public static  List<Personne> bindlist(){
		try{
			Connection con=getConnection();
			Statement st=con.createStatement();
			ResultSet rst=st.executeQuery("select * from vueleve2");
			List<Personne> ls=new ArrayList<Personne>();
			while(rst.next()){
				
				Personne ps=new Personne(Integer.parseInt(rst.getString("id")),
						Float.parseFloat(rst.getString("moy1")),
						Float.parseFloat(rst.getString("moy2")),
						Float.parseFloat(rst.getString("moy3")),
						Float.parseFloat(rst.getString("moyan")),
		rst.getString("matricule"),rst.getString("nom"),rst.getString("daten"),
		rst.getString("sexe"),rst.getString("classe"),rst.getString("tuteur"),
		rst.getString("constat"),rst.getBytes("photo")
	);
			ls.add(ps);	
				
			}
			return ls;
		}
		catch(SQLException ex){
			
		}
		return null;
	}
	///
	public void showposinfo(int index){
		//tf4=daten,tf5=lieudn,rb1 ou rb2=sexe,tf6=nationalite,jcb1=typrhdbc
		tfmat.setText(bindlist().get(index).getMatricule());
		tfnom.setText(bindlist().get(index).getNom());
		tfdaten.setText(bindlist().get(index).getDaten());
		tftuteur.setText(bindlist().get(index).getTuteur());
		//
		if(Float.toString(bindlist().get(index).getMoy1()).equals("0.0")) tfmoy1.setText("INDISPONIBLE");else tfmoy1.setText(Float.toString(bindlist().get(index).getMoy1()));
		if(Float.toString(bindlist().get(index).getMoy2()).equals("0.0")) tfmoy2.setText("INDISPONIBLE");else tfmoy2.setText(Float.toString(bindlist().get(index).getMoy2()));
		if(Float.toString(bindlist().get(index).getMoy3()).equals("0.0")) tfmoy3.setText("INDISPONIBLE");else tfmoy3.setText(Float.toString(bindlist().get(index).getMoy3()));
		//
		if(bindlist().get(index).getSexe().equals("MASCULIN"))
			rb1.setSelected(true); else rb2.setSelected(true);
		jcb_classe.setSelectedItem(bindlist().get(index).getClasse());
		if(bindlist().get(index).getPhoto()==null)
			lbimage.setIcon(null);
		else{
		 byte[] img=bindlist().get(index).getPhoto();
		 ImageIcon image=new ImageIcon(img);
		 Image im=image.getImage();
Image myImg=im.getScaledInstance(lbimage.getWidth(),lbimage.getHeight(),Image.SCALE_SMOOTH);
ImageIcon newImag=new ImageIcon(myImg);
lbimage.setIcon(newImag);}
		//lbmoyan2.setText(Float.toString(bindlist().get(index).getMoyan()));
		if(
				Float.toString(bindlist().get(index).getMoy1()).equals("0.0")||Float.toString(bindlist().get(index).getMoy2()).equals("0.0")||
				Float.toString(bindlist().get(index).getMoy3()).equals("0.0")) 
		lbmoyan2.setText("INDISPONIBLE");
		else lbmoyan2.setText(Float.toString(bindlist().get(index).getMoyan()));
		
		//
		if(Float.toString(bindlist().get(index).getMoy1()).equals("0.0")||
				Float.toString(bindlist().get(index).getMoy2()).equals("0.0")||Float.toString(bindlist().get(index).getMoy3()).equals("0.0"))
			lbconst2.setText("PAS ENCORE");
		else{lbconst2.setText(bindlist().get(index).getConstat());}
		//
	
	}
	///
	public Gestion(){
		this.setTitle("chcode_appli");
		this.setSize(800,660);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		 pn=new JPanel();
		 pn.setBackground(Color.LIGHT_GRAY);
		pn.setLayout(null);
		//pn.setBackground(new Color(0,210,255));
		add(pn);
		lbtitre=new JLabel("SOURCE DE PROGRES-APPLICATION DE GESTION");
		lbtitre.setFont(new Font("Arial",Font.BOLD,20));
		lbtitre.setForeground(Color.BLUE);
		lbtitre.setBounds(10,10,620,30);
		//lbtitre.setBorder(BorderFactory.createLineBorder(Color.blue));
		pn.add(lbtitre);
		//titre2
		lbtitre2=new JLabel("FORMULAIRE DE RENSEIGNEMENT SUR LES ELEVES");
		lbtitre2.setFont(new Font("Arial",Font.BOLD,16));
		//lb.setForeground(Color.BLUE);
		lbtitre2.setBounds(20,50,430,30);
		lbtitre2.setBorder(BorderFactory.createLineBorder(Color.blue));
		pn.add(lbtitre2);
		
		//
		lbmat=new JLabel("MATRICULE :");
		lbmat.setFont(new Font("Arial",Font.BOLD,12));
		lbmat.setForeground(Color.BLUE);
		lbmat.setBounds(80,95,150,30);
		pn.add(lbmat);
		//
		tfmat=new JTextField();
		tfmat.setBounds(160,95,150,25);
		pn.add(tfmat);
		
		//
		lbnom=new JLabel("NOM :");
		lbnom.setFont(new Font("Arial",Font.BOLD,12));
		lbnom.setForeground(Color.BLUE);
		lbnom.setBounds(40,135,80,30);
		pn.add(lbnom);
		
		//
		tfnom=new JTextField();
		tfnom.setBounds(90,135,220,25);
		pn.add(tfnom);
		
		//date de naissance
		lbdn=new JLabel("DATE DE NAISSANCE :");
		lbdn.setFont(new Font("Arial",Font.BOLD,12));
		lbdn.setForeground(Color.BLUE);
		lbdn.setBounds(27,175,150,30);
		pn.add(lbdn);

		//
		tfdaten=new JTextField();
		tfdaten.setBounds(160,175,150,25);
		pn.add(tfdaten);
		
	
		//*/
		lbsexe=new JLabel("SEXE :");
		lbsexe.setFont(new Font("Arial",Font.BOLD,12));
		lbsexe.setForeground(Color.BLUE);
		lbsexe.setBounds(95,215,150,30);
		pn.add(lbsexe);
		//radiosexe
		rb1=new JRadioButton("MASCULIN");
		rb1.setBounds(145,215,100,25);
	
		rb2=new JRadioButton("FEMININ");
		rb2.setBounds(245,215,70,25);
		pn.add(rb1);
		pn.add(rb2);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		//tfsexe
		tfsexe=new JTextField();
		tfsexe.setBounds(145,275,150,25);
		//pn.add(tfsexe);
		
		//*/
		lbclasse=new JLabel("CLASSE :");
		lbclasse.setFont(new Font("Arial",Font.BOLD,12));
		lbclasse.setForeground(Color.BLUE);
		lbclasse.setBounds(100,255,150,30);
		pn.add(lbclasse);
		//
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
		jcb_classe.setBounds(160,255,150,25);
		pn.add(jcb_classe);
		//tuteur
		lbtuteur=new JLabel("CONTACT TUTEUR :");
		lbtuteur.setFont(new Font("Arial",Font.BOLD,12));
		lbtuteur.setForeground(Color.BLUE);
		lbtuteur.setBounds(40,295,110,30);
		pn.add(lbtuteur);
		//
		tftuteur=new JTextField();
		tftuteur.setBounds(160,295,150,25);
		pn.add(tftuteur);
		//moy1
		lbmoy1=new JLabel("MOYENNE TRIMESTRE1 :");
		lbmoy1.setFont(new Font("Arial",Font.BOLD,12));
		lbmoy1.setForeground(Color.BLUE);
		lbmoy1.setBounds(17,335,150,30);
		pn.add(lbmoy1);

		//
		tfmoy1=new JTextField();
		tfmoy1.setBounds(160,335,150,25);
		pn.add(tfmoy1);
		//moy2
		lbmoy2=new JLabel("MOYENNE TRIMESTRE2 :");
		lbmoy2.setFont(new Font("Arial",Font.BOLD,12));
		lbmoy2.setForeground(Color.BLUE);
		lbmoy2.setBounds(17,375,150,30);
		pn.add(lbmoy2);

		//
		tfmoy2=new JTextField();
		tfmoy2.setBounds(160,375,150,25);
		pn.add(tfmoy2);
		//moy3
		lbmoy3=new JLabel("MOYENNE TRIMESTRE3 :");
		lbmoy3.setFont(new Font("Arial",Font.BOLD,12));
		lbmoy3.setForeground(Color.BLUE);
		lbmoy3.setBounds(17,415,150,30);
		pn.add(lbmoy3);
		//
		tfmoy3=new JTextField();
		tfmoy3.setBounds(160,415,150,25);
		pn.add(tfmoy3);
		//moy an
				lbmoyan=new JLabel("MOYENNE ANNUELLE :");
				lbmoyan.setFont(new Font("Arial",Font.BOLD,12));
				lbmoyan.setForeground(Color.BLUE);
				lbmoyan.setBounds(27,455,150,30);
				pn.add(lbmoyan);

				lbmoyan2=new JLabel();
				lbmoyan2.setFont(new Font("Arial",Font.BOLD,14));
				lbmoyan2.setForeground(Color.RED);
				lbmoyan2.setBounds(180,455,150,30);
				pn.add(lbmoyan2);
				//constat
				lbconst=new JLabel("CONSTAT :");
				lbconst.setFont(new Font("Arial",Font.BOLD,12));
				lbconst.setForeground(Color.BLUE);
				lbconst.setBounds(92,485,150,30);
				pn.add(lbconst);

				lbconst2=new JLabel();
				lbconst2.setFont(new Font("Arial",Font.BOLD,14));
				lbconst2.setForeground(Color.magenta);
				lbconst2.setBounds(180,485,150,30);
				pn.add(lbconst2);
		//rech2
		lbr=new JLabel("RECHERCHE PAR NOM :");
		lbr.setFont(new Font("Arial",Font.BOLD,12));
		lbr.setForeground(Color.BLUE);
		lbr.setBounds(70,530,200,30);
		pn.add(lbr);
		//
		tfr=new JTextField();
		tfr.setBounds(210,530,100,25);
		pn.add(tfr);
		//
		jbr=new JButton("OK");
		jbr.setBounds(310,530,60,25);
		jbr.addActionListener(this);
		pn.add(jbr);
	
		//
		jbajout=new JButton("Ajouter");
		jbajout.setBounds(20,580,100,30);
		jbajout.setFont(new Font("Arial",Font.BOLD,16));
		jbajout.setBackground(Color.green);
		jbajout.addActionListener(this);
		pn.add(jbajout);
		
		jbmodif=new JButton("Modifier");
		jbmodif.setBounds(150,580,100,30);
		jbmodif.setFont(new Font("Arial",Font.BOLD,16));
		jbmodif.setBackground(Color.orange);
		jbmodif.addActionListener(this);
		pn.add(jbmodif);
		//
		jbsupp=new JButton("Supprimer");
		jbsupp.setBounds(280,580,130,30);
		jbsupp.setFont(new Font("Arial",Font.BOLD,16));
		jbsupp.setBackground(Color.red);
		jbsupp.addActionListener(this);
		pn.add(jbsupp);
		//
		jbrech=new JButton("RECHERCHE");
		jbrech.setBounds(320,95,130,25);
		jbrech.setFont(new Font("Arial",Font.BOLD,14));
		jbrech.setBackground(Color.gray);
		jbrech.setForeground(Color.white);
		jbrech.addActionListener(this);
		pn.add(jbrech);

		//
		lbimage=new JLabel();
		lbimage.setBounds(520,20,260,220);
		pn.add(lbimage);
		//
		jbajpht=new JButton("Ajouter une photo");
		jbajpht.setBounds(480,300,200,25);
		jbajpht.setFont(new Font("Arial",Font.BOLD,14));
		jbajpht.setBackground(Color.cyan);
		jbajpht.addActionListener(this);
		pn.add(jbajpht);
		//
		jbrqcollective=new JButton("Requetes collectives");
		jbrqcollective.setBounds(480,350,200,25);
		jbrqcollective.setFont(new Font("Arial",Font.BOLD,14));
		jbrqcollective.setBackground(Color.cyan);
		jbrqcollective.addActionListener(this);
		pn.add(jbrqcollective);
		
		//
		jbnv=new JButton("Nouveau");
		jbnv.setBounds(430,580,130,30);
		jbnv.setFont(new Font("Arial",Font.BOLD,16));
		jbnv.setBackground(Color.orange);
		jbnv.addActionListener(this);
		pn.add(jbnv);
		//
		jbapropos=new JButton("A propos");
		jbapropos.setBounds(680,580,100,30);
		jbapropos.setFont(new Font("Arial",Font.BOLD,14));
		jbapropos.setBackground(Color.cyan);
		jbapropos.addActionListener(this);
		pn.add(jbapropos);
		
		//defilement
		first=new JButton("-Debut");
		first.setBounds(370,150,80,20);
		first.addActionListener(this);
		pn.add(first);
		
		next=new JButton("Suivant>");
		next.setBounds(360,180,90,20);
		next.addActionListener(this);
		pn.add(next);
		
		previous=new JButton("<Precedant");
		previous.setBounds(350,210,100,20);
		previous.addActionListener(this);
		pn.add(previous);
		

		last=new JButton("Fin-");
		last.setBounds(390,240,60,20);
		last.addActionListener(this);
		pn.add(last);
		
		
		
	}
	public static void main(String[] args){
		Gestion gn=new Gestion();
		gn.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e)  {
		// TODO Auto-generated method stub
		//ajout
		if(e.getSource()==jbajout){
			String a,b,c,d,f,g,h,i,j;
			a=tfmat.getText();b=tfnom.getText();c=tfdaten.getText();
			if(rb1.isSelected()) d=rb1.getText(); else d=rb2.getText();
			f=jcb_classe.getSelectedItem().toString();
			//
			if(tfmoy1.getText().equals("")) g="0"; else g=tfmoy1.getText();
			if(tfmoy2.getText().equals("")) h="0"; else h=tfmoy2.getText();
			if(tfmoy3.getText().equals("")) i="0"; else i=tfmoy3.getText();
			//
		j=tftuteur.getText();
			String qr="insert into eleve (matricule,nom,daten,sexe,classe,moy1,moy2,moy3,tuteur) "
		+ "values('"+a+"','"+b+"','"+c+"','"+d+"','"+f+"','"+g+"','"+h+"','"+i+"','"+j+"')";
			try{
				st=cn.laconnexion().createStatement();
				if(JOptionPane.showConfirmDialog(this,"Voulez-vous ajouter?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
					if(!vf.verifier(a)){
					st.executeUpdate(qr);
			      JOptionPane.showMessageDialog(this,"Insertion reussie!");}
					else
			JOptionPane.showMessageDialog(this,"Ce matricule existe deja!",null,JOptionPane.ERROR_MESSAGE);

					
				
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(this,"Impossible d'inserer!",null,JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//recherche
		if(e.getSource()==jbrech){
			
			String a=tfmat.getText();
			String k="select * from vueleve2 where matricule='"+a+"'";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(k);
				if(rst.next()){
					tfmat.setText(rst.getString("matricule"));			
		tfnom.setText(rst.getString("nom"));
		tfdaten.setText(rst.getString("daten"));
		//
	if(rst.getString("moy1").equals("0.00")) tfmoy1.setText("INDISPONIBLE");else tfmoy1.setText(rst.getString("moy1"));
	if(rst.getString("moy2").equals("0.00")) tfmoy2.setText("INDISPONIBLE");else tfmoy2.setText(rst.getString("moy2"));
	if(rst.getString("moy3").equals("0.00")) tfmoy3.setText("INDISPONIBLE");else tfmoy3.setText(rst.getString("moy3"));
		tftuteur.setText(rst.getString("tuteur"));
		//lbmoyan2.setText(rst.getString("moyan"));
		//
		if(
				rst.getString("moy1").equals("0.00")||rst.getString("moy2").equals("0.00")||
				rst.getString("moy3").equals("0.00")) 
		lbmoyan2.setText("INDISPONIBLE");
		else lbmoyan2.setText(rst.getString("moyan"));
		//
		jcb_classe.setSelectedItem(rst.getString("classe"));
		//
		if(rst.getString("moy1").equals("0.00")||
				rst.getString("moy2").equals("0.00")||rst.getString("moy3").equals("0.00"))
			lbconst2.setText("PAS ENCORE");
		else{lbconst2.setText(rst.getString("constat"));}
		//
		if(rst.getString("sexe").equals("MASCULIN")) rb1.setSelected(true);
		else {rb2.setSelected(true);}
		
		
		
		
		if(rst.getBytes("photo")==null)
			lbimage.setIcon(null);
		else{
		 byte[] img=rst.getBytes("photo");
		 ImageIcon image=new ImageIcon(img);
		 Image im=image.getImage();
Image myImg=im.getScaledInstance(lbimage.getWidth(),lbimage.getHeight(),Image.SCALE_SMOOTH);
ImageIcon newImag=new ImageIcon(myImg);
lbimage.setIcon(newImag);}


}
     
				else
		JOptionPane.showMessageDialog(this,"Introuvable!",null,JOptionPane.ERROR_MESSAGE);
			}
			catch(SQLException ex){		
		}	
			
		}
		//rech2
if(e.getSource()==jbr){
			
			String a=tfr.getText();
			String k="select * from vueleve2 where nom like'%"+a+"%'";
			try{
				st=cn.laconnexion().createStatement();
				rst=st.executeQuery(k);
				if(rst.next()){
					tfmat.setText(rst.getString("matricule"));			
		tfnom.setText(rst.getString("nom"));
		tfdaten.setText(rst.getString("daten"));	
		if(rst.getString("sexe").equals("MASCULIN")) rb1.setSelected(true);
		else {rb2.setSelected(true);}
		jcb_classe.setSelectedItem(rst.getString("classe"));
		///
		if(rst.getString("moy1").equals("0.00")) tfmoy1.setText("INDISPONIBLE");else tfmoy1.setText(rst.getString("moy1"));
	if(rst.getString("moy2").equals("0.00")) tfmoy2.setText("INDISPONIBLE");else tfmoy2.setText(rst.getString("moy2"));
	if(rst.getString("moy3").equals("0.00")) tfmoy3.setText("INDISPONIBLE");else tfmoy3.setText(rst.getString("moy3"));
		tftuteur.setText(rst.getString("tuteur"));
		tftuteur.setText(rst.getString("tuteur"));
		//lbmoyan2.setText(rst.getString("moyan"));
		if(
				rst.getString("moy1").equals("0.00")||rst.getString("moy2").equals("0.00")||
				rst.getString("moy3").equals("0.00")) 
		lbmoyan2.setText("INDISPONIBLE");
		else lbmoyan2.setText(rst.getString("moyan"));
		//
		if(rst.getString("moy1").equals("0.00")||
				rst.getString("moy2").equals("0.00")||rst.getString("moy3").equals("0.00"))
			lbconst2.setText("PAS ENCORE");
		else{lbconst2.setText(rst.getString("constat"));}
		//
		
		if(rst.getBytes("photo")==null)
			lbimage.setIcon(null);
		else{
		 byte[] img=rst.getBytes("photo");
		 ImageIcon image=new ImageIcon(img);
		 Image im=image.getImage();
Image myImg=im.getScaledInstance(lbimage.getWidth(),lbimage.getHeight(),Image.SCALE_SMOOTH);
ImageIcon newImag=new ImageIcon(myImg);
lbimage.setIcon(newImag);}


}
     
				else
		JOptionPane.showMessageDialog(this,"Introuvable!",null,JOptionPane.ERROR_MESSAGE);
			}
			catch(SQLException ex){
				
		}	
		}
		//supprimer
		if(e.getSource()==jbsupp){
			String rq="delete from eleve where matricule='"+tfmat.getText()+"'";
			try{
				st=cn.laconnexion().createStatement();
				if(JOptionPane.showConfirmDialog(this,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					st.executeUpdate(rq);
					JOptionPane.showMessageDialog(this,"Suppression reussie!");
				}
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(this,"Impossible de supprimer!",null,JOptionPane.ERROR_MESSAGE);	
			}
		}
		//modifier
		if(e.getSource()==jbmodif){
			String a,b,c,d,f,g,h,i,j,k,l;
			a=tfmat.getText(); b=tfnom.getText(); 
			c=tfdaten.getText();
			if(rb1.isSelected()) d=rb1.getText(); else d=rb2.getText();
			f=jcb_classe.getSelectedItem().toString();
			//g=tfmoy1.getText();h=tfmoy2.getText();i=tfmoy3.getText();
			if(tfmoy1.getText().equals("")||tfmoy1.getText().equals("INDISPONIBLE")) g="0"; else g=tfmoy1.getText();
			if(tfmoy2.getText().equals("")||tfmoy2.getText().equals("INDISPONIBLE")) h="0"; else h=tfmoy2.getText();
			if(tfmoy3.getText().equals("")||tfmoy3.getText().equals("INDISPONIBLE")) i="0"; else i=tfmoy3.getText();
			j=tftuteur.getText();
			
String rq="update eleve set nom='"+b+"',daten='"+c+"',sexe='"+d+"',classe='"+f+"'"
		+ ",moy1='"+g+"',moy2='"+h+"',moy3='"+i+"',tuteur='"+j+"' where matricule='"+a+"'";
			try{
				st=cn.laconnexion().createStatement();
				if(JOptionPane.showConfirmDialog(this,"Voulez-vous modifier?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					st.executeUpdate(rq);
					JOptionPane.showMessageDialog(this,"Modification reussie!");
				}
				
			}
          catch(SQLException ex){
        	  JOptionPane.showMessageDialog(this,"Impossible de modifier!",null,JOptionPane.ERROR_MESSAGE); 
          }
		}
		//RQ
		if(e.getSource()==jbrqcollective){
			Requetes rq=new Requetes();
			rq.setVisible(true);
			
		}
		//apropos
		if(e.getSource()==jbapropos){
			Apropos rq=new Apropos();
			rq.setVisible(true);
			
		}
	//nouveau
		if(e.getSource()==jbnv){
			dispose();
			Gestion gs=new Gestion();
			gs.setVisible(true);
		}
		//ajout photo
		if(e.getSource()==jbajpht){
			AjoutPhoto aj=new AjoutPhoto();
			aj.setVisible(true);
		}
		
if(e.getSource()==next){
	pos++;
	if(pos<bindlist().size()){
		showposinfo(pos);
		/*rb1.setVisible(false);rb2.setVisible(false);
		pn.add(tfsexe);*/
	}
	else
		pos=bindlist().size()-1;
	showposinfo(pos);
}

	if(e.getSource()==first){
		pos=0;
		showposinfo(pos);
		/*rb1.setVisible(false);rb2.setVisible(false);
		pn.add(tfsexe);*/
	}
	if(e.getSource()==last){
		pos=bindlist().size()-1;
		showposinfo(pos);
		/*rb1.setVisible(false);rb2.setVisible(false);
		pn.add(tfsexe);*/
		
	}
	if(e.getSource()==previous){
		pos--;
		if(pos>0){
			showposinfo(pos);
			/*rb1.setVisible(false);rb2.setVisible(false);
			pn.add(tfsexe);*/
			
		}
		else
			pos=0;
		showposinfo(pos);
	}
	
	}
       //
	
		
	}
	


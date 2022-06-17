package ges;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Font;
import java.sql.*;
public class Apropos extends JFrame {
	Connectage cn=new Connectage();
	public Apropos(){
		this.setTitle("chcode_appli");
		this.setSize(360,300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JTextArea ar=new JTextArea();
		ar.setBounds(15,10,322,240);
		ar.setEditable(false);
		ar.setFont(new Font("Arial",Font.BOLD,12));
		add(ar);
		setLayout(null);
		try{
			Statement st=cn.laconnexion().createStatement();
			ResultSet rst=st.executeQuery("select comment from aide where id=1");
			if(rst.next()){
				ar.setText(rst.getString("comment"));
			}
			
		}
		catch(SQLException ex){
			
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Apropos ap=new Apropos();
		ap.setVisible(true);

	}

}

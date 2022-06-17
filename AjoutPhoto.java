package ges;
import java.awt.Color;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AjoutPhoto extends JFrame implements ActionListener {
	JLabel lb1,lb2,lb3;
	JTextField tf;
	JButton jb,jb2;
	String s;
    public  AjoutPhoto(){
    	this.setTitle("chcode_appli");
    	this.setSize(340,400);
    	this.setLocationRelativeTo(null);
    	JPanel pn=new JPanel();
    	pn.setBackground(Color.LIGHT_GRAY);
    	pn.setLayout(null);
    	add(pn);
    	lb1=new JLabel("MATRICULE");
    	lb1.setBounds(10,20,100,30);
    	pn.add(lb1);
    	tf=new JTextField();
    	tf.setBounds(100,20,100,30);
    	pn.add(tf);
    	
    	lb2=new JLabel("PHOTO");
    	lb2.setBounds(40,60,100,30);
    	pn.add(lb2);
    	jb=new JButton("Parcourir");
    	jb.setBounds(100,60,100,30);
    	jb.addActionListener(this);
    	pn.add(jb);
    	
    	lb3=new JLabel();
    	lb3.setBounds(10,110,300,200);
    	pn.add(lb3);
    	
    	jb2=new JButton("Ajouter");
    	jb2.setBounds(100,320,100,30);
    	jb2.addActionListener(this);
    	pn.add(jb2);
    	
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 AjoutPhoto ph=new  AjoutPhoto();
   ph.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//parcourir general
		if(e.getSource()==jb){
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter=new FileNameExtensionFilter("*.IMAGE","jpg","gif","png");
			fileChooser.addChoosableFileFilter(filter);
			int result=fileChooser.showSaveDialog(null);
			if(result==fileChooser.APPROVE_OPTION){
				File selectedFile=fileChooser.getSelectedFile();
				String path=selectedFile.getAbsolutePath();
				lb3.setIcon(ResizeImage(path));
				 s=path;
			}
			else if(result==JFileChooser.CANCEL_OPTION){
				System.out.println("No data!");
			}
		}
		
		//ajout 
		if(e.getSource()==jb2){
			try{
				//Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sourcedb","root","");
PreparedStatement ps=con.prepareStatement("update eleve set photo=?  where matricule=? ");
			InputStream is=new FileInputStream(new File(s));
			ps.setBlob(1,is);
			ps.setString(2,tf.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Data inserted!"); 
			dispose();
			}
			catch(Exception ex){
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"probleme d'insertion!",null,JOptionPane.ERROR_MESSAGE); 
			}
			
		}//
		
	}
	public ImageIcon ResizeImage(String imPath){
		ImageIcon myImage=new ImageIcon(imPath);
		Image img=myImage.getImage();
		Image  newImage=img.getScaledInstance(lb3.getWidth(),lb3.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImage);
		return image;
		
	}
}

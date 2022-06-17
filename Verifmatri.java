package ges;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Verifmatri {
	Connectage cn=new Connectage();
	Statement st;
	ResultSet rst;
	boolean matrix;
	public boolean verifier(String tfmatri){
		boolean test=false;
	String matri=null;
	String qr="select matricule from eleve ";
	
	
	try{
		st=cn.laconnexion().createStatement();
		rst=st.executeQuery(qr);
		while((rst.next())||(test==true)){
			matri=rst.getString("matricule");
			if(matri.equals(tfmatri)){
				test=true;
				
			}
			
		}
		
	}
	catch(SQLException ex){
		
	}
	
		return test;
		
		
	}

}

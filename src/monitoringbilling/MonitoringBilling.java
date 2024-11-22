/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringbilling;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static monitoringbilling.loginn.*;
import static monitoringbilling.viewshipments.*;
import static monitoringbilling.addshipment.*;
import static monitoringbilling.Refundhistory.*;
import static monitoringbilling.Select.*;

/**
 *
 * @author Jhon Kenneth Doroja
 */
public class MonitoringBilling {

    Connection cn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    public static void main(String[] args) {
        loading a=new loading();
        a.setVisible(true);
        loginn b=new loginn();
        
        try {
            for(int i = 0; i <= 100; i ++){
            Thread.sleep(40);
            a.percentnum.setText(Integer.toString(i));
            a.bar.setValue(i);
                if (i==100) {
                    a.setVisible(false);
                    b.setVisible(true);
                }
            }
        } catch (Exception e) {
        }
    }

    public void loginn(){
        if (jTextFieldluser.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Enter Username");
        }
        else if(jPasswordFieldlpass.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter Password");
        }else{
            try {
                String sql="select * from accounts where username=? and password=? ";
                cn = DriverManager.getConnection("jdbc:mysql://localhost/monitoringg","root","");
                ps = cn.prepareStatement(sql);
                ps.setString(1,jTextFieldluser.getText());
                ps.setString(2,jPasswordFieldlpass.getText());
                
                rs=ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Success Login");
                    
                    
                    homepage a=new homepage();
                    a.setVisible(true);
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Failed Login");
                    new loginn().setVisible(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

/*    public void registerr(){
        if (jTextFielduser.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Username");
        }else if(jPasswordFieldpass.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter Password");
        }else if(jPasswordFieldpass.getText().length()<=5){
            JOptionPane.showMessageDialog(null,"Enter Password Atleast 6 Character");
        }else if(jPasswordFieldcpass.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Re Type Password");
        }else if(jPasswordFieldpass.getText().equals(jPasswordFieldcpass.getText())){
            try {
                String sql="INSERT INTO `accounts`(`username`, `password`) VALUES (?,?)";
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringg","root","");
                ps=cn.prepareStatement(sql);
                
                ps.setString(1, jTextFielduser.getText());
                ps.setString(2, jPasswordFieldpass.getText());
                
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Success");
                
                loginn a=new loginn();
                a.setVisible(true);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Your Password is Not Match");
            }
        }    */
    
   public void confirmships() {
        if (jTextField1_view.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Input a Container No");
        }else if(jTextField2_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Customer's FirstName");
        }else if(jTextField3_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Customer's MiddleName");
        }else if(jTextField4_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Customer's LastName");
        }else if(jTextField5_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Address");
        }else if(jTextField6_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Mobile No");
        }else if(jTextField8_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Deposit Costs");
        }else if(jTextField9_view.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Company Name");
        }
        else{
            try{
            String sql="INSERT INTO `history`(Con_No, Client_FirstName, Client_MiddleName, Client_LastName, Address, Mobile_No, Deposit_Costs, Company_No) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringg","root","");
            ps=cn.prepareStatement(sql);
            
            ps.setString(1, jTextField1_view.getText());
            ps.setString(2, jTextField2_view.getText());
            ps.setString(3, jTextField3_view.getText());
            ps.setString(4, jTextField4_view.getText());
            ps.setString(5, jTextField5_view.getText());
            ps.setString(6, jTextField6_view.getText());
            ps.setString(7, jTextField8_view.getText());
            ps.setString(8, jTextField9_view.getText());
            
            
            ps.executeUpdate();
            
        } 
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
                String sql= "DELETE FROM `Shipmentss` WHERE Con_No=?";
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringg","root","");
                ps=cn.prepareStatement(sql);
                
                ps.setString(1, jTextField1_view.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Shipment Confirmed");
                
                
                
            } 
                catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        
        
            
        
        }
    
        } 
   
   public void deleterefunds(){
               if(select1.getText().trim().isEmpty()) 
                {
                JOptionPane.showMessageDialog(null, "Please pick a Record.");  
       try {
                String sql= "DELETE FROM `history` WHERE Con_No=?";
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringg","root","");
                ps=cn.prepareStatement(sql);
                
                ps.setString(1, select1.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "One Refund History Deleted!");
                new Refundhistory().setVisible(true);
            } 
                catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
   }
   
   private JFrame frame;
   public void updateships() {
           
        if (jTextField1_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Container No.");
            }
            else if (jTextField2_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Client FirstName.");
            }
            else if (jTextField3_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Client MiddleName.");
            }
            else if (jTextField4_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert LastName.");
            }
            else if (jTextField5_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Address.");
            }
            else if (jTextField6_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Mobile No.");
            }
            else if (jTextField8_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Deposit Costs.");
            }
            else if (jTextField9_view.getText().trim().isEmpty()) 
            {
            JOptionPane.showMessageDialog(null, "Insert Company Name.");
            }
           
            else
            {          
                
        try
        {
            String sql = "UPDATE Shipmentss SET Con_No=?, Client_FirstName=?,"
                    + " Client_MiddleName=?, Client_LastName=?, Address=?, Mobile_No=?, Deposit_Costs=?, Company_No=? WHERE Con_No=?";
                     
            
            cn = DriverManager.getConnection("jdbc:mysql://localhost/monitoringg","root","");
            ps = cn.prepareStatement(sql);
            
            ps.setString(9, jTextField1_view.getText());
            ps.setString(1, jTextField1_view.getText());
            ps.setString(2, jTextField2_view.getText());
            ps.setString(3, jTextField3_view.getText());
            ps.setString(4, jTextField4_view.getText());
            ps.setString(5, jTextField5_view.getText());
            ps.setString(6, jTextField6_view.getText());
            ps.setString(7, jTextField8_view.getText());
            ps.setString(8, jTextField9_view.getText());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Shipment Updated Succesfully.");   
            
            
                }        
                catch (HeadlessException | SQLException ex)
                {
                JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
   
   
   public void Inserts(){
            frame = new JFrame("Please Confirm.");
        if (JOptionPane.showConfirmDialog(frame, "Please Confirm your Container Number, Once you've save it. it cannot be change again.", "Please Confirm.", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){
        frame = new JFrame("Confirmed?");
        if (JOptionPane.showConfirmDialog(frame, "Have you already confirmed your container no? Are you Sure?", "Confirmed?", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){
                if (jTextField1_add.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Input a Container No");
        }else if(jTextField2_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Customer's FirstName");
        }else if(jTextField3_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Customer's MiddleName");
        }else if(jTextField4_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Customer's LastName");
        }else if(jTextField10_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Address");
        }else if(jTextField6_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Mobile No");
        }else if(jTextField8_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Deposit Costs");
        }else if(jTextField10_add.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input a Company Name");
        }else{
        
            try{
            String sql = "INSERT INTO `shipmentss` (Con_No, Client_FirstName, Client_MiddleName, Client_LastName, Address, Mobile_No, Deposit_Costs, Company_No) VALUES (?,?,?,?,?,?,?,?)";
                    
                    
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringg","root","");
            ps=cn.prepareStatement(sql);
            
            ps.setString(1, jTextField1_add.getText());
            ps.setString(2, jTextField2_add.getText());
            ps.setString(3, jTextField3_add.getText());
            ps.setString(4, jTextField4_add.getText());
            ps.setString(5, jTextField10_add.getText());
            ps.setString(6, jTextField6_add.getText());
            ps.setString(7, jTextField8_add.getText());
            ps.setString(8, jTextField10_add.getText());
            
            
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Insert Succesfully!");
            
            new viewshipments().setVisible(true);
            
        
        }catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e); 
        }
        }
            new addshipment().setVisible(false);
        }
        }
   }
   
   public void view(){
               
                try {
                String sql="select * from shipmentss where Con_No=?";
                cn = DriverManager.getConnection("jdbc:mysql://localhost/monitoringg","root","");
                ps = cn.prepareStatement(sql);
                ps.setString(1,jTextField1_view.getText());
                
                
                rs=ps.executeQuery();
                if (rs.next()) {
                    
                    new Select().setVisible(true);
                    jTextArea1_select.setText("\tGT LORENZO LOGISTICS INC\n\n\n"
                                     + "________________________________________________________________\n"
                                     + "| Field Names\t|\t Field Values \t                  |\n"
                                     + "________________________________________________________________\n\n"
                                     + "\n"+jLabel1_select.getText()+":\t|\t"+jTextField1_view.getText()+"\n\n"
                                     + ""+jLabel2_select.getText()+":\t|\t"+jTextField2_view.getText()+"\n\n"
                                     + ""+jLabel3_select.getText()+":\t|\t"+jTextField3_view.getText()+"\n\n"
                                     + ""+jLabel4_select.getText()+":\t|\t"+jTextField4_view.getText()+"\n\n"
                                     + ""+jLabel5_select.getText()+":\t\t|\t"+jTextField5_view.getText()+"\n\n"        
                                     + ""+jLabel6_select.getText()+":\t|\t"+jTextField6_view.getText()+"\n\n"               
                                     + ""+jLabel8_select.getText()+":\t|\t"+jTextField8_view.getText()+"\n\n"                 
                                     + ""+jLabel9_select.getText()+":\t|\t"+jTextField9_view.getText()+"\n\n"
                                     + "________________________________________________________________"); 
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "There is no Value given.");
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
   }    
}

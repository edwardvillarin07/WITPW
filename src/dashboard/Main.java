package dashboard;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main 
{
  public static void main(String[] args) 
  {
    try 
  {
      String url = "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false";
      String user = "root";
      String password = "";
    
      Connection con = DriverManager.getConnection(url, user, password);
    
      String query = "SELECT * FROM users";
    
      Statement stm = con.createStatement();
      ResultSet res = stm.executeQuery(query);
    
      String columns[] = { "ID", "Name", "Age" };
      String data[][] = new String[8][3];
    
      int i = 0;
      while (res.next()) {
        int id = res.getInt("id");
        String username = res.getString("username");
        String pass = res.getString("password");
        data[i][0] = id + "";
        data[i][1] = username;
        data[i][2] = pass;
        i++;
      }
    
      DefaultTableModel model = new DefaultTableModel(data, columns);
      JTable table = new JTable(model);
      table.setShowGrid(true);
      table.setShowVerticalLines(true);
      JScrollPane pane = new JScrollPane(table);
      JFrame f = new JFrame("Populate JTable from Database");
      JPanel panel = new JPanel();
      panel.add(pane);
      f.add(panel);
      f.setSize(500, 250);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
    
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package crud;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jewela Amorganda
 */
public class CrudController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfaddress;
    @FXML
    private TextField tfnumber;
    @FXML
    private TableView<tableClass> tvtable;
    @FXML
    private TableColumn<tableClass, Integer> colid;
    @FXML
    private TableColumn<tableClass, Integer> coltel;
    @FXML
    private TableColumn<tableClass, String> coladdress;
    @FXML
    private TableColumn<tableClass, String> colname;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    
 
    


    
    public class tableClass {
        private int id;
        private String name;
        private String address;
        private int number;

        public tableClass(int id, String name, String address, int number) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.number = number;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public int getNumber() {
            return number;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTable();
    }
    @FXML
    private void btnClicked(ActionEvent event) {
        if(event.getSource()==btninsert) {
            insertValue();
        }
        else if(event.getSource()==btnupdate){
            updateValue();
        }
        else if(event.getSource()==btndelete){
            deleteValue();
        }   
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cruddatabase?user=root");
            return conn;
        }catch(SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public ObservableList<tableClass> getTableList() {
        ObservableList<tableClass> tableList=FXCollections.observableArrayList();
        Connection conn=getConnection();
        String query="SELECT * FROM crudtable";
        Statement st;
        ResultSet rs;
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            tableClass tableClass;
            while(rs.next()) {
                tableClass=new tableClass(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getInt("number"));
                tableList.add(tableClass);
            }   
        }catch(SQLException ex){
            System.out.print(ex);
        }
        return tableList;
    }
    
    public void showTable() {
         ObservableList<tableClass>list= getTableList();
         colid.setCellValueFactory(new PropertyValueFactory<>("id"));
         colname.setCellValueFactory(new PropertyValueFactory<>("name"));
         coltel.setCellValueFactory(new PropertyValueFactory<>("number"));
         coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
         
         tvtable.setItems(list);
    }
    
    public void insertValue() {
        String query= "INSERT INTO crudtable VALUES ("+tfid.getText() + ",'"+ tfname.getText()+ "','"+ tfaddress.getText() +"',"+ tfnumber.getText()+ ")";
        executeQuery(query);
        showTable();
    }
    
    public void updateValue() {
        String query="UPDATE crudtable SET id="+ tfid.getText()+ ",address='"+ tfaddress.getText() +"',number="+ tfnumber.getText()+ " WHERE name='"+tfname.getText()+"'";
        executeQuery(query);
        showTable();
    }
    
    public void deleteValue() {
        String query="DELETE FROM crudtable WHERE name='"+tfname.getText()+"'";
        executeQuery(query);
        showTable();
    }
    private void executeQuery(String query) {
        Connection conn=getConnection();
        Statement st;
        try {
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex) {
            System.out.print(ex);
        }
    }
    
    @FXML
    private void mouseClicked(MouseEvent event) {
        tableClass table=tvtable.getSelectionModel().getSelectedItem();
        tfid.setText(""+table.getId());
        tfname.setText(table.getName());
        tfaddress.setText(table.getAddress());
        tfnumber.setText(""+table.getNumber());
    }

    
}

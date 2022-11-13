import java.sql.*;

/*
 1. Import
 2. Load and register the driver. There are different drivers for diff databases
 3. Create connection
 4. Create a statement
 5. Execute the query.
 6. Process the results.
 7. Close connection.
 */

public class DemoClass {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/jdbc_tutorial?user=postgres&password=Pranita123";
        //String uname = "", pswd = "";

        //DQL - Select query
        String query = "select * from student";
        Class.forName("java.sql.Driver");                                   // ClassNotFoundException
        Connection con = DriverManager.getConnection(url);       // SQLException
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        rs.next();  // To get the pointer to first of data
        String name = rs.getString("name");
        System.out.println(name);

        //DML -Insert Data
//        int id = 5;
//        String query2 = "insert into student values (" + id + ",'Boo1',25)";    //using id variable

        //Prepared statement -- when value keeps changing for your query
        String query2 = "insert into student values (?,?,?)";
        PreparedStatement ps = con.prepareStatement(query2);
        ps.setInt(1,6);
        ps.setString(2,"Boo2");
        ps.setInt(3,26);
        int count = ps.executeUpdate();     //no need to send query2 here
        System.out.println(count);


        st.close();
        con.close();
    }
}

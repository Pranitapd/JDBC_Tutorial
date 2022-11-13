import java.sql.*;

class StudentDao{

    Connection con;

    public void connect() throws SQLException {
        try{
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_tutorial?user=postgres&password=Pranita123");
    }

    public int addStudent(int id, String name, int age) throws SQLException {
        connect();
        String addQuery = "insert into student values (?,?,?)";
        PreparedStatement pst = con.prepareStatement(addQuery);
        pst.setInt(1,id);
        pst.setString(2, name);
        pst.setInt(3,age);
        return pst.executeUpdate();
    }

    public void removeStudent(int id) throws SQLException {
        connect();
        String removeQuery = "delete from student where id=?";
        PreparedStatement pst = con.prepareStatement(removeQuery);
        pst.setInt(1,id);
        pst.executeUpdate();
    }

    public int updateStudent(int id, String name) throws SQLException {
        connect();
        String updateQuery = "update student set name=? where id=?";
        PreparedStatement pst = con.prepareStatement(updateQuery);
        pst.setInt(2,id);
        pst.setString(1,name);
        return pst.executeUpdate();
    }

    public void showAllRows() throws SQLException {
        connect();
        String getAllRows = "select * from student";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(getAllRows);
        System.out.println("All rows in db-");
        while(rs.next()){
            System.out.println("id " + rs.getInt("id") + " name " + rs.getString("name") + " age " + rs.getInt("age"));
        }
    }
}

public class DaoExample {
    public static void main(String[] args) throws SQLException {
        StudentDao s = new StudentDao();

        s.addStudent(14,"Pimmi",24);
        s.removeStudent(14);
        s.updateStudent(1,"Neha");
        s.showAllRows();
    }

}

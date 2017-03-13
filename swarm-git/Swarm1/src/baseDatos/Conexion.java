package baseDatos;

import java.sql.*;

public class Conexion {

	
	public Conexion()
	{
	}
	
	protected Connection conectar()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url= "jdbc:mysql://localhost:8081/usuario";
		Connection con=null;
		try {
			con = DriverManager.getConnection(url, "root","12345");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	protected void cerrarConexion(Connection con)
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected Statement abrirStatement(Connection con)
	{
		Statement st=null;
		try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	
	protected void cerrarStatement(Statement st)
	{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}

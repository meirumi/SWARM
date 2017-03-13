package baseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Usuario;

public class UsuariosBD extends Conexion {

	
	public UsuariosBD()
	{
		
	}
	//Método que sirve para comprobar si se ha insertado correctamente un usuario o si un nombre de usuario ya existe
	public boolean comprobarUsuario(String usuario)
	{
		Connection con=conectar();
		Statement s=abrirStatement(con);
		
		String query="Select idUsuario from usuario where nombreUsuario like '"+usuario+"' ";
		boolean insertado=false;
		ResultSet r=null;
		try {
			r = s.executeQuery(query);
		
			if(r.next())
			{
				insertado=true;
			}
			r.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			cerrarStatement(s);
			cerrarConexion(con);
		}
		return insertado;
	}
	
	public void insertUsuario(Usuario u)
	{
		Connection con=super.conectar();
		Statement s=super.abrirStatement(con);
		boolean existeYa=comprobarUsuario(u.getName());
		
		String insert="insert into usuario (nombreUsuario,password,email1,email2) values ('"+u.getName()+"','"+u.getPwd()+"','"+u.getEmail1()+"','"+u.getEmail2()+"')";
		if(existeYa==false)
		{
			try {
				s.executeUpdate(insert);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boolean insertado=comprobarUsuario(u.getName());
			if(insertado==true)
			{
				System.out.println("El usuario ha sido insertado");
			}
		}
		else{
			System.out.println("El usuario ya existe");
		}

		cerrarStatement(s);
		cerrarConexion(con);
		
	
	}
	
	public Usuario mostrarUsuario(Usuario u)
	{
		Connection con=super.conectar();
		Statement s=super.abrirStatement(con);
		
		String datos="Select nombreUsuario,password,email1,email2 from usuario where nombreUsuario like '"+u.getName()+"'";
				
		ResultSet r=null;
		try {
			r = s.executeQuery(datos);
		
			while(r.next())
			{
				u.setPwd(r.getString("password"));
				u.setEmail1(r.getString("email1"));
				u.setEmail2(r.getString("email2"));
			}
			r.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			cerrarStatement(s);
			cerrarConexion(con);
		}
		return u;
	}
	
}

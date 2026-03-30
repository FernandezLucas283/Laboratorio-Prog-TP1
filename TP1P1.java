import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class TP1P1 {
    
    private Connection conexion = null;
    private Scanner leer = new Scanner(System.in); 

    public TP1P1() {
        try {
            conectar();
            menu(); 
        } catch (SQLException e) {
            e.printStackTrace();    
        } finally {
            cerrar();
        }
    }

    public void conectar() throws SQLException {
        String jdbc = "jdbc:mysql://localhost/playlist_spotify";
        conexion = DriverManager.getConnection(jdbc, "root", "0109");
        System.out.println("Conexion OK");
    }

    public void cerrar() {
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void menu() throws SQLException {
        int opcion = 0;
        do {
            System.out.println("1. Agregar tema ");
            System.out.println("2. Mostrar todos ");
            System.out.println("3. Modificar tema ");
            System.out.println("4. Borrar tema");
            System.out.println("5. Buscar tema");
            System.out.println("0. Salir");
            System.out.print("Elija una opcion: ");
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1: agregarTema(); 
                break;
                case 2: mostrarTemas(); 
                break;
                case 3: modificarTema(); 
                break;
                case 4: borrarTema(); 
                break;
                case 5: buscarTema(); 
                break;
                case 0: System.out.println("Saliendo"); 
                break;
                default: System.out.println("No valido");
            }
        } while (opcion != 0);
    }

    private void agregarTema() throws SQLException {
        String SQL = "INSERT INTO splaylist (titulo, interprete, cantidad_temas, duracion_total, genero_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement tema = null;  
        try {
            tema = conexion.prepareStatement(SQL);            
            System.out.print("Titulo de la cancion: ");
            tema.setString(1, leer.nextLine()); 

            System.out.print("Nombre del Intérprete: ");
            tema.setString(2, leer.nextLine()); 

            System.out.print("Cantidad de temas en el álbum: ");
            tema.setInt(3, leer.nextInt()); 

            System.out.print("Duración total: ");
            tema.setDouble(4, leer.nextDouble()); 

            System.out.print("ID del Género (1:Rock, 2:Pop, 3:Latino, 4:Jazz): ");
            tema.setInt(5, leer.nextInt()); 
            
            leer.nextLine(); 

            tema.executeUpdate();         
            System.out.println("Tema guardado");

        } catch (SQLException e) {
            System.err.println("Error al intentar agregar el tema.");
            e.printStackTrace();
        } finally {
            if (tema != null) {
                tema.close();
            }           
        }
    }

		
   private void mostrarTemas() throws SQLException {
    String SQL = "SELECT p.id, p.titulo, p.interprete, p.cantidad_temas, p.duracion_total, g.nombre " + "FROM splaylist p " + "INNER JOIN generos g ON p.genero_id = g.id";
                 
    PreparedStatement tema = null;  
    try {
        tema = conexion.prepareStatement(SQL);
        java.sql.ResultSet set = tema.executeQuery();
        while (set.next()) {
            int id = set.getInt("id");
            String titulo = set.getString("titulo");
            String interprete = set.getString("interprete");
            int cantidad_temas = set.getInt("cantidad_temas");
            double duracion_total = set.getDouble("duracion_total");
            String nombreGenero = set.getString("nombre"); 

            System.out.println("id: " + id + ", Titulo: " + titulo + ", Interprete: " + interprete + ", Genero: " + nombreGenero + ", Temas: " + cantidad_temas + ", Duración: " + duracion_total);
        }
    } catch (SQLException e) {
        System.err.println("Error al intentar mostrar los temas.");
        e.printStackTrace();
    } finally {
        if (tema != null) {
            tema.close();
        }
    }
}
	private void modificarTema() throws SQLException {
		String SQL = "UPDATE splaylist SET titulo = ?, interprete = ?, cantidad_temas = ?, duracion_total = ?, genero_id = ? WHERE id = ?";
		PreparedStatement tema = null;  
		try {
			tema = conexion.prepareStatement(SQL);            
			System.out.print("ID del tema a modificar: ");
			int id = leer.nextInt();
			leer.nextLine(); 

			System.out.print("Nuevo iítulo de la cancion: ");
			tema.setString(1, leer.nextLine()); 

			System.out.print("Nuevo nombre del interprete: ");
			tema.setString(2, leer.nextLine()); 

			System.out.print("Nueva cantidad de temas en el álbum: ");
			tema.setInt(3, leer.nextInt()); 

			System.out.print("Nueva duración total: ");
			tema.setDouble(4, leer.nextDouble()); 

			System.out.print("Nuevo ID del Género (1:Rock, 2:Pop, 3:Latino, 4:Jazz): ");
			tema.setInt(5, leer.nextInt()); 
			
			tema.setInt(6, id); 

			tema.executeUpdate();         
			System.out.println("Tema modificado");

		} catch (SQLException e) {
			System.err.println("Error al intentar modificar el tema.");
			e.printStackTrace();
		} finally {
			if (tema != null) {
				tema.close();
			}           
		}
	}
	private void borrarTema() throws SQLException {
		String SQL = "DELETE FROM splaylist WHERE id = ?";
		PreparedStatement tema = null;
		try {
			tema = conexion.prepareStatement(SQL);
			System.out.print("Id del tema a borrar: ");
			int id = leer.nextInt();
			
			tema.setInt(1, id);
			tema.executeUpdate();
			System.out.println("Tema borrado");
		}
		 catch (SQLException e) {
			System.err.println("Error al intentar eliminar un tema.");
			e.printStackTrace();
		} finally {
			if (tema != null) {
				tema.close();
			}
		}   
	}
	
    private void buscarTema() throws SQLException {
    String SQL = "SELECT p.id, p.titulo, p.interprete, p.cantidad_temas, p.duracion_total, g.nombre " + "FROM splaylist p " + "INNER JOIN generos g ON p.genero_id = g.id " + "WHERE p.id = ?";
                 
    PreparedStatement tema = null;
    ResultSet set = null; 

    try {
        tema = conexion.prepareStatement(SQL);
        System.out.print("ID del tema a buscar: ");
        int id = leer.nextInt();
        leer.nextLine();
        tema.setInt(1, id);
        set = tema.executeQuery(); 
        
        System.out.println("Datos de busqueda: ");
        boolean encontro = false;
        while (set.next()) {
            encontro = true;
            int idTema = set.getInt("id");
            String titulo = set.getString("titulo");
            String interprete = set.getString("interprete");
            int cantidad_temas = set.getInt("cantidad_temas");
            double duracion_total = set.getDouble("duracion_total"); 
            String nombreGenero = set.getString("nombre"); 

            System.out.println("ID: " + idTema + " Titulo: " + titulo +  " Interprete: " + interprete + " Género: " + nombreGenero + " Temas: " + cantidad_temas +  " Duración: " + duracion_total);
        }
        
        if (!encontro) {
            System.out.println("No se encontro");
        }
    } catch (SQLException e) {
        System.err.println("Error al intentar buscar un tema.");
        e.printStackTrace();
    } finally {
        if (set != null) set.close();
        if (tema != null) tema.close();
    }
}

    public static void main(String[] args) {
        new TP1P1();
    }
}
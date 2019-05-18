import java.sql.*;
import java.util.*;
/**
 *
 * @author ighor
 */
public class ComputadoresDao {
    private final String sqlC = "INSERT INTO computadores (marca, processador, ram, disco)"
            + " VALUES (?,?,?,?)";
    private final String sqlR = "SELECT * FROM computadores";
    private final String sqlU = "UPDATE computadores SET marca =?, processador=?, ram=?, disco=?"
            + " WHERE id =?";
    private final String sqlD = "DELETE FROM computadores WHERE id=?";
    
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    public ComputadoresDao(ConexaoJavaDb conexao) throws DaoException, ConexaoException{
        try{
            Connection con = conexao.getConnection();
            stmC = con.prepareStatement(sqlC, Statement.RETURN_GENERATED_KEYS);
            stmR = con.prepareStatement(sqlR);
            stmU = con.prepareStatement(sqlU);
            stmD = con.prepareStatement(sqlD);     
        } catch (SQLException ex){
            throw new DaoException("Falha ao preparar statement: " + ex.getMessage());
        }
    }
    
    public long create (Computador c) throws DaoException{
        long id = 0;
        try{
            stmC.setString(1, c.getMarca());
            stmC.setString(2, c.getProcessador());
            stmC.setInt(3, c.getRam());
            stmC.setString(4, c.getDisco());
            int r = stmC.executeUpdate();
            ResultSet rs = stmC.getGeneratedKeys();
            if(rs.next()){
                id = rs.getLong(1);
            }
        }catch(SQLException ex){
            throw new DaoException("Falha ao criar registro: "+ ex.getMessage());
        }
        return id;
    } 
    
    public List<Computador> read() throws DaoException{
        List<Computador> computadores = new ArrayList<>();
        try{
            ResultSet rs = stmR.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                String marca = rs.getString("marca");
                String processador = rs.getString("processador");
                int ram = rs.getShort("ram");
                String disco = rs.getString("disco");
                
                Computador c = new Computador(id, marca, processador, ram, disco);
                computadores.add(c);
            }
            rs.close();
        } catch(SQLException ex){
            throw new DaoException ("Falha ao ler registros: " + ex.getMessage());
        }
        return computadores;
    }
    
    public void update(Computador c) throws DaoException{
        try{
            stmU.setString(1, c.getMarca());
            stmU.setString(2, c.getProcessador());
            stmU.setInt(3, c.getRam());
            stmU.setString(4, c.getDisco());
            stmU.setLong(5, c.getId());
            int r = stmU.executeUpdate();
        } catch(SQLException ex){
            throw new DaoException("Falha ao atualizar registro: " + ex.getMessage());
        }
    }
    
    public void delete (long id)throws DaoException{
        try{
            stmD.setLong(1, id);
            int r = stmD.executeUpdate();
        } catch(SQLException ex){
            throw new DaoException ("Falha ao apagar registro: " + ex.getMessage());
        }
    }
    
    public void close() throws DaoException{
        try{
            stmC.close();
            stmR.close();
            stmU.close();
            stmD.close();
        } catch(SQLException ex){
            throw new DaoException("Falha ao fechar DAO: " + ex.getMessage());
        }
    }
    
}

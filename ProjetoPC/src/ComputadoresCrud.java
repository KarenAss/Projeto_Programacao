
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31846327
 */
public class ComputadoresCrud {
    public static void main(String[] args) {
        try{
            //carregamento do driver
            //conexao com bd 
            //definição das frases SQL
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            out.println("Driver JDBC carregado!");
            
            String url = "jdbc:derby://localhost:1527/computador";
            String usuario = "computador";
            String senha = "computador";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            out.println("Conexão estabelecida com sucesso!");
            
            
            String sqlInsert = "INSERT INTO computadores (marca, processador, ram, disco)";
            sqlInsert += "VALUES (?,?,?,?)";
            String sqlSelect = "SELECT * FROM computadores";
            String sqlUpdate = "UPDATE computadores SET marca =?, processador=?, ram=?, disco=?";
            sqlUpdate += "WHERE id =?";
            String sqlDelete = "DELETE FROM computadores WHERE id=?";
            
            //sentenças para execução
            PreparedStatement stmInsert = conexao.prepareStatement(sqlInsert);
            PreparedStatement stmSelect = conexao.prepareStatement(sqlSelect);
            PreparedStatement stmUpdate = conexao.prepareStatement(sqlUpdate);
            PreparedStatement stmDelete = conexao.prepareStatement(sqlDelete);
            
            
            //menu principal
            boolean querSair = false;
            Scanner sc = new Scanner (System.in);
            
            while (!querSair){
                out.println("\nMenu: ");
                out.println("(1) Criar Registro");
                out.println("(2) Ler Registros");
                out.println("(3) Atualizar Registro");
                out.println("(4) Apagar Registro");
                out.println("(5) Sair");
                out.print("Opção escolhida: ");
                int opcao = Integer.parseInt(sc.nextLine());
                
            //inserção 
                if (opcao == 1){
                    System.out.println("Marca do computador: ");
                    String marca = sc.nextLine();
                    System.out.println("Processador do computador: ");
                    String processador = sc.nextLine();
                    System.out.println("Ram do computador (somente numeros): ");
                    int ram = Integer.parseInt(sc.nextLine());
                    System.out.println("Tamanho do disco: ");
                    String disco = sc.nextLine();
                    
                    //insert 
                    stmInsert.setString(1, marca);
                    stmInsert.setString(2, processador);
                    stmInsert.setInt(3, ram);
                    stmInsert.setString(4, disco);
                    
                    int retorno = stmInsert.executeUpdate();
                    out.println("Registros inseridos: " + retorno);
                }
                //consultar e imprimir 
                else if (opcao == 2){
                    ResultSet rs = stmSelect.executeQuery();
                    out.print("Computadores: ");
                    
                    while (rs.next()){
                        Long id = rs.getLong("id");
                        String marca = rs.getString("marca");
                        String processador = rs.getString("processador");
                        int ram = rs.getShort("ram");
                        String disco = rs.getString("disco");
                        
                        
                        out.println(id + " - " + marca + " - " + processador + " - " + ram + " - " + disco);
                    }
                    rs.close();
                }
                
               
                
                //update 
                else if (opcao == 3){
                    out.println("Id do registro a ser atualizado: ");
                    long id = Long.parseLong(sc.nextLine());
                    out.println("Nova marca: ");
                    String marca = sc.nextLine();
                    out.println("Novo processador: ");
                    String processador = sc.nextLine();
                    out.println("Nova ram: ");
                    int ram = Integer.parseInt(sc.nextLine());
                    out.println("Novo disco: ");
                    String disco = sc.nextLine();
                    
                    //atualizar o registro
                    stmUpdate.setString(1, marca);
                    stmUpdate.setString(2, processador);
                    stmUpdate.setInt(3, ram);
                    stmUpdate.setString(4, disco);
                    stmUpdate.setLong(5, id);
                    
                    int retorno = stmUpdate.executeUpdate();
                    out.println("Registros atualizados: " + retorno);   
                }
                
                 
                //delete
                else if (opcao == 4){
                    out.println("Id do registro a ser apagado: ");
                    long id = Long.parseLong(sc.nextLine());
                    
                    // apagar o registro
                    stmDelete.setLong(1, id);
                    int retorno = stmDelete.executeUpdate();
                    out.println("Registros apagados: "+ retorno);
                    
                }
                else if (opcao == 5){
                    querSair = true;
                }
                else{
                    out.println("Opção Inválida!");
                }
            }
            
            conexao.close();
        } 
            
        
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            out.println("Driver não encontrado!");
        } catch (SQLException ex){
            ex.printStackTrace();
            out.println("Erro de conexão!");   
        }
    }
}



import static java.lang.System.out;
import java.util.*;
/**
 *
 * @author ighor
 */
public class App {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner (System.in);
            ConexaoJavaDb conexao = new ConexaoJavaDb("computador", "computador", 
            "localhost", 1527, "computador");
            ComputadoresDao dao  = new ComputadoresDao(conexao);
            
            //menu principal 
            boolean querSair = false;
            while (!querSair){
                out.println("\nMenu: ");
                out.println("(1) Adicionar novo Computador ");
                out.println("(2) Listar Computadores");
                out.println("(3) Atualizar um Computador");
                out.println("(4) Apagar um Computador");
                out.println("(5) Sair");
                out.print("Opção escolhida: ");
                int opcao = Integer.parseInt(sc.nextLine());
            
            
            if (opcao == 1){
                
            }
            
            else if (opcao == 2){
                // read 
            List<Computador> computadores = dao.read();
            System.out.println("\nLISTA DE COMPUTADORES: ");
            for (int i = 0; i < computadores.size(); i++) {
                Computador c = (Computador) computadores.get(i);
                System.out.println("*" + c.getMarca() + " - Processador: " + c.getProcessador()
                + " - RAM: " + c.getRam() + " - Disco: " + c.getDisco());
            }
            System.out.println("\nPressione Enter para continuar");
            sc.nextLine();
            }
            
            else if (opcao == 5){
            querSair = true;
            }
            }
             dao.close();
             conexao.close();
        } catch (DaoException ex){
            System.out.println(ex.getMsg());
        } catch (ConexaoException ex){
            System.out.println(ex.getMsg());
        }
    }
}

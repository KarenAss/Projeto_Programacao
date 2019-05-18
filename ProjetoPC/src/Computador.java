
/**
 *
 * @author ighor
 */
public class Computador {
    private long id;
    private String marca;
    private String processador;
    private int ram;
    private String disco;
    
    public Computador (long id, String marca, String processador, int ram, String disco){
        this.id = id;
        this.marca = marca;
        this.processador = processador;
        this.ram = ram;
        this.disco = disco;      
    }
    
    public Computador(){
        id = -1;
        marca = "";
        processador = "";
        ram = 0;
        disco = "";
    }
    
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    
    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getProcessador(){
        return processador;
    }
    public void setProcessador(String processador){
        this.processador = processador;
    }
    
    public int getRam(){
        return ram;
    }
    public void setRam(int ram){
        this.ram = ram;
    }
    
    public String getDisco(){
        return disco;
    }
    public void setDisco(String disco){
        this.disco = disco;
    }
}


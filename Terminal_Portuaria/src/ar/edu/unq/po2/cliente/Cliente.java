package ar.edu.unq.po2.cliente;


/*
* Para que tipee en unas partes del codigo. Quien implemente shipper y consignee vera que cambios hacer e implementar ;)
* */
/*
public interface Cliente {
   
    public String nombreCliente();
}


*/
public class Cliente{
	
	String nombreCliente;
	
	public Cliente(String nombre) {
		this.nombreCliente=nombre;
	}
	
   public String nombreCliente() {
	   return nombreCliente;
   }

}
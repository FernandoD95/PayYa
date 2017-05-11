/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayYa;
import java.util.Calendar;
/**
 *
 * @author Fernando
 */
public class Operacion {
    //"CuentaPY{" + "Nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI +", Nombre Entidad="+ NomEntidad  + ", Cantidad de dinero= "+dinero+ '}' 
    
   private String nombre;
   private String apellidos;
   private String Receptor;
   private String infoReceptor;
   private int IDop=0;
   private Calendar fecha=Calendar.getInstance();
   private float cantidad;
   private float cuenta;
   private String[] recep ={", Receptor=",", Emisor="};
   
    public Operacion(String nombre, String apellidos, String Receptor,String infoReceptor ,float cantidad,float cuenta, int id,int año,int mes) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.infoReceptor = infoReceptor;
        this.Receptor = Receptor;
        this.cantidad = cantidad;
        this.cuenta=cuenta;
        this.IDop = id;
        this.fecha.set(año, mes,1);
    }

    public float getCantidad() {
        return cantidad;
    }

    public int getIDop() {
        return IDop;
    }

    public Calendar getFecha() {
        return fecha;
    }
    
    
    
    @Override
    /*public String toString() {               //Esto seria el toString normal, lo que he hecho es simplificarlo
        return "Operaciones{" + " Operacion =" + (IDop+1) + "º , Nombre=" + nombre + ", Apellidos=" + apellidos + ", Receptor=" + Receptor + ", Informacion receptor=" + infoReceptor +", Saldo en la cuenta="+cuenta+ ", Cantidad=" + cantidad +", Fecha(MM/yyyy)="+fecha.get(Calendar.MONTH) +"/"+fecha.get(Calendar.YEAR) + '}';
    }*/
    public String toString() {
        return " Op " + (IDop+1) + "º , Nombre=" + nombre + ((cantidad>0)?recep[0]:recep[1] )+ Receptor + ", Saldo en la cuenta="+cuenta+ ", Cantidad=" + cantidad +", Fecha(MM/yyyy)="+fecha.get(Calendar.MONTH) +"/"+fecha.get(Calendar.YEAR) + '}';
    }
    

    
    
   
   
}

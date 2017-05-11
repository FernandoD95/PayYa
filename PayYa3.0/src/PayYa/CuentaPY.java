/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayYa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
/**
 *
 * @author Fernando
 */
public class CuentaPY {
     private String nombre,apellidos;
     private String DNI;
     private String Email;
     private String Direccion;
     private String CuentaBancaria,NomEntidad,DirEntidad;  //Nombre y direccion de la entidad
     private String Contraseña;
     private int año=1990,mes=1;
     /**
      * Cantidad de dinero en la cuenta.
      */
     private float cuenta;
     private float max;
     private int max_op=50;
     private Operacion[] operaciones=new Operacion[max_op];
     //private ArrayList<Operacion> operaciones;
     private int operacion=0;
     
     /**
      * Esto es el constructor de la cuenta de usuario particular.
      * @param nombre
      * @param apellidos
      * @param DNI
      * @param Email
     * @param Contraseña
      * @param Direccion
      * @param CuentaBancaria
      * @param NomEntidad
      * @param DirEntidad
      * @param cuenta 
      */
    public CuentaPY(String nombre, String apellidos, String DNI, String Email,String Contraseña, String Direccion, String CuentaBancaria, String NomEntidad, String DirEntidad, float cuenta) {
       if(cuenta>0){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.Email = Email;
        this.Direccion = Direccion;
        this.CuentaBancaria = CuentaBancaria;
        this.NomEntidad = NomEntidad;
        this.DirEntidad = DirEntidad;
        this.cuenta = cuenta;
        this.max=cuenta;
        this.Contraseña=Contraseña;
        
       }
    }

    /**
     * @author Fernando
     * Esta funcion hace transferencias de personas reciviendo su Nombre y informacion es decir
     * el correo, y tambien genera una nueva Transaccion() @see 
     * @param dinero
     * @param Nombre
     * @param info 
     */
    public void transPersona(float dinero, String Nombre, String info){
    
    this.cuenta=this.cuenta+dinero;
     if(this.cuenta>this.max)
            this.max=this.cuenta;
        Transaccion(this.nombre, this.apellidos, Nombre, info, dinero,this.año,this.mes);   
    }
    
    /**
     * Este metodo sirve para hacer transacciones con el banco, 
     * coge el dinero y lo incluye en la cuenta, tambien 
     * genera una transaccion.
     * @param dinero 
     */
    public void transBanco(float dinero){
        this.cuenta=this.cuenta+dinero;
        if(this.cuenta>this.max)
            this.max=this.cuenta;
        Transaccion(this.nombre, this.apellidos, this.DNI, this.NomEntidad, dinero,this.año,this.mes);      
    }
    
    /**
     * Este metodo sirve para extraer dinero de la cuenta, y ademas con el resto de parametros
     * los ponemos en la nueva transaccion y en esta se guarda el nombre y otro campo llamado info.
     * @param retirar Cantidad de dinero a retirar
     * @param Nombre Nombre del que retira o nombre de la entidad
     * @param info Informacion del Nombre
     */
    public void retirarDinero(float retirar, String Nombre, String info){
       if((-max*0.05)<(this.cuenta-retirar)| (this.cuenta-retirar>0)){
            this.cuenta=this.cuenta-retirar;
            Transaccion(this.nombre, this.apellidos, Nombre, info, -retirar,this.año,this.mes);
       }
   }
    
    /**
     * Este metodo sirve para hacer transferencias de la cuenta de PayYa
     * a la cuenta del banco con el parametro Nombre como nombre del parametro.
     * @param retirar
     * @param Nombre 
     */
     public void retirarDinero(float retirar){
       if((-max*0.05)<(this.cuenta-retirar) | (this.cuenta-retirar>0)){
            this.cuenta=this.cuenta-retirar;
            Transaccion(this.nombre, this.apellidos, this.DirEntidad, this.NomEntidad, -retirar,this.año,this.mes);
       }
   }
     
   /**
    * Este metodo genera un nuevo objeto Transaccion que guarda la informacion de la transaccion realizada
    * @param nom Nombre de la persona de la cuenta
    * @param apellido Apellido de la persona de la cuenta
    * @param info Informacion de la segunda persona
    * @param nom2 Entidad con la que se hizo la operacion
    * @param cantidad Valor de la transferencia
    * @param año Fecha de la operacion
    * @param mes Fecha de la operacion
    */
    private void Transaccion(String nom, String apellido, String info, String nom2,float cantidad, int año, int mes){
        operaciones[operacion]=new Operacion(nom, apellido, nom2, info ,cantidad,this.cuenta, operacion, año,mes);
        this.operacion++;     
    }
    
    /**
     * Ordena e imprime el array por orden de cuantia 
     * @return Una array de String con las operaciones ordenadas
     */
    public String[] ImprTranCuantia(){
       Operacion[] op=new Operacion[this.operacion];
       Operacion aux;
       String[] auxop=new String[this.operacion];
       for(int i=0;i<this.operacion;i++){
           op[i]=this.operaciones[i];          
       }
      
       for(int i=1;i<this.operacion;i++){
              for(int j=0;j<this.operacion-1;j++){
                     if(op[j].getCantidad() < op[j+1].getCantidad()){
                            aux=op[j];
                            op[j]=op[j+1];
                            op[j+1]=aux;
                            
                     }
            }
       }
       
       for(int i=0;i<this.operacion;i++){        
           auxop[i]=op[i].toString();
       }
       
       return auxop;
     
    }
    
    /**
 * Imprime el array de operaciones por la fecha en la que se hizo la operacion
 * @param anyo
 * @param mes 
 * @return  Una assay de  String con todas las operaciones del mes
 */
    public String[] ImprimirFecha(int anyo, int mes){
        String[] auxop=new String[this.operacion];
        int pos=0;
        float total=0;
        Calendar fecha=Calendar.getInstance();
        fecha.set(anyo, mes,1);
        for(int i=0;i<this.operacion;i++){
            
            if(operaciones[i].getFecha().get(Calendar.YEAR)==fecha.get(Calendar.YEAR) & operaciones[i].getFecha().get(Calendar.MONTH)==fecha.get(Calendar.MONTH)){     //if(operaciones[i].getFecha().equals(fecha)   -> He utilizado este metodo pero no funciona
                total=total+operaciones[i].getCantidad();
                auxop[pos]=operaciones[i].toString()+", Valance del mes: "+total;                                                                                                                                                                                     
                pos++;
            }           
        }
        
        
        return auxop;
        
    }
    
    /**
     * Imprime todas las operaciones y las ordena por fecha, y dado que las operaciones son consecutivas
     * no hace falta ordenarlas
     * @return Array con todas las operaciones
     */
    public String[] ImprimirTranFecha(){
        String[] aux =new String[this.operacion];
        for (int i = 0; i < this.operacion; i++) {
            aux[i]=operaciones[i].toString();
        }
        return aux;
    }
    
    public  String getEmail() {
        return Email;
    }
 
    public String getDNI() {
        return DNI;
    }
    
    /**
     * Esta funcion es la encargada de decir la cantidad de dinero que tiene la cuenta
     * @return Float con la cantidad de dinero en la cuenta
     */
    public float getCuenta() {
        return this.cuenta;
    }
    
    public String ImprTran(int i){
        return this.operaciones[i].toString();
    }
    
    /**
     * Este metodo sirve para simular la fecha en la que queremos hacer la transaccion 
     * @param anyo
     * @param mes 
     */
    public void setFecha(int anyo, int mes){
        this.año=anyo;
        this.mes=mes; 
    }

    public String getContraseña() {
        return Contraseña;
    }

    /*public String getNombreApellidos() {
        return nombre+""+apellidos;
    }*/

    public String getNomEntidad() {
        return NomEntidad;
    }
      
    
    @Override
    public String toString() {
        return "CuentaPY{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI + ", Email=" + Email + ", Direccion=" + Direccion + ", CuentaBancaria=" + CuentaBancaria + ", NomEntidad=" + NomEntidad + ", DirEntidad=" + DirEntidad + ", cuenta=" + cuenta + '}';
    }     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayYa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class Gestor_CuentasPY {
    
    
    
   // private CuentaPY[] Cuentas=new CuentaPY[this.max];
    private HashMap<String,CuentaPY> Cuentas= new HashMap<>();
    /**
     * En este gestor de cuentas es donde se gestiona la entrada de nuevos clientes
     * Se compara si ya hay alguna otra cuenta con ese correo
     * Y si no es asi se almacena en la base de datos 
     * Solo es necesario enviar la nueva cuenta creada
     * @see CuentaExistente(String Email)
     * @param cuenta 
     * 
     */
    public  boolean AnyadirCuenta(CuentaPY cuenta){ 
        
        if(!Cuentas.containsKey(cuenta.getEmail())){
            Cuentas.put(cuenta.getEmail(), cuenta);
            return true;
        }
        else 
            return false;
    }
    
    public  boolean IniciarSesion(String correo, String contraseña){
        boolean correcto=false;
        if(Cuentas.get(correo).getContraseña().equals(contraseña))
            correcto=true;
        return correcto;
    }
    
    public CuentaPY ObtenerCuenta(String correo){
        return Cuentas.get(correo);
    }
    
    
    
    
    
    /**
     * Este metodo tiene como finalidad ver las cuentas que no 
     * tienen un saldo positivo
     * 
     */
    public  String[]  numerosRojos(){
        ArrayList<String> aux=new ArrayList<>();
        
        
        for (Map.Entry<String, CuentaPY> entry : Cuentas.entrySet()) {                              //https://es.stackoverflow.com/questions/2464/c%C3%B3mo-iterar-a-trav%C3%A9s-de-un-hashmap
            if(0>entry.getValue().getCuenta())
            aux.add(entry.toString());
        }
        String[] aux1= new String[aux.size()];
        for(int i=0;i<aux.size();i++){
            aux1[i]=aux.get(i);
        }
        return aux1;
    }
    
    /**
 * Este metodo sirve para fijar una fecha y hacer operaciones, con el fin
 * de poder hacer transacciones en diferentes meses y años.
 * @param año 
 * @param mes 
 */    
    public void setFechas(int año, int mes){
     for (Map.Entry<String, CuentaPY> entry : Cuentas.entrySet()) { 
            entry.getValue().setFecha(año, mes);
 
                }
    
}    
    
    /**
     * Este metodo nos sirve para poder mover dinero del banco a la cuenta
     * de PayYa indicando el dinero recivido y el correo, este posteriormente sera 
     * comprobado con el fin de saber si se encuentra en la base de datos.
     * @param dinero 
     * @param correo Correo al que se le va a dar el dinero
     * @see BuscarCuenta(String correo)
     */
    public  void TransferenciaBanco(float dinero,String correo){
        if((!BuscarCuenta(correo).equals("-1"))&RangoTrans(dinero))
            Cuentas.get(BuscarCuenta(correo)).transBanco(dinero);
        //else
            //System.out.println("La operacion no se ha podido realizar");   
        }
    
    /**
    * Metodo que transfiere el dinero de la cuenta al banco
    * @param dinero
    * @param correo Cuenta que va a dar el dinero
    */ 
    public void TransferenciaCuentaBanco(float dinero,String correo){
         if((!BuscarCuenta(correo).equals("-1"))&RangoTrans(dinero))
            Cuentas.get(BuscarCuenta(correo)).retirarDinero(dinero);
        //else
            //System.out.println("La operacion no se ha podido realizar");   
    }
    
    /**
        *Esta funcion devuelve un int con la posicion en el array de cuentas de usuario
        * @param correo Este es el correo que se va a buscar
        * @return Devuelve la posicion en la cual esta la cuenta, en caso de no encontrar coincidencia devuelve -1
    */
    private String BuscarCuenta(String correo){  //Creo que se podria hacer quitando pos y poniendo dentro del bucle un return i .     
        String NIF="-1";
        for (Map.Entry<String, CuentaPY> entry : Cuentas.entrySet()) {     
            if(entry.getValue().getEmail().equals(correo))
                NIF=entry.getKey();
            
        }
        return NIF;
    }
    public boolean CorreoExistente(String correo){
        for (Map.Entry<String, CuentaPY> entry : Cuentas.entrySet()){                            
            if(entry.getValue().getEmail().equals(correo))
                return true;
        }
        return false;
    }
    
    /**
         * Esta funcion junto con BuscarCuenta(String cuenta) y Costes(float cantidad) quita el dinero correo1 y lo pone en correo2
         * con sus comisiones
     * @param cantidad 
     * @param correo1 de entrada
     * @param correo2 de salida
     * @see BuscarCuenta()
     * @see RangoTrans()
         * 
         */
    public  void TraspasoPerPer(float cantidad,String correo1,String correo2){
       String c1=BuscarCuenta(correo1),c2=BuscarCuenta(correo2);
        
        if(!c1.equals("-1") & !c2.equals("-1") & RangoTrans(cantidad)){
            if(!correo1.equals(correo2)){
                
                Cuentas.get(c1).retirarDinero((cantidad+Costes(cantidad)),Cuentas.get(c2).getEmail(), Cuentas.get(c2).getDNI() );
                Cuentas.get(c2).transPersona(cantidad, Cuentas.get(c1).getEmail(), Cuentas.get(c1).getDNI());
            }
             //else
                //System.out.println("No se puede transferir a la misma persona");
           }
        //else
            //System.out.println("La operacion no se ha podido realizar");   //Error dice que la cuenta no existe y que no esta en el rango
    }   
    
    /**
     * Este metodo sirve para imprimir las operaciones que ha realizado la cuenta
     * por la cuantia 
     * 
     * @param correo correo de la persona 
     * @return  Devuelve el array de String
     */
    public  String[] ImprimirCuantia(String correo){
        return Cuentas.get(BuscarCuenta(correo)).ImprTranCuantia(); 
    }
    
    /**
     *  Este metodo sirve para imprimir las operaciones que ha realizado la cuenta
     * por la fecha
     * @param correo
     * @return Devuelve el array de String
     */
    public  String[] ImprimirTranFecha(String correo){
        return Cuentas.get(BuscarCuenta(correo)).ImprimirTranFecha(); 
    }

    /**
      * Metodo interno que comprueba que el rango de la transaccion se encuentra
      * entre los limites pre-establecidos 0 y 10000
      * @param cantidad Valor de la transaccion
      * @return 
      */
    public  boolean RangoTrans(float cantidad){
        boolean rango=true;
        if(cantidad<=0|cantidad>10000){
            rango=false;
            // System.out.println("La transaccion ha de ser de un numero entre 0 y 10000");//Podria crear un tipo de transferencia fallida con el fin de saber que se intento pero no llego a realizarse
        }    
        return rango;
    }
    
    /**
     * Este metodo interno sirve para ver las comisiones que 
     * va a tener cierta transaccion
     * 
     * @param cantidad
     * @return Devuelve la el coste de la comision
     */
    private  float Costes(float cantidad){
        float comision=0;
        if(RangoTrans(cantidad)){
        if(cantidad<=10)
            comision=cantidad*(0.1F);
        if(cantidad>10 & cantidad<=1000 )
            comision=cantidad*(0.3F);
        if(cantidad>1000 )
            comision=cantidad*(0.4F);
        }
        return comision;
    }
    
    /**Este metodo filtra las operaciones en funcion del mes en el que se
     * realizaron,    
     * @param correo Correo que se va a filtrar
     * @param anyo Año del filtro
     * @param mes Mes del filtro
     * @return 
     */
    public  String[] ImprimirFecha(String correo,int anyo,int mes){   
        return Cuentas.get(BuscarCuenta(correo)).ImprimirFecha(anyo, mes);       
        }
    
    public  ArrayList<String> Usuarios(){
        ArrayList<String> aux=new ArrayList<>();
        for (Map.Entry<String, CuentaPY> entry : Cuentas.entrySet()) {     
           aux.add("Usuario: "+entry.getValue().getEmail()+" Contraseña: "+entry.getValue().getContraseña());
        }
        return aux;
        
    }
    
   @Override
    public String toString() {
        return "Gestor_CuentasPY{" + "Numero de cuentas=" +Cuentas.size() + '}' ;// Arrays.toString(Cuentas) forma de imprimir todo el array
    }
    
    
    
}


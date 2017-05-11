/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Probador_PayYa;
import PayYa.CuentaPY;
import PayYa.Gestor_CuentasPY;
import PayYa.Operacion;
/**
 *
 * @author Fernando
 */
public class Probador_de_PayYa {

    /**
     *
     * @param args
     */
    public static void main (String args[]){
        int ops=0;
        Gestor_CuentasPY PayYa= new Gestor_CuentasPY();
        CuentaPY Luis=new CuentaPY("Luis","M P","120302131N","Luis@Gmail","1","Calle 2","436243652345","Santander","Calle 1",5),pepe=new CuentaPY("Pepe","M M","312413414J","Pepe2000@outlook","1","Calle 4","68574549648","Bankia","Calle 1",50),jose=new CuentaPY("Jose","T G","31235425H","Jose66@MSN","1","Calle 6","4896864383","Santander","Calle 4",5),uno=new CuentaPY("Nom","Apellido","1","1","1","Calle 2","436243652345","Santander","Calle 1",5);
        PayYa.AnyadirCuenta(Luis);
        PayYa.AnyadirCuenta(pepe);
        PayYa.AnyadirCuenta(jose);
        PayYa.AnyadirCuenta(uno);
        
        PayYa.setFechas(2016, 5);
        //Primeros traspasos 
        PayYa.TransferenciaBanco(1000, "Jose66@MSN");
        PayYa.TransferenciaBanco(1000, "Luis@Gmail");
        //Gestor_CuentasPY.TransferenciaBanco(100, "Pepe2000@outlook");
        PayYa.TransferenciaBanco(500, "Luis@Gmail");
        PayYa.TransferenciaBanco(500, "1");
        //Cambio de mes
        PayYa.setFechas(2017, 1);
        PayYa.TransferenciaCuentaBanco(15, "Luis@Gmail");
        //Gestor_CuentasPY.TraspasoPerPer(15, "Pepe2000@outlook","Luis@Gmail");
        PayYa.TransferenciaCuentaBanco(535, "Luis@Gmail");
        //Gestor_CuentasPY.TraspasoPerPer(25, "Luis@Gmail", "Pepe2000@outlook");
        //Gestor_CuentasPY.TraspasoPerPer(4, "Pepe2000@outlook","Jose66@MSN");
        PayYa.TransferenciaCuentaBanco(950, "Luis@Gmail");
        PayYa.TraspasoPerPer(2, "Luis@Gmail", "Jose66@MSN");
        PayYa.TransferenciaBanco(1000, "Luis@Gmail");
        PayYa.TransferenciaCuentaBanco(51, "Pepe2000@outlook");
        System.out.println("Numeros rojos:");
        imprimir(PayYa.numerosRojos());
          
         System.out.println("Por fecha(2016-5):");
         imprimir(PayYa.ImprimirFecha("Luis@Gmail", 2016, 5));
         System.out.println("Por fecha(2017-1):");
         imprimir(PayYa.ImprimirFecha("Luis@Gmail", 2017, 1));
         
         System.out.println("Por cuantia:");
        imprimir(PayYa.ImprimirCuantia("Luis@Gmail"));
        
         System.out.println("Por fecha:");
        imprimir(PayYa.ImprimirTranFecha("Luis@Gmail"));
         
         
         
    }
    
    
    public static void imprimir(String[] imp){
        
        for(int i=0;i<imp.length;i++){
            if(imp[i]!=null)
          System.out.println(imp[i]);     
        }      
    }
   
    public static void crearcuentas(Gestor_CuentasPY Cuentas){
         //Gestor_CuentasPY PayYa= new Gestor_CuentasPY();
        CuentaPY Luis=new CuentaPY("Luis","M P","120302131N","Luis@Gmail","1","Calle 2","436243652345","Santander","Calle 1",5),pepe=new CuentaPY("Pepe","M M","312413414J","Pepe2000@outlook","1","Calle 4","68574549648","Bankia","Calle 1",50),jose=new CuentaPY("Jose","T G","31235425H","Jose66@MSN","1","Calle 6","4896864383","Santander","Calle 4",5),uno=new CuentaPY("Nom","Apellido","1","1","1","Calle 2","436243652345","Santander","Calle 1",5);
        Cuentas.AnyadirCuenta(Luis);
        Cuentas.AnyadirCuenta(pepe);
        Cuentas.AnyadirCuenta(jose);
        Cuentas.AnyadirCuenta(uno);
        
        Cuentas.setFechas(2016, 5);
        //Primeros traspasos 
        Cuentas.TransferenciaBanco(1000, "Jose66@MSN");
        Cuentas.TransferenciaBanco(1000, "Luis@Gmail");
        //Gestor_CuentasPY.TransferenciaBanco(100, "Pepe2000@outlook");
        Cuentas.TransferenciaBanco(500, "Luis@Gmail");
        Cuentas.TransferenciaBanco(500, "1");
      
        //Cambio de mes
        Cuentas.setFechas(2017, 1);
       Cuentas.TransferenciaCuentaBanco(15, "Luis@Gmail");
        //Gestor_CuentasPY.TraspasoPerPer(15, "Pepe2000@outlook","Luis@Gmail");
        Cuentas.TransferenciaCuentaBanco(535, "Luis@Gmail");
        //Gestor_CuentasPY.TraspasoPerPer(25, "Luis@Gmail", "Pepe2000@outlook");
        //Gestor_CuentasPY.TraspasoPerPer(4, "Pepe2000@outlook","Jose66@MSN");
        Cuentas.TransferenciaCuentaBanco(950, "Luis@Gmail");
        Cuentas.TraspasoPerPer(2, "Luis@Gmail", "Jose66@MSN");
        Cuentas.TransferenciaBanco(1000, "Luis@Gmail");
        //Cuentas.TransferenciaCuentaBanco(51, "Pepe2000@outlook");
        System.out.println("Numeros rojos:");
        //imprimir(Gestor_CuentasPY.numerosRojos());
          
         /*System.out.println("Por fecha(2016-5):");
         imprimir(Gestor_CuentasPY.ImprimirFecha("Luis@Gmail", 2016, 5));
         System.out.println("Por fecha(2017-1):");
         imprimir(Gestor_CuentasPY.ImprimirFecha("Luis@Gmail", 2017, 1));
         
         System.out.println("Por cuantia:");
        imprimir(Gestor_CuentasPY.ImprimirCuantia("Luis@Gmail"));
        
         System.out.println("Por fecha:");
        imprimir(Gestor_CuentasPY.ImprimirTranFecha("Luis@Gmail"));*/
         
    }
    
}

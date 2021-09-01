import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        mostrarMenu();
        do
            options(generarArreglo());
        while (salir(validInt(getIntInput(),0,1)));

    }

    public static void mostrarMenu(){
        System.out.println("1 - Mostrar Arreglo");
        System.out.println("2 - Mostrar sismo de mayor magnitud");
        System.out.println("3 - Contar sismos mayores o iguales a 5.0");
        System.out.println("4 - Enviar SMS por cada sismo mayor o igual a 7.0\n");
        System.out.println("0 - Salir");
    }

    public static double[][] generarArreglo() {
        double magnitud[][] = new double[7][10];
        for (int i=0;i<7;i++) {
            for (int j=0;j<10;j++){
                magnitud[i][j]=generarSismo();
            }
        }

        return magnitud;
    }

    public static double generarSismo(){
        Random r= new Random();
        return r.nextDouble()+r.nextInt(9);
    }

    public static boolean options(double[][] sismos){
        switch (validInt(getIntInput(),0,4)) {
            case 0:
                System.out.println("Si desea cerrar el programa ingrese '0', sino ingrese cualquier número");
                break;
            case 1:
                mostrarArreglo(sismos);
                break;
            case 2:
                System.out.println(String.format("%.1f",buscarMayorSismo(sismos)));
                break;
            case 3:
                System.out.println(contarSismos(sismos));
                break;
            case 4:
                System.out.println(enviarSMS(sismos));
                break;
        }
        return false;
    }

    public static void mostrarArreglo(double[][] sismos){
        for (int i=0;i<7;i++) {
            System.out.println("Día: "+i+1);
            for (int j=0;j<10;j++){
                System.out.print(String.format("%.1f",sismos[i][j])+" ; ");
            }
            System.out.println("");
        }
    }

    public static double buscarMayorSismo( double[][] sismos){
        double max=0;
        for (int i=0;i<10;i++) {
            for (int j=0;j<7;j++){
                if (sismos[i][j]>max){
                    max=sismos[i][j];
                }
            }
        }
        return max;
    }

    public static int contarSismos( double[][] sismos){
        int counter=0;
        for (int i=0;i<7;i++) {
            for (int j=0;j<10;j++){
                if (sismos[i][j]>5){
                    counter++;
                }
            }
        }
        return counter;
    }

    public static String enviarSMS(double[][] sismos){
        boolean alerta=false;
        for (int i=0;i<7;i++) {
            for (int j=0;j<10;j++){
                if (sismos[i][j]>7){
                    alerta=true;
                }
            }
        }
        if(alerta) return ("Alerta!!! se debe evacuar zona costera!");
        else return "";
    }

    public static boolean salir(int input){
        if (input == 0) {
            return true;
        }else return false;
    }

    public static int getIntInput(){
        Scanner intro = new Scanner(System.in);
        return err(intro.nextLine());
    }

    public static int err(String input){
        Scanner intro = new Scanner(System.in);
        int in=0;
        try {
            in=Integer.parseInt(input);
        } catch (Exception ex) {
            System.out.println("Favor ingresar Dígitos y no carácteres");
            in=err(intro.next());
        }
        return in;
    }

    public static int validInt(int in,int bot, int top){
        if (in<bot || in >top) {
            System.out.println("Favor ingresar un número entre "+bot+" y "+top);
            in=validInt(getIntInput(),bot,top);
        }
        return in;
    }
}
package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //Variables
        //Input
        Scanner sc = new Scanner(System.in);
        String posReinas = "";
        boolean entReinas = true;
        String[] reinasStr = new String[8];
        int reinasX[] = new int[8];
        int reinasY[] = new int[8];
        //Diagonales
        int diagX = 0;
        int diagY = 0;
        boolean ataque = false;
        int reinaAtaque = 0;

        //Pedir Input
        pedirInput(posReinas,entReinas,sc,reinasStr,reinasX,reinasY);

        //Comprobacion de reinas
        for (int j=0;j<reinasX.length;j++){
            for (int k=0;k<reinasX.length;k++){
                //Evitar misma reina
                if (j!=k){
                    //Horizontal y Vertical
                    if (reinasX[j]==reinasX[k]||reinasY[j]==reinasY[k]){
                        ataque = false;
                        reinaAtaque = j;
                        k = reinasX.length;
                        j = reinasX.length;
                    }else{
                        //Diagonal
                        diagX = reinasX[j];
                        diagY = reinasY[j];
                        for (int r=0;r<reinasX.length;r++){
                            if ((reinasX[k]==diagX+r && reinasY[k]==diagY+r)||
                                    (reinasX[k]==diagX-r && reinasY[k]==diagY-r)||
                                    (reinasX[k]==diagX+r && reinasY[k]==diagY-r)||
                                    (reinasX[k]==diagX-r && reinasY[k]==diagY+r)){
                                ataque = false;
                                reinaAtaque = j;
                                r = reinasX.length;
                                k = reinasX.length;
                                j = reinasX.length;
                            } else {
                                ataque = true;
                            }
                        }
                    }
                }
            }
        }
        //Respuesta Final
        if (ataque){
            System.out.println(true);
        } else {
            System.out.println("\"("+reinasX[reinaAtaque]+","+reinasY[reinaAtaque]+")\"");
        }
    }

    //Metodo para obtener inputs
    public static void pedirInput(String posReinas,boolean entReinas,Scanner sc,String[] reinasStr,int reinasX[],
                                  int reinasY[]){
        do{ //Evita pattern incorrecto
            System.out.println("Solo acepta este formato de input:");
            System.out.println("[\"(2,1)\", \"(4,3)\", \"(6,3)\", \"(8,4)\", \"(3,4)\", \"(1,6)\", \"(7,7)\", \"(5,8)\"]");
            System.out.println("Ingresa las posiciones de las Reinas:");
            posReinas = sc.nextLine();
            entReinas = Pattern.compile("\\[(\"\\([1-8],[1-8]\\)\",\\s){7}(\"\\([1-8],[1-8]\\)\")\\]").
                    matcher(posReinas).matches();
        }while(!entReinas);
        //Separar
        reinasStr = posReinas.split("\\(");
        //Ingresar a Arrays X y Y
        for (int y=1,h=0;y<reinasStr.length;y++){
            reinasX[h] = Integer.parseInt(reinasStr[y].substring(0,1));
            reinasY[h] = Integer.parseInt(reinasStr[y].substring(2,3));
            h++;
        }
        //Comprobar que 2 reinas no esten en las mismas coordenadas
        for (int q=0;q<8;q++) {
            for (int t=0;t<8;t++) {
                if (q!=t) {
                    if (reinasX[q]==reinasX[t] && reinasY[q]==reinasY[t]) {
                        System.out.println("Incorrecto, 2 posiciones de Reinas iguales");
                        pedirInput(posReinas,entReinas,sc,reinasStr,reinasX,reinasY);
                    }
                }
            }
        }
    }

}

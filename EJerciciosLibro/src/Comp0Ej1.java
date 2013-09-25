import io.IO;

public class Comp0Ej1 {
	public static void main(String[] args) {
//		int x = IO.pideEntero("Ponga un numero",-99999,99999);
//		int y = IO.pideEntero("Ponga otro numero",-99999,99999);
//		System.out.println("La suma de ellos es "+ (x + y));
//		String nombre = IO.pideCadena("Pon tu nombre");
//		System.out.println("Hola, estimado "+ nombre);
//		int lado1, lado2, lado3;
//		lado1 = IO.pideInt("Lado 1:",0,1000);
//		lado2 = IO.pideInt("Lado 2:",0,1000);
//		lado3 = IO.pideInt("Lado 3:",0,1000);
//		System.out.println("El perímetro es: "+(lado1+lado2+lado3));
		int numero1, numero2;
		numero1 = IO.pideInt("Primer número:", 0, 1000);
		numero2 = IO.pideInt("Segundo número:", 0, 1000);
		if(numero1 > numero2){
			System.out.println("El mayor es "+ numero1);
		}else if(numero2 > numero1){
			System.out.println("El mayor es "+ numero2);
		}else{
			System.out.println("Son iguales");
		}
	}
}

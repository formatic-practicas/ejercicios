import io.IO;



public class Comp0Ej6 {
	public static void main(String[] args) {
		float numero1, numero2;
		numero1 = IO.pideFloat("Pon un numero", 0, 1000);
		numero2 = IO.pideFloat("Pon otro numero", 0, 1000);
		if(numero1 > numero2){
			System.out.println("La superficie del cuadrado es: "+ numero1*numero1);
			System.out.println("La superficie del círculo es: " +Math.PI * numero2 * numero2);
		}else{
			System.out.println("La superficie del cuadrado es: "+ numero2*numero2);
			System.out.println("La superficie del círculo es: " +Math.PI * numero1 * numero1);
			
		}
	}
}

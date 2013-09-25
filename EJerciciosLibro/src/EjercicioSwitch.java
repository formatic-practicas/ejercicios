import io.IO;

public class EjercicioSwitch {
	
	public static void main(String[] args) {
		int numero;
		do {
			numero = IO.pideEntero("Ponga un numero de "+DESDE+" a "+ HASTA+ "(0 para terminar)",DESDE, HASTA);
			switch (numero) {
			case 0:
				break;
			case 1:
				System.out.println("Amarás a dios sobre todas las cosas");
				break;
			case 2:
				System.out.println("No cometerás actos impuros");
				break;
			case 3:
				System.out.println("No matarás");
				break;
			case 4:
				System.out.println("Santificarás las fiestas");
				break;
			case 5:
				System.out.println("No desearás a la mujer del prójimo");
				break;
			case 6:
				System.out.println("Visitarás la meca una vez en la vida");
				break;
			case 7:
				System.out.println("Apedrearás a la mujer adúltera");
				break;
			case 8:
				System.out.println("Cortarás una mano al ladrón");
				break;
			case 9:
				System.out.println("No verás Canal+");
				break;
			case 10:
				System.out.println("Te gustará Billy Wilder");
				break;
			default:
				break;
			}
		} while (numero != 0);
	}

	
	static int DESDE = 0;
	/**
	 * 
	 */
	static int HASTA = 10;
}

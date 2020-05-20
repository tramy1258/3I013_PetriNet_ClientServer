package clientserver;
public class TestGenerator {

	public static void main (String[] args) {
		
		int nbcli;
		int nbsv;
		
		try {
			nbcli = Integer.parseInt(args[0]);
			nbsv  = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: ./cliser_os nb_clients nb_serveurs");			
			return;
		}
		
		System.out.println("Model of "+nbcli+" client(s)-"+nbsv+" server(s) ");
		Generator1 gen = new Generator1();
		gen.generate_file(nbcli, nbsv, 750, 500);

	}
}

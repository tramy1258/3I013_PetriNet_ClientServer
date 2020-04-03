public class TestGenerator {

	public static void main (String[] args) {
		
		/*System.out.println("---Automatic files generating---");
		Generator1 genauto = new Generator1();
		for (int i=1; i<4; i++) {
			for (int j=1; j<4; j++) {
				genauto.generate_file(i, j, 500);
			}			
		}
		System.out.println("--------------------------------\n");*/
		int nbcli;
		int nbsv;
		
		try {
			nbcli = Integer.parseInt(args[0]);
			nbsv  = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage (to generate a specific model): java TestGenerator nb_clients nb_serveurs");			
			return;
		}
		
		System.out.println("Model of "+nbcli+" client(s)-"+nbsv+" server(s) ");
		Generator1 gen = new Generator1();
		gen.generate_file(nbcli, nbsv, 500);

	}
}

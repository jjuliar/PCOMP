import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{

			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			
			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);
			System.out.println(codigo);
			String output = backend.geraResultado(arv);
			System.out.println("Resultado: " + output);

			File arquivo = new File("/home/maria/Documents/comp/maq/comp-trab-1/teste-saida");
			try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
				escritor.write(codigo);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}



	}
}

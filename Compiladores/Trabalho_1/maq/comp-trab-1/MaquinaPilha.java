import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class MaquinaPilha {
    public static void main(String[] args) throws Exception{
        String entrada = args[0]; //pega o arquivo passado como argumento
        Stack<Integer> pilha = new Stack<>();
        BufferedReader reader = new BufferedReader(new FileReader(entrada));

        String linha;
        Integer resultado = 0;
        while ((linha = reader.readLine()) != null) { // aqui ele vai lendo o arquivo e colocando os resultados na pilha
            if (linha.startsWith("PUSH")) {
                int num = Integer.parseInt(linha.split(" ")[1]); //pegar o num depois do espaço
                pilha.push(num);
                System.out.println(pilha.peek());
            } else if (linha.equals("MULT")) {
                int left = pilha.pop();
                int right = pilha.pop();
                resultado = right *left;
                pilha.push(resultado);
            }else if (linha.equals("DIV")) {
                int left = pilha.pop();
                int right = pilha.pop();
                resultado = right /left;
                pilha.push(resultado);
            }else if (linha.equals("SUM")) {
                int left = pilha.pop();
                int right = pilha.pop();
                resultado = right +left;
                pilha.push(resultado);
            }else if (linha.equals("SUB")) {
                int left = pilha.pop();
                int right = pilha.pop();
                resultado = right -left;
                pilha.push(resultado);
            }else if (linha.equals("PRINT")) {
                System.out.println(pilha.peek());
            }

        }
        reader.close();
    }
}
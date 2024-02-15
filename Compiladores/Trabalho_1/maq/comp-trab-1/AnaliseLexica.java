import java.io.*;

enum TokenType{ NUM, SOMA, MULT, SUB, DIV, APar, FPar, EOF}

class Token{
  String lexema;
  TokenType token;

 Token (String l, TokenType t)
 	{ lexema=l;token = t;}	

}

class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	Token getNextToken() throws Exception
	{	
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;
		// Usar a classe StringBuilder para ir juntando os numeros
		StringBuilder numBuilder = new StringBuilder();

			do{
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 != eof && currchar1 !=10)
			{
								
	
				if (currchar >= '0' && currchar <= '9'){
					// return (new Token (currchar, TokenType.NUM)); // return antigo

					numBuilder.append(currchar); //guarda o primeiro char
					arquivo.mark(1); // marca a posição da stream no arquivo

					while ((currchar1 = arquivo.read()) != -1){ //verifica se existem proximos caracteres!!
						currchar = (char) currchar1;

						if (currchar >= '0' && currchar <= '9') {
							numBuilder.append(currchar);
							arquivo.mark(1);
						} else {
							arquivo.reset(); // se não for um numero ele tira o marcador e sai do loop
							break;
						}
					}

					return new Token(numBuilder.toString(), TokenType.NUM); // return novo

				}
				else
					switch (currchar){
						case '(':
							//return (new Token (currchar,TokenType.APar));
							return (new Token (String.valueOf(currchar),TokenType.APar));
						case ')':
							//return (new Token (currchar,TokenType.FPar));
							return (new Token (String.valueOf(currchar),TokenType.FPar));
						case '+':
							//return (new Token (currchar,TokenType.SOMA));
							return (new Token (String.valueOf(currchar),TokenType.SOMA));
						case '-':
							return (new Token (String.valueOf(currchar),TokenType.SUB));
						case '*':
							//return (new Token (currchar,TokenType.MULT));
							return (new Token (String.valueOf(currchar),TokenType.MULT));
						case '/':
							return (new Token (String.valueOf(currchar),TokenType.DIV));
						
						default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
			}

			arquivo.close();
			
		return (new Token(String.valueOf(currchar),TokenType.EOF));
		
	}
}

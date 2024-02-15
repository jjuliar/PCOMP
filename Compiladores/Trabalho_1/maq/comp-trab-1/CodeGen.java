class CodeGen{

	
	String geraCodigo (ArvoreSintatica arv)
	{
		return (geraCodigo2(arv) + "PRINT");
	}
	String geraCodigo2 (ArvoreSintatica arv)
	{

	if (arv instanceof Mult)
		return (geraCodigo2(((Mult) arv).arg1) + 
			geraCodigo2(((Mult) arv).arg2) +
			"MULT\n");

	if (arv instanceof Div)
		return (geraCodigo2(((Div) arv).arg1) + 
			geraCodigo2(((Div) arv).arg2) +
			"DIV\n");

	if (arv instanceof Soma)
		return (geraCodigo2(((Soma) arv).arg1) + 
			geraCodigo2(((Soma) arv).arg2) +
			"SUM\n");

	if (arv instanceof Sub)
		return (geraCodigo2(((Sub) arv).arg1) + 
			geraCodigo2(((Sub) arv).arg2) +
			"SUB\n");
			
	if (arv instanceof Num)
		return ("PUSH "  + ((Num) arv).num + "\n");

	return "";
	}

	String geraResultado (ArvoreSintatica arv) 
	{
	
		if (arv instanceof Num) {
			return String.valueOf(((Num) arv).num);
		} else if (arv instanceof Mult) {
			int left = Integer.valueOf(geraResultado(((Mult) arv).arg1));
			int right = Integer.valueOf(geraResultado(((Mult) arv).arg2));
			return String.valueOf(left * right);
		} else if (arv instanceof Div) {
			int left = Integer.valueOf(geraResultado(((Div) arv).arg1));
			int right = Integer.valueOf(geraResultado(((Div) arv).arg2));
			return String.valueOf(left / right);
		} else if (arv instanceof Soma) {
			int left = Integer.valueOf(geraResultado(((Soma) arv).arg1));
			int right = Integer.valueOf(geraResultado(((Soma) arv).arg2));
			return String.valueOf(left + right);
		} else if (arv instanceof Sub) {
			int left = Integer.valueOf(geraResultado(((Sub) arv).arg1));
			int right = Integer.valueOf(geraResultado(((Sub) arv).arg2));
			return String.valueOf(left - right);
		} else {
			return "";
		}

	}
}

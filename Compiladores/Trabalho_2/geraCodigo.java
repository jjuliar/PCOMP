import java.io.*;
import java.util.*;

import ast.*;

public class GeraCodigo {
    private static PrintWriter writer;

    public static void gerar(Prog prog) {
        try {
            writer = new PrintWriter("output.java", "UTF-8");

            for(Fun f : prog.fun){ 
                geraFun(f);
            }

            geraMain(prog.main);

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Gera o código da classe Main
    public static void geraMain(Main main){
        writer.println("public static void main(String[] args) {");

        for(VarDecl vardecl: main.vars){
            geraVarDecl(vardecl);
        }

        geraComandos(main.coms);
        
        writer.println("}");
    }

    // Gera o código da classe Fun
    public static void geraFun(Fun fun){
        writer.println("public static " + fun.retorno + " " + fun.nome + "(");
        for(ParamFormalFun param: fun.params){
            writer.println(param.type + " " + param.var + ",");
        }
        writer.println(") {");

        for(VarDecl vardecl: fun.vars){
            geraVarDecl(vardecl);
        }

        geraComandos(fun.body);
        
        writer.println("}");
    }

    /**
     * public class Comando{}

     */
    public static void geraComandos(ArrayList<Comando> comandos){
        for(Comando comando: comandos){
            geraComando(comando);
        }
    }

    public static void geraComando(Comando comando){
        if(comando instanceof CAtribuicao){
            geraAtribuicao((CAtribuicao)comando);
        }else if(comando instanceof CIf){
            geraIf((CIf)comando);
        }else if(comando instanceof CWhile){
            geraWhile((CWhile)comando);
        }else if(comando instanceof CPrint){
            geraPrint((CPrint)comando);
        }else if(comando instanceof CReturn){
            geraReturn((CReturn)comando);
        }else if(comando instanceof CReadInput){
            geraReadInput((CReadInput)comando);
        } else if (comando instanceof CChamadaFun){
            geraChamadaFun((CChamadaFun)comando);
        }
    }

    // Gera o código da classe CAtribuicao
    public static void geraAtribuicao(CAtribuicao atribuicao){
        writer.println(atribuicao.var + " = " + geraExp(atribuicao.exp) + ";");
    }

    // Gera o código da classe CIf
    public static void geraIf(CIf ifcmd){
        writer.println("if(" + geraExp(ifcmd.exp) + "){");
        geraComandos(ifcmd.bloco);
        writer.println("}");
    }

    // Gera o código da classe CWhile
    public static void geraWhile(CWhile whilecmd){
        writer.println("while(" + geraExp(whilecmd.exp) + "){");
        geraComandos(whilecmd.bloco);
        writer.println("}");
    }

    // Gera o código da classe CPrint
    public static void geraPrint(CPrint print){
        writer.println("System.out.println(" + geraExp(print.exp) + ");");
    }

    // Gera o código da classe CReturn
    public static void geraReturn(CReturn ret){
        writer.println("return " + geraExp(ret.exp) + ";");
    }

    // Gera o código da classe CReadInput
    public static void geraReadInput(CReadInput read){
        writer.println(read.var + " = " + "new Scanner(System.in).nextInt();");
    }

    // Gera o código da classe CChamadaFun
    public static void geraChamadaFun(CChamadaFun chamada){
        writer.println(chamada.fun + "(" + geraArgs(chamada.args) + ");");
    }

    public static String geraArgs(ArrayList<Exp> args){
        String ret = "";
        for(Exp exp: args){
            ret += geraExp(exp) + ",";
        }
        return ret;
    }

    public static String geraExp(Exp exp){
        if(exp instanceof ETrue){
            return "True";
        }else if(exp instanceof EFloat){
            return String.valueOf(((EFloat)exp).value);
        }else if(exp instanceof EVar){
            return ((EVar)exp).var;
        }else if(exp instanceof EChamadaFun){
            return geraChamadaFun((EChamadaFun)exp);
        }else if(exp instanceof EOpExp){
            return geraOper((EOpExp)exp);
        } else if (exp instanceof EFalse){
            return "False";
        }
        return "";
    }

    // Gera o código da classe EChamadaFun
    public static String geraChamadaFun(EChamadaFun chamada){
        String ret = chamada.fun + "(";
        for(Exp exp: chamada.args){
            ret += geraExp(exp) + ",";
        }
        return ret + ")";
    }

    // Gera o código da classe EOpExp
    public static String geraOper(EOpExp op){
        return geraExp(op.arg1) + " " + op.op + " " + geraExp(op.arg2);
    }

    // Gera o código da classe VarDecl
    public static void geraVarDecl(VarDecl vardecl){
        writer.println(vardecl.type + " " + vardecl.var + ";");
    }
}
package edu.slu.cs311b;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Interpreter {

    public static void interpret(Symbol root) {
        Program p = new Program(root);

        p.interpret();
    }
}

// Rule No. 1: <program> → ...
class Program {
    

    public Program(Symbol lhs) {

    }

    public void interpret() {

    }
}

// Rule No. ?: ....
class Expr1 {
    // DO NOT DELETE.
    // Use this class can evaluation arithmetic, relational or logical expressions
    // Just change the case labels/selectable segments in the switch statement to suit your programming langauge

    // See usage in LINE 192 and LINE 203 in MyInterpreter.java for your reference
       
    
    LinkedList<Symbol> expression;
    Stack<Integer> operands = new Stack();

    public Expr1(Symbol lhs) {
        expression = Expression.getExpression(lhs);
    }

    public int interpret() {
        Variable var;
        int result = 0;

        while (!expression.isEmpty()) {
            Symbol sym = expression.removeFirst();

            switch (sym.type) {
                // sym is an operand
                case "<const>":
                    operands.push(Integer.parseInt(sym.lexeme));
                    break;

                case "<iden>":
                    var = Variable.symbolTable.get(sym.lexeme);

                    operands.push((Integer) var.value);
                    break;

                // sym is an operator
                default:
                    int operand2 = operands.pop();
                    int operand1 = operands.pop();

                    switch (sym.lexeme) {
                        case "+":
                            operands.push(operand1 + operand2);
                            break;

                        case "-":
                            operands.push(operand1 - operand2);
                            break;

                        case "*":
                            operands.push(operand1 * operand2);
                            break;

                        case "/":
                            operands.push(operand1 / operand2);
                            break;
                    }
            }
        }

        return operands.pop();
    }
}

// Rule No. 14: <declaration> → <var> <iden> <colon> <type> <initial_value> <semi>
class Declaration {

    Symbol iden;
    Symbol type;
    Initial_value initial_value;

    public Declaration(Symbol lhs) {
        iden = lhs.children.get(1);
        type = lhs.children.get(3);
        initial_value = Initial_value.construct(lhs.children.get(4));
    }

    public void interpret() {
        Variable v = new Variable(iden.lexeme, type.lexeme, 0);
        v.value = initial_value.interpret();

        System.out.println("\t" + Variable.symbolTable);
    }
}

abstract class Initial_value {

    public static Initial_value construct(Symbol sym) {
        switch (sym.ruleNo) {
            case 15:
                return new Initial_value1(sym);

            case 16:
                return new Initial_value2(sym);

            default:
                return null;
        }
    }

    public abstract Object interpret();
}

// Rule No. 15: <initial_value> → <eq> <term>
class Initial_value1 extends Initial_value {

    Term term;

    public Initial_value1(Symbol lhs) {
        term = Term.construct(lhs.children.get(1));
    }

    @Override
    public Object interpret() {
        return term.interpret();
    }
}

// Rule No. 16: <initial_value> → ε
class Initial_value2 extends Initial_value {

    public Initial_value2(Symbol lhs) {
        // rhs is EPSILON - default to null(???)
    }

    @Override
    public Object interpret() {
        return null;
    }
}

abstract class Term {

    public static Term construct(Symbol sym) {
        switch (sym.ruleNo) {
            case 17:
                return new Term1(sym);

            case 18:
                return new Term2(sym);

            default:
                return null;
        }
    }

    public abstract Object interpret();
}

// Rule No. 17: <term> → <iden>
class Term1 extends Term {

    Variable iden;

    public Term1(Symbol lhs) {
        iden = Variable.symbolTable.get(lhs.children.get(0).lexeme);
    }

    public Object interpret() {
        return iden.value;
    }
}

// Rule No. 18: <term> → <const>
class Term2 extends Term {

    String const1;

    public Term2(Symbol lhs) {
        const1 = lhs.children.get(0).lexeme;
    }

    @Override
    public Object interpret() {
        return Integer.parseInt(const1);
    }
}

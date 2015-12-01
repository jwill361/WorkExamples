package interpreterClasses;

import interpreterClasses.BooleanExpression.RelativeOperator;
import java.io.FileNotFoundException;

public class Parser {

	private LineNumber m;

	private StatementList l;

	private LexicalAnalyzer lex;

	public Parser(String fileName) throws FileNotFoundException {
		lex = new LexicalAnalyzer(fileName);
	}

	public Program parse() throws ParserException {
		l = getStatementList();
		return new Program(l);
	}

	private StatementList getStatementList() throws ParserException {
		StatementList l = new StatementList();
		Statement s = getStatement();
		l.add(m.getValue(), s);
		while (lex.hasMoreTokens()) {
			s = getStatement();
			l.add(m.getValue(), s);
		}
		return l;
	}

	private Statement getStatement() throws ParserException {
		Statement stmt;
		m = new LineNumber(getLineNumber());
		String s = lex.getLookaheadToken();
		if (s.equals("IF")) {
			stmt = getIfStatement(m);
		} else if (s.equals("GOTO")) {
			stmt = getGotoStatement(m);
		} else if (s.equals("PRINT")) {
			stmt = getPrintStatement(m);
		} else if (s.equals("STOP")) {
			stmt = getStopStatement(m);
		} else {
			stmt = getLetStatement(m);
		}
		return stmt;
	}

	private Statement getLetStatement(LineNumber m2) throws ParserException {
		match("LET");
		Id var = getId();
		match("=");
		Expression expr = getExpression();
		return new LetStatement(var, expr);
	}

	private Expression getExpression() throws ParserException {
		Expression expr;
		String s = lex.getLookaheadToken();
		if (isValidArithmeticOp(s)) {
			BinaryExpression.ArithmeticOperator relop = getArithmeticOperator();
			Expression op1 = getExpression();
			Expression op2 = getExpression();
			expr = createBinaryExpression(relop, op1, op2);
		} else {
			Expression op = getUnaryExpression();
			expr = new UnaryExpression(op);
		}
		return expr;
	}

	private Expression createBinaryExpression(
			BinaryExpression.ArithmeticOperator op, Expression op1,
			Expression op2) {
		Expression expr;
		if (op == BinaryExpression.ArithmeticOperator.add)
			expr = new AddExpression(op1, op2);
		else if (op == BinaryExpression.ArithmeticOperator.sub)
			expr = new SubExpression(op1, op2);
		else if (op == BinaryExpression.ArithmeticOperator.mul)
			expr = new MulExpression(op1, op2);
		else
			expr = new DivExpression(op1, op2);
		return expr;
	}

	private BinaryExpression.ArithmeticOperator getArithmeticOperator()
			throws ParserException {
		BinaryExpression.ArithmeticOperator op;
		String s = lex.getToken();
		if (s.equals("+"))
			op = BinaryExpression.ArithmeticOperator.add;
		else if (s.equals("-"))
			op = BinaryExpression.ArithmeticOperator.sub;
		else if (s.equals("*"))
			op = BinaryExpression.ArithmeticOperator.mul;
		else if (s.equals("/"))
			op = BinaryExpression.ArithmeticOperator.div;
		else
			throw new ParserException("arithmetic operator expected got: " + s);
		return op;
	}

	private boolean isValidArithmeticOp(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}

	private Statement getPrintStatement(LineNumber m2) throws ParserException {
		match("PRINT");
		Id var = getId();
		return new PrintStatement(var);
	}

	private Id getId() throws ParserException {
		String s = lex.getToken();
		if (s == null || s.length() != 1 || !Character.isLetter(s.charAt(0)))
			throw new ParserException("id expected got: " + s);
		return new Id(s.charAt(0));
	}

	private Statement getStopStatement(LineNumber m2) throws ParserException {
		match("STOP");
		return new StopStatement();
	}

	private Statement getGotoStatement(LineNumber m2) throws ParserException {
		match("GOTO");
		LineNumber line = new LineNumber(getLineNumber());
		return new GoToStatement(line);
	}

	private int getLineNumber() throws ParserException {
		String s = lex.getToken();
		Integer n;
		if (isValidLineNumber(s)) {
			try {
				n = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				throw new ParserException("Line number expected");
			}
		} else
			throw new ParserException("Not a valid line number got: " + s);
		return n;
	}

	private boolean isValidLineNumber(String s) {
		return Character.isDigit(s.charAt(0)) && s.length() <= 3;
	}

	private BooleanExpression getBooleanExpression() throws ParserException {
		RelativeOperator op = getRelativeOp();
		Expression op1 = getUnaryExpression();
		Expression op2 = getUnaryExpression();
		return createBooleanExpression(op, op1, op2);
	}

	private BooleanExpression createBooleanExpression(RelativeOperator op,
			Expression op1, Expression op2) {
		BooleanExpression expr;
		if (op == BooleanExpression.RelativeOperator.EQ)
			expr = new EqualExpression(op1, op2);
		else if (op == BooleanExpression.RelativeOperator.NE)
			expr = new NotEqualExpression(op1, op2);
		else if (op == BooleanExpression.RelativeOperator.GT)
			expr = new GreaterThanExpression(op1, op2);
		else if (op == BooleanExpression.RelativeOperator.GE)
			expr = new GreaterOrEqualExpression(op1, op2);
		else if (op == BooleanExpression.RelativeOperator.LT)
			expr = new LessThanExpression(op1, op2);
		else
			expr = new LessOrEqualExpression(op1, op2);
		return expr;
	}

	private BooleanExpression.RelativeOperator getRelativeOp()
			throws ParserException {
		BooleanExpression.RelativeOperator op;
		String tok = lex.getToken();
		if (tok.equals("<"))
			op = BooleanExpression.RelativeOperator.LT;
		else if (tok.equals("<="))
			op = BooleanExpression.RelativeOperator.LE;
		else if (tok.equals(">"))
			op = BooleanExpression.RelativeOperator.GT;
		else if (tok.equals(">="))
			op = BooleanExpression.RelativeOperator.GE;
		else if (tok.equals("=="))
			op = BooleanExpression.RelativeOperator.EQ;
		else if (tok.equals("<>"))
			op = BooleanExpression.RelativeOperator.NE;
		else
			throw new ParserException("relative operator expected got: " + tok);
		return op;
	}

	private Expression getUnaryExpression() throws ParserException {
		Expression op;
		try {
			String s = lex.getLookaheadToken();
			if (s == null)
				throw new ParserException("Expression expected got: " + s);
			else if (Character.isDigit(s.charAt(0)))
				op = getLiteralInteger();
			else if (s.equals("maxint"))
				op = getLiteralInteger();
			else
				op = getId();
		} catch (ParserException e) {
			throw new ParserException("Expression expected");
		}
		return op;
	}

	private LiteralInteger getLiteralInteger() throws ParserException {
		String s = lex.getToken();
		Integer n;
		if (s.equals("maxint"))
			n = Integer.MAX_VALUE;
		else
			try {
				n = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				throw new ParserException("literal integer expected");
			}
		return new LiteralInteger(n);
	}

	private Statement getIfStatement(LineNumber m2) throws ParserException {
		match("IF");
		BooleanExpression expr = getBooleanExpression();
		match("GOTO");
		LineNumber line = new LineNumber(getLineNumber());
		return new IfStatement(expr, line);
	}

	public void match(String expected) throws ParserException {
		String token = lex.getToken();
		if (!expected.equals(token))
			throw new ParserException(expected + " expected - got " + token);
	}
}

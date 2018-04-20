package com.dlz.common.util.calculate;
import java.util.Map;
import java.util.Stack;

/**
 * 计算表达式
 * 
 * @author dk
 * 
 */
public class EvaluateExpressionUtil {

	private static String STR_OPERATE = "+-*/()#^";
	private static String STR_EXPRESSION = "+-*/()#^01234567890";
	private static char STR_START = '#';
	private static char STR_END = '#';
	private static char prior[][] = { // 运算符优先级表
	// '+' '-' '*' '/' '(' ')' '#' '^'
			{ '>', '>', '<', '<', '<', '>', '>', '<' }, /* '+' */
			{ '>', '>', '<', '<', '<', '>', '>', '<' }, /* '-' */
			{ '>', '>', '>', '>', '<', '>', '>', '<' }, /* '*' */
			{ '>', '>', '>', '>', '<', '>', '>', '<' }, /* '/' */
			{ '<', '<', '<', '<', '<', '=', ' ', '<' }, /* '(' */
			{ '>', '>', '>', '>', ' ', '>', '>', '>' }, /* ')' */
			{ '<', '<', '<', '<', '<', ' ', '=', '<' }, /* '#' */
			{ '>', '>', '>', '>', '<', '>', '>', '>' } /* '^' */
	};

	/**
	 * 计算函数Operate
	 * 
	 * @param a
	 * @param oper
	 * @param b
	 * @return
	 */
	private static double operate(double a, char oper, double b) {
		switch (oper) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		case '^':
			return Math.pow(a, b);
		default:
			return 0;
		}
	}

	/**
	 * 判断是否运算符
	 * 
	 * @param ch
	 * @return
	 */
	private static boolean isOperators(char ch) {
		return STR_OPERATE.indexOf(ch) != -1; // 查找不到，表示不是运算符
	}

	/**
	 * 判断是否参数
	 * 
	 * @param ch
	 * @return
	 */
	private static boolean isPara(char ch) {
		return STR_EXPRESSION.indexOf(ch) == -1; // 查找不到，表示不是运算符
	}

	/**
	 * 计算表达式
	 * 
	 * @param myExpression
	 * @return
	 */
	public static double evaluate(String myExpression) {
		myExpression = myExpression.replaceAll("\\s*", "") + STR_END;// 去除字符串中所有空白
		Stack<Character> OPTR = new Stack<Character>();// 运算符栈
		Stack<Double> OPND = new Stack<Double>();// 数据栈
		OPTR.push(STR_START);
		StringBuffer tempdata = new StringBuffer();// 临时操作数
		int i = 0;
		int len = myExpression.length();
		while (i < len) {
			char c = myExpression.charAt(i);// 当前字符
			if (c == STR_END) {
				break;
			}
			char cNext = myExpression.charAt(i + 1);// 下一字符
			if (!isOperators(c)) {// 数字
				tempdata.append(c);// 字符串追加
				i++;
				if (isOperators(cNext)) {
					double tmp = Double.parseDouble(tempdata.toString()); // 字符串转换为数值的方法(double)
					OPND.push(tmp);
					tempdata.delete(0, tempdata.length()); // clear
				}
			} else {// 运算符
				// 负数处理
				if ((c == '(' && cNext == '-') || (i == 0 && c == '-')) {
					OPND.push(0.0);
				}
				// 判断运算符优先级
				char op = prior[STR_OPERATE.indexOf(OPTR.peek())][STR_OPERATE.indexOf(c)];
				switch (op) {
				case '<': // 栈顶元素优先级低
					OPTR.push(c);
					i++;
					break;
				case '=': // 脱括号并接收下一字符
					OPTR.pop();
					i++;
					break;
				case '>': // 退栈并将运算结果入栈
					char cop = OPTR.pop();
					double b = OPND.pop();
					double a = OPND.pop();
					OPND.push(operate(a, cop, b));
					break;
				}
			}
		}
		// 退栈并将运算结果入栈
		while (OPTR.peek() != STR_START) {
			char cop = OPTR.pop();
			double b = OPND.pop();
			double a = OPND.pop();
			OPND.push(operate(a, cop, b));
		}
		return OPND.peek();
	}

	/**
	 * 计算带参数的表达式
	 * 
	 * @param myExpression
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public static double evaluate(String myExpression, Map<String, String> parameter) throws Exception {
		myExpression = myExpression.replaceAll("\\s*", "").replaceAll("#", "") + STR_END;// 去除字符串中所有空白
		StringBuffer tempdata = new StringBuffer();// 临时操作数
		StringBuffer expression = new StringBuffer();// 临时操作数
		int i = 0;
		int len = myExpression.length();
		boolean isPara = false;
		while (i < len) {
			char c = myExpression.charAt(i);// 当前字符
			if (c == STR_END) {
				break;
			}
			char cNext = myExpression.charAt(i + 1);// 下一字符
			if (isPara || isPara(c)) {// 参数
				tempdata.append(c);
				isPara = true;
				if (isOperators(cNext)) {
					String paraValue = String.valueOf(parameter.get(tempdata.toString()));
					if (paraValue == null) {
						throw new Exception("表示式有误，参数["+tempdata.toString()+"]未传递！");
					}
					expression.append(paraValue);
					tempdata.delete(0, tempdata.length());
					isPara = false;
				}
			} else {
				expression.append(c);
			}
			i++;
		}
		return evaluate(expression.toString());
	}
}
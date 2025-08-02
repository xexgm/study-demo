package com.gm.study.designPattern.composite;

/**
 * @Author: xexgm
 */
public class Calc {

    public static void main(String[] args) {
        // 加号左边是一个表达式，加号右边也是一个表达式
        // 加号本身也是一个表达式
        // 1+1 + 2+3
        Expression oneAndOne = new AddExpression(new NumberExpression(1), new NumberExpression(1));
        Expression twoAndThree = new AddExpression(new NumberExpression(2), new NumberExpression(3));
        Expression expression = new AddExpression(oneAndOne, twoAndThree);
        System.out.println(expression.getValue());
    }

    interface Expression {
        int getValue();
    }

    static class NumberExpression implements Expression {

        int val;

        public NumberExpression(int val) {
            this.val = val;
        }

        @Override
        public int getValue() {
            return this.val;
        }
    }

    static class AddExpression implements Expression {

        Expression left;
        Expression right;

        public AddExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int getValue() {
            return left.getValue() + right.getValue();
        }
    }


}

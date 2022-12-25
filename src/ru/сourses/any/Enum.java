package ru.сourses.any;

class TestEnum {
    public static void main(String[] args) {
        int x = 10, y = 2;

        Operation plus = Operation.PLUS;
        Operation minus = Operation.MINUS;
        Operation multi = Operation.MULTI;
        Operation div = Operation.DIV;

        System.out.println(plus.result(x, y));
        System.out.println(minus.result(x, y));
        System.out.println(multi.result(x, y));
        System.out.println(div.result(x, y));

        System.out.println();

    }
}

enum Operation {
    PLUS {
        public int result(int x, int y) {
            return x + y;
        }
    }, MINUS {
        public int result(int x, int y) {
            return x - y;
        }
    }, MULTI {
        public int result(int x, int y) {
            return x * y;
        }
    }, DIV {
        public int result(int x, int y) {
            return x / y;
        }
    };

    public abstract int result(int x, int y);
}



import Number;

public class TestNumber {
    static Number n1 = new Number();
    static Number n2 = new Number();

    public static void TestNumber() {
        n1.i = 2;
        n2.i= 5;
        n1 = n2;
        n2.i = 10;
        system.out.println("n1.i = " + n1.i);
        n1.i = 20;
        system.out.println("n2.i = " + n2.i);

        Number n = New Number();
        n.i = 14;
    }
    }
}
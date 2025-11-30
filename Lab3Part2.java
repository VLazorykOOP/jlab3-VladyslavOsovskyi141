

abstract class Pair {
    protected int a;
    protected int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public abstract Pair add(Pair other);
    public abstract Pair sub(Pair other);
    public abstract Pair mul(Pair other);
    public abstract Pair divInt(int value);

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);
}

class Money extends Pair {

    public Money(int hryvnias, int kopecks) {
        super(hryvnias, kopecks);
        normalize();
    }

    private void normalize() {
        if (b >= 100) {
            a += b / 100;
            b = b % 100;
        } else if (b < 0) {
            int borrow = (Math.abs(b) + 99) / 100;
            a -= borrow;
            b += borrow * 100;
        }
    }

    @Override
    public Money add(Pair other) {
        if (other instanceof Money) {
            Money o = (Money) other;
            return new Money(a + o.a, b + o.b);
        }
        return null;
    }

    @Override
    public Money sub(Pair other) {
        if (other instanceof Money) {
            Money o = (Money) other;
            return new Money(a - o.a, b - o.b);
        }
        return null;
    }

    @Override
    public Money mul(Pair other) {
        if (other instanceof Money) {
            Money o = (Money) other;
            int total1 = a * 100 + b;
            int total2 = o.a * 100 + o.b;
            int result = total1 * total2 / 100; // результат у копійках
            return new Money(result / 100, result % 100);
        }
        return null;
    }

    @Override
    public Money divInt(int value) {
        int total = a * 100 + b;
        int result = total / value;
        return new Money(result / 100, result % 100);
    }

    @Override
    public String toString() {
        return a + " грн " + b + " коп";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money o = (Money) obj;
        return a == o.a && b == o.b;
    }
}

class Complex extends Pair {

    public Complex(int real, int imag) {
        super(real, imag);
    }

    @Override
    public Complex add(Pair other) {
        if (other instanceof Complex) {
            Complex o = (Complex) other;
            return new Complex(a + o.a, b + o.b);
        }
        return null;
    }

    @Override
    public Complex sub(Pair other) {
        if (other instanceof Complex) {
            Complex o = (Complex) other;
            return new Complex(a - o.a, b - o.b);
        }
        return null;
    }

    @Override
    public Complex mul(Pair other) {
        if (other instanceof Complex) {
            Complex o = (Complex) other;
            int real = a * o.a - b * o.b;
            int imag = a * o.b + b * o.a;
            return new Complex(real, imag);
        }
        return null;
    }

    @Override
    public Complex divInt(int value) {
        return new Complex(a / value, b / value);
    }

    @Override
    public String toString() {
        if (b >= 0)
            return a + " + " + b + "i";
        else
            return a + " - " + Math.abs(b) + "i";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Complex)) return false;
        Complex o = (Complex) obj;
        return a == o.a && b == o.b;
    }
}

public class Lab3Part2 {
    public static void main(String[] args) {
        Pair[] pairs = new Pair[4];

        pairs[0] = new Money(10, 50);
        pairs[1] = new Money(5, 75);
        pairs[2] = new Complex(3, 4);
        pairs[3] = new Complex(1, -2);

        System.out.println("=== Demonstration of Pair subclasses ===");

        Money m1 = (Money) pairs[0];
        Money m2 = (Money) pairs[1];
        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);
        System.out.println("m1 + m2 = " + m1.add(m2));
        System.out.println("m1 - m2 = " + m1.sub(m2));
        System.out.println("m1 / 2 = " + m1.divInt(2));
        System.out.println("m1 equals m2? " + m1.equals(m2));

        System.out.println();

        Complex c1 = (Complex) pairs[2];
        Complex c2 = (Complex) pairs[3];
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c1 + c2 = " + c1.add(c2));
        System.out.println("c1 - c2 = " + c1.sub(c2));
        System.out.println("c1 * c2 = " + c1.mul(c2));
        System.out.println("c1 / 2 = " + c1.divInt(2));
        System.out.println("c1 equals c2? " + c1.equals(c2));
    }
}
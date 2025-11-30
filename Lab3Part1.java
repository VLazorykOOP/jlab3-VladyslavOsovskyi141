// Lab3Part1.java
import java.util.Scanner;

class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void Show() {
        System.out.println("Animal: " + name + ", age: " + age);
    }
}

// Клас Ссавець (похідний від Тварина)
class Mammal extends Animal {
    protected boolean hasFur;

    public Mammal(String name, int age, boolean hasFur) {
        super(name, age);
        this.hasFur = hasFur;
    }

    @Override
    public void Show() {
        System.out.println("Mammal: " + name + ", age: " + age + ", has fur: " + hasFur);
    }
}

// Клас Парнокопитне (похідний від Ссавець)
class Artiodactyl extends Mammal {
    private int hooves;

    public Artiodactyl(String name, int age, boolean hasFur, int hooves) {
        super(name, age, hasFur);
        this.hooves = hooves;
    }

    @Override
    public void Show() {
        System.out.println("Artiodactyl: " + name + ", age: " + age + ", has fur: " + hasFur + ", hooves: " + hooves);
    }
}

// Клас Птах (похідний від Тварина)
class Bird extends Animal {
    private double wingspan;

    public Bird(String name, int age, double wingspan) {
        super(name, age);
        this.wingspan = wingspan;
    }

    @Override
    public void Show() {
        System.out.println("Bird: " + name + ", age: " + age + ", wingspan: " + wingspan + " m");
    }
}

public class Lab3Part1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of animals: ");
        int n = sc.nextInt();
        sc.nextLine(); // очистка буфера після nextInt()

        Animal[] animals = new Animal[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nChoose type of animal #" + (i + 1) + ":");
            System.out.println("1 - Mammal");
            System.out.println("2 - Artiodactyl");
            System.out.println("3 - Bird");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Has fur (true/false): ");
                    boolean hasFur = sc.nextBoolean();
                    sc.nextLine();
                    animals[i] = new Mammal(name, age, hasFur);
                    break;

                case 2:
                    System.out.print("Has fur (true/false): ");
                    boolean fur = sc.nextBoolean();
                    System.out.print("Enter number of hooves: ");
                    int hooves = sc.nextInt();
                    sc.nextLine();
                    animals[i] = new Artiodactyl(name, age, fur, hooves);
                    break;

                case 3:
                    System.out.print("Enter wingspan (meters): ");
                    double wingspan = sc.nextDouble();
                    sc.nextLine();
                    animals[i] = new Bird(name, age, wingspan);
                    break;

                default:
                    System.out.println("Invalid choice! Creating generic Animal.");
                    animals[i] = new Animal(name, age);
            }
        }

        System.out.println("\n=== Animal list ===");
        for (Animal a : animals) {
            a.Show();
        }

        sc.close();
    }
}

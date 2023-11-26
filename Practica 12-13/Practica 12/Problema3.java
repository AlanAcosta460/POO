import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Ulam implements Runnable {
    private static final ArrayList<Integer> sequence = new ArrayList<>();;
    private final int u;
    private final int v;
    private final int n;

    public Ulam(int u, int v, int n) {
        this.u = u;
        this.v = v;
        this.n = n;
    }

    static synchronized void ulam(int u, int v, int n) {
        sequence.clear();
        sequence.add(u);
        sequence.add(v);

        for (int i = 0; i < n - 2; i++) {
            ArrayList<Integer> sums = new ArrayList<>();

            for (int j = 0; j < sequence.size(); j++) {
                for (int k = j + 1; k < sequence.size(); k++)
                    sums.add(sequence.get(j) + sequence.get(k));
            }

            sums.sort(Comparator.naturalOrder());

            for (int j = 0; j < sums.size(); j++) {
                if (sequence.contains(sums.get(j))) {
                    sums.remove(j);
                    j--;
                }
            }

            while (true) {
                if (sums.size() == 1) {
                    sequence.add(sums.get(0));
                    break;
                } else if (sums.get(0).equals(sums.get(1))) {
                    int temp = sums.get(0);
                    while (sums.get(0).equals(temp))
                        sums.remove(0);
                } else {
                    sequence.add(sums.get(0));
                    break;
                }
            }
        }
    }

    static synchronized ArrayList<Integer> getSequence() {
        return new ArrayList<>(sequence);
    }

    @Override
    public void run() {
        ulam(u, v, n);
    }
}

public class Problema3 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Numero 1: ");
        int u = sc.nextInt();
        System.out.print("Numero 2: ");
        int v = sc.nextInt();
        System.out.print("Cuantos numeros? ");
        int n = sc.nextInt();

        Ulam ulam = new Ulam(u, v, n);

        Thread thread1 = new Thread(ulam);
        Thread thread2 = new Thread(ulam);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        ArrayList<Integer> ulamSequence = Ulam.getSequence();

        System.out.println("\nSecuencia de Ulam:");
        System.out.println(ulamSequence);

        sc.close();
    }
}

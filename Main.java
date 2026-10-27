import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque(10);
        List<Animal> animais = Arrays.asList(
            new Animal("Leão", 3, estoque),
            new Animal("Macaco", 1, estoque),
            new Animal("Elefante", 4, estoque),
            new Animal("Tigre", 3, estoque)
        );

        Thread[] threads = new Thread[animais.size() + 2];
        for (int i = 0; i < animais.size(); i++) {
            threads[i] = new Thread(animais.get(i), "Animal-" + i);
        }
        threads[animais.size()] = new Thread(new Tratador(estoque), "Tratador");
        threads[animais.size() + 1] = new Thread(new Veterinario(animais), "Veterinario");

        System.out.println("=== ZOOLÓGICO ABERTO ===");
        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("=== ZOOLÓGICO FECHADO ===");
            System.out.println("Comida restante: " + estoque.consultar() + " porções.");
        } catch (InterruptedException e) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
            Thread.currentThread().interrupt();
            System.out.println("Simulação interrompida.");
        }
    }
}

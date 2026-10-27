import java.util.concurrent.ThreadLocalRandom;

public class Animal implements Runnable {
    private final String nome;
    private final int porcoes;
    private final Estoque estoque;
    private int energia = 60;

    public Animal(String nome, int porcoes, Estoque estoque) {
        this.nome = nome;
        this.porcoes = porcoes;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public synchronized void examinar() {
        System.out.printf("[VET] %s examinado. Energia: %d%%%n", nome, energia);
    }

    private synchronized void alterarEnergia(int valor) {
        energia = Math.max(0, Math.min(100, energia + valor));
    }

    @Override
    public void run() {
        try {
            for (int dia = 1; dia <= 4; dia++) {
                System.out.printf("[%s] Dia %d: passeando.%n", nome, dia);
                alterarEnergia(-15);
                Thread.sleep(ThreadLocalRandom.current().nextInt(200, 600));

                if (estoque.retirar(porcoes, nome)) {
                    System.out.printf("[%s] Comendo...%n", nome);
                    alterarEnergia(25);
                } else {
                    System.out.printf("[%s] Ficou sem alimento neste ciclo.%n", nome);
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(200, 600));

                System.out.printf("[%s] Descansando.%n", nome);
                alterarEnergia(10);
                Thread.sleep(ThreadLocalRandom.current().nextInt(200, 600));
            }
            System.out.printf("[%s] Finalizou as atividades.%n", nome);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("[%s] Atividades interrompidas.%n", nome);
        }
    }
}

public class Tratador implements Runnable {
    private final Estoque estoque;

    public Tratador(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(700);
                estoque.repor(5);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[TRATADOR] Trabalho interrompido.");
        }
    }
}

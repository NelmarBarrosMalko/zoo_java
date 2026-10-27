import java.util.List;

public class Veterinario implements Runnable {
    private final List<Animal> animais;

    public Veterinario(List<Animal> animais) {
        this.animais = animais;
    }

    @Override
    public void run() {
        try {
            for (int rodada = 0; rodada < 3; rodada++) {
                Thread.sleep(900);
                for (Animal animal : animais) {
                    animal.examinar();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[VET] Atendimento interrompido.");
        }
    }
}

public class Estoque {
    private int comida;

    public Estoque(int comidaInicial) {
        this.comida = comidaInicial;
    }

    public synchronized boolean retirar(int quantidade, String animal) {
        if (comida < quantidade) {
            System.out.printf("[ESTOQUE] Sem comida suficiente para %s (pedido: %d, disponível: %d)%n", animal, quantidade, comida);
            return false;
        }
        comida -= quantidade;
        System.out.printf("[ESTOQUE] %s recebeu %d porções. Restam %d.%n", animal, quantidade, comida);
        return true;
    }

    public synchronized void repor(int quantidade) {
        comida += quantidade;
        System.out.printf("[ESTOQUE] Tratador repôs %d porções. Total: %d.%n", quantidade, comida);
    }

    public synchronized int consultar() {
        return comida;
    }
}

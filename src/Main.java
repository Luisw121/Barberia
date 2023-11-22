public class Main {
    public static void main(String[] args) {
        final int Sillas = 4;
        final int Clientes = 6;

        Barberia barberia = new Barberia(Sillas);
        Barbero elBarbero = new Barbero(barberia);
        Cliente[] losClientes = new Cliente[10];

        elBarbero.start();

        for (int i = 0; i < Clientes; i++) {
            losClientes[i] = new Cliente(barberia, i);
            losClientes[i].start();
        }
    }
}
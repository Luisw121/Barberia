class Barbero extends Thread {
    private Barberia laBarberia;

    public Barbero(Barberia laBarberia) {
        this.laBarberia = laBarberia;
    }

    public void run() {
        while (true) {
            try {
                laBarberia.esperarCliente();
                // tiempo en que tarda en cortar el pelo
                Thread.sleep(5000);
                laBarberia.acabarCorte();
                // tiempo que es su descanso
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            ;
        }
    }
}
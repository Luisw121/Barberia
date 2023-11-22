class Cliente extends Thread {
    private Barberia laBarberia;
    private int cliente;
    private boolean cortePelo = false;

    public Cliente(Barberia laBarberia, int cliente) {
        this.laBarberia = laBarberia;
        this.cliente = cliente;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                cortePelo = laBarberia.entrar(cliente);
                if (cortePelo) {
                    // espera hasta que crezca el pelo
                    Thread.sleep(25000);
                } else {
                    // espero y lo vuelvo a intentar
                    Thread.sleep(4000);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
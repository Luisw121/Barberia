import java.util.concurrent.Semaphore;

class Barberia {
    private int SillasEspera;
    private int SillasEsperaOcupadas = 0;
    private boolean SillaBarberoOcupada = false;
    private boolean FinCorte = false;
    private boolean BarberoDormido = false;

    public Barberia(int nSillasEspera) {
        this.SillasEspera = nSillasEspera;
    }

    public synchronized boolean entrar(int cliente)
            throws InterruptedException {
        if (SillasEsperaOcupadas == SillasEspera) {
            // Si no hay sillas libres, el cliente se marcha
            System.out.println("El cliente " + cliente + " se va sin cortarse el pelo");
            return false;
        } else {
            SillasEsperaOcupadas++;
            System.out.println("El cliente " + cliente + " se sienta en la silla de espera");
            while (SillaBarberoOcupada) {
                wait();
            }

            // para desocupar la silla de espera
            SillasEsperaOcupadas--;

            // para sentarse en la silla del barbero
            SillaBarberoOcupada = true;
            FinCorte = false;

            // si el barbero esta dormido el cliente lo dspeirta
            if (BarberoDormido) {
                System.out.println("El cliente " + cliente + " despierta al barbero");
                notifyAll();
            }

            // aqui el cliente espera a que le corten el pelo
            System.out.println("El cliente " + cliente + " en la silla de barbero");
            while (!FinCorte) {
                wait();
            }

            SillaBarberoOcupada = false;

            // para que pase el siguiente
            notifyAll();

            System.out.println("El cliente " + cliente + " se va con el pelo cortado");
            return true;
        }
    }

    public synchronized void esperarCliente() throws InterruptedException {
        BarberoDormido = true;
        while (!SillaBarberoOcupada) {
            System.out.println("Barbero esta dormido");
            wait();
        }
        BarberoDormido = false;
        System.out.println("El barbero esta cortando el pelo");
    }

    public synchronized void acabarCorte() {
        FinCorte = true;
        System.out.println("El barbero termina de cortar el pelo");
        //Para que pase el siguiente
        notifyAll();
    }
    }

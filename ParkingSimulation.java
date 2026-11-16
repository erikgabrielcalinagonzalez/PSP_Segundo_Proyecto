import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Simulación de un aparcamiento (práctica UD2 PSP). Esqueleto: completad los TODO en la fase que
 * indica cada uno. No cambiéis los nombres ni las firmas de los métodos.
 *
 * Llegan CARS coches, uno cada ARRIVAL_MS, por dos puertas. Cada coche es una tarea de un grupo
 * de THREADS hilos: espera una plaza libre, aparca un tiempo aleatorio y se va. Mientras tanto,
 * un ScheduledExecutorService escribe un informe cada segundo. Al final se escribe un resumen.
 *
 * Uso: java ParkingSimulation
 */
public class ParkingSimulation {

    static final int SPACES = 5;
    static final int CARS = 20;
    static final int THREADS = 10;
    static final long ARRIVAL_MS = 200;
    static final int MIN_STAY_MS = 500;
    static final int MAX_STAY_MS = 2000;
    static final String[] GATES = {"Norte", "Sur"};
    static final long START = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = new ParkingLot(SPACES);
        Random random = new Random(42);       // con la misma semilla, siempre los mismos tiempos

        // TODO fase 1: con try-with-resources, un grupo de THREADS hilos. Para cada coche, de 1 a
        //      CARS, calculad:
        //        - su nombre: String.format("Coche-%02d", i)
        //        - su puerta: GATES[i % GATES.length]
        //        - cuánto tiempo aparca: MIN_STAY_MS + random.nextInt(MAX_STAY_MS - MIN_STAY_MS + 1)
        //      Entregad al grupo la tarea visit(...) de ese coche y esperad ARRIVAL_MS hasta que
        //      llegue el siguiente.
        try (ExecutorService ejecutor = Executors.newFixedThreadPool(CARS)) {
            for (int i = 1; i < CARS + 1; i++) {
                String car = String.format("Coche-½02d", i);
                String gate = GATES[i % GATES.length];
                long stayMs = MIN_STAY_MS + random.nextInt(MAX_STAY_MS - MIN_STAY_MS + 1);
                ejecutor.submit(() -> visit(lot,car,gate,stayMs));
            }
        }
        // TODO fase 4: con otro try-with-resources que envuelva al anterior, un
        //      ScheduledExecutorService que llame a report(lot) cada segundo.
        // TODO fase 3: cuando se hayan ido todos los coches, printSummary(lot).
    }

    /** Lo que hace cada coche: entra, aparca stayMs milisegundos y sale. */
    static void visit(ParkingLot lot, String car, String gate, long stayMs) {
        // TODO fase 1: entrar (lot.enter), aparcar (dormir stayMs) y salir (lot.leave)

        // TODO fase 2: que el coche salga aunque haya una excepción mientras está aparcado
    }

    /** Informe periódico: cuántos coches hay dentro, cuántos esperan y cuántos se han ido. */
    static void report(ParkingLot lot) {
        // TODO fase 4: con log(), por ejemplo: "INFORME: dentro 5/5, esperando 2, se han ido 3"
    }

    /** Resumen final, cuando ya se han ido todos los coches. */
    static void printSummary(ParkingLot lot) {
        // TODO fase 3: coches que han aparcado (de CARS), coches por puerta, espera media en ms y
        //      cuántos hay dentro y esperando al terminar (tiene que ser 0 y 0)
    }

    /** Escribe un mensaje con los milisegundos desde el inicio y el nombre del hilo. Ya está hecho. */
    static void log(String message) {
        long ms = System.currentTimeMillis() - START;
        System.out.printf("%5d ms  %-16s %s%n", ms, Thread.currentThread().getName(), message);
    }
}

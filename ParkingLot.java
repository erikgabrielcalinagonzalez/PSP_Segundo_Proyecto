import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Un aparcamiento con un número fijo de plazas (práctica UD2 PSP). Esqueleto: completad los TODO
 * en la fase que indica cada uno. No cambiéis los nombres ni las firmas de los métodos.
 *
 * TODO fase 5: explicad en este comentario si la clase es segura para hilos y por qué.
 */
public class ParkingLot {

    private final int capacity;
    private final Semaphore semaphore;
    // TODO fase 2: el semáforo de las plazas, con un permiso por plaza
    // TODO fase 3: las estadísticas: coches dentro, coches esperando, coches que se han ido y
    //      suma de las esperas en milisegundos (variables atómicas); coches que han entrado por
    //      cada puerta (ConcurrentHashMap)

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.semaphore = new Semaphore(capacity);
        // TODO fase 2: crear el semáforo
    }

    /**
     * El coche espera a que haya una plaza libre y la ocupa. Anota cuánto ha esperado y por qué
     * puerta ha entrado.
     */
    public void enter(String car, String gate) throws InterruptedException {
        // TODO fase 1: escribir con ParkingSimulation.log() que el coche entra por esa puerta
        // TODO fase 2: antes de entrar, esperar a que haya una plaza libre
        semaphore.acquire();
        ParkingSimulation.log(car + " entra por la puerta: " + gate);
        // TODO fase 3: actualizar las estadísticas
    }

    /** El coche deja libre su plaza. */
    public void leave(String car) {
        // TODO fase 1: escribir con ParkingSimulation.log() que el coche sale
        ParkingSimulation.log(car + " se va");
        // TODO fase 2: después, dejar libre la plaza
        semaphore.release();
        // TODO fase 3: actualizar las estadísticas
    }

    public int getCapacity() {
        return capacity;
    }

    public int getInside() {
        // TODO fase 3
        return 0;  // provisional, para que compile
    }

    public int getWaiting() {
        // TODO fase 3
        return 0;  // provisional, para que compile
    }

    public int getServedCars() {
        // TODO fase 3
        return 0;  // provisional, para que compile
    }

    public long getTotalWaitMs() {
        // TODO fase 3
        return 0;  // provisional, para que compile
    }

    public Map<String, Integer> getCarsByGate() {
        // TODO fase 3
        return Map.of();  // provisional, para que compile
    }
}

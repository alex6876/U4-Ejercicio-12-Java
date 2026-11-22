import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CentroComputos centro = new CentroComputos();

        BoletaIntendente intendente = new BoletaIntendente(
                "Juan Perez", "Partido A"
        );

        BoletaConcejales concejales = new BoletaConcejales(
                "Maria Gomez", "Partido B", 2
        );

        BoletaLegisladores legisladores = new BoletaLegisladores(
                "Carlos Lopez", "Partido C", 4
        );


        centro.agregarBoleta(intendente);

        centro.agregarBoleta(concejales);

        centro.agregarBoleta(legisladores);


        ArrayList<Integer> votos = new ArrayList<>();

        votos.add(1500);
        votos.add(1200);
        votos.add(800);


        centro.procesarVotos(votos);
    }
}
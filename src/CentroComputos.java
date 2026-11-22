import java.util.ArrayList;

public class CentroComputos {

    private ArrayList<BoletaElectoral> boletas = new ArrayList<>();

    public void agregarBoleta(BoletaElectoral boleta) {
        boletas.add(boleta);
    }

    public void procesarVotos(ArrayList<Integer> votosEmitidos) {

        int totalVotos = 0;

        System.out.println("===== REPORTE ELECTORAL =====");

        for (int i = 0; i < boletas.size(); i++) {

            BoletaElectoral boleta = boletas.get(i);

            int votosValidos = boleta.contarVoto(votosEmitidos.get(i));

            totalVotos += votosValidos;

            System.out.println("------------------------------");

            System.out.println("Candidato: " + boleta.getNombreCandidato());

            System.out.println("Partido: " + boleta.getPartidoPolitico());

            System.out.println("Votos validos: " + votosValidos);
        }

        System.out.println("------------------------------");

        System.out.println("TOTAL DE VOTOS VALIDOS: " + totalVotos);
    }
}

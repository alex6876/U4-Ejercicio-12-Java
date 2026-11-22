public class BoletaConcejales extends BoletaElectoral {

    private int cantidadTachas;

    public BoletaConcejales(String nombreCandidato, String partidoPolitico, int cantidadTachas) {

        super(nombreCandidato, partidoPolitico);

        this.cantidadTachas = cantidadTachas;
    }

    @Override
    public int contarVoto(int cantidadEmitida) {

        if (cantidadEmitida < 0 || cantidadTachas < 0 || cantidadTachas > 2) {
            return 0;
        }

        return cantidadEmitida;
    }
}
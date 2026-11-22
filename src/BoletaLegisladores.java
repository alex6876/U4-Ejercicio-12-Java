public class BoletaLegisladores extends BoletaElectoral {

    private int cantidadPreferencias;

    public BoletaLegisladores(String nombreCandidato, String partidoPolitico, int cantidadPreferencias) {

        super(nombreCandidato, partidoPolitico);

        this.cantidadPreferencias = cantidadPreferencias;
    }

    @Override
    public int contarVoto(int cantidadEmitida) {

        if (cantidadEmitida < 0 || cantidadPreferencias < 0 || cantidadPreferencias > 3) {
            return 0;
        }

        return cantidadEmitida;
    }
}
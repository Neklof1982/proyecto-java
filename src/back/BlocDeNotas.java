package back;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlocDeNotas {

    // #### ATRIBUTOS #################

    private Map<Integer,Nota> getBlockDeNotas = new LinkedHashMap<Integer,Nota>();

    // #### METODOS ###################

    public void anadirNota(Nota nota) {

    }

    // #### CONSTRUCTOR ###############

    public BlocDeNotas(Map getBlockDeNotas) {
        this.getBlockDeNotas = getBlockDeNotas;
    }

    // #### GET and SET ###############

    public void setGetBlockDeNotas(Map getBlockDeNotas) {
        this.getBlockDeNotas = getBlockDeNotas;
    }

    public Map getGetBlockDeNotas() {
        return getBlockDeNotas;
    }
}

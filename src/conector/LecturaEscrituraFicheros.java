package conector;

import java.io.*;

public class LecturaEscrituraFicheros {

    static boolean escribirJson(String json) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("entrada.json"));
            bw.write(json);                                                     // Escribimos el contenido del String `json` en el archivo.
        } catch (IOException e) {
            System.out.println("Error al leer el archivo del Front");           // Si ocurre una IOException, imprimimos un mensaje de error.
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Error al guardar el archivo del Back"); // Si ocurre una IOException, imprimimos un mensaje de error.
                    //throw new RuntimeException(e);
                    return false;
                }
            }
        }
            return true;                                                       // Si el proceso de escritura y cierre fue exitoso, devolvemos true.
    }
}


    /*
    private static final Gson gson = new Gson();

    // Método para leer archivo del front (entrada) y convertirlo a JSON
    public static String leerArchivoFrontYConvertirAJson(String nombreArchivo) {
        String contenido = leerArchivoFront(nombreArchivo);
        // Convertir el contenido del archivo a JSON
        return toJson(contenido);
    }
    /*
    static boolean LecturaTxt (String txt){
        BufferedReader br = null;
        String contenido = "";
        try{
            br = new BufferedReader(new FileReader( "./conexion.txt"));
            String linea;
            while((linea=br.readLine())!=null){
                contenido += linea;
            }

        } catch (IOException e) {
            return false;
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
    */


/*

    // Método para escribir archivo de respuesta del back (salida) desde JSON
    public static void escribirArchivoBackDesdeJson(String nombreArchivo, String json) {
        String contenido = fromJson(json);
        escribirArchivoBack(nombreArchivo, contenido);
    }

    // Método para convertir un objeto a JSON
    private static String toJson(Object objeto) {
        return gson.toJson(objeto);
    }

    // Método para convertir JSON a un objeto
    private static String fromJson(String json) {
        return gson.fromJson(json, String.class);
    }

    // Método para leer archivo del front (entrada)
    private static String leerArchivoFront(String nombreArchivo) {
        String contenido = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("front_input/" + nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contenido;
    }

    // Método para escribir archivo de respuesta del back (salida)
    private static void escribirArchivoBack(String nombreArchivo, String contenido) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("back_output/" + nombreArchivo));
            bw.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    } comentado
    */

/*



    // Método para guardar datos en archivo de persistencia
    public static void guardarDatos(String nombreArchivo, String datos) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(nombreArchivo));
            bw.write(datos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para recuperar datos de archivo de persistencia
    public static String recuperarDatos(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contenido.toString();
    }
}
*/


/*
// Métodos para manejar la lectura y escritura desde el front
    public static void escribirArchivoFront(String nombreArchivo, String contenido) {
        escribirArchivo(nombreArchivo, contenido);
    }

    public static String leerArchivoFront(String nombreArchivo) {
        return leerArchivo(nombreArchivo);
    }

    // Métodos para manejar la lectura y escritura desde el back
    public static void escribirArchivoBack(String nombreArchivo, String contenido) {
        escribirArchivo(nombreArchivo, contenido);
    }

    public static String leerArchivoBack(String nombreArchivo) {
        return leerArchivo(nombreArchivo);
    }

    // Método genérico para escribir en un archivo
    private static void escribirArchivo(String nombreArchivo, String contenido) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
            writer.write(contenido);
            writer.close();
            System.out.println("Los datos se han escrito correctamente en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método genérico para leer desde un archivo
    private static String leerArchivo(String nombreArchivo) {
        String contenido = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido += linea + "\n";
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contenido;
    }
 */
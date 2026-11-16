import java.util.ArrayList;
import java.util.List;

/**
 * APE 4 - Fase 1: Grafos y vertices
 * Estudiante: Tuza Quinatoa Edith Noemi
 * Red social: 6 usuarios, 5 amistades (grafo simple, no dirigido y no ponderado).
 * Todo se muestra por consola y responde las 6 preguntas del reto.
 */
public class RedSocial {

    static String[] ids     = {"U01", "U02", "U03", "U04", "U05", "U06"};
    static String[] nombres = {"Mateo", "Sofia", "Luis", "Valeria", "Andres", "Camila"};
    // Aristas: cada fila es una amistad (indices de los vertices)
    static int[][] aristas  = {{0, 1}, {1, 2}, {2, 0}, {0, 3}, {3, 4}};

    static int indice(String id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(id)) return i;
        }
        return -1;
    }

    static boolean existeRelacion(String a, String b) {
        int x = indice(a), y = indice(b);
        if (x == -1 || y == -1) {
            throw new IllegalArgumentException("Identificador inexistente: " + (x == -1 ? a : b));
        }
        for (int[] e : aristas) {
            if ((e[0] == x && e[1] == y) || (e[0] == y && e[1] == x)) return true;
        }
        return false;
    }

    static List<String> vecinos(String id) {
        int x = indice(id);
        if (x == -1) throw new IllegalArgumentException("Identificador inexistente: " + id);
        List<String> res = new ArrayList<>();
        for (int[] e : aristas) {
            if (e[0] == x) res.add(ids[e[1]]);
            else if (e[1] == x) res.add(ids[e[0]]);
        }
        return res;
    }

    static int grado(String id) {
        return vecinos(id).size();
    }

    // Valida si una amistad puede agregarse; devuelve el motivo del rechazo o "OK"
    static String validarAmistad(String a, String b) {
        if (indice(a) == -1 || indice(b) == -1) return "RECHAZADA: usuario inexistente";
        if (a.equals(b)) return "RECHAZADA: lazo (un usuario no puede ser amigo de si mismo)";
        if (existeRelacion(a, b)) return "RECHAZADA: amistad duplicada";
        return "OK";
    }

    static String nombre(String id) {
        return nombres[indice(id)];
    }

    public static void main(String[] args) {
        System.out.println("=== RED SOCIAL - APE 4 FASE 1 ===");

        // Pregunta 1: V y E
        System.out.println("\n1) G = (V, E)");
        System.out.print("V = {");
        for (int i = 0; i < ids.length; i++) {
            System.out.print(ids[i] + (i < ids.length - 1 ? ", " : ""));
        }
        System.out.println("}  -> |V| = " + ids.length);
        System.out.print("E = {");
        for (int i = 0; i < aristas.length; i++) {
            System.out.print("{" + ids[aristas[i][0]] + "," + ids[aristas[i][1]] + "}"
                    + (i < aristas.length - 1 ? ", " : ""));
        }
        System.out.println("}  -> |E| = " + aristas.length);
        for (int i = 0; i < ids.length; i++) {
            System.out.println("   " + ids[i] + " = " + nombres[i]);
        }

        // Pregunta 2: tipo de grafo
        System.out.println("\n2) Tipo de grafo");
        System.out.println("   Simple: sin lazos ni aristas repetidas.");
        System.out.println("   No dirigido: la amistad es mutua (U01-U02 es igual a U02-U01).");
        System.out.println("   No ponderado: las aristas no tienen pesos.");

        // Pregunta 3: grados
        System.out.println("\n3) Grados");
        int suma = 0, mayor = -1;
        String conMasAmigos = "", aislado = "";
        for (String id : ids) {
            int g = grado(id);
            suma += g;
            System.out.println("   grado(" + id + " " + nombre(id) + ") = " + g);
            if (g > mayor) { mayor = g; conMasAmigos = id; }
            if (g == 0) aislado = id;
        }
        System.out.println("   Usuario aislado: " + aislado + " (" + nombre(aislado) + ")");
        System.out.println("   Mayor numero de amistades: " + conMasAmigos + " ("
                + nombre(conMasAmigos) + ") con grado " + mayor);
        System.out.println("   Suma de grados = " + suma + " ; 2|E| = " + (2 * aristas.length)
                + " -> " + (suma == 2 * aristas.length ? "se cumple" : "NO se cumple"));

        // Pregunta 4: ciclo y conectividad
        System.out.println("\n4) Ciclo y conectividad");
        System.out.println("   Ciclo: U01 - U02 - U03 - U01 (Mateo - Sofia - Luis - Mateo)");
        System.out.println("   Componente conectado: U01, U02, U03, U04, U05");
        System.out.println("   Componente aparte: U06 (Camila)");
        System.out.println("   El grafo NO es conexo: no todos los usuarios estan conectados.");

        // Pregunta 5: consultas
        System.out.println("\n5) Consultas");
        System.out.println("   existeRelacion(U01, U02) -> " + existeRelacion("U01", "U02"));
        System.out.println("   existeRelacion(U03, U04) -> " + existeRelacion("U03", "U04"));
        System.out.println("   existeRelacion(U02, U05) -> " + existeRelacion("U02", "U05"));
        System.out.println("   vecinos(U01) -> " + vecinos("U01"));
        System.out.println("   grado(U01)   -> " + grado("U01"));
        System.out.println("   vecinos(U06) -> " + vecinos("U06") + " (vacio)");
        System.out.println("   grado(U06)   -> " + grado("U06"));

        // Pregunta 6: relaciones rechazadas
        System.out.println("\n6) Relaciones que deben rechazarse");
        System.out.println("   U03-U03: " + validarAmistad("U03", "U03"));
        System.out.println("   U02-U01: " + validarAmistad("U02", "U01"));
        System.out.println("   U01-U09: " + validarAmistad("U01", "U09"));
    }
}

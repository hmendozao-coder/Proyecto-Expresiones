/*
 * Proyecto Expresiones
 * Clase: Nodo
 * Función: representar un nodo dentro del árbol de expresión,
 * almacenando un valor (operador o variable) y referencias a sus hijos.
 */

package proyectoexpresiones;

/**
 * Clase Nodo
 * Representa un nodo del árbol de expresión.
 * Cada nodo contiene:
 * - Un valor (operador o variable)
 * - Referencia al hijo izquierdo
 * - Referencia al hijo derecho
 * 
 * Esta estructura permite construir y recorrer el árbol de manera ordenada.
 * 
 * @author Grupo I
 */
public class Nodo {
    private String valor;     // Valor del nodo (operador o variable)
    private Nodo izquierdo;   // Hijo izquierdo
    private Nodo derecho;     // Hijo derecho

    /**
     * Constructor de la clase Nodo
     * Inicializa el nodo con un valor y sin hijos.
     * 
     * @param valor contenido del nodo (operador o variable)
     */
    public Nodo(String valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Método getValor
     * @return el valor almacenado en el nodo
     */
    public String getValor() {
        return valor;
    }

    /**
     * Método setValor
     * @param valor nuevo contenido para el nodo
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Método getIzquierdo
     * @return referencia al hijo izquierdo
     */
    public Nodo getIzquierdo() {
        return izquierdo;
    }

    /**
     * Método setIzquierdo
     * @param izquierdo asigna un nuevo hijo izquierdo
     */
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Método getDerecho
     * @return referencia al hijo derecho
     */
    public Nodo getDerecho() {
        return derecho;
    }

    /**
     * Método setDerecho
     * @param derecho asigna un nuevo hijo derecho
     */
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}

/*
 * Nota teórica:
 * - Cada nodo es la unidad básica del árbol de expresión.
 * - Los nodos pueden representar variables (ej. a, b, c) o operadores (+, -, *, /, ^).
 * - La estructura izquierda/derecha permite organizar la expresión en forma jerárquica.
 * 
 * Conclusión: la clase Nodo es esencial para modelar expresiones matemáticas como árboles,
 * facilitando recorridos y evaluaciones posteriores.
 */

/*
 * Proyecto Expresiones
 * Clase: ArbolExpresion
 * Función: construir un árbol binario a partir de una expresión infija,
 * aplicando reglas de precedencia y paréntesis para organizar operadores y operandos.
 */

package proyectoexpresiones;
import java.util.Stack;

/**
 * Clase ArbolExpresion
 * Se encarga de:
 * - Construir el árbol de expresión desde una cadena en notación infija
 * - Aplicar precedencia de operadores y manejo de paréntesis
 * - Generar la raíz del árbol para recorridos y evaluación
 * 
 * @author Grupo I
 */
public class ArbolExpresion {
    
    private Nodo raiz; // Nodo raíz del árbol de expresión

    /**
     * Método getRaiz
     * @return nodo raíz del árbol
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * Método construirArbol
     * Función: construye el árbol binario a partir de una expresión infija.
     * Utiliza dos pilas: una para operandos y otra para operadores.
     * 
     * @param expresion cadena en notación infija
     */
    public void construirArbol(String expresion) {
        Stack<Nodo> operandos = new Stack<>();
        Stack<String> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            // Ignorar espacios
            if (c == ' ') continue;

            // Si es letra o número → operando
            if (Character.isLetterOrDigit(c)) {
                operandos.push(new Nodo(String.valueOf(c)));
            }
            // Si es operador
            else if (esOperador(c)) {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(String.valueOf(c))) {
                    crearSubArbol(operandos, operadores.pop());
                }
                operadores.push(String.valueOf(c));
            }
            // Si es paréntesis de apertura
            else if (c == '(') {
                operadores.push(String.valueOf(c));
            }
            // Si es paréntesis de cierre
            else if (c == ')') {
                while (!operadores.peek().equals("(")) {
                    crearSubArbol(operandos, operadores.pop());
                }
                operadores.pop(); // quitar "("
            }
        }

        // Procesar operadores restantes
        while (!operadores.isEmpty()) {
            crearSubArbol(operandos, operadores.pop());
        }

        // La raíz será el último nodo en operandos
        raiz = operandos.pop();
    }

    /**
     * Método crearSubArbol
     * Función: crea un subárbol con un operador y dos operandos.
     * @param operandos pila de operandos
     * @param operador símbolo del operador
     */
    private void crearSubArbol(Stack<Nodo> operandos, String operador) {
        Nodo derecho = operandos.pop();
        Nodo izquierdo = operandos.pop();
        Nodo nuevo = new Nodo(operador);
        nuevo.setIzquierdo(izquierdo);
        nuevo.setDerecho(derecho);
        operandos.push(nuevo);
    }

    /**
     * Método esOperador
     * Función: verifica si un carácter es un operador válido.
     * @param c carácter a evaluar
     * @return true si es operador, false en caso contrario
     */
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     * Método precedencia
     * Función: asigna un nivel de precedencia a cada operador.
     * @param op operador en forma de cadena
     * @return valor numérico de precedencia
     */
    private int precedencia(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
        }
        return -1;
    }
}

/*
 * Nota teórica:
 * - El árbol de expresión organiza operadores y operandos jerárquicamente.
 * - La precedencia asegura que operaciones como multiplicación y división
 *   se resuelvan antes que suma y resta.
 * - Los paréntesis permiten modificar el orden natural de evaluación.
 * 
 * Conclusión: la clase ArbolExpresion es el núcleo del proyecto, ya que
 * transforma una expresión infija en una estructura que puede recorrerse
 * y evaluarse de manera sistemática.
 */


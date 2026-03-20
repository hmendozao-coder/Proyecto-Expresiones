/*
 * Proyecto Expresiones
 * Clase principal: ProyectoExpresiones
 * Función: gestionar la entrada del usuario, validar la expresión,
 * construir el árbol, mostrar recorridos y evaluar la expresión paso a paso.
 */

package proyectoexpresiones;
import java.util.*;

/**
 * Clase ProyectoExpresiones
 * Se encarga de:
 * - Validar la expresión ingresada
 * - Construir el árbol de expresión
 * - Detectar variables y solicitar valores
 * - Mostrar recorridos (inorden, preorden, postorden)
 * - Evaluar la expresión mostrando el comportamiento de la pila
 * 
 * @author Grupo I
 */
public class ProyectoExpresiones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ejemplos de expresiones posibles:
        //String expresion = "a+b*c";                // suma y multiplicación
        String expresion = "a+b-(c-d)+e";          // paréntesis y resta
        // String expresion = "(a*b)/(c+d)";          // multiplicación y división
        // String expresion = "(a+b)*(c-d)/e+f";      // combinación larga con varios operadores
        // String expresion = "(a+b)*(c/d)";          // paréntesis con división
        // String expresion = "a^b*c";                // potencia y multiplicación
        // String expresion = "(a+b+c+d)";            // suma encadenada
        // String expresion = "a-b-c";                // resta encadenada
        // String expresion = "a*b-c/d";              // mezcla de multiplicación y división
        // String expresion = "(a+b)^(c-d)";          // potencia con paréntesis
        // String expresion = "√a+b";                 // raíz y suma (requiere habilitar caso "√" en el switch)
        //String expresion = "a^b+c";

        // Validar expresión antes de construir el árbol
        if (!ValidadorExpresion.validar(expresion)) {
            System.out.println("La expresión contiene caracteres inválidos.");
            return;
        }

        // Construir árbol de la expresión
        ArbolExpresion arbol = new ArbolExpresion();
        arbol.construirArbol(expresion);

        // Detectar variables presentes en la expresión
        Set<Character> variables = detectarVariables(expresion);

        // Solicitar valores para cada variable detectada
        Map<String, Double> valores = new HashMap<>();
        for (char var : variables) {
            System.out.print("Ingrese valor para " + var + ": ");
            double valor = sc.nextDouble();
            valores.put(String.valueOf(var), valor);
        }

        // Mostrar recorridos del árbol
        System.out.println("\nExpresión: " + expresion);
        System.out.print("Recorrido Inorden: ");
        recorrerInorden(arbol.getRaiz());
        System.out.println();

        System.out.print("Recorrido Preorden: ");
        recorrerPreorden(arbol.getRaiz());
        System.out.println();

        System.out.print("Recorrido Postorden: ");
        recorrerPostorden(arbol.getRaiz());
        System.out.println();

        // Mostrar valores ingresados
        System.out.println("\nValores ingresados: " + valores);

        // Evaluar expresión mostrando pila paso a paso
        double resultado = evaluarPostfija(arbol.getRaiz(), valores);
        System.out.println("\nResultado de la expresión: " + resultado);
    }

    /**
     * Método detectarVariables
     * Función: identifica las letras presentes en la expresión
     * y las devuelve como conjunto de variables.
     * 
     * @param expresion cadena ingresada
     * @return conjunto de variables encontradas
     */
    public static Set<Character> detectarVariables(String expresion) {
        Set<Character> variables = new HashSet<>();
        for (char c : expresion.toCharArray()) {
            if (Character.isLetter(c)) {
                variables.add(c);
            }
        }
        return variables;
    }

    /**
     * Método recorrerInorden
     * Función: muestra la expresión en orden natural (izquierda → raíz → derecha).
     * Este recorrido refleja cómo se escribiría la expresión en papel.
     */
    public static void recorrerInorden(Nodo nodo) {
        if (nodo != null) {
            recorrerInorden(nodo.getIzquierdo());
            System.out.print(nodo.getValor() + " ");
            recorrerInorden(nodo.getDerecho());
        }
    }

    /**
     * Método recorrerPreorden
     * Función: muestra primero el operador, seguido de los operandos.
     * Este recorrido corresponde a la notación prefija.
     */
    public static void recorrerPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValor() + " ");
            recorrerPreorden(nodo.getIzquierdo());
            recorrerPreorden(nodo.getDerecho());
        }
    }

    /**
     * Método recorrerPostorden
     * Función: muestra primero los operandos y luego el operador.
     * Este recorrido corresponde a la notación postfija, útil para evaluación con pila.
     */
    public static void recorrerPostorden(Nodo nodo) {
        if (nodo != null) {
            recorrerPostorden(nodo.getIzquierdo());
            recorrerPostorden(nodo.getDerecho());
            System.out.print(nodo.getValor() + " ");
        }
    }

    /**
     * Método evaluarPostfija
     * Función: evalúa la expresión en notación postfija mostrando
     * el comportamiento de la pila paso a paso.
     * 
     * @param nodo raíz del árbol
     * @param valores mapa con valores de variables
     * @return resultado numérico de la expresión
     */
    public static double evaluarPostfija(Nodo nodo, Map<String, Double> valores) {
        List<String> postfija = new ArrayList<>();
        generarPostfija(nodo, postfija);

        Stack<Double> pila = new Stack<>();

        System.out.println("\n--- Evaluación paso a paso ---");
        for (String token : postfija) {
            if (Character.isLetter(token.charAt(0))) {
                double val = valores.get(token);
                pila.push(val);
                System.out.println("Token: " + token + " -> apilar valor " + val + " -> Pila: " + pila);
            } else if (Character.isDigit(token.charAt(0))) {
                double val = Double.parseDouble(token);
                pila.push(val);
                System.out.println("Token: " + token + " -> apilar número " + val + " -> Pila: " + pila);
            } else {
                double b = pila.pop();
                double a = pila.pop();
                double resultado = 0;
                switch (token) {
                    case "+": resultado = a + b; break;
                    case "-": resultado = a - b; break;
                    case "*": resultado = a * b; break;
                    case "/": resultado = a / b; break;
                    case "^": resultado = Math.pow(a, b); break;
                }
                pila.push(resultado);
                System.out.println("Token: " + token + " -> operar " + a + " " + token + " " + b + " = " + resultado + " -> Pila: " + pila);
            }
        }
        System.out.println("--- Fin de evaluación ---");
        return pila.pop();
    }

    /**
     * Método generarPostfija
     * Función: genera la notación postfija de la expresión
     * a partir del árbol de expresión.
     * 
     * @param nodo raíz del árbol
     * @param lista lista donde se almacenará la notación postfija
     */
    public static void generarPostfija(Nodo nodo, List<String> lista) {
        if (nodo != null) {
            generarPostfija(nodo.getIzquierdo(), lista);
            generarPostfija(nodo.getDerecho(), lista);
            lista.add(nodo.getValor());
        }
    }
}

/*
 * Nota teórica:
 * - Inorden: refleja la expresión original en orden natural.
 * - Preorden: muestra primero el operador, útil para notación prefija.
 * - Postorden: muestra primero operandos y luego operador, útil para evaluación con pila.
 * 
 * Conclusión: los recorridos permiten representar la misma expresión en distintas notaciones,
 * y la evaluación paso a paso con pila asegura transparencia en el cálculo.
 */

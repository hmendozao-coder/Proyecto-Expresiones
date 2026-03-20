/*
 * Proyecto Expresiones
 * Clase: ValidadorExpresion
 * Función: validar que la expresión ingresada por el usuario
 * contenga únicamente caracteres permitidos (letras, números,
 * operadores y paréntesis).
 */

package proyectoexpresiones;

/**
 * Clase ValidadorExpresion
 * Se encarga de verificar que la expresión cumpla con el patrón
 * definido y evitar errores al construir el árbol de expresiones.
 * 
 * @author Grupo I
 */
public class ValidadorExpresion {

    /**
     * Método validar
     * Recibe una cadena y comprueba que solo contenga caracteres válidos:
     * - Letras (a-z, A-Z)
     * - Números (0-9)
     * - Operadores (+, -, *, /, ^, √)
     * - Paréntesis ()
     * - Espacios
     * 
     * @param expresion La expresión ingresada por el usuario
     * @return true si la expresión es válida, false en caso contrario
     */
    public static boolean validar(String expresion) {
        // Expresión regular que permite letras, números, operadores y paréntesis
        String regex = "[a-zA-Z0-9+\\-*/^√()\\s]+";

        // Si la expresión cumple con el patrón, es válida
        return expresion.matches(regex);
    }
}

/*
 * Nota teórica:
 * - La validación es un paso previo fundamental para evitar errores en la construcción del árbol.
 * - Garantiza que solo se procesen expresiones con caracteres permitidos.
 * - Esto asegura consistencia y evita que el programa falle por entradas inválidas.
 * 
 * Conclusión: el ValidadorExpresion actúa como un filtro inicial que protege la lógica del proyecto,
 * asegurando que las expresiones matemáticas sean correctas antes de ser evaluadas.
 */

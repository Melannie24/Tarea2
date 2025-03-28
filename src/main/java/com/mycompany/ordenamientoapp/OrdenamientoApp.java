/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordenamientoapp;

import javax.swing.JOptionPane; // importa JOptionPane para las ventanas emergentes
import java.util.Arrays; // importa Arrays para manipular arreglos
import java.util.ArrayList; // importa ArrayList para Bucket Sort
import java.util.Collections; // importa Collections para ordenar listas
import java.util.List; // importa List para manejar listas en Bucket Sort

/**
 *
 * @author Rosse Lorenzana
 */

public class OrdenamientoApp {
    public static void main(String[] args) {        
        String inputSize = JOptionPane.showInputDialog("Ingrese la cantidad de elementos para ordenar:");
        int cantidadElementos = Integer.parseInt(inputSize); // Convertir la entrada a numero entero
        int[] datos = new int[cantidadElementos]; // creo arreglo con el tamaño que ingreso

        // pido al usuario los valores para el arreglo
        for (int i = 0; i < cantidadElementos; i++) {
            String inputNumero = JOptionPane.showInputDialog("Ingrese el elemento " + (i + 1) + ":");
            datos[i] = Integer.parseInt(inputNumero); // convierte el valor ingresado a numero entero
        }

        //menu de seleccion del metodo de ordenamiento
        String menuOpciones = """
            Seleccione el método de ordenamiento:
            1. Selection Sort (Seleccion)
            2. Bubble Sort (Burbuja)
            3. Insertion Sort (Insercion)
            4. Merge Sort (Combinacion)
            5. Quick Sort (Rapida)
            6. Heap Sort (Monton)
            7. Counting Sort (Conteo)
            8. Radix Sort (Raiz)
            9. Bucket Sort (Cubo)
            """;

        String seleccionUsuario = JOptionPane.showInputDialog(menuOpciones);
        int opcion = Integer.parseInt(seleccionUsuario); // convierte la opcion seleccionada a numero entero

        int[] copiaOriginal = datos.clone(); // guarda una copia del arreglo original para comparaciones

       
String metodoUsado = ""; // variable para almacenar el nombre del método utilizado

switch (opcion) {
    case 1 -> {
        selectionSort(datos);
        metodoUsado = "Selection Sort (Selección)";
    }
    case 2 -> {
        bubbleSort(datos);
        metodoUsado = "Bubble Sort (Burbuja)";
    }
    case 3 -> {
        insertionSort(datos);
        metodoUsado = "Insertion Sort (Inserción)";
    }
    case 4 -> {
        mergeSort(datos, 0, datos.length - 1);
        metodoUsado = "Merge Sort (Combinación)";
    }
    case 5 -> {
        quickSort(datos, 0, datos.length - 1);
        metodoUsado = "Quick Sort (Rápida)";
    }
    case 6 -> {
        heapSort(datos);
        metodoUsado = "Heap Sort (Montón)";
    }
    case 7 -> {
        datos = countingSort(datos);
        metodoUsado = "Counting Sort (Conteo)";
    }
    case 8 -> {
        radixSort(datos);
        metodoUsado = "Radix Sort (Raíz)";
    }
    case 9 -> {
        datos = bucketSort(datos);
        metodoUsado = "Bucket Sort (Cubo)";
    }
    default -> {
        JOptionPane.showMessageDialog(null, "opcion no valida.");
        return;
    }
}

// muestra los resultados en un cuadro de dialogo con el nombre del metodo utilizado
JOptionPane.showMessageDialog(null, "metodo utilizado: " + metodoUsado +
        "\nArreglo original: " + Arrays.toString(copiaOriginal) +
        "\nArreglo ordenado: " + Arrays.toString(datos));
}

    //(Selection Sort)
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            // intercambio
            int temp = arr[indiceMinimo];
            arr[indiceMinimo] = arr[i];
            arr[i] = temp;
        }
    }

    //(Bubble Sort)
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // intercambio
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //(Insertion Sort)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int clave = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > clave) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = clave;
        }
    }

    //(Merge Sort)
    public static void mergeSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            mergeSort(arr, inicio, medio);
            mergeSort(arr, medio + 1, fin);
            merge(arr, inicio, medio, fin);
        }
    }

    private static void merge(int[] arr, int inicio, int medio, int fin) {
        int[] izquierda = Arrays.copyOfRange(arr, inicio, medio + 1);
        int[] derecha = Arrays.copyOfRange(arr, medio + 1, fin + 1);

        int i = 0, j = 0, k = inicio;
        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i] <= derecha[j]) {
                arr[k++] = izquierda[i++];
            } else {
                arr[k++] = derecha[j++];
            }
        }

        while (i < izquierda.length) {
            arr[k++] = izquierda[i++];
        }

        while (j < derecha.length) {
            arr[k++] = derecha[j++];
        }
    }

    //(Quick Sort)
    public static void quickSort(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int pivote = partition(arr, bajo, alto);
            quickSort(arr, bajo, pivote - 1);
            quickSort(arr, pivote + 1, alto);
        }
    }

    private static int partition(int[] arr, int bajo, int alto) {
        int pivote = arr[alto];
        int i = bajo - 1;
        for (int j = bajo; j < alto; j++) {
            if (arr[j] < pivote) {
                i++;
                // intercambio
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // intercambio
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        return i + 1;
    }

    //(Heap Sort)
    public static void heapSort(int[] arr) {
        int n = arr.length;
        // Crear el heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // extrae elementos del heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int mayor = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        if (izquierda < n && arr[izquierda] > arr[mayor]) {
            mayor = izquierda;
        }
        if (derecha < n && arr[derecha] > arr[mayor]) {
            mayor = derecha;
        }
        if (mayor != i) {
            int temp = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = temp;
            heapify(arr, n, mayor);
        }
    }

    //(Counting Sort)
    public static int[] countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt(); // encuentra el valor maximo
        int[] count = new int[max + 1]; // cuenta las ocurrencias
        int[] output = new int[arr.length]; // arreglo de salida

        // cuenta las ocurrencias de cada elemento
        for (int num : arr) {
            count[num]++;
        }

        // modifica el arreglo count para que cada posicion contenga la cantidad total
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // construimos el arreglo ordenado
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        return output;
    }

    // (Radix Sort)
    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt(); // encuentra el valor maximo

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // cuenta las ocurrencias de los digitos (0-9)

        // cuenta las ocurrencias de cada digito
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // acumula las posiciones en el arreglo count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // construimos el arreglo de salida
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // copia el arreglo de salida al arreglo original
        System.arraycopy(output, 0, arr, 0, n);
    }

    // (Bucket Sort)
    public static int[] bucketSort(int[] arr) {
        if (arr.length <= 0) {
            return arr;
        }

        // encontramos el valor maximo en el arreglo
        int max = Arrays.stream(arr).max().getAsInt();
        int numberOfBuckets = (int) Math.sqrt(arr.length);

        // creamos los cubos (buckets)
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // distribuimos los elementos de arr en los cubos
        for (int num : arr) {
            int bucketIndex = (num * numberOfBuckets) / (max + 1);
            buckets.get(bucketIndex).add(num);
        }

        // ordena cada cubo y reconstruir el arreglo
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }

        return arr;
    }
}

# 📊 SAV (Sorting Algorithm Viewer)
![Exemplo SAV](src/resources/img/sav.png)

## 📋 Introdução
O projeto visa desenvolver um **visor de algoritmos de ordenação**, um algorítmo que localiza os elementos de uma lista em uma certa ordem.

## 🌟 Funcionalidades
A ideia é poder mostrar visualmente como a lista **muda** em cada iteraçao, de acordo com o algoritmo de ordenação. Exemplo:
![Exemplo SAV](src/resources/img/sorting.png)

## ⚙️ Diagrama de Classe
```mermaid
classDiagram
    class SortViewerFrame {
        +SortViewerFrame()
        +showMenu()
        +validateInput(algorithm: String, type: String, order: String): boolean
        +getAlgorithmFullName(algorithm: String): String
        +getTypeFullName(type: String): String
        +getOrderFullName(order: String): String
        +startSorting()
        -generateRandomNumbers(numElements: int): int[]
        -generateRandomChars(numElements: int): char[]
        -sizeField: JTextField
        -pauseField: JTextField
        -algorithmBox: JComboBox
        -typeBox: JComboBox
        -orderBox: JComboBox
        -startButton: JButton
        -menuButton: JButton
        -control: SortController
        -panel: SortPanel
    }

    class SortController {
        +SortController(panel: SortPanel, pauseDuration: int, order: String)
        +bubbleSort(array: int[])
        +bubbleSort(array: char[])
        +selectionSort(array: int[])
        +selectionSort(array: char[])
        +insertionSort(array: int[])
        +insertionSort(array: char[])
        +quickSort(array: int[], low: int, high: int)
        +quickSort(array: char[], low: int, high: int)
        -panel: SortPanel
        -pauseDuration: int
        -ascending: boolean
    }

    class Sorting {
        <<abstract>>
        +Sorting(panel: SortPanel, ascending: boolean, pauseDuration: int)
        +sort(array: int[])
        +sort(array: char[])
        -highlightBar(index: int)
        -sleep()
        -panel: SortPanel
        -ascending: boolean
        -pauseDuration: int
    }

    class BubbleSort {
        +BubbleSort(panel: SortPanel, ascending: boolean, pauseDuration: int)
        +sort(array: int[])
        +sort(array: char[])
    }

    class SelectionSort {
        +SelectionSort(panel: SortPanel, ascending: boolean, pauseDuration: int)
        +sort(array: int[])
        +sort(array: char[])
    }

    class InsertionSort {
        +InsertionSort(panel: SortPanel, ascending: boolean, pauseDuration: int)
        +sort(array: int[])
        +sort(array: char[])
    }

    class QuickSort {
        +QuickSort(panel: SortPanel, ascending: boolean, pauseDuration: int)
        +sort(array: int[])
        +sort(array: char[])
        -quickSortHelper(array: int[], low: int, high: int)
        -partition(array: int[], low: int, high: int): int
        -quickSortHelper(array: char[], low: int, high: int)
        -partition(array: char[], low: int, high: int): int
    }

    class SortPanel {
        +setIntArray(array: int[])
        +setCharArray(array: char[])
        +setCurrentBar(index: int)
        -paintComponent(g: Graphics)
        -updateBuffer()
        -drawArray(g: Graphics, array: int[])
        -drawArray(g: Graphics, array: char[])
        -getMaxValue(array: int[]): int
        -getMaxValue(array: char[]): int
        -intArray: int[]
        -charArray: char[]
        -isCharArray: boolean
        -buffer: BufferedImage
        -barSpacing: int
        -currentBar: int
    }

    SortViewerFrame --> SortController : Uses
    SortController --> Sorting : Uses
    Sorting <|-- BubbleSort
    Sorting <|-- SelectionSort
    Sorting <|-- InsertionSort
    Sorting <|-- QuickSort
    SortPanel <-- Sorting : Uses
    SortController --> SortPanel : Uses
    SortViewerFrame --> SortPanel : Uses

```

## 🛠️ Implementação
- **Linguagem de Programação:** Java
- **Interface Gráfica:** Swing
- **Controle de Versão:** Git

## 📈 Ordenação Visual (Interface Gráfica)
Para visualizar em tempo real os diferentes tipos de ordenação dos elementos (`Bubble Sort`, `Insertion Sort`, `Quick Sort`, `Seletion Sort`), execute a classe principal do projeto: `MainApp`.

`Path: src/main/java/MainApp.java`

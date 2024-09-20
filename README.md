# 📊 SAV (Sorting Algorithm Viewer)
![Exemplo SAV](src/resources/img/sav.png)

## 📋 Introdução
O projeto visa desenvolver um **visor de algoritmos e ordenação**, um algorítmo que localiza os elementos de uma lista em uma certa ordem.

## 🌟 Funcionalidades
A ideia é poder mostrar visualmente como a lista **muda** em cada iteraçao, de acordo com o algoritmo de ordenação. Exemplo:

![Exemplo SAV](src/resources/img/sorting.png)

### 🧮 Ordenações utilizadas:
- **Bubble Sort**: Pequenas e frequentes trocas de elementos adjacentes. A lista gradualmente se ordena de trás para frente.
- **Insertion Sort**: Elementos são movidos e inseridos gradualmente na posição correta. A parte ordenada cresce à medida que o algoritmo avança.
- **Quick Sort**: Segmentos da lista são ordenados em diferentes pontos, com partições visíveis onde o pivô divide a lista.
- **Selection Sort**: O menor elemento é selecionado e trocado com o elemento na posição inicial da parte não ordenada da lista.

### 🧱 Em construção:
- No fim da ordenação, mostrar o tempo de execução até a ordenação da lista.
- Ajustar o layout do painel de ordenação:
    - tamanho das barras para caracteres manuais (diferença entre minúsculos e maiúsculos).
    - legenda das barras (padronizar)
    - espaçamento do painel
- Melhorar visual do painel de controle.
- Opção de pausar a ordenação. (?)

### ⚠️ Atualizar o Diagrama ⬇️
## ⚙️ Diagrama de Classe
```mermaid
  classDiagram
    direction LR
    class SortViewerFrame {
          +SortViewerFrame()
          +startSorting()
          -sizeField: JTextField
          -pauseField: JTextField
          -algorithmBox: JComboBox
          -typeBox: JComboBox
          -orderBox: JComboBox
          -valueListBox: JComboBox
          -control: SortController
          -panel: SortPanel
      }
      
      class InputValidation {
          +validateInput(algorithm: String, type: String, order: String, valuesList: String): boolean
          +getAlgorithmFullName(algorithm: String): String
          +getTypeFullName(type: String): String
          +getOrderFullName(order: String): String
          +getValuesListFullName(valuesList: String): String
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
          -drawArray(g: Graphics, int, int)
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
      SortViewerFrame --> InputValidation : Uses
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
    - **Versão utilizada:** JDK 17
- **Interface Gráfica:** Swing
- **Controle de Versão:** Git

## 📈 Ordenação Visual (Interface Gráfica)
Agora você irá visualizar em tempo real os diferentes tipos de ordenação dos elementos (`Bubble Sort`, `Insertion Sort`, `Quick Sort`, `Seletion Sort`).

### 🔝 Você deve entrar com os parâmetro via CLI!
Abra o terminal e navegue até a pasta do projeto (caso não esteja), e execute os seguintes comandos:

- #### Compilação:
  Foi usado o arquivo `Makefile` para automatizar o processo de compilação. Para executá-lo basta por `make` no terminal.
  ```bash
    make
  ```
- #### Execução (Exemplo):
    - aleatório (caracteres)
      ```bash
        java -cp out main.java.MainApp a=b t=c o=az in=r r=20 s=100
      ```
    
    - aleatório (números)
      ```bash
        java -cp out main.java.MainApp a=q t=n o=az in=r r=50 s=100
      ```
    
    - manual (caracteres)
      ```bash
        java -cp out main.java.MainApp a=s t=c o=za in=m v="H,r,x,C,Q,L,w,o,c,z,M,g,p,z,b,R,a,h" s=100
      ```
    
    - manual (números)
      ```bash
        java -cp out main.java.MainApp a=i t=n o=za in=m v="21,50,3,2,7,17,23,15,38,12,4,47,31" s=100
      ```
`Path: src/main/java/MainApp.java`

## 📝 Licença
Este projeto é licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

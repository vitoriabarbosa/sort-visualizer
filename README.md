# 📊 SAV (Sorting Algorithm Viewer)
![Exemplo SAV](src/resources/img/sav.png)

## 📋 Introdução
O projeto visa desenvolver um **visor de algoritmos de ordenação**, um algorítmo que localiza os elementos de uma lista em uma certa ordem.

## 🌟 Funcionalidades
A ideia é poder mostrar visualmente como a lista **muda** em cada iteraçao, de acordo com o algoritmo de ordenação. Exemplo:
![Exemplo SAV](src/resources/img/sorting.png)

## ⚙️ Diagrama de Classe
![Diagrama SAV](src/resources/img/diagram-sav.png)

## 🛠️ Implementação
- **Linguagem de Programação:** Java
- **Interface Gráfica:** Swing
- **Controle de Versão:** Git

## 📈 Ordenação Visual (Interface Gráfica)
Para visualizar em tempo real os diferentes tipos de ordenação dos elementos (`Bubble Sort`, `Insertion Sort`, `Quick Sort`, `Seletion Sort`), execute a classe principal do projeto: `MainApp`.

`Path: src/main/java/MainApp.java`

## 🔝 Ordenação Simples via CLI
- #### Compilação:
  ```bash
  javac -d out src/sav/*.java
  ```
- #### Execução (Exemplo):
  ```bash
  java -cp out sav.Main t=n v="0,-1,3,1,10"
    
  java -cp out sav.Main t=c v="z,a,Z,A,b"
  ```
  
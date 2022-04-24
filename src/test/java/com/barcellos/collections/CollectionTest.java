package com.barcellos.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class CollectionTest {

    public void collection() {

        Collection<String> list1 = new ArrayList<String>();

        Collection<String> set1 = new HashSet<String>();
    }

    /**
     * LISTAS
     *
     * São estruturas lineares de armazenamento
     *
     * - Uma lista é uma coleção de elementos arrumados numa ordem linear, isto é,
     * onde cada elemento tem um antecessor (exceto o primeiro) e um sucessor
     * (exceto o último)
     * - Normalmente implementada como "Array" ou "Lista Encadeada"
     * - A Lista pode ser mantida ordenada ou não
     */
    public void list() {

        // Possui um Array encapsulado como estrutura de dados
        // Permite acesso indexado
        // Lista +50% | add e remove mais lento | get mais rápido | iteração mais rapido
        List<String> list1 = new ArrayList<String>();

        // Encapsula uma lista duplamente encadeada com descritor;
        // Não possui acesso indexado;
        // Lista +100% | add e remove mais rapido | get mais lento | iteração mais lento
        // bom pra implementar stack ou queue
        List<String> list2 = new LinkedList<String>();

        // Sincronizado
        List<String> list3 = new Vector<String>();

        // Pilha de objetos LIFO (last in, first out)
        List<String> list4 = new Stack<String>();
    }

    /**
     * CONJUNTOS
     *
     * Os conjuntos são estruturas que não permitem elementos repetidos.
     *
     * - Um conjunto é uma coleção que não possui elementos duplicados
     * - No conjunto, não há noção de "ordem dos elementos"
     * - O Conjunto pode ser mantido ordenado ou não
     * - Normalmente implementada como "Tabela Hash" ou "Árvore"
     * ---- Somente a implementação na forma de árvore pode ser ordenada
     */
    public void set() {

        // Conjunto implementado como Hash
        // È mais rápido que todos os conjuntos
        Set<String> set1 = new HashSet<String>();

        // Implementa um algoritmo conhecido por red-black tree ou árvore rubro-negra
        //
        // O uso da interface SortedSet, já garante a ordenação na forma crescente.
        //
        // Pelo fato de ele implementar SortedSet ele possui elementos ordenados
        // automaticamente, ou seja, independente da ordem que você inserir os
        // elementos, eles serão ordenados
        Set<String> set2 = new TreeSet<String>();

        // É um meio termo entre HashSet e TreeSet, ou seja, ela nos proporciona um
        // pouco da performance do HashSet e um pouco do poder de ordenação do TreeSet
        //
        // O LinkedHashSet faz uso também do HashTable com linked list, ou seja, temos
        // aqui a seguinte situação: Os elementos continuam na ordem que são inseridos,
        // diferente do HashSet que “embaralha” tudo
        Set<String> set3 = new LinkedHashSet<String>();

        Queue<String> queue1 = new PriorityQueue<String>();

    }

    /**
     * MAPAS
     *
     * Estruturas de dados baseado na combinação:
     * ▪ Chave -> valor
     */
    public void map() {

        // É um mapa utilizando a estrutura de dados Hashing
        // Recebe todas as características de uma implementação de Hash com as ações
        // esperadas para um mapa.
        Map<String, Object> map1 = new HashMap<String, Object>();

        // Recebe todas as características de uma árvore
        // Permite todas as operações de um mapa
        // Inficado para mapas em que precisa-se de ordenação das chaves
        Map<String, Object> map2 = new TreeMap<String, Object>();
    }
}

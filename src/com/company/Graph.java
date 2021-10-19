package com.company;

/**
 * Clase Grafo
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

//Librerias
import java.util.LinkedList;

public class Graph
{
    private int V;
    private LinkedList<Integer> adj[];
    private int[] cost;
    LinkedList<Integer> queue = new LinkedList<>();
    boolean [] tree;

    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
        {
            adj[i] = new LinkedList<>();
        }
        cost = new int[v];
    }

    void addEdge(int v, int w,int cost)
    {
        adj[v].add(w);
        adj[w].add(v);
        this.cost[v] = cost;
    }
}
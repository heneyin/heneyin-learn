package com.example.jprapht;

import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import java.util.*;

/**
 * @author hongliang.yin/Heneyin
 * @date 2025/4/29
 */
public class JGraphtTest {

    private Graph<String, DefaultEdge> fullGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

    @BeforeEach
    void setUp() {
        fullGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        String google = "google";
        String wikipedia = "wikipedia";
        String jgrapht = "jgrapht";
        String deepseek = "deepseek";
        String openai = "openai";

        // add the vertices
        fullGraph.addVertex(google);
        fullGraph.addVertex(wikipedia);
        fullGraph.addVertex(jgrapht);
        fullGraph.addVertex(deepseek);
        fullGraph.addVertex(openai);

        // add edges to create linking structure
        fullGraph.addEdge(jgrapht, wikipedia);

        fullGraph.addEdge(google, jgrapht);

        fullGraph.addEdge(google, wikipedia);

        fullGraph.addEdge(wikipedia, google);

        fullGraph.addEdge(deepseek, jgrapht);

        fullGraph.addEdge(jgrapht, openai);

    }

    @Test
    public void addVertex() {
        // 添加新节点
        fullGraph.addVertex("claude");
        // 添加新边
        fullGraph.addEdge("openai", "claude");
    }

    @Test
    public void deleteVertex() {
        // 删除节点 B（同时会删除所有关联的边）
        fullGraph.removeVertex("openai");
    }

    /**
     * 获取固定节点下的子图
     */
    @Test
    public void subGraph() {
        // 创建子图（基于顶点集合）
        Set<String> subgraphNodes = new HashSet<>();
        subgraphNodes.add("deepseek");
        subgraphNodes.add("jgrapht");
        subgraphNodes.add("openai");
        subgraphNodes.add("notExisting");

        Graph<String, DefaultEdge> subgraph = new AsSubgraph<>(fullGraph, subgraphNodes);

        Set<String> strings = subgraph.vertexSet();
        strings.forEach(System.out::println);
        Set<DefaultEdge> defaultEdges = subgraph.edgeSet();
        defaultEdges.forEach(System.out::println);
    }


    @Test
    public void subConnectSubGraph() {
        Set<String> sources = new HashSet<>();
        sources.add("deepseek");
        // JGraphT 不直接提供该功能
        // 灵活性需求不同
        //  深度计算方式可能因场景不同（如最短路径深度、无权图层数、有向/无向图等）。
        //  用户可能需要对遍历过程进行定制（如过滤某些节点或边）。
        //性能权衡
        //  直接提供该功能可能导致 API 复杂化（如支持多种深度计算方式）。
        //  现有的 BreadthFirstIterator 和 AsSubgraph 已足够组合出高效实现。

        Graph<String, DefaultEdge> subgraph = getSubgraphWithinDepth(fullGraph, sources, 2);
        System.out.println("子图节点: " + subgraph.vertexSet());
        System.out.println("子图边: " + subgraph.edgeSet());
    }

    /**
     * 获取从多个源点出发、在指定深度内的子图
     * @param graph      原图
     * @param sources    起始节点集合
     * @param maxDepth   最大深度
     * @return 子图（仅包含深度范围内的节点和边）
     */
    public static <V, E> Graph<V, E> getSubgraphWithinDepth(
            Graph<V, E> graph,
            Set<V> sources,
            int maxDepth
    ) {
        // 校验输入
        if (graph == null || sources == null || sources.isEmpty()) {
            throw new IllegalArgumentException("图和源点不能为空");
        }
        // 记录节点深度
        Map<V, Integer> nodeDepths = new HashMap<>();
        // 待遍历的节点队列（按深度递增顺序处理）
        Queue<V> queue = new LinkedList<>();
        // 结果节点集合
        Set<V> nodesWithinDepth = new HashSet<>();
        // 初始化所有源点
        for (V source : sources) {
            if (!graph.containsVertex(source)) {
                throw new IllegalArgumentException("图中不包含源点: " + source);
            }
            nodeDepths.put(source, 0);
            queue.add(source);
            nodesWithinDepth.add(source);
        }
        // 开始遍历
        while (!queue.isEmpty()) {
            V currentNode = queue.poll();
            int currentDepth = nodeDepths.get(currentNode);
            // 如果当前深度已超限，跳过邻居
            if (currentDepth >= maxDepth) {
                continue;
            }
            // 遍历所有邻居
            for (E edge : graph.outgoingEdgesOf(currentNode)) {
                V neighbor = graph.getEdgeTarget(edge);
                // 如果邻居未被访问过，记录其深度并加入队列
                if (!nodeDepths.containsKey(neighbor)) {
                    nodeDepths.put(neighbor, currentDepth + 1);
                    nodesWithinDepth.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        // 构造子图
        return new AsSubgraph<>(graph, nodesWithinDepth);
    }

}

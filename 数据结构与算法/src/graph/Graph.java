package graph;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numOfEdges; //表示边的数目
    //定义数组boolean[]，记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试图是否创建OK
        int n = 5; //结点的个数
        String[] vertexs = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        //显示邻接矩阵
        graph.showGraph();

        //测试dfs
//        System.out.println("深度遍历");
//        graph.dfs();
        System.out.println("广度遍历");
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    /**
     *
     * @param v1 当前结点下标
     * @param v2 前一个邻接结点下标
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j =v2 +1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点，输出
        System.out.print(getValueByIndex(i)+"->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1) { //说明有
            if (!isVisited[w]) {
                dfs(isVisited,w);
            }
            else { //如果w结点已经被访问过
                w = getNextNeighbor(i, w);
            }
        }
    }

    //对dfs 进行一个重载 遍历我们所有的结点，并进行dfs
    public void dfs() {
        //遍历所有的结点，进行dfs
        for (int i = 0; i < getNumOgVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }

    //对一个结点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u; //表示队列的头节点对应的下标
        int w; //邻接结点的下标
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标w
            w = getFirstNeighbor(u);

            while (w != -1) { //找到了
                //是否访问过
                if (! isVisited[w]) {
                    System.out.print(getValueByIndex(w)+"->");
                    //标记为已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //若访问过，找u的继w的下一个邻接结点
                w = getNextNeighbor(u,w); //体现出广度优先
            }

        }
    }

    //遍历所有的结点，都进行BFS
    public void bfs() {
        for (int i = 0; i < getNumOgVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }

    //图中常用的方法：
    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
    //返回顶点的个数
    public int getNumOgVertex() {
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回顶点i(下标)对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边
    /**
     *
     * @param v1 表示第一个点的下标，即是第几个顶点
     * @param v2 第二个顶点对应的下标
     * @param weight 表示是否相连 0/1
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}

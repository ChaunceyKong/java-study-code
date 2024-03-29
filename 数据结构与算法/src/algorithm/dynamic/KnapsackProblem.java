package algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; //物品重量
        int[] val = {1500, 3000, 2000}; //物品价值
        int m = 4; //背包容量
        int n = val.length; //物品个数

        //为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[n+1][m+1];


        //创建二维数组
        //v[i][j] 表示i个物品装入容量为j的背包最大价值
        int[][] v = new int[n+1][m+1];

        //初始化第一行，第一列
        for (int i=0; i<v.length; i++) {
            v[i][0]=0; //第一列为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0; //第一行为0
        }

        //动态规划处理
        for (int i = 1; i < v.length; i++) { //不处理第一行
            for (int j = 1; j < v[0].length; j++) { //不处理第一列
                //套用公式
                if (w[i-1]>j) { //因为程序i从1开始，原公式i修改i-1
                    v[i][j]=v[i-1][j];
                }
                else {
                    //说明：因为i从1开始，公式需要调整
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放背包的情况，不能简单的使用Math.max，需要使用if-else
                    if (v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]) {
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        //把当前情况记录
                        path[i][j]=1;
                    }
                    else {
                        v[i][j] = v[i-1][j];
                    }
                }

            }
        }
        //输出结果
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        //输出放入的哪些商品
        int i = path.length-1; //行的最大下标
        int j = path[0].length-1; //列的最大下标
        while (i>0 && j>0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n",i);
                j-=w[i-1];
            }
            i--;
        }
    }
}

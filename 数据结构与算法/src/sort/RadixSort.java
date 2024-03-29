package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args){
        int[] arr=new int[] {53,3,542,748,14,214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序
    public static void radixSort(int[] arr){
        //1.得到数组中最大的数的位数
        int max=arr[0];
        for (int i=1; i<arr.length; i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength=(max+"").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候数据溢出，则每个一维数组（桶）大小定义为arr.length
        //3.明确，基数排序就是使用空间换时间的经典算法
        int[][] bucket=new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入数据的个数
        //可以这样理解
        //比如：bucketElementCounts[0]，记录的就是bucket[0]桶的放入数据个数
        int[] bucketElementCounts=new int[10];

        for (int i=0, n=1;i<maxLength;i++, n*=10){
            //针对每个元素对应位进行排序，第一次是个位、第二次是十位....
            for (int j=0;j<arr.length;j++){
                //取出每个元素的各位的值
                int digitOfElement=arr[j]/n%10;
                //放入到对应桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index=0;
            //遍历每一桶，并将桶中的数据放入到原来数组
            for(int k=0;k<bucketElementCounts.length;k++){
                //如果桶中有数据，我们才放入到原数组
                if(bucketElementCounts[k]!=0){
                    //循环该桶，即第k个桶，放入
                    for(int l=0;l<bucketElementCounts[k];l++){
                        //取出元素放入到arr
                        arr[index++]=bucket[k][l];
                    }
                }
                //第l轮处理后，需要将每个bucketElementCounts[k]=0!!!
                bucketElementCounts[k]=0;
            }
        }
    }
}

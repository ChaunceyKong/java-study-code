package queue;

import java.util.Scanner;

public class ArrayQueueDemo{
    public static void main(String[] args){
        //测试
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in); //接收
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):取数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0); //接收一个字符

            switch(key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取数据
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头数据
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

//使用数组模拟队列
class ArrayQueue{
    private int maxSize; //表示队列最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //存放数的队列

    //创建队列构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部数据前一个位置
        rear = -1; // 指向队列尾
    }

    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，无法加入数据");
            return;
        }
        rear++; //让rear后移
        arr[rear]=n;
    }

    //数据出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        front++; //front后移
        return arr[front];
    }


    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = 0;i<arr.length; i++){
            System.out.printf("arr[%d]=%d",i,arr[i]);
        }
    }

    //显示队列的头，注意不是取出数据
    public int headQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }

        return arr[front+1];
    }
}

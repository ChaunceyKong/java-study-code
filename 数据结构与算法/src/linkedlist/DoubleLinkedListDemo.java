package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args){
        //测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4=new HeroNode2(4,"林冲","豹子头");
        //创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //添加节点
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode=new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后链表情况");
        doubleLinkedList.list();

    }
}


//创建一个双向链表
class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 gethead(){
        return head;
    }

    //遍历双向链表的方法
    //通过遍历显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将next后移，否则死循环
            temp = temp.next;
        }

    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此需要一个辅助变量
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向了链表最后
        //构成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点的内容，和单向链表一样
    public void update(HeroNode2 newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag=false; //表示是否找到该节点
        while(true){
            if(temp == null){
                break; //链表已经遍历结束
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag，判断是否找到要修改的节点
        if (flag){
            temp.name= newHeroNode.name;
            temp.nickname= newHeroNode.nickname;
        }
        else{ //没有找到
            System.out.printf("没有找到编号 %d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    //从双向链表中删除一个节点
    //说明
    //1.对于双向链表，我们可以直接找到要删除的节点，而非前一个节点
    //2.找到后自我删除即可
    public void del(int no){
        //判断当前链表是否非空
        if (head.next == null){
            System.out.println("链表为空，无法删除");
        }

        HeroNode2 temp = head.next; //赋值变量
        boolean flag = false; //标志是否找到待删除的节点
        while(true){
            if(temp == null){ //已经到链表的最后节点
                break;
            }
            if(temp.no == no){
                //找到要删除的节点
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if(flag){
            //可以删除
            temp.pre.next = temp.next;
            //这里代码有问题
            //如果是最后一个节点则不需要执行下面这句话，否则出现空指针异常
            //temp.next.pre ===> null.pre
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }
        else{
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }

}


//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示节点，重写toString方法
    public String toString() {
        return "tree.HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}

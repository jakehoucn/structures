package linked.singlelined;

import java.sql.SQLOutput;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1,"宋江","及时雨");
        HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode h3 = new HeroNode(3,"吴用","智多星");
        HeroNode h4 = new HeroNode(4,"林冲","豹子头");
        HeroNode h5 = new HeroNode(5,"鲁智深","花和尚");
        HeroNode h6 = new HeroNode(6,"张无忌","明教教主");
        HeroNode h7 = new HeroNode(7,"虚竹","灵鹫宫宫主");
        HeroNode h8 = new HeroNode(8,"令狐冲","独孤九剑");

        SingleLinkedList list = new SingleLinkedList();

        list.addHeroAsOrder(h2);
        list.addHeroAsOrder(h4);
        list.addHeroAsOrder(h1);
        list.addHeroAsOrder(h3);

        SingleLinkedList small = new SingleLinkedList();
        small.addByOrder(h5);
        small.addByOrder(h6);
        small.addByOrder(h7);
        small.addByOrder(h8);

        SingleLinkedList result = SingleLinkedList.connectedByOrder(list, small);
        result.listHero();

    }
}

class SingleLinkedList{
    HeroNode head = new HeroNode(0,"","");

    //遇到问题，参数的值传递有问题
    public static SingleLinkedList connectedByOrder(SingleLinkedList l1,SingleLinkedList l2){
        SingleLinkedList t3 = new SingleLinkedList();
        HeroNode t1 = t3.head;
        HeroNode t2 = null;
        if (l1.head.next == null){
            return l2;
        }
        if (l2.head.next == null){
            return l1;
        }

        while (l1.head.next!=null && l2.head.next!=null){
            if(l1.head.next.no > l2.head.next.no){
                t1.next = l2.head.next;
                l2.head.next = l2.head.next.next;
            }else{
                t1.next = l1.head.next;
                l1.head.next = l1.head.next.next;
            }

            if (l1.head.next == null) {
                t1.next=l2.head.next;
            }else if(l2.head.next == null) {
                t1.next = l1.head.next;
            }

            t1 = t1.next;
        }
        return t3;
    }
    //链表的复制
    public void copyList(SingleLinkedList sl){
        if (this.head.next == null) {
            System.out.println("第一个链表为空，无法复制");
            return;
        }
        HeroNode tempOld = this.head.next;
        HeroNode tempNew = sl.head;

        while (tempOld!=null){
            tempNew.next = new HeroNode(tempOld.no,tempOld.name,tempOld.nickname);
            tempNew = tempNew.next;
            tempOld = tempOld.next;
        }
    }

    public void connectLinkedList(SingleLinkedList b){
        if (this.head.next == null) {
            System.out.println("第一个链表为空");
            return;
        }
        if (b.head.next == null){
            System.out.println("第二个链表为空");
            return;
        }
        HeroNode temp = this.head;
        while (true){
            if (temp.next == null) {
                temp.next = b.head.next;
                break;
            }
            temp = temp.next;
        }
    }

    //百度面试题，将链表从头到位打印
    public  void reversePrint(){
        Stack<HeroNode> sc = new Stack<>();
        HeroNode temp = head;
        while (temp.next != null){
            sc.push(temp.next);
            temp = temp.next;
        }
        while (sc.size()>0){
            System.out.println(sc.pop());
        }
    }

    //百度面试题，将链表从尾到头打印
    public void printReverse(){
        SingleLinkedList h = this;
        if (h.head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        HeroNode cur = h.head;
        HeroNode follow = null;
        while (h.head.next !=null){
            if (cur.next != null){
                follow = cur;
                cur = cur.next;
            }else{
                System.out.println(cur);
                follow.next = null;
                cur = h.head;
            }
        }
    }

    //腾讯面试题，将链表反转
    public void reverseList(){
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode next  = null;
        while (cur!=null){
            next = cur.next;
            cur.next=reverseHead.next;
            reverseHead.next = cur;
            cur=next;
        }
        head.next = reverseHead.next;
    }


    //删除元素：个人，按照角标来删除
    public void delete(int index){
        if (index < 0||index > length()){
            System.out.println("没有找到指定的英雄，删除失败");
        }else{
            HeroNode temp = head;
            for (int i = 1 ; i< index; i++){
                temp = temp.next;
            }
            temp.next= temp.next.next;
        }
    }

    //网课，按照编号删除，都可以
    public void del(int no){
        if (head.next == null) {
            System.out.println("山上英雄都被你撵跑完了！还要删除！");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null) {
                break;// 抵达链表尾部
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.next = temp.next.next;//没有完成删除语句
        }else{
            System.out.println("这位英雄没在山上，他现在在哪？");
        }

    }

    //修改元素:个人，没有添加链表为空的情况
    public void updateHero(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
           if(temp.next == null){
                System.out.printf("修改失败：没有%s的信息\n",heroNode.nickname);
                break;
            } else if (temp.next.no == heroNode.no ){
                heroNode.next = temp.next.next;
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    //网课修改：使用标识符，考虑了链表为空的情况
    public void update(HeroNode heroNode){
        if (head.next == null) {
            System.out.println("当前英雄榜没人");
            return;
        }
        boolean flag = false;
        HeroNode temp = head.next;
        while (true){
            if (temp == null) {
                break;//已到达链表结尾
            }else if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else{
            System.out.println("山上还没有这个英雄，去叫他来吧！");
        }
    }

    //添加元素
    private void addHero(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                temp.next = heroNode;
                break;
            }
            else
                temp = temp.next;
        }
    }

    //个人思路，使用了双指针
    public void addHeroAsOrder(HeroNode heroNode){
        HeroNode temp = head.next;//前指针，找到no比待插入no大的位置
        HeroNode standby = head;//后指针，比前指针落后一个位置，一旦前指针满足停止条件，则开始添加元素
        while (true){
            if (temp == null){
                standby.next = heroNode;
                break;
            }else{
                if (temp.no > heroNode.no){
                    standby.next = heroNode;
                    heroNode.next=temp;
                    break;
                }else if(temp.no == heroNode.no){
                    System.out.printf("山上已经有英雄%s了，再来一个，" +
                            "说明一定有奸细\n",heroNode.nickname);
                    break;
                }else{
                    temp = temp.next;
                    standby = standby.next;
                }
            }
        }
    }

    //网课思路，只用了单指针，当单指针的next域的no大于待插入no，即可停止，进行交换
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null) {
                break;
            }else if (temp.next.no > heroNode.no){//找到指定位置
                break;
            }else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            System.out.printf("英雄添加失败：已有%s\n", heroNode.nickname);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //统计长度
    public int length(){
        int count = 0;
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                return count;
            }else{
                count++;
                temp = temp.next;
            }
        }
    }

    //遍历链表显示
    public void listHero(){
        HeroNode temp = head;
        if (temp.next == null) {
            System.out.println("当前没有英雄上山");
        }
        while (true){
            if (temp.next == null)
                break;
            System.out.println(temp.next);
            temp=temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "英雄榜{" +
                "英雄编号=" + no +
                ", 姓名='" + name + '\'' +
                ", 外号='" + nickname + '\'' +
                '}';
    }
}

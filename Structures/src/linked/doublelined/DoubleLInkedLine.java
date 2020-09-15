package linked.doublelined;

public class DoubleLInkedLine {

    FamilyNode head = new FamilyNode(0, "", "");

    public void update(FamilyNode familyNode){
        FamilyNode temp = head.next;
        if (temp == null) {
            System.out.println("当前没有家庭成员，无法修改信息");
            return;
        }
        boolean flag =false;
        while (temp != null){
            if (temp.no == familyNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = familyNode.name;
            temp.call = familyNode.call;
        }else{
            System.out.println("没有找到当前成员，无法修改数据哦");
        }
    }

    public void del(int no){
        FamilyNode temp = head.next;
        boolean flag = false;
        if (temp == null) {
            System.out.println("当前没有家庭成员，无法进行删除操作哦");
            return;
        }

        while (temp!=null){
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("删除失败，目前列表没有找到该家庭成员");
        }
    }

    public void addAsOrder(FamilyNode familyNode) {
        FamilyNode temp = head;
        while (true) {
            if (temp.next==null){//到尽头了
                temp.next = familyNode;
                familyNode.pre = temp;
                break;
            } else if (temp.next.no > familyNode.no) {
                    temp.next.pre = familyNode;
                    familyNode.next = temp.next;
                    temp.next = familyNode;
                    familyNode.pre = temp;
                    break;
            } else {
                temp= temp.next;
            }
        }
    }

    public FamilyNode createFamilyMember(int no, String name, String call) {
        return new FamilyNode(no, name, call);
    }

    public void listAll(){
        FamilyNode temp = head;
        if (temp.next == null) {
            System.out.println("当前还没有家庭成员哦，无法显示");
        }
        while (temp.next!=null){
            System.out.println(temp.next);
            temp=temp.next;
        }
    }

    class FamilyNode {
        int no;
        private String name;
        private String call;
        private FamilyNode next;
        private FamilyNode pre;

        public FamilyNode(int no, String name, String call) {
            this.no = no;
            this.name = name;
            this.call = call;
        }

        @Override
        public String toString() {
            return "FamilyNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", call='" + call + '\'' +
                    '}';
        }
    }
}

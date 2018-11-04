package www.nice.java;

//双向链表


interface ILink{
    //增加节点
    boolean add(Object data);
    //判断指定内容节点在链表中是否存在
    int contains(Object data);
    //删除指定内容节点
    boolean remove(Object data);
    //根据指定下标修改节点内容
    Object set(int index,Object newData);
    //根据指定下标返回节点内容
    Object get(int index);
    //链表清空
    void clear();
    //将链表转为数组
    Object[] toArray();
    //链表长度
    int size();
    //遍历链表
    void printLink();
}

class ILinkImpl implements ILink{
    private Node first;
    private  Node last;
    private int size;
    //-------------------------------------
    private class Node{
        private Node prev;
        private Object data;
        private Node next;

        public Node(Node prev, Object data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    //-------------------------------------

    @Override
    public boolean add(Object data) {
        Node tmp = this.last;
        Node newNode = new Node(tmp,data,null);
        this.last = newNode;
        if(this.first == null){
            this.first = newNode;
        }else{
            tmp.next = newNode;
        }
        this.size ++;
        return true;
    }

    @Override
    public int contains(Object data) {
        if(data == null){
            int i = 0;
            for(Node tmp = this.first; tmp != null; tmp = tmp.next){
                if(tmp.data == null){
                    return i;
                }
                i++;
            }
        }else{
            int i = 0;
            for(Node tmp = this.first; tmp != null; tmp = tmp.next){
                if(tmp.data.equals(data)){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object data) {
        if(data == null){
            for(Node tmp = this.first; tmp != null; tmp = tmp.next){
                if(tmp.data == null){
                    unlink(tmp);
                    return true;
                }
            }
        }else{
            for(Node tmp = this.first; tmp != null; tmp = tmp.next){
                if(data.equals(tmp.data)){
                    unlink(tmp);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object set(int index, Object newData) {
        if(!isLinkIndex(index)){
            return null;
        }
        Node node = node(index);
        Object elementData = node.data;
        node.data = newData;
        return elementData;
    }

    @Override
    public Object get(int index) {
        if(!isLinkIndex(index)){
            return null;
        }
        return node(index).data;
    }

    @Override
    public void clear() {
        for(Node tmp = this.first; tmp != null;){
            tmp.data = null;
            Node node = tmp.next;
            tmp.prev = tmp.next = null;
            tmp = node;
            this.size --;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for(Node tmp = this.first; tmp != null; tmp = tmp.next){
            result[i++] = tmp.data;
        }
        return result;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printLink() {
        Object[] data = this.toArray();
        for(Object tmp:data){
            System.out.println(tmp);
        }
    }

    //判断是否为有效下标
    private boolean isLinkIndex(int n){
        return (n>=0 && n<size);
    }

    //根据下标查找节点
    private Node node(int index){
        if(index < (size>>1)){
            Node tmp = this.first;
            for(int i = 0; i < index; i++){
                tmp = tmp.next;
            }
            return tmp;
        }else{
            Node tmp = this.last;
            for(int i = size-1; i > index; i--){
                tmp = tmp.prev;
            }
                return tmp;
        }
    }

    //删除操作
    private Object unlink(Node node){
        Node prev = node.prev;
        Node next = node.next;
        Object data = node.data;
        if(prev == null){
            this.first = next;
        }else{
            prev.next = next;
            node.prev = null;
        }
        if(next == null){
            this.last = prev;
        }else{
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        this.size --;
        return data;
    }

}

public class Test {
    public static void main(String[] args) {
        ILink link = new ILinkImpl();
        link.add("火车头");
        link.add("第一节车厢");
        link.add("第二节车厢");
        link.add("火车尾");
        link.printLink();
        System.out.println(link.size());
        System.out.println("*************************");
        System.out.println(link.contains("火车尾"));
        System.out.println(link.get(3));
        System.out.println("*************************");
        link.set(0,"head");
        link.printLink();
        System.out.println("*************************");
        link.remove("head");
        link.printLink();
    }
}

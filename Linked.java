
//单链表
interface ILink{
    /**
     * 插入数据
     * @param data
     * @return
     */
    boolean add(Object data);

    /**
     * 判断指定内容节点在链表中是否存在
     * @param data
     * @return 返回该节点下标
     */
    int contains(Object data);

    /**
     * 删除指定节点
     * @param data
     * @return
     */
    boolean remove(Object data);

    /**
     * 根据指定下标修改节点的内容
     * @param index
     * @param newData
     * @return 返回原来的节点内容
     */
    Object set(int index,Object newData);

    /**
     * 根据指定下标返回节点内容
     * @param index
     * @return
     */
    Object get(int index);

    /**
     * 清空链表
     */
    void clear();

    /**
     * 链表转化为数组
     * @return
     */
    Object[] toArray();

    /**
     * 链表长度
     * @return
     */
    int size();

    /**
     * 遍历链表
     */
    void printLink();
}

//--------------------------------------------------
 class Node{
     Object data;
     Node next;

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }
}
//------------------------------------------------------


class IlinkImpl implements ILink{
    private Node head;
    private Node tail;
    int size = 0;

    @Override
    public boolean add(Object data) {
/*        //尾插
        Node tmp = this.tail;
        Node node = new Node(data,null);
        this.tail = node;
        if(this.head == null){
            this.head = node;
        }else {
            tmp.next = node;
        }
        this.size++;
        return true;*/
        //头插
        Node tmp = this.head;
        Node node = new Node(data,null);
        this.head = node;
        if(this.tail == null){
            this.tail = node;
        }else {
            node.next = tmp;
        }
        this.size++;
        return true;
    }

    @Override
    public int contains(Object data) {
        if(data == null){
            int i = 0;
            for(Node tmp = this.head; tmp != null; tmp = tmp.next){
                if(null == tmp.data){
                    return i;
                }
                i++;
            }
        }else{
            int i = 0;
            for(Node tmp = this.head; tmp != null; tmp = tmp.next){
                if(data.equals(tmp.data)){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object data) {
        Node node = null;
        if(data == null){
            for(Node tmp = this.head; tmp != null; ){
                if(tmp.data == null){
                    unlink(node,tmp);
                    return true;
                }
                node = tmp;
                tmp = tmp.next;
            }
        }else{
            for(Node tmp = this.head; tmp != null; ){
                if(tmp.data.equals(data)){
                    unlink(node,tmp);
                    return true;
                }
                node = tmp;
                tmp = tmp.next;
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
        Object elementdata = node.data;
        node.data = newData;
        return elementdata;
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
        for(Node tmp = this.head; tmp != null;){
            tmp.data = null;
            Node res = tmp.next;
            tmp.next = null;
            tmp = res;
            this.size--;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int k = 0;
        for(Node tmp = this.head; tmp!=null; tmp=tmp.next){
            arr[k++] = tmp.data;
        }
        return arr;
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

    public boolean isLinkIndex(int index){
        return (index>=0 && index <size);
    }

    //根据下标查找节点的内容
    public Node node(int index){
        Node tmp = this.head;
        for(int i = 0; i < index; i++){
            tmp = tmp.next;
        }
        return tmp;
    }

    //删除节点
    public Object unlink(Node node1, Node node2){
        Object data = node2.data;
        Node next = node2.next;
        //若为头结点
        if(node1==null){
            this.head = next;
            node2.next = null;
        }
        //若为尾节点
        else if(node2.next == null){
            this.tail = node1;
            node1.next = null;
        }else{
            node1.next = node2.next;
            node2.next = null;
        }
        node2.data = null;
        this.size--;
        return data;
    }

}

/*public class Linked {
    public static void main(String[] args) {

    }
}*/


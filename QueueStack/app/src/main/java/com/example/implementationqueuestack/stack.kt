import java.util.*

class MyStack() {

    /** Initialize your data structure here. */
    var q1: Queue<Int> = LinkedList<Int>()
    var q2: Queue<Int> = LinkedList<Int>()

    /** Push element x onto stack. */
    fun push(x: Int) {

        while (!q1.isEmpty()) {
            val temp = q1.poll()
            q2.add(temp)
        }

        q1.add(x)

        while (!q2.isEmpty()) {
            val temp = q2.poll()
            q1.add(temp)
        }
//        q2.forEach{
//            q1.add(it)
//            q2.poll()
//        }
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        if (q1.isEmpty())
            return -1;
        return q1.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        if (q1.isEmpty())
            return -1;
        return q1.peek()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return q1.isEmpty()
    }

}

fun main() {
    var obj = MyStack()
    obj.push(5)
    obj.push(16)
    obj.push(1)
    obj.push(7)
    var param_2 = obj.pop()
    var param_3 = obj.top()
    var param_4 = obj.empty()
    println(param_2)
    println(param_3)
    println(param_4)
}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */
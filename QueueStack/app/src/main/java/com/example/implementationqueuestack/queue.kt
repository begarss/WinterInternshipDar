import java.util.*

class MyQueue() {

    /** Initialize your data structure here. */
    var s1 = Stack<Int>()
    var s2 = Stack<Int>()


    /** Push element x to the back of queue. */
    fun push(x: Int) {
        s1.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        var x = 0;
        if (s1.isEmpty() && s2.isEmpty()) {
            println("Queue is empty")
        }

        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                x = s1.pop()
                s2.push(x)
            }
        }

        x = s2.pop()
        return x;
    }

    /** Get the front element. */
    fun peek(): Int {
        return s2.peek()
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return s1.isEmpty() && s2.isEmpty()
    }

}

fun main() {
    var obj = MyQueue()
    obj.push(5)
    obj.push(16)
    obj.push(1)
    obj.push(7)
    var param_2 = obj.pop()
    var param_3 = obj.peek()
    var param_4 = obj.empty()
    println(param_2)
    println(param_3)
    println(param_4)
    println(
        obj.peek()
    )
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
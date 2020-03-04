package com.wzy.train.queue;

import java.util.Stack;

/**
 * @Author: wzy
 * @Description:
 * @Date: 2020/2/21 13:12
 */
public class MyQueue {

    private Stack<Integer> in;

    private Stack<Integer> out;

    private int front;

    /** Initialize your data structure here. */
    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(in.isEmpty()){
            front = x;
        }
        in.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!out.isEmpty()) {
            return out.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

}

package com.wzy.train.queue;

/**
 * @Author: wzy
 * @Description: MyCircularQueue(k): Constructor, set the queue length to k 。
 * Front: Gets elements from the head of the team. If the queue is empty, return -1 。
 * Rear: Gets the tail element. If the queue is empty, return -1 。
 * enQueue(value): Insert an element into the circular queue. If inserted successfully, true is returned。
 * deQueue(): Removes an element from the circular queue. True is returned if the deletion is successful。
 * isEmpty(): Check if the circular queue is empty。
 * isFull(): Check that the circular queue is full。
 * @Date: 2019/12/12 15:30
 */
public class MyCircularQueue {

    private int[] data;
    private int head, tail, size;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[tail];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return head == -1;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}

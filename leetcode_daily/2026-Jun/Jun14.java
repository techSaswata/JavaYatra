class Solution {
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        
        int maxSum = 0;
        while (prev != null) {
            int sum = head.val + prev.val;
            if (sum > maxSum) {
                maxSum = sum;
            }
            head = head.next;
            prev = prev.next;
        }
        
        return maxSum;
    }
}
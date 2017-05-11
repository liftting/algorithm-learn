# coding=utf-8
import sys

class SelfListNode(object):
    def __init__(self,value,next_node):
        self.node_value = value
        self.next = next_node
        
    def self_print(head):
        while head != None:
            print head.node_value
            head = head.next
        

def self_revert(head):
    result = None; # result是用来保存最后的节点头的
    # head就一直保存要翻转的节点
    while head!=None:
        temp = head.next # 先拿到下一个节点，保存起来，这个就是下一个要翻转的节点
        head.next = result #
        result = head # result是上个节点，
        head = temp # head就是下一个了
    return result
    
# 实现从数组中构造链表出来
def self_build(data_array):
    if data_array == None or len(data_array) <=0:
        return None
    
    last = None
    # 逆序遍历
    for x in data_array[::-1]:
        current = SelfListNode(x,last)
        last = current
    
    return current
    
if __name__ == '__main__':
    list_head = self_build([1,2,3,4,5,6,7])
    SelfListNode.self_print(list_head)
    SelfListNode.self_print(self_revert(list_head))
    
    
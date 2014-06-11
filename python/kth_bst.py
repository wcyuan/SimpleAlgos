#!/usr/bin/env python

from __future__ import absolute_import, division, with_statement

def main():
    """
    >>> bst = None
    >>> kth(1, bst)
    >>> kth(100, bst)
    >>> bst = BST(4)
    >>> kth(1, bst)
    4
    >>> kth(2, bst)
    >>> kth(0, bst)
    >>> kth(-1, bst)
    >>> bst = BST(4, BST(3), BST(5))
    >>> kth(1, bst)
    3
    >>> kth(2, bst)
    4
    >>> kth(3, bst)
    5
    >>> bst = BST(4, BST(3, BST(2)), BST(5))
    >>> kth(1, bst)
    2
    >>> kth(2, bst)
    3
    >>> kth(3, bst)
    4
    >>> kth(4, bst)
    5
    """
    kth(1, BST(1))

class BST(object):
    def __init__(self, value, left=None, right=None):
        self.value = value
        self.left = left
        self.right = right

def kth(k, bst):
    return kth_helper(k, bst)[1]

def kth_helper(k, bst):
    """
    Returns a tuple (num_values, kth_value)
    """
    if bst is None:
        return (0, None)
    if k < 1:
        return (0, None)
    (num_left, value) = kth_helper(k, bst.left)
    if num_left == k:
        return (num_left, value)
    if num_left == k-1:
        return (num_left + 1, bst.value)
    (num_right, value) = kth_helper(k - num_left - 1, bst.right)
    return (num_left + num_right + 1, value)

if __name__ == "__main__":
    main()

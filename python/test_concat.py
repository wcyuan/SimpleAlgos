#!/usr/bin/env python
"""
You have an array of english words {cat, then, hen, end, dog}. Can you make out if the given sentence is a concatenation of only words from the array?
Cathen valid
thend  not valid
cathenend  valid
"""

import optparse

def main():
    parser = optparse.OptionParser()
    _, args = parser.parse_args()
    sentence = args.pop()
    print is_concat(args, sentence)

def is_concat(words, sentence):
    """
    >>> words = ['cat', 'then', 'hen', 'end', 'dog']
    >>> is_concat(words, "Cathen")
    True
    >>> is_concat(words, "thend")
    False
    >>> is_concat(words, "cathenend")
    True
    """
    if len(sentence) == 0:
        return True
    for word in words:
        if (sentence.lower().startswith(word.lower()) and
            is_concat(words, sentence[len(word):])):
            return True
    return False

if __name__ == "__main__":
    main()


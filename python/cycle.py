#!/usr/bin/env python
#
# Given a fraction how would you find length of the first cycle of
# decimals that repeats when repeating the division (e.g. 1/7).
#

import sys
def main():
    """
    >>> find_cycle(1, 7)
    6
    >>> find_cycle(1, 3)
    1
    >>> find_cycle(1, 6)
    1
    >>> find_cycle(2, 5)
    1
    >>> find_cycle(0, 2)
    1
    >>> find_cycle(3, 7)
    6
    >>> find_cycle(345, 999)
    3
    >>> find_cycle(121, 999)
    3
    >>> find_cycle(111, 999)
    1
    >>> find_cycle(2525, 9999)
    2
    >>> find_cycle(2345, 9999)
    4
    """
    print find_cycle(int(sys.argv[1]), int(sys.argv[2]))

def find_cycle(num, den):
    return find_cycle_arr(num, den)

def find_cycle_hash(num, den):
    num = num % den
    seen = {}
    i = 0
    while num not in seen:
        seen[num] = i
        i += 1
        num = (num * 10) % den
    return i - seen[num]

# If I can't use a hash, I could get the same effect with an array of
# size den
def find_cycle_arr(num, den):
    num = num % den

    # Create a list of size den with all values initialized to 0.
    # This will map from the remainder to the digit at which we were
    # left with that remainder.
    seen = [0] * den

    i = 1
    while seen[num] == 0:
        seen[num] = i
        i += 1
        # Basically replicate long division.  num is the remainder that is
        # left at each step of the long division.
        num = (num * 10) % den
    return i - seen[num]

if __name__ == "__main__":
    main()

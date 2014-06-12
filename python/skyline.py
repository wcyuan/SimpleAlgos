#!/usr/bin/env python
"""
You have a skyline of buildings with the upper coordinates for each
building. You have to output the coordinates of the outline made by
all the buildings combined
"""

import sys
def main():
    """
    >>> skyline([])
    []
    >>> skyline([Building(Coord(2, 4), Coord(8, 4)), Building(Coord(3, 5), Coord(6, 5))])
    [Coord(2, 0), Coord(2, 4), Coord(3, 4), Coord(3, 5), Coord(6, 5), Coord(6, 4), Coord(8, 4), Coord(8, 0)]
    """
    if len(sys.argv) < 2:
        args = [2, 4, 8, 4, 3, 5, 6, 5]
    else:
        args = [int(x) for x in sys.argv[1:]]
    print skyline([Building(Coord(x, y), Coord(x2, y2))
                   for (x, y, x2, y2) in
                   zip(args[::4], args[1::4], args[2::4], args[3::4])])

class Coord(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y
    def __repr__(self):
        return "Coord({self.x}, {self.y})".format(self=self)

class Building(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
        assert(self.start.x > 0)
        assert(self.start.y > 0)
        assert(self.end.x > 0)
        assert(self.end.y > 0)
        assert(self.start.y == self.end.y)
    def __repr__(self):
        return "Building({self.start!r}, {self.end!r})".format(self=self)
    @property
    def startx(self):
        return self.start.x
    @property
    def endx(self):
        return self.end.x
    @property
    def height(self):
        return self.start.y

def skyline_hashes(buildings):
    retval = []
    starts = {}
    ends = {}
    for b in buildings:
        starts.setdefault(b.startx, set()).add(b)
        ends.setdefault(b.endx, set()).add(b)
    x_coords = sorted(set(starts) | set(ends))
    height = 0
    current_buildings = set()
    for x in x_coords:
        new_buildings = (current_buildings | starts.get(x, set())) - ends.get(x, set())
        if new_buildings:
            new_height = max(b.height for b in new_buildings)
        else:
            new_height = 0
        if new_height != height:
            retval.append(Coord(x, height))
            retval.append(Coord(x, new_height))
            height = new_height
        current_buildings = new_buildings
    return retval


# Here's a version that mostly avoids hashes (except for finding
# unique x_coordinates)
def skyline(buildings):
    retval = []
    x_coords = set()
    for b in buildings:
        x_coords.add(b.startx)
        x_coords.add(b.endx)
    height = 0
    nbuildings = len(buildings)
    current_buildings = [False for _ in buildings]
    new_buildings = [False for _ in buildings]
    for x in sorted(x_coords):
        new_height = 0
        for ii in xrange(nbuildings):
            if x == buildings[ii].startx:
                new_buildings[ii] = True
            elif x == buildings[ii].endx:
                new_buildings[ii] = False
            else:
                new_buildings[ii] = current_buildings[ii]
            if new_buildings[ii]:
                new_height = max(new_height, buildings[ii].height)
        if new_height != height:
            retval.append(Coord(x, height))
            retval.append(Coord(x, new_height))
            height = new_height
        # swap current_buildings and new_buildings
        tmp = current_buildings
        current_buildings = new_buildings
        new_buildings = tmp
    return retval

if __name__ == "__main__":
    main()

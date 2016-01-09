#!/usr/bin/python

# 2. Is the rate of new pages arrival different for different topics?

import sys
import os

for line in sys.stdin:
    
    from_url, to_url, classified_relevant = line.strip().split(' ')

    print('%s %s %s' % (from_url, to_url, classified_relevant))

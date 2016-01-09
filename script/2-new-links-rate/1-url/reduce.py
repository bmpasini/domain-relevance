#!/usr/bin/python

# 2. Is the rate of new pages arrival different for different topics?

import sys
import os

line = next(sys.stdin)

current_from_url, current_to_url, classified_relevant = line.strip().split(' ')
cnt_relevants = 0
cnt_outlinks = 1

if classified_relevant == '1':
    cnt_relevants = 1

for line in sys.stdin:
    
    from_url, to_url, classified_relevant = line.strip().split(' ')

    if current_from_url != from_url:
        relevancy = float(cnt_relevants) / float(cnt_outlinks)
        print('%s %s %s %s %s' % (current_from_url, current_to_url, cnt_relevants, cnt_outlinks, relevancy))
        cnt_relevants = 0
        cnt_outlinks = 0

    if classified_relevant == '1':
        cnt_relevants += 1

    cnt_outlinks += 1

    current_from_url = from_url
    current_to_url = to_url

#!/usr/bin/python

# 0. Find the relevancy of outlinks

import sys
import os

current_to_url = None
classified_relevant = None
from_urls = list()

print 'from_url', 'to_url', 'classified_relevant'

for line in sys.stdin:

    to_url, tag, value = line.strip().split(' ')

    if to_url != current_to_url:
        # if classified_relevant is not None:
        for from_url in from_urls:
            print('%s %s %s' % (from_url, current_to_url, classified_relevant))
        from_urls = list()
        classified_relevant = None

    if tag == 'outlink':
        from_urls.append(value)
    elif tag == 'pageData':
        classified_relevant = value

    current_to_url = to_url

for from_url in from_urls:
    print('%s %s %s' % (from_url, current_to_url, classified_relevant))
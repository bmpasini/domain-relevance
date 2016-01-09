#!/usr/bin/python

# 0. Find the relevancy of outlinks

import sys
import os

current_from_url = None
outlink_info = list()

print 'crawl_domain', 'crawl_date', 'url_domain', 'original_url', 'outlink_url', 'outlink_relevant'

for line in sys.stdin:

    try:
        from_url, tag, values = line.strip().split('\t')
    except ValueError:
        continue

    if from_url != current_from_url:
        for outlink_and_relevancy in outlink_info:
            if classified_relevant == '1': # find relevancy of relevant pages only, since we do not hava data on outlinks of irrelevant pages
                print('%s %s %s %s %s' % (domain, date, url_domain, current_from_url, outlink_and_relevancy))
        outlink_info = list()

    if tag == 'outlinks':
        outlink_info.append(values)
    elif tag == 'pageData':
        domain, date, url_domain, classified_relevant = values.strip().split(' ')

    current_from_url = from_url

for outlink_and_relevancy in outlink_info:
    if classified_relevant == '1':
        print('%s %s %s %s %s' % (domain, date, url_domain, current_from_url, outlink_and_relevancy))
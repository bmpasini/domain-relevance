#!/usr/bin/python

# 0. Find the relevancy of outlinks

import sys
import os

for line in sys.stdin:

    line = line.strip().split(' ')

    if len(line) == 3: # outlinks

        from_url, to_url, outlink_relevant = line

        if from_url != 'from_url' and to_url != 'to_url':
            print('%s\toutlinks\t%s %s' % (from_url, to_url, outlink_relevant))

    elif len(line) == 8: # pageData

        domain, date, fetch_time, url_domain, original_url, redirected_url, classified_relevant, content_hash = line

        if original_url != 'original_url' and classified_relevant != 'classified_relevant':
            print('%s\tpageData\t%s %s %s %s' % (original_url, domain, date, url_domain, classified_relevant))

#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import string
import os

TOTAL_DAYS = 2
last_domain = None
last_original_url = None
last_content_hash = None
times_changed = 0
total_days = 1

print 'domain', 'url_domain', 'original_url', 'times_changed', 'total_days', 'change_rate'

next(sys.stdin)

for line in sys.stdin:
    
    domain, url_domain, original_url, date, content_hash = line.strip().split(' ')

    if domain != last_domain:
        times_changed = 0
        total_days = 1
    elif original_url != last_original_url:
        if total_days == TOTAL_DAYS:
            change_rate = (times_changed / (total_days - 1))
            print('%s %s %s %s %s %s' % (domain, url_domain, original_url, times_changed, total_days, change_rate))
        times_changed = 0
        total_days = 1
    elif content_hash != last_content_hash:
        times_changed += 1
        total_days += 1
    else:
        total_days += 1

    last_domain = domain
    last_original_url = original_url
    last_content_hash = content_hash


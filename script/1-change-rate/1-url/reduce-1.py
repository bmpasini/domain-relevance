#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

print 'domain', 'url_domain', 'original_url', 'times_changed', 'days_changed', 'change_rate'

next(sys.stdin)
line = next(sys.stdin)

last_domain, last_url_domain, last_original_url, date, last_content_hash = line.strip().split(' ')
times_changed = 0
total_days = 1

def get_total_days(domain):
    if domain == "movies":
        return 23
    else:
        return 20

for line in sys.stdin:

    domain, url_domain, original_url, date, content_hash = line.strip().split(' ')

    if original_url != last_original_url:
        if total_days == get_total_days(last_domain):
            change_rate = (float(times_changed) / float(total_days - 1))
            print('%s %s %s %s %s %s' % (last_domain, last_url_domain, last_original_url, times_changed, total_days - 1, change_rate))
        times_changed = 0
        total_days = 1
    elif content_hash != last_content_hash:
        times_changed += 1
        total_days += 1
    else:
        total_days += 1

    if domain != last_domain:
        times_changed = 0
        total_days = 1

    last_domain = domain
    last_url_domain = url_domain
    last_original_url = original_url
    last_content_hash = content_hash


change_rate = (float(times_changed) / float(total_days - 1))
print('%s %s %s %s %s %s' % (domain, url_domain, original_url, times_changed, total_days, change_rate))
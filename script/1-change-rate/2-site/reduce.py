#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import string
import os

print 'domain', 'url_domain', 'times_changed_in_domain', 'day_changes_in_domain', 'change_rate'

next(sys.stdin)
line = next(sys.stdin)

last_domain, last_url_domain, times_changed_in_domain, day_changes_in_domain = line.strip().split(' ')
times_changed_in_domain = float(times_changed_in_domain)
day_changes_in_domain = float(day_changes_in_domain)

for line in sys.stdin:
    
    domain, url_domain, times_changed, total_days = line.strip().split(' ')

    try:
        times_changed = float(times_changed)
        total_days = float(total_days)
    except ValueError:
        continue

    if domain != last_domain:
        times_changed_in_domain = times_changed
        day_changes_in_domain = total_days
    elif url_domain != last_url_domain:
        change_rate = times_changed_in_domain / day_changes_in_domain
        print('%s %s %s %s %s' % (domain, url_domain, int(times_changed_in_domain), int(day_changes_in_domain), change_rate))
        times_changed_in_domain = times_changed
        day_changes_in_domain = total_days
    else:
        times_changed_in_domain += times_changed
        day_changes_in_domain += (total_days - 1)

    last_domain = domain
    last_url_domain = url_domain

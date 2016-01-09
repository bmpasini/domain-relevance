#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

print 'domain', 'url_domain', 'times_changed_in_site', 'day_changes_in_site', 'change_rate'

next(sys.stdin)
line = next(sys.stdin)

last_domain, last_url_domain, times_changed_in_site, day_changes_in_site = line.strip().split(' ')
times_changed_in_site = float(times_changed_in_site)
day_changes_in_site = float(day_changes_in_site)

for line in sys.stdin:
    
    domain, url_domain, times_changed, days_changed = line.strip().split(' ')

    try:
        times_changed = float(times_changed)
        days_changed = float(days_changed)
    except ValueError:
        continue

    if url_domain != last_url_domain:
        change_rate = times_changed_in_site / day_changes_in_site
        print('%s %s %s %s %s' % (last_domain, last_url_domain, int(times_changed_in_site), int(day_changes_in_site), change_rate))
        times_changed_in_site = times_changed
        day_changes_in_site = days_changed
    else:
        times_changed_in_site += times_changed
        day_changes_in_site += days_changed

    if domain != last_domain:
        times_changed_in_site = times_changed
        day_changes_in_site = days_changed

    last_domain = domain
    last_url_domain = url_domain

change_rate = times_changed_in_site / day_changes_in_site
print('%s %s %s %s %s' % (last_domain, last_url_domain, int(times_changed_in_site), int(day_changes_in_site), change_rate))


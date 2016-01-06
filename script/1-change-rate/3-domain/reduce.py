#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import string
import os

print 'domain', 'times_changed_in_domain', 'day_changes_in_domain', 'change_rate'

next(sys.stdin)
line = next(sys.stdin)

last_domain, times_changed_in_site, day_changes_in_site = line.strip().split(' ')
times_changed_in_domain = float(times_changed_in_site)
day_changes_in_domain = float(day_changes_in_site)

domain = last_domain

for line in sys.stdin:

    try:
        times_changed_in_site = float(times_changed_in_site)
        day_changes_in_site = float(day_changes_in_site)
    except ValueError:
        continue

    if domain != last_domain:
        change_rate = times_changed_in_domain / day_changes_in_domain
        print('%s %s %s %s' % (last_domain, int(times_changed_in_domain), int(day_changes_in_domain), change_rate))
        times_changed_in_domain = times_changed_in_site
        day_changes_in_domain = day_changes_in_site
    else:
        times_changed_in_domain += times_changed_in_site
        day_changes_in_domain += day_changes_in_site

    last_domain = domain

    domain, times_changed_in_site, day_changes_in_site = line.strip().split(' ')




times_changed_in_domain += float(times_changed_in_site)
day_changes_in_domain += float(day_changes_in_site)

change_rate = times_changed_in_domain / day_changes_in_domain
print('%s %s %s %s' % (domain, int(times_changed_in_domain), int(day_changes_in_domain), change_rate))
times_changed_in_domain = times_changed_in_site
day_changes_in_domain = day_changes_in_site



# NOW GO BACK AND FIX ALL BUGS!!!!!!!!!!
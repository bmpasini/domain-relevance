#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

print 'domain', 'url_domain', 'times_relevancy_changed_in_site', 'day_changes_in_site', 'stability'

next(sys.stdin)
line = next(sys.stdin)

last_domain, last_url_domain, times_relevancy_changed_in_site, day_changes_in_site = line.strip().split(' ')
times_relevancy_changed_in_site = float(times_relevancy_changed_in_site)
day_changes_in_site = float(day_changes_in_site)

for line in sys.stdin:
    
    domain, url_domain, times_relevancy_changed, days_changed = line.strip().split(' ')

    try:
        times_relevancy_changed = float(times_relevancy_changed)
        days_changed = float(days_changed)
    except ValueError:
        continue

    if url_domain != last_url_domain:
        stability = 1 - (float(times_relevancy_changed_in_site) / float(day_changes_in_site))
        # if stability < 1:
        print('%s %s %s %s %s' % (last_domain, last_url_domain, int(times_relevancy_changed_in_site), int(day_changes_in_site), stability))
        times_relevancy_changed_in_site = times_relevancy_changed
        day_changes_in_site = days_changed
    else:
        times_relevancy_changed_in_site += times_relevancy_changed
        day_changes_in_site += days_changed

    if domain != last_domain:
        times_relevancy_changed_in_site = times_relevancy_changed
        day_changes_in_site = days_changed

    last_domain = domain
    last_url_domain = url_domain

stability = 1 - (float(times_relevancy_changed_in_site) / float(day_changes_in_site))
print('%s %s %s %s %s' % (last_domain, last_url_domain, int(times_relevancy_changed_in_site), int(day_changes_in_site), stability))


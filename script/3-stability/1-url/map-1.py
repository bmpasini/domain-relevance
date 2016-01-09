#!/usr/bin/python

# 2.
# Stability: times_relevancy_didnt_change / days_passed
# Percentage of pages that remain relevant during all days

import sys
import os

for line in sys.stdin:
    
    domain, date, fetch_time, url_domain, original_url, redirected_url, classified_relevant, content_hash = line.strip().split(' ')

    print('%s %s %s %s %s' % (domain, url_domain, original_url, date, classified_relevant))

#!/usr/bin/python

# 2.
# Stability: times_relevancy_didnt_change / days_passed
# Percentage of pages that remain relevant during all days

import sys
import os

print 'domain', 'url_domain', 'original_url', 'days_negative', 'days_positive', 'times_relevancy_changed', 'days_passed', 'stability'

for line in sys.stdin:
    
    domain, stability, url_domain, original_url, days_negative, days_positive, times_relevancy_changed, days_passed = line.strip().split(' ')

    print('%s %s %s %s %s %s %s %s' % (domain, url_domain, original_url, days_negative, days_positive, times_relevancy_changed, days_passed, stability))
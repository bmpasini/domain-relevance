#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

for line in sys.stdin:
    
    domain, url_domain, original_url, days_negative, days_positive, times_relevancy_changed, day_changes, stability = line.strip().split(' ')

    print('%s %s %s %s' % (domain, url_domain, times_relevancy_changed, day_changes))

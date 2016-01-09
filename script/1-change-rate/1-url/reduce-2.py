#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

print 'domain', 'url_domain', 'original_url', 'times_changed', 'days_changed', 'change_rate'

for line in sys.stdin:
    
    domain, change_rate, url_domain, original_url, times_changed, days_changed = line.strip().split(' ')
    
    print('%s %s %s %s %s %s' % (domain, url_domain, original_url, times_changed, days_changed, change_rate))
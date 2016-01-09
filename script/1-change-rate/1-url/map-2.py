#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

next(sys.stdin)

for line in sys.stdin:
    
    domain, url_domain, original_url, times_changed, days_changed, change_rate = line.strip().split(' ')
    
    print('%s %s %s %s %s %s' % (domain, change_rate, url_domain, original_url, times_changed, days_changed))

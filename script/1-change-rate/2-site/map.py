#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import string
import os

for line in sys.stdin:
    
    domain, url_domain, original_url, times_changed, total_days, change_rate = line.strip().split(' ')

    print('%s %s %s %s' % (domain, url_domain, times_changed, total_days))

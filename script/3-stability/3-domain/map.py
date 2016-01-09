#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

for line in sys.stdin:
    
    domain, url_domain, times_relevancy_changed_in_site, day_changes_in_site, stability = line.strip().split(' ')

    print('%s %s %s' % (domain, times_relevancy_changed_in_site, day_changes_in_site))

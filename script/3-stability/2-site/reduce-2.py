#!/usr/bin/python

# 1. Do the web pages in different topics change at different rates?

import sys
import os

print 'domain', 'url_domain', 'times_relevancy_changed_in_site', 'day_changes_in_site', 'stability'

for line in sys.stdin:
    
    domain, stability, url_domain, times_relevancy_changed_in_site, day_changes_in_site = line.strip().split(' ')

    print('%s %s %s %s %s' % (domain, url_domain, times_relevancy_changed_in_site, day_changes_in_site, stability))
#!/usr/bin/python

# 2.
# Stability: times_relevancy_didnt_change / days_passed
# Percentage of pages that remain relevant during all days

import sys
import os

print 'domain', 'url_domain', 'original_url', 'days_negative', 'days_positive', 'times_relevancy_changed', 'days_passed', 'stability'

next(sys.stdin)
line = next(sys.stdin)

last_domain, last_url_domain, last_original_url, date, last_classified_relevant = line.strip().split(' ')
times_relevancy_changed = 0
total_days = 1
days_negative = 0
days_positive = 0

def get_total_days(domain):
    if domain == "movies":
        return 23
    else:
        return 20

for line in sys.stdin:

    domain, url_domain, original_url, date, classified_relevant = line.strip().split(' ')

    if last_classified_relevant == '1':
        days_positive += 1
    else:
        days_negative += 1

    if original_url != last_original_url:
        if total_days == get_total_days(last_domain):
            stability = 1 - (float(times_relevancy_changed) / float(total_days - 1))
            # if stability < 1:
            print('%s %s %s %s %s %s %s %s' % (last_domain, last_url_domain, last_original_url, days_negative, days_positive, times_relevancy_changed, total_days - 1, stability))
        times_relevancy_changed = 0
        total_days = 1
        days_negative = 0
        days_positive = 0
    elif classified_relevant != last_classified_relevant:
        times_relevancy_changed += 1
        total_days += 1
    else:
        total_days += 1

    if domain != last_domain:
        times_relevancy_changed = 0
        total_days = 1

    last_domain = domain
    last_url_domain = url_domain
    last_original_url = original_url
    last_classified_relevant = classified_relevant

stability = 1 - (float(times_relevancy_changed) / float(total_days - 1))
print('%s %s %s %s %s %s %s %s' % (last_domain, last_url_domain, last_original_url, days_negative, days_positive, times_relevancy_changed, total_days - 1, stability))
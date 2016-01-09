cat ../input-data/* | python map.py | sort
cat ../input-data/* | python map.py | sort | python reduce.py

cat ../1-url/ChangeRateUrl/part-00000 | python map.py | sort
cat ../1-url/ChangeRateUrl/part-00000 | python map.py | sort | python reduce.py

cat ../2-site/ChangeRateSite/part-00000 | python map.py | sort
cat ../2-site/ChangeRateSite/part-00000 | python map.py | sort | python reduce.py
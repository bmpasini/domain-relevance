rm -rf ChangeRateDomain/
chmod +x map.py reduce.py
hadoop jar /usr/local/Cellar/hadoop/2.6.0/libexec/share/hadoop/tools/lib/hadoop-streaming-2.6.0.jar \
-D mapreduce.job.reduces=1 \
-D stream.num.map.output.key.fields=3 \
-file map.py \
-mapper map.py \
-file reduce.py \
-reducer reduce.py \
-input file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/1-change-rate/2-site/ChangeRateSite/part-00000 \
-output file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/1-change-rate/3-domain/ChangeRateDomain
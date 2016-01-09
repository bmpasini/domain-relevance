rm -rf StabilitySite/
chmod +x map-1.py reduce-1.py
hadoop jar /usr/local/Cellar/hadoop/2.6.0/libexec/share/hadoop/tools/lib/hadoop-streaming-2.6.0.jar \
-D mapreduce.job.reduces=1 \
-D stream.num.map.output.key.fields=2 \
-file map-1.py \
-mapper map-1.py \
-file reduce-1.py \
-reducer reduce-1.py \
-input file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/3-stability/1-url/StabilityUrl/part-00000 \
-output file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/3-stability/2-site/StabilitySite
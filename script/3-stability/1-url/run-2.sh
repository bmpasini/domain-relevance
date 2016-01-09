rm -rf StabilityUrlSorted/
chmod +x map-2.py reduce-2.py
hadoop jar /usr/local/Cellar/hadoop/2.6.0/libexec/share/hadoop/tools/lib/hadoop-streaming-2.6.0.jar \
-D mapreduce.job.reduces=1 \
-D stream.num.map.output.key.fields=1 \
-file map-2.py \
-mapper map-2.py \
-file reduce-2.py \
-reducer reduce-2.py \
-input file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/3-stability/1-url/StabilityUrl/part-00000 \
-output file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/3-stability/1-url/StabilityUrlSorted
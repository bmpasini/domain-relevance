rm -rf OutlinksRelevance/
chmod +x map.py reduce.py
hadoop jar /usr/local/Cellar/hadoop/2.6.0/libexec/share/hadoop/tools/lib/hadoop-streaming-2.6.0.jar \
-D mapreduce.job.reduces=1 \
-D stream.num.map.output.key.fields=1 \
-file map.py \
-mapper map.py \
-file reduce.py \
-reducer reduce.py \
-input file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/0-outlinks-relevance/1-outlinks-relevance/input-data/outlinks-2015-12-16 \
-input file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/0-outlinks-relevance/1-outlinks-relevance/input-data/pageData-2015-12-16 \
-output file:///Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/domain-relevance/script/0-outlinks-relevance/1-outlinks-relevance/OutlinksRelevance
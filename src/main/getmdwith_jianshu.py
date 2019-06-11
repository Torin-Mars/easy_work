import os

base_path = os.path.dirname(__file__)
tf = open(os.path.join(base_path, "src.md"), "w", encoding="utf8")
mmt = {
    "vue":"html",
    "js":"js",
    "css":"css",
    "java":"java",
    "yml":"yaml",
    "xml":"xml"
}

fs = []
fnref_count = 0
def doDir(path):
    for f in os.listdir(path):
        cur_f = os.path.join(path, f)
        if os.path.isdir(cur_f):
            doDir(cur_f)
        else:
            
            try:
                type_s = mmt[cur_f[cur_f.rfind(".") + 1: ]]
                # print(type_s)
                ab_f = cur_f.replace(base_path, "")
                global fnref_count
                fnref_count += 1
                tf.write("- [%s](#fnref%d)\n" %(ab_f, fnref_count))
                fs.append(cur_f)
            except Exception as e:
                print(cur_f)
 
def write_tf(cur_f):
    with open(cur_f, "r", encoding="utf8") as rf:
        data = rf.read()
        type_s = mmt[cur_f[cur_f.rfind(".") + 1: ]]
        global fnref_count
        fnref_count += 1
        tf.write("#### %s[^a%d]\n```%s\n" % (cur_f.replace(base_path, ""), fnref_count, type_s))
        tf.write(data)
        tf.write("\n```\n\n")
# print(base_path)
doDir(base_path)
fnref_count = 0   
for i in fs:
    write_tf(os.path.join(base_path,i))

fnref_count = 0
for i in range(len(fs)):
    fnref_count += 1
    tf.write("[^a%d]:%s\n" % (fnref_count, fs[i].replace(base_path, "")))
tf.close()                    
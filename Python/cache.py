import json
from pathlib import Path
from Stats import statsController

def cacheController(dataset, fil):
    if fil is None:
        return fileOpen('statsDataset.json')

    relpath='./cacheFiles/'
    cache = cacheOpen(relpath)
    f = json.loads(fil)
    key = str(keyConstructor(f))

    if key in cache.keys():
        return fileOpen(relpath+cache[key])

    else:
        stats = statsController(dataset, f)
        newFileCache(stats, relpath+key+'.json', cache, key)
        return stats
        
def fileOpen(fileName):
    parse = ""
    with open(fileName,"r") as file:
        parse = json.load(file)
    return parse

def newFileCache(stats, fileName, cache, key):
    datasetFile = json.dumps(stats)
    print(datasetFile,file=open(fileName,"w+"))
    cache[key] = fileName
    cacheFile = json.dumps(cache)
    print(cacheFile, file=open('cache.json',"w+"))

def cacheOpen(relpath):
    parse = ""
    with open(relpath+"cache.json","r") as file:
       parse = json.load(file)
    return parse

def keyConstructor(fil):
    a = int(fil['$start'])
    b = int(fil['$end'])
    c=0
    d=0
    e=0
    op = {'$in', '$not', '$and'}
    for i in op:
        if i in fil.keys():
            l1=fil[i]
            for k in i:
                e = e + ord(k)
            t1 = l1[0]['GEO']
            t2 = l1[1]['OBJ']
            for elem in t1:
                for k in elem:
                    c = c +(ord(k))
            for elem in t2:
                for k in elem:
                    d = d + (ord(k))
        else:
            pass
    key = (a+b+c+d)*e
    return key

'''
d = {}
print(d,file=open('cache.json','w+'))
fil = '{ "$in": [ {"GEO": ["UK", "DK"] }, { "OBJ": ["OTH", "OBJ104"] } ], "$start": "2005", "$end": "2016" }'
#f = json.loads(fil)
with open("dataset.json","r") as file:
    parse = json.load(file)
print(cacheController(parse, fil))
'''
import azure.functions as func
import logging
import urllib
import json

#from Stats import statsController
#from cache import cacheController

def main(req: func.HttpRequest) -> str:
    fil = req.params.get('FILTER')
    recived_body = req.get_body()
    logging.info(fil)
    logging.info(recived_body)
    print(fil)
    print(recived_body)

    try:
        dataset = str(recived_body)
        dataset = dataset[2: len(dataset)-1]
    except urllib.error.HTTPError as e:
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))

    f = json.loads(fil)
    result = statsController(dataset, f)
    #result = cacheController(dataset, fil)
    return json.dumps(result)
    

################################################################STATS
import urllib.request
import urllib.error
import math

def Parser(dataset):
    DataList =[]

    #with open("dataset.json","r") as file:
    #    parse = json.load(file)
    
    parsed = json.loads(dataset)
    for row in parsed:
        DataList.append(row)
    return DataList

def statsController(dataset, filtro):
    lista = Parser(dataset)
    if len(lista) == 1:
        return jsonColumnsComposer(lista, lista[0], filtro)
    elif len(lista) > 1:
        return jsonRowsComposer(lista, filtro)
    else:
        l = []
        error = { "error": "Not Found with this characteristics" }
        l.append(error)
        l.append(filtro)
        return l

def jsonRowsComposer(lista, filtro):
    listReturn = []
    startYear=2000
    if filtro is not None: 
        startYear = int(filtro['$start'])
    
    listReturn.append(filtro)

    context = {
            'counter': Counter(lista)
            }
    listReturn.append(context)

    for year in range(len(lista[0]['timePeriod'])):
        yearsStats = {
            'year': year+startYear,
            'max': maxRows(lista,year),
            'min': minRows(lista,year),
            'average': AverageRows(lista,year),
            'stdDev': StdDevRows(lista,year)
            }
        listReturn.append(yearsStats)
    return listReturn

def jsonColumnsComposer(lista, row, filtro):
    l=[]
    l.append(filtro)
    l.append(row)
    d = {
        'counter': 1,
        'max': maxColumns(lista),
        'min': minColumns(lista),
        'average': AverageColumns(lista),
        'stdDev': StdDevColumns(lista)
        }
    l.append(d)
    return l

def Counter(lista):
    return len(lista)

def AverageRows(lista, year):
    som=0
    for row in range(len(lista)):
        som = som + lista[row]['timePeriod'][year]
    return som/len(lista)

def AverageColumns(lista):
    som=0
    for row in range(len(lista[0]['timePeriod'])):
        som = som + lista[0]['timePeriod'][row]
    return som/len(lista)

def StdDevRows(lista, year):
    aritmetic_average = AverageRows(lista,year)
    summation = 0
    for row in range(len(lista)):
        xi = lista[row]['timePeriod'][year]
        summation = summation + (xi - aritmetic_average)**2
    stDev = math.sqrt(summation/len(lista))
    return stDev

def StdDevColumns(lista):
    aritmetic_average = AverageColumns(lista)
    summation = 0
    for row in range(len(lista[0]['timePeriod'])):
        xi = lista[0]['timePeriod'][row]
        summation = summation + (xi - aritmetic_average)**2
    stDev = math.sqrt(summation/len(lista))
    return stDev

def maxRows(lista,Year):
    l = []
    for row in range(len(lista)):
        l.append(lista[row]['timePeriod'][Year])
    return max(l)

def minRows(lista,Year):
    l = []
    for row in range(len(lista)):
        l.append(lista[row]['timePeriod'][Year])
    return min(l)

def maxColumns(lista):
    l = []
    for column in range(len(lista[0]['timePeriod'])):
        l.append(lista[0]['timePeriod'][column])
    return max(l)

def minColumns(lista):
    l = []
    for column in range(len(lista[0]['timePeriod'])):
        l.append(lista[0]['timePeriod'][column])
    return min(l)







###########################################################################################CACHE
def cacheController(dataset, fil):
    relpath='./cacheFiles/'
    if fil is None:
        return fileOpen(relpath+'statsDataset.json')

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

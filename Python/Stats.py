import urllib.request
import urllib.error
import json

class Dataset:
    def __init__(self, freq, geo, unit, objective, timePeriod):
        self.freq=freq
        self.geo=geo
        self.unit=unit
        self.objective=objective
        self.timePeriod=timePeriod

    def getFreq(self, value):
        return self.freq



def Downloader(url="http://127.0.0.1:8080/data"):
    dataset = ""
    try:
        jret = urllib.request.urlopen(url).readlines()
        dataset = str(jret)
        dataset = dataset[3: len(dataset)-2]
        print(dataset,file=open("dataset.json","w+"))
    except urllib.error.HTTPError as e:        
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))
    else:
        print('good')
    return dataset

def Parser():
    dicList =[]
    classList =[]
    row = []
    with open("dataset.json",'r') as file:
        parse = json.load(file)
    for row in parse:
        dicList.append(row)
        newObj = Dataset(row['freq'], row['geo'], row['unit'], row['objective'],row['timePeriod'])
        classList.append(newObj)
    CounterClass(classList)
    print()
    CounterDict(dicList)
    print()
    #print(dataList)
    #return dataList

def CounterClass(lista):
    n=sum(p.freq == 'A' for p in lista)
    '''
    for i in range(0,100):
        n=lista.count(lista[i].freq)'''
    print(n)



def CounterDict(lista):

    n=sum(e['freq'] == 'A' for e in lista)
    '''
    for i in range(0,120):
        n=lista[i]['freq']'''
    print(n)


'''
def DictParser():
    def __setitem__ (self, ):
        if key in self:
'''

#Downloader()
Parser()
#row_list = Parser()
#print(row_list)

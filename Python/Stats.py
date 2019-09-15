import urllib.request
import urllib.error
import json

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
    DataList =[]
    #row = []
    with open("dataset.json",'r') as file:
        parse = json.load(file)
    for row in parse:
        DataList.append(row)
    return DataList

def SubsetSelector(lista, key='', value=''):
    subset = list(row[key] == value for row in lista)
    #filter(lista[])
    print(subset)
    return subset


def Counter(lista, key="", value=""):
    print(sum(row[key] == value for row in lista))

'''
def Average(lista, key):
    numOfElements = CounterDict(lista, key, value)
    instances = sum(row[for )
'''

#Downloader()
lista = Parser()
subset = SubsetSelector(lista,'geo','IT')
print(subset)
#row_list = Parser()
#print(row_list)

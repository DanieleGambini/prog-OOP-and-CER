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
        print('download good')
    return dataset

def Parser():
    DataList =[]
    #row = []
    with open("dataset.json",'r') as file:
        parse = json.load(file)
    for row in parse:
        DataList.append(row)
    return DataList

# List Comprehensions
def SubListSelector(lista, key='', value=''):
    sublist = [row for row in lista if row[key]==value]
    return sublist

def Counter(lista, key="", value=""):
    return len(SubListSelector(lista, key, value))
    #sum(row[key] == value for row in lista)

def Average(lista, key, value, year):
    sublist = SubListSelector(lista, key, value)
    numOfElements = Counter(sublist, key, value)
    som=0
    year = year - 2000
    for row in range(0,len(lista)):
        som = som + sublist[row]['timePeriod'][year]
    print(som)
    return som/numOfElements
    #return sum(row[key] for row in sublist)/numOfElements

def StdDev(lista,key,value,year):
    Average(lista,key,value,year)
    


    return stdDev

#Downloader()
lista = Parser()
subset = SubListSelector(lista,'geo','IT')
n = Counter(lista,'geo','IT')
av = Average(subset,'geo','IT',2000)
print(av, '\n', n)
#row_list = Parser()
#print(row_list)

import urllib.request
import urllib.error
import json
import math

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
    #numOfElements = Counter(sublist, key, value)
    som=0
    year = year - 2000
    for row in range(0,len(lista)):
        som = som + sublist[row]['timePeriod'][year]
    print(som)
    return som/len(sublist)
    #return sum(row[key] for row in sublist)/numOfElements

def StdDev(lista,key,value,year):
    sublist = SubListSelector(lista,key,value)
    aritmetic_average = Average(lista,key,value,year)
    for row in range(0,len(lista)):
        xi = lista[row]['timePeriod'][year]
        summation = (xi - aritmetic_average)*(xi - aritmetic_average)
    stDev = math.sqrt(summation/len(sublist))
    return stDev

#Downloader()
lista = Parser()
subset = SubListSelector(lista,'geo','IT')
n = Counter(lista,'geo','IT')
av = Average(subset,'geo','IT',2000)
StdDev(lista,'geo','IT',2000)
print(av, '\n', n)
#row_list = Parser()
#print(row_list)

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
def SubListSelector(lista, geo='', obj=''):
    if geo is '' and obj is '':
        return lista
    elif obj is '':
        sublist = [row for row in lista if row['geo']==geo]
        return sublist
    elif geo is '':
        sublist = [row for row in lista if row['objective']==obj]
        return sublist
    else:
        sublist1 = [row for row in lista if row['geo']==geo]
        sublist = [row for row in sublist1 if row['objective']==obj]
        print(sublist)
        return sublist

def Counter(lista):
    return len(lista)
    #sum(row[key] == value for row in lista)

def Average(lista, year):
    som=0
    for row in range(len(lista)):
        som = som + lista[row]['timePeriod'][year]
    return som/len(lista)

def StdDev(lista, year):
    aritmetic_average = Average(lista,year)
    summation = 0
    for row in range(len(lista)):
        xi = lista[row]['timePeriod'][year]
        summation = summation + (xi - aritmetic_average)**2
    stDev = math.sqrt(summation/len(lista))
    return stDev

def maxValue(lista,Year):
    l = []
    for row in range(len(lista)):
        l.append(lista[row]['timePeriod'][Year])
    return max(l)

def minValue(lista,Year):
    l = []
    for row in range(len(lista)):
        l.append(lista[row]['timePeriod'][Year])
    return min(l)
   

def Stats(geo='', obj='',startYear=2000, endYear=2017):
    lista = Parser()
    sublist = SubListSelector(lista,geo,obj)
    listReturn = []
    for year in range(startYear-2000,endYear-2000):
        dictTemplate = { 
            'Counter': Counter(sublist),
            'Max': maxValue(sublist,year),
            'Min': minValue(sublist,year),
            'Average': Average(sublist,year),
            'StdDev': StdDev(sublist,year) }
        listReturn.append(dictTemplate)
    return listReturn
import urllib.request
import urllib.error
import json
import math

def Parser(dataset):
    DataList =[]
    '''
    with open("dataset.json",'r') as file:
        parse = json.load(file)
    '''
    parsed = json.loads(dataset)
    for row in parsed:
        DataList.append(row)
    return DataList

def statsController(dataset, geo='', obj='', startYear=2000, endYear=2017):
    lista = Parser(dataset)

    if geo is '' and obj is '':
        stats = jsonRowsComposer(lista, geo, obj, startYear, endYear)
        return stats
    
    elif obj is '':
        sublist = [row for row in lista if row['geo']==geo]
        stats = jsonRowsComposer(sublist, geo, obj, startYear, endYear)
        return stats
    
    elif geo is '':
        sublist = [row for row in lista if row['objective']==obj]
        stats = jsonRowsComposer(sublist, geo, obj, startYear, endYear)
        return stats
    
    else:
        sublist1 = [row for row in lista if row['geo']==geo]
        sublist = [row for row in sublist1 if row['objective']==obj]
        l = []
        for column in sublist[0]['timePeriod']:
            l.append(column)
        stats = jsonColumnsComposer(l, geo, obj, sublist[0])
        return stats

def jsonRowsComposer(lista, geo, obj, startYear, endYear):
    listReturn = []
    context = {
            'geo': geo,
            'objective': obj,
            'Counter': Counter(lista)
            }
    listReturn.append(context)
    for year in range(startYear-2000,endYear-1999):
        yearsStats = {
            'year': year+2000,
            'Max': maxRows(lista,year),
            'Min': minRows(lista,year),
            'Average': AverageRows(lista,year),
            'StdDev': StdDevRows(lista,year)
            }
        listReturn.append(yearsStats)
    return listReturn

def jsonColumnsComposer(lista, geo, obj, row):
    l=[]
    l.append(row)
    d = {
        'geo': geo,
        'objective':obj,
        'Counter': 1,
        'Max': maxColumns(lista),
        'Min': minColumns(lista),
        'Average': AverageColumns(lista),
        'StdDev': StdDevColumns(lista)
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
    for row in range(len(lista)):
        som = som + lista[row]
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
    for row in range(len(lista)):
        xi = lista[row]
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
    for column in range(len(lista)):
        l.append(lista[column])
    return max(l)

def minColumns(lista):
    l = []
    for column in range(len(lista)):
        l.append(lista[column])
    return min(l)
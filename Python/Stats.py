import urllib.request
import urllib.error
import json
import math

def Parser(dataset):
    DataList =[]
    '''
    with open("dataset.json","r") as file:
        parse = json.load(file)
        '''
    parsed = json.loads(dataset)
    for row in parsed:
        DataList.append(row)
    return DataList

def statsController(dataset, filtro):
    startYear = int(filtro['$start'])
    lista = Parser(dataset)
    if len(lista) == 1:
        return jsonColumnsComposer(lista)
    else:
        return jsonRowsComposer(lista, startYear)

def jsonRowsComposer(lista, start):
    listReturn = []
    context = {
            'Counter': Counter(lista)
            }
    listReturn.append(context)
    for year in range(len(lista[0]['timePeriod'])):
        yearsStats = {
            'year': year+start,
            'Max': maxRows(lista,year),
            'Min': minRows(lista,year),
            'Average': AverageRows(lista,year),
            'StdDev': StdDevRows(lista,year)
            }
        listReturn.append(yearsStats)
    return listReturn

def jsonColumnsComposer(lista, row):
    l=[]
    l.append(row)
    d = {
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
'''
dataset = 'd.json'
r = statsController(dataset)
print(r)
'''
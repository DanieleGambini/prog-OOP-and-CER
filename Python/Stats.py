import urllib.request
import urllib.error
import json
import math

def Parser(dataset):
    DataList =[]
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
    if '$start' in filtro: 
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
import json

def Parser():
    DataList =[]
    with open("dataset.json",'r') as file:
        parse = json.load(file)
    #parsed = json.loads(dataset)
    for row in parse:
        DataList.append(row)
    return DataList

def Controller():
    lista = Parser()
    #result = opIN(lista, 'objective', 'OBJ02')
    #result = opIN(lista, 'geo', 'LV')

    #result = opNOT(lista, 'geo', 'EU28')
    #result = opNOT(lista, 'objective', 'OBJ02')

    selector = { 'GEO': ['IT', 'LV'], 'OBJ': ['OBJ104', 'OBJ40'] }
    #result = opNIN(lista, selector)
    #result = opOR(lista, selector)
    #result = opAND(lista, 'IT', 'TOTAL')

    print(result)

def opIN(lista, key, value):
    sublist = [row for row in lista if row[key]==value]
    return sublist

def opNOT(lista, key, value):
    sublist = [row for row in lista if row[key]!=value]
    return sublist

def opNIN(lista, selector):
    for value in selector['GEO']:
        index = 0
        for row in lista:
            if row['geo'] == value:
                del lista[index]
            index = index + 1

    for value in selector['OBJ']:
        index = 0
        for row in lista:
            if row['objective'] == value:
                del lista[index]
            index = index + 1
    return lista

def opAND(lista, geo, obj):
    sublist1 = [row for row in lista if row['geo']==geo]
    sublist = [row for row in sublist1 if row['objective']==obj]
    return sublist

def opOR(lista, selector):
    l = []
    for value in selector['GEO']:
        for row in lista:
            if row['geo'] == value:
                l.append(row)

    for value in selector['OBJ']:
        for row in lista:
            if row['objective'] == value:
                l.append(row)
    return l


Controller()